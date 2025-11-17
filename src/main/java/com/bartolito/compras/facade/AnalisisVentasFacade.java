package com.bartolito.compras.facade;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bartolito.compras.dto.BaseOperacionResponse;
import com.bartolito.compras.dto.Constantes;
import com.bartolito.compras.dto.farmacia.FarmaciaResponse;
import com.bartolito.compras.dto.rotacionProductos.ComboRotacionProductosGeneral;
import com.bartolito.compras.dto.rotacionProductos.GenericosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionEspecificosSeleccionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionEspecificosSeleccionadosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionGeneralSeleccionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionObservacionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosEspecificosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosSeleccionadoResponse;
import com.bartolito.compras.dto.rotacionProductos.TipoProductoResponse;
import com.bartolito.compras.dto.rotacionProductos.TipoRentabilidadResponse;
import com.bartolito.compras.service.AnalisisVentasService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AnalisisVentasFacade {

	@Autowired
	private AnalisisVentasService analisisVentasService;

	public List<RotacionProductosResponse> loadRotacionProductos() {

		List<RotacionProductosResponse> collection = new ArrayList<>();
		List<Map<String, Object>> listDTO = analisisVentasService.obtenerListadoRotacionProductos();
		List<Map<String, Object>> productos = analisisVentasService.obtenerDatosProductosLolfar();

		Map<String, Map<String, Object>> productoMap = productos.stream()
				.collect(Collectors.toMap(x -> (String) x.get("codpro"), x -> x));

		for (Map<String, Object> fila : listDTO) {

			String codpro = (String) fila.get("prodId");

			Map<String, Object> producto = productoMap.get(codpro);

			if (producto == null) {
				continue;
			}

			RotacionProductosResponse r = new RotacionProductosResponse();
			r.setProdId((String) fila.get("prodId"));
			r.setProdDesc((String) fila.get("prodDesc"));
			r.setEnlaceWeb((String) fila.get("enlace_web"));
			Object indice = fila.get("indice_rotacion");
			Object stockProm = fila.get("stock_promedio_valorizado");
			r.setIndiceRotacion(indice != null ? ((Number) indice).doubleValue() : null);
			r.setStockPromedioValorizado(stockProm != null ? ((Number) stockProm).doubleValue() : null);

			List<Map<String, Object>> data = analisisVentasService.obtenerStockAlmacenTodos(r.getProdId());

			List<Map<String, Object>> ventas = analisisVentasService.obtenerVentasUltimos30Dias(r.getProdId());
			List<Map<String, Object>> ultimaCompra = analisisVentasService.obtenerFechaUltimaCompra(r.getProdId());

			Double totalStkAlm = 0.0;
			Double totalStkMin2 = 0.0;
			Double stkValIgvAlm = 0.0;
			int contadorStkValIgv = 0;

			for (Map<String, Object> xx : data) {

				Object stkalmObj = xx.get("stock");
				Object stkmin2Obj = xx.get("stkmin2");
				Object stkValIgv = xx.get("stock_val_igv");

				if (stkalmObj instanceof Number) {
					totalStkAlm += ((Number) stkalmObj).doubleValue();
				}
				if (stkmin2Obj instanceof Number) {
					totalStkMin2 += ((Number) stkmin2Obj).doubleValue();
				}
				if (stkValIgv instanceof Number) {
					stkValIgvAlm += ((Number) stkValIgv).doubleValue();
					contadorStkValIgv++;
				}
			}

			Double promedioStkValIgv = contadorStkValIgv > 0 ? stkValIgvAlm / contadorStkValIgv : null;

			Double coberturaMensual = null;
			if (totalStkMin2 > 0) {
				coberturaMensual = totalStkAlm / totalStkMin2;
			}

			r.setCodgen((String) producto.get("codgen"));
			r.setDesgen((String) producto.get("desgen"));
			r.setCodtip((String) producto.get("codtip"));
			r.setDestip((String) producto.get("destip"));
			r.setCategvta((String) producto.get("categvta"));

			for (Map<String, Object> ff : ventas) {
				Object ultimos30Dias = ff.get("ventas");
				r.setVentasUltimos(ultimos30Dias != null ? ((Number) ultimos30Dias).doubleValue() : null);
			}

			for (Map<String, Object> uu : ultimaCompra) {

				Object fechaObj = uu.get("fecha_ultima_compra");

				if (fechaObj instanceof java.sql.Timestamp) {
					java.sql.Timestamp ts = (java.sql.Timestamp) fechaObj;

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

					String fechaFormateada = ts.toLocalDateTime().format(formatter);

					r.setFechaUltimaCompra(fechaFormateada);
				} else if (fechaObj != null) {

					r.setFechaUltimaCompra(fechaObj.toString());
				} else {
					r.setFechaUltimaCompra(null);
				}
			}

			r.setCoberturaMensual(coberturaMensual != null ? ((Number) coberturaMensual).doubleValue() : null);
			r.setStock(totalStkAlm != null ? ((Number) totalStkAlm).doubleValue() : null);
			r.setUltimoStockValorizado(promedioStkValIgv);

			collection.add(r);
		}

		return collection;
	}

	public List<RotacionProductosEspecificosResponse> loadRotacionProductosEspecifico(Integer siscod) {

		List<RotacionProductosEspecificosResponse> collection = new ArrayList<>();

		List<Map<String, Object>> rotaciones = analisisVentasService.obtenerListadoRotacionProductosEspecificos();
		List<Map<String, Object>> productos = analisisVentasService.obtenerProductosByFarmacia(siscod);

		Map<String, Map<String, Object>> rotacionMap = rotaciones.stream()
				.collect(Collectors.toMap(x -> (String) x.get("prodId"), x -> x));

		for (Map<String, Object> filaProd : productos) {

			String codpro = (String) filaProd.get("codpro");

			String codalm = filaProd.get("codalm").toString();

			Map<String, Object> rotacion = rotacionMap.get(codpro);

			if (rotacion == null) {
				continue;
			}

			RotacionProductosEspecificosResponse r = new RotacionProductosEspecificosResponse();

			r.setProdId(codpro);
			r.setProdDesc((String) filaProd.get("despro"));
			r.setCodgen((String) filaProd.get("codgen"));
			r.setDesgen((String) filaProd.get("desgen"));
			r.setCodtip((String) filaProd.get("codtip"));
			r.setDestip((String) filaProd.get("destip"));
			r.setCategvta((String) filaProd.get("categvta"));
			r.setSiscod((Integer) filaProd.get("siscod"));
			r.setSisent((String) filaProd.get("sisent"));

			Object stock = filaProd.get("stock");
			r.setStock(stock != null ? ((Number) stock).doubleValue() : null);

			Object coberturaMensual = filaProd.get("cobertura_mensual");
			r.setCoberturaMensual(coberturaMensual != null ? ((Number) coberturaMensual).doubleValue() : null);

			Object ultimoStock = filaProd.get("stock_val_igv");
			r.setUltimoStockValorizado(ultimoStock != null ? ((Number) ultimoStock).doubleValue() : null);

			// DATOS DE ROTACION

			r.setEnlaceWeb((String) rotacion.get("enlace_web"));

			Object indice = rotacion.get("indice_rotacion");
			r.setIndiceRotacion(indice != null ? ((Number) indice).doubleValue() : null);
			Object stockProm = rotacion.get("stock_promedio_valorizado");
			r.setStockPromedioValorizado(stockProm != null ? ((Number) stockProm).doubleValue() : null);

			// ===== VENTAS ÚLTIMOS 30 DÍAS =====
			List<Map<String, Object>> ventas = analisisVentasService.obtenerVentasUltimos30DiasByFarmacia(codpro,
					codalm);

			if (!ventas.isEmpty()) {
				Object ventaObj = ventas.get(0).get("ventas");
				r.setVentasUltimos(ventaObj != null ? ((Number) ventaObj).doubleValue() : null);
			}

			// ===== TASA =====
			List<Map<String, Object>> tasa = analisisVentasService.obtenerTasaByFarmacia(codpro, codalm);

			if (!tasa.isEmpty()) {
				Object tasaObj = tasa.get(0).get("tasa");
				r.setTasa(tasaObj != null ? ((Number) tasaObj).doubleValue() : null);
			}

			collection.add(r);

		}

		return collection;
	}

	public List<RotacionEspecificosSeleccionadosResponse> loadRotacionProductosEspecificosSeleccionados(
			Integer siscod) {

		List<RotacionEspecificosSeleccionadosResponse> collection = new ArrayList<>();

		List<Map<String, Object>> rotaciones = analisisVentasService
				.obtenerListadoRotacionProductosEspecificosSeleccionados(siscod);
		List<Map<String, Object>> datoRotacion = analisisVentasService.obtenerListadoRotacionProductosEspecificos();
		List<Map<String, Object>> productos = analisisVentasService.obtenerProductosByFarmacia(siscod);

		Map<String, Map<String, Object>> productonMap = productos.stream()
				.collect(Collectors.toMap(x -> (String) x.get("codpro"), x -> x));

		Map<String, Map<String, Object>> rotacionMap = datoRotacion.stream()
				.collect(Collectors.toMap(x -> (String) x.get("prodId"), x -> x));

		for (Map<String, Object> filaProd : rotaciones) {

			String codpro = (String) filaProd.get("codpro");

			Map<String, Object> rotacion = rotacionMap.get(codpro);

			Map<String, Object> producto = productonMap.get(codpro);

			String codalm = producto.get("codalm").toString();

			if (producto == null) {
				continue;
			}

			RotacionEspecificosSeleccionadosResponse r = new RotacionEspecificosSeleccionadosResponse();

			r.setRotaespid((Integer) filaProd.get("rotaespid"));
			r.setProdId(codpro);
			r.setProdDesc((String) filaProd.get("prodDesc"));
			r.setCodgen((String) filaProd.get("codgen"));
			r.setDesgen((String) filaProd.get("desgen"));
			r.setCodtip((String) filaProd.get("codtip"));
			r.setDestip((String) filaProd.get("destip"));
			r.setCategvta((String) filaProd.get("categvta"));
			r.setSiscod((Integer) filaProd.get("siscod"));
			r.setObservacion((String) filaProd.get("observacion"));

			Object stock = producto.get("stock");
			r.setStock(stock != null ? ((Number) stock).doubleValue() : null);

			Object coberturaMensual = producto.get("cobertura_mensual");
			r.setCoberturaMensual(coberturaMensual != null ? ((Number) coberturaMensual).doubleValue() : null);

			Object ultimoStock = producto.get("stock_val_igv");
			r.setUltimoStockValorizado(ultimoStock != null ? ((Number) ultimoStock).doubleValue() : null);

			// DATOS DE ROTACION

			r.setEnlaceWeb((String) filaProd.get("enlace_web"));

			Object indice = rotacion.get("indice_rotacion");
			r.setIndiceRotacion(indice != null ? ((Number) indice).doubleValue() : null);
			Object stockProm = rotacion.get("stock_promedio_valorizado");
			r.setStockPromedioValorizado(stockProm != null ? ((Number) stockProm).doubleValue() : null);

			// ===== VENTAS ÚLTIMOS 30 DÍAS =====
			List<Map<String, Object>> ventas = analisisVentasService.obtenerVentasUltimos30DiasByFarmacia(codpro,
					codalm);

			if (!ventas.isEmpty()) {
				Object ventaObj = ventas.get(0).get("ventas");
				r.setVentasUltimos(ventaObj != null ? ((Number) ventaObj).doubleValue() : null);
			}

			// ===== TASA =====
			List<Map<String, Object>> tasa = analisisVentasService.obtenerTasaByFarmacia(codpro, codalm);

			if (!tasa.isEmpty()) {
				Object tasaObj = tasa.get(0).get("tasa");
				r.setTasa(tasaObj != null ? ((Number) tasaObj).doubleValue() : null);
			}

			collection.add(r);
		}

		return collection;
	}

	public BaseOperacionResponse saveOrUpdate(MultipartFile file) {
		analisisVentasService.saveOrUpdate(file);
		return new BaseOperacionResponse(Constantes.SUCCESS, "Archivo guardado correctamente.");
	}

	public BaseOperacionResponse saveRotacionProductoGeneralSeleccion(List<RotacionGeneralSeleccionRequest> request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(request);

			analisisVentasService.saveRotacionProductoGeneralSeleccion(json);

			return new BaseOperacionResponse(Constantes.SUCCESS, "Productos guardados correctamente.");
		} catch (Exception e) {
			return new BaseOperacionResponse(Constantes.ERROR, "Error al guardar productos: " + e.getMessage());
		}
	}

	public BaseOperacionResponse deleteRotacionProductoGeneral(String codpro) {
		analisisVentasService.deleteRotacionProductoGeneralSeleccion(codpro);
		return new BaseOperacionResponse(Constantes.SUCCESS, "Producto eliminado correctamente.");
	}

	public BaseOperacionResponse updateRotacionProductoGeneral(RotacionObservacionRequest t) {
		analisisVentasService.updateRotacionProductoGeneral(t);
		return new BaseOperacionResponse(Constantes.SUCCESS, "Producto actualizado correctamente.");
	}

	public ComboRotacionProductosGeneral initCombosRotacionGeneral() {

		ComboRotacionProductosGeneral combo = new ComboRotacionProductosGeneral();

		List<Map<String, Object>> comboCategvta = analisisVentasService.initComboCategvta();
		List<Map<String, Object>> comboGenerico = analisisVentasService.initComboGenericos();
		List<Map<String, Object>> comboTipo = analisisVentasService.initComboTipos();

		List<TipoRentabilidadResponse> tipoRentabilidad = new ArrayList<>();
		List<GenericosResponse> genericos = new ArrayList<>();
		List<TipoProductoResponse> tipoProducto = new ArrayList<>();

		for (Map<String, Object> tt : comboCategvta) {

			TipoRentabilidadResponse response = new TipoRentabilidadResponse();

			response.setCategvta((String) tt.get("categvta"));

			tipoRentabilidad.add(response);

		}

		for (Map<String, Object> tt : comboGenerico) {

			GenericosResponse response = new GenericosResponse();

			response.setCodgen((String) tt.get("codgen"));
			response.setDesgen((String) tt.get("desgen"));

			genericos.add(response);

		}

		for (Map<String, Object> tt : comboTipo) {

			TipoProductoResponse response = new TipoProductoResponse();

			response.setCodtip((String) tt.get("codtip"));
			response.setDestip((String) tt.get("destip"));

			tipoProducto.add(response);
		}

		combo.setTipoRentabilidad(tipoRentabilidad);
		combo.setGenerico(genericos);
		combo.setTipoProducto(tipoProducto);

		return combo;
	}

	public List<FarmaciaResponse> loadFarmacias() {

		List<FarmaciaResponse> collection = new ArrayList<>();
		List<Map<String, Object>> listDTO = analisisVentasService.obtenerFarmacias();

		for (Map<String, Object> fila : listDTO) {

			FarmaciaResponse response = new FarmaciaResponse();

			Object siscod = fila.get("siscod");
			response.setSisent((String) fila.get("sisent"));
			response.setSiscod(siscod != null ? ((Number) siscod).intValue() : null);

			collection.add(response);
		}

		return collection;

	}

	public List<RotacionProductosSeleccionadoResponse> loadRotacionProductosGeneralSeleccionados() {

		List<RotacionProductosSeleccionadoResponse> collection = new ArrayList<>();
		List<Map<String, Object>> listDTO = analisisVentasService.obtenerListadoRotacionGeneralProductosSeleccionados();
		List<Map<String, Object>> productos = analisisVentasService.obtenerDatosProductosLolfar();

		Map<String, Map<String, Object>> productoMap = productos.stream()
				.collect(Collectors.toMap(x -> (String) x.get("codpro"), x -> x));

		for (Map<String, Object> fila : listDTO) {

			String codpro = (String) fila.get("codpro");

			Map<String, Object> producto = productoMap.get(codpro);

			if (producto == null) {
				continue;
			}

			RotacionProductosSeleccionadoResponse r = new RotacionProductosSeleccionadoResponse();

			r.setProdId((String) fila.get("codpro"));
			r.setProdDesc((String) fila.get("prodDesc"));
			r.setCodgen((String) fila.get("codgen"));
			r.setCodtip((String) fila.get("codtip"));
			r.setCategvta((String) fila.get("categvta"));
			r.setObservacion((String) fila.get("observacion"));
			r.setEnlaceWeb((String) fila.get("enlace_web"));
			r.setFechaUltimaCompra((String) fila.get("fecha_ultima_compra"));
			Object stock = fila.get("stock");
			Object ventasUltimos = fila.get("ventas_ultimos");
			Object indice = fila.get("indice_rotacion");
			Object stockProm = fila.get("stock_promedio_valorizado");
			Object ultimoStock = fila.get("ultimo_stock_valorizado");

			r.setStock(stock != null ? ((Number) stock).doubleValue() : null);
			r.setVentasUltimos(ventasUltimos != null ? ((Number) ventasUltimos).doubleValue() : null);
			r.setIndiceRotacion(indice != null ? ((Number) indice).doubleValue() : null);
			r.setStockPromedioValorizado(stockProm != null ? ((Number) stockProm).doubleValue() : null);
			r.setUltimoStockValorizado(ultimoStock != null ? ((Number) ultimoStock).doubleValue() : null);

			r.setDesgen((String) producto.get("desgen"));
			r.setDestip((String) producto.get("destip"));

			collection.add(r);
		}

		return collection;
	}

	public BaseOperacionResponse saveRotacionProductoEspecificosSeleccion(
			List<RotacionEspecificosSeleccionRequest> request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(request);

			analisisVentasService.saveRotacionProductoEspecificosSeleccion(json);

			return new BaseOperacionResponse(Constantes.SUCCESS, "Productos guardados correctamente.");
		} catch (Exception e) {
			return new BaseOperacionResponse(Constantes.ERROR, "Error al guardar productos: " + e.getMessage());
		}
	}

	public BaseOperacionResponse deleteRotacionProductoEspecificosSeleccion(Integer rotaespid) {
		analisisVentasService.deleteRotacionProductoEspecificosSeleccion(rotaespid);
		return new BaseOperacionResponse(Constantes.SUCCESS, "Producto eliminado correctamente.");
	}

	public BaseOperacionResponse updateRotacionProductoEspecificosSeleccion(RotacionObservacionRequest t) {
		analisisVentasService.updateRotacionProductoEspecificosSeleccion(t);
		return new BaseOperacionResponse(Constantes.SUCCESS, "Producto actualizado correctamente.");
	}

}

package com.bartolito.compras.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bartolito.compras.dto.BaseOperacionResponse;
import com.bartolito.compras.dto.Constantes;
import com.bartolito.compras.dto.rotacionProductos.ComboRotacionProductosGeneral;
import com.bartolito.compras.dto.rotacionProductos.GenericosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionGeneralSeleccionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosSeleccionadoResponse;
import com.bartolito.compras.dto.rotacionProductos.TipoProductoResponse;
import com.bartolito.compras.dto.rotacionProductos.TipoRentabilidadResponse;
import com.bartolito.compras.service.AnalisisVentasService;
import com.bartolito.compras.service.GraficaProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AnalisisVentasFacade {

	@Autowired
	private AnalisisVentasService analisisVentasService;

	@Autowired
	private GraficaProductoService graficaProductoService;

	public List<RotacionProductosResponse> loadRotacionProductos() {

		List<RotacionProductosResponse> collection = new ArrayList<>();
		List<Map<String, Object>> listDTO = analisisVentasService.obtenerListadoRotacionProductos();

		for (Map<String, Object> fila : listDTO) {
			RotacionProductosResponse r = new RotacionProductosResponse();
			r.setProdId((String) fila.get("prodId"));
			r.setProdDesc((String) fila.get("prodDesc"));
			Object indice = fila.get("indice_rotacion");
			Object stockProm = fila.get("stock_promedio_valorizado");
			Object ultimoStock = fila.get("ultimo_stock_valorizado");

			r.setIndiceRotacion(indice != null ? ((Number) indice).doubleValue() : null);
			r.setStockPromedioValorizado(stockProm != null ? ((Number) stockProm).doubleValue() : null);
			r.setUltimoStockValorizado(ultimoStock != null ? ((Number) ultimoStock).doubleValue() : null);

			List<Map<String, Object>> data = graficaProductoService.obtenerStockAlmacenTodos(r.getProdId());
			List<Map<String, Object>> producto = analisisVentasService.obtenerDatosProductosLolfarById(r.getProdId());
			List<Map<String, Object>> ventas = analisisVentasService.obtenerVentasUltimos30Dias(r.getProdId());

			Double totalStkAlm = 0.0;
			Double totalStkMin2 = 0.0;

			for (Map<String, Object> xx : data) {

				Object stkalmObj = xx.get("stkalm");
				Object stkmin2Obj = xx.get("stkmin2");

				if (stkalmObj instanceof Number) {
					totalStkAlm += ((Number) stkalmObj).doubleValue();
				}
				if (stkmin2Obj instanceof Number) {
					totalStkMin2 += ((Number) stkmin2Obj).doubleValue();
				}

			}

			Double coberturaMensual = null;
			if (totalStkMin2 > 0) {
				coberturaMensual = totalStkAlm / totalStkMin2;
			}

			for (Map<String, Object> yy : producto) {
				r.setCodgen((String) yy.get("codgen"));
				r.setDesgen((String) yy.get("desgen"));
				r.setCodtip((String) yy.get("codtip"));
				r.setDestip((String) yy.get("destip"));
				r.setCategvta((String) yy.get("categvta"));
			}

			for (Map<String, Object> ff : ventas) {
				Object ultimos30Dias = ff.get("ventas");
				r.setVentasUltimos(ultimos30Dias != null ? ((Number) ultimos30Dias).doubleValue() : null);
			}

			r.setCoberturaMensual(coberturaMensual != null ? ((Number) coberturaMensual).doubleValue() : null);
			r.setStock(totalStkAlm != null ? ((Number) totalStkAlm).doubleValue() : null);

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

	public List<RotacionProductosSeleccionadoResponse> loadRotacionProductosGeneralSeleccionados() {

		List<RotacionProductosSeleccionadoResponse> collection = new ArrayList<>();
		List<Map<String, Object>> listDTO = analisisVentasService.obtenerListadoRotacionGeneralProductosSeleccionados();

		for (Map<String, Object> fila : listDTO) {
			RotacionProductosSeleccionadoResponse r = new RotacionProductosSeleccionadoResponse();

			r.setProdId((String) fila.get("codpro"));
			r.setProdDesc((String) fila.get("prodDesc"));
			r.setCodgen((String) fila.get("codgen"));
			r.setCodtip((String) fila.get("codtip"));
			r.setCategvta((String) fila.get("categvta"));
			r.setObservacion((String) fila.get("observacion"));
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

			List<Map<String, Object>> producto = analisisVentasService.obtenerDatosProductosLolfarById(r.getProdId());

			for (Map<String, Object> yy : producto) {
				r.setDesgen((String) yy.get("desgen"));
				r.setDestip((String) yy.get("destip"));
			}

			collection.add(r);
		}

		return collection;
	}

}

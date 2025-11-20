package com.bartolito.compras.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bartolito.compras.dto.rotacionProductos.RotacionObservacionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionEspecificosRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionGeneralRequest;

@Repository
public class AnalisisVentasRepository {

	@Autowired
	@Qualifier("biJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("lolfarJdbcTemplate")
	private JdbcTemplate jdbcLolfarTemplate;

	public List<Map<String, Object>> obtenerListadoRotacionProductos() {
		String sql = "EXEC sp_bart_rotacion_productos_general_listar";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String, Object>> obtenerListadoRotacionGeneralProductosSeleccionados() {
		String sql = "EXEC sp_bart_rotacion_productos_general_listar_seleccionados";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> initComboCategvta() {
		String sql = "EXEC sp_bart_rotacion_combo_categvta";
		return jdbcLolfarTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> initComboGenericos() {
		String sql = "EXEC sp_bart_rotacion_combo_genericos";
		return jdbcLolfarTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> initComboTipos() {
		String sql = "EXEC sp_bart_rotacion_combo_tipos";
		return jdbcLolfarTemplate.queryForList(sql);
	}

	public int saveOrUpdateRotacionProducto(RotacionGeneralRequest t) {
		String sql = "EXEC sp_bart_rotacion_productos_general_saveOrUpdate ?, ?, ?, ?, ?, ?";
		return jdbcTemplate.update(sql, t.getProdId(), t.getProdDesc(), t.getIndiceRotacion(),
				t.getStockPromedioValorizado(), t.getUltimoStockValorizado(), t.getEnlaceWeb());
	}
	
	public int saveOrUpdateRotacionEspecificos(RotacionEspecificosRequest t) {
		String sql = "EXEC sp_bart_rotacion_productos_especificos_saveOrUpdate ?, ?, ?, ?, ?, ?, ?";
		return jdbcTemplate.update(sql, t.getProdId(), t.getProdDesc(), t.getIndiceRotacion(),
				t.getStockPromedioValorizado(), t.getUltimoStockValorizado(), t.getEnlaceWeb(), t.getSiscod());
	}

	public List<Map<String, Object>> obtenerDatosProductosLolfar() {
		String sql = "EXEC sp_bart_rotacion_productos_lolfar_get";
		return jdbcLolfarTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> obtenerVentasUltimos30Dias(String codpro) {
		String sql = "EXEC sp_bart_compras_grafica_producto_ventas_ultimos30dias ?";
		return jdbcLolfarTemplate.queryForList(sql, codpro);
	}
	
	public int saveRotacionProductoGeneralSeleccion(String json) {
		String sql = "EXEC sp_bart_rotacion_productos_general_save ?";
		return jdbcTemplate.update(sql, json);
	}
	
	public int deleteRotacionProductoGeneralSeleccion(String codpro) {
		String sql = "EXEC sp_bart_rotacion_productos_general_delete_seleccionado ?";
		return jdbcTemplate.update(sql, codpro);
	}
	
	public int updateRotacionProductoGeneral(RotacionObservacionRequest t) {
		String sql = "EXEC sp_bart_rotacion_productos_general_update_seleccionado ?,?";
		return jdbcTemplate.update(sql, t.getCodpro(), t.getObservacion());
	}
	
	public List<Map<String, Object>> obtenerFarmacias() {
		String sql = "EXEC sp_bart_compras_analisis_farmacias";
		return jdbcLolfarTemplate.queryForList(sql);
	}
	
	public List<Map<String, Object>> obtenerStockAlmacenTodos(String codpro) {
		String sql = "EXEC sp_bart_rotacion_productos_stock_almacen_todos ?";
		return jdbcLolfarTemplate.queryForList(sql, codpro);
	}
	
	public List<Map<String, Object>> obtenerFechaUltimaCompra(String codpro) {
		String sql = "EXEC sp_bart_rotacion_productos_fecha_ultima_compra ?";
		return jdbcLolfarTemplate.queryForList(sql, codpro);
	}
	
	/*ROTACION ESPECIFICOS*/
	
	public List<Map<String, Object>> obtenerListadoRotacionProductosEspecificos(Integer siscod) {
		String sql = "EXEC sp_bart_rotacion_productos_especificos_listar ?";
		return jdbcTemplate.queryForList(sql, siscod);
	}
	
	public List<Map<String, Object>> obtenerProductosByFarmacia(Integer siscod) {
		String sql = "EXEC sp_bart_rotacion_productos_stock_almacen_farmacia_todos ?";
		return jdbcLolfarTemplate.queryForList(sql, siscod);
	}
	
	public List<Map<String, Object>> obtenerVentasUltimos30DiasByFarmacia(String codpro, String almacen) {
		String sql = "EXEC sp_bart_rotacion_especificos_producto_ventas_ultimos30dias ?,?";
		return jdbcLolfarTemplate.queryForList(sql, codpro, almacen);
	}
	
	public List<Map<String, Object>> obtenerTasaByFarmacia(String codpro, String almacen) {
		String sql = "EXEC sp_bart_rotacion_especificos_tasa ?,?";
		return jdbcLolfarTemplate.queryForList(sql, codpro, almacen);
	}
	
	public List<Map<String, Object>> obtenerListadoRotacionProductosEspecificosSeleccionados(Integer siscod) {
		String sql = "EXEC sp_bart_rotacion_productos_especificos_listar_seleccionados ?";
		return jdbcTemplate.queryForList(sql, siscod);
	}
	
	public int saveRotacionProductoEspecificosSeleccion(String json) {
		String sql = "EXEC sp_bart_rotacion_productos_especificos_save ?";
		return jdbcTemplate.update(sql, json);
	}
	
	public int deleteRotacionProductoEspecificosSeleccion(Integer rotaespid) {
		String sql = "EXEC sp_bart_rotacion_productos_especificos_delete_seleccionado ?";
		return jdbcTemplate.update(sql, rotaespid);
	}
	
	public int updateRotacionProductoEspecificosSeleccion(RotacionObservacionRequest t) {
		String sql = "EXEC sp_bart_rotacion_productos_especificos_update_seleccionado ?,?";
		return jdbcTemplate.update(sql, t.getRotaespid(), t.getObservacion());
	}
	
}

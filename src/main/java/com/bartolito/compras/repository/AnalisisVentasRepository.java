package com.bartolito.compras.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bartolito.compras.dto.rotacionProductos.RotacionGeneralSeleccionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosRequest;

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

	public int saveOrUpdateRotacionProducto(RotacionProductosRequest t) {
		String sql = "EXEC sp_bart_rotacion_productos_general_saveOrUpdate ?, ?, ?, ?, ?, ?";
		return jdbcTemplate.update(sql, t.getProdId(), t.getProdDesc(), t.getIndiceRotacion(),
				t.getStockPromedioValorizado(), t.getUltimoStockValorizado(), t.getEnlaceWeb());
	}

	public List<Map<String, Object>> obtenerDatosProductosLolfarById(String codpro) {
		String sql = "EXEC sp_bart_rotacion_productos_lolfar_get ?";
		return jdbcLolfarTemplate.queryForList(sql, codpro);
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
	

}

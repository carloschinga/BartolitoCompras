package com.bartolito.compras.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ComprasRepository {
	
    @Autowired
    @Qualifier("lolfarJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /*public String obtenerComprasSolpeManual(String codtip, String codlab, String codgen, String estr, String pet) {
        String sql = "EXEC sp_bart_compras_solpe_manual_listar ?, ?, ?, ?, ?";

        // Ejecuta el procedimiento y obtiene lista de filas como Map<String, Object>
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, codtip, codlab, codgen, estr, pet);

        // Convierte dinámicamente la lista en JSON
        org.json.JSONArray jsonArray = new org.json.JSONArray(rows);
        return jsonArray.toString();
    }*/


    public List<Map<String, Object>> obtenerComprasSolpeManual(String codtip, String codlab, String codgen, String estr, String pet) {
        String sql = "EXEC sp_bart_compras_solpe_manual_listar ?, ?, ?, ?, ?";
        return jdbcTemplate.queryForList(sql, codtip, codlab, codgen, estr, pet);
    }





}

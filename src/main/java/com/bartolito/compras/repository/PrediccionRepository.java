package com.bartolito.compras.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PrediccionRepository {
    @Autowired
    @Qualifier("biJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void calcularEstacionalidadGenericos() {
        String sql = "EXEC sp_bart_estacionalidad_genericos_calcular";
        jdbcTemplate.execute(sql);
    }

    public List<Map<String, Object>> obtenerEstacionalidadGenericos() {
        String sql = "EXEC sp_bart_estacionalidad_genericos_listar";
        return jdbcTemplate.queryForList(sql);
    }
}

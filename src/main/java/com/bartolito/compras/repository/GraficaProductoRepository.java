package com.bartolito.compras.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GraficaProductoRepository {

	@Autowired
    @Qualifier("lolfarJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> obtenerStockAlmacenTodos(String codpro) {
        String sql = "EXEC sp_bart_compras_grafica_producto_stkalm_todos ?";
        return jdbcTemplate.queryForList(sql, codpro);
    }

    public List<Map<String, Object>> obtenerIndicadorCompra(String codpro) {
        String sql = "EXEC sp_bart_compras_grafica_producto_indicador_compra ?";
        return jdbcTemplate.queryForList(sql, codpro);
    }
    public List<Map<String, Object>> obtenerIndicadorVenta(String codpro) {
        String sql = "EXEC sp_bart_compras_grafica_producto_indicador_venta ?";
        return jdbcTemplate.queryForList(sql, codpro);
    }

    public List<Map<String, Object>> obtenerVentasPorFarmacia(String codpro) {
        String sql = "EXEC sp_bart_compras_grafica_producto_ventas_farmacia ?";
        return jdbcTemplate.queryForList(sql, codpro);
    }

    public List<Map<String, Object>> obtenerVentasAcumulado(String codpro) {
        String sql = "EXEC sp_bart_compras_grafica_producto_ventas_acumulado ?";
        return jdbcTemplate.queryForList(sql, codpro);
    }

    public List<Map<String, Object>> obtenerInfoUltimasCompras(String codpro) {
        String sql = "EXEC sp_bart_compras_grafica_producto_infoultimascompras ?";
        return jdbcTemplate.queryForList(sql, codpro);
    }

    // =========================================
    // GRAFICA PRODUCTO - VENTAS
    // =========================================
    public List<Map<String, Object>> graficaProductoVentas(
            String codpro
    ) {

        String sql = "EXEC sp_bart_compras_grafica_producto_ventas ?";

        return jdbcTemplate.queryForList(
                sql,
                codpro
        );
    }

    // =========================================
    // GRAFICA PRODUCTO - PREDICCION VENTAS
    // =========================================
    public List<Map<String, Object>> graficaProductoPrediccionVentas(
            String codpro
    ) {

        String sql = "EXEC sp_bart_compras_grafica_producto_prediccion_ventas ?";

        return jdbcTemplate.queryForList(
                sql,
                codpro
        );
    }

    // =========================================
    // GRAFICA PRODUCTO - PRECIOS
    // =========================================
    public List<Map<String, Object>> graficaPreciosProductos(
            String codpro
    ) {

        String sql = "EXEC sp_bart_compras_grafica_precios_productos ?";

        return jdbcTemplate.queryForList(
                sql,
                codpro
        );
    }
}

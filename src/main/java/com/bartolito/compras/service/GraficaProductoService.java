package com.bartolito.compras.service;


import com.bartolito.compras.repository.GraficaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GraficaProductoService {

    @Autowired
    private GraficaProductoRepository repository;


    public List<Map<String, Object>> obtenerVentasPorFarmacia(String codpro) {
        return repository.obtenerVentasPorFarmacia(codpro);
    }
    public List<Map<String, Object>> obtenerVentasAcumulado(String codpro){
        return repository.obtenerVentasAcumulado(codpro);
    }

    public List<Map<String, Object>> obtenerStockAlmacenTodos(String codpro) {
        return repository.obtenerStockAlmacenTodos(codpro);
    }
    public  List<Map<String, Object>> obtenerIndicadorCompra(String codpro) {
        return repository.obtenerIndicadorCompra(codpro);
    }
    public  List<Map<String, Object>> obtenerIndicadorVenta(String codpro) {
        return repository.obtenerIndicadorVenta(codpro);
    }

    public  List<Map<String, Object>> obtenerInfoUltimasCompras(String codpro) {
        return repository.obtenerInfoUltimasCompras(codpro);
    }

    // =========================================
    // GRAFICA PRODUCTO - VENTAS
    // =========================================
    public List<Map<String, Object>> graficaProductoVentas(
            String codpro
    ) {

        return repository.graficaProductoVentas(
                codpro
        );
    }

    // =========================================
    // GRAFICA PRODUCTO - PREDICCION VENTAS
    // =========================================
    public List<Map<String, Object>> graficaProductoPrediccionVentas(
            String codpro
    ) {

        return repository.graficaProductoPrediccionVentas(
                codpro
        );
    }

    // =========================================
    // GRAFICA PRODUCTO - PRECIOS
    // =========================================
    public List<Map<String, Object>> graficaPreciosProductos(
            String codpro
    ) {

        return repository.graficaPreciosProductos(
                codpro
        );
    }
}

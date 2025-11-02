package com.bartolito.compras.service;


import com.bartolito.compras.repository.GraficaProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GraficaProductoService {

    private final GraficaProductoRepository repository;

    public GraficaProductoService(GraficaProductoRepository repository) {
        this.repository = repository;
    }

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

    public  List<Map<String, Object>> obtenerInfoUltimasCompras(String codpro) {
        return repository.obtenerInfoUltimasCompras(codpro);
    }

}

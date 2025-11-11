package com.bartolito.compras.controller;

import com.bartolito.compras.dto.ParametroProductoDTO;
import com.bartolito.compras.service.GraficaProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grafica-producto")
public class GraficaProductoController {
    private final GraficaProductoService service;

    public GraficaProductoController(GraficaProductoService service) {
        this.service = service;
    }

    @PostMapping("/obtener-ventas-farmacia")
    public ResponseEntity<Map<String, Object>> obtenerVentasPorFarmacia(@RequestBody ParametroProductoDTO parametroProductoDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> resultado = service.obtenerVentasPorFarmacia(parametroProductoDTO.getCodpro());
            response.put("resultado", "ok");
            response.put("graficoVentas", resultado);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("resultado", "error");
            response.put("mensaje", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/obtener-ventas-acumulado")
    public ResponseEntity<Map<String, Object>> obtenerVentasAcumulado(@RequestBody ParametroProductoDTO parametroProductoDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> data = service.obtenerVentasAcumulado(parametroProductoDTO.getCodpro());
            response.put("resultado", "ok");
            response.put("data", data);
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            response.put("resultado", "error");
            response.put("mensaje", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/obtener-stock-almacen-todos")
    public ResponseEntity<Map<String, Object>> obtenerStockAlmacenTodos(@RequestBody ParametroProductoDTO parametroProductoDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> data = service.obtenerStockAlmacenTodos(parametroProductoDTO.getCodpro());
            response.put("resultado", "ok");
            response.put("data", data);
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            response.put("resultado", "error");
            response.put("mensaje", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/obtener-indicador-compra")
    public ResponseEntity<Map<String, Object>> obtenerIndicadorCompra(@RequestBody ParametroProductoDTO parametroProductoDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> data = service.obtenerIndicadorCompra(parametroProductoDTO.getCodpro());
            response.put("resultado", "ok");
            response.put("data", data);
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            response.put("resultado", "error");
            response.put("mensaje", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/obtener-indicador-venta")
    public ResponseEntity<Map<String, Object>> obtenerIndicadorVenta(@RequestBody ParametroProductoDTO parametroProductoDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> data = service.obtenerIndicadorVenta(parametroProductoDTO.getCodpro());
            response.put("resultado", "ok");
            response.put("data", data);
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            response.put("resultado", "error");
            response.put("mensaje", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/obtener-info-ultimas-compras")
    public ResponseEntity<Map<String, Object>> obtenerInfoUltimasCompras(@RequestBody ParametroProductoDTO parametroProductoDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> data = service.obtenerInfoUltimasCompras(parametroProductoDTO.getCodpro());
            response.put("resultado", "ok");
            response.put("data", data);
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            response.put("resultado", "error");
            response.put("mensaje", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}

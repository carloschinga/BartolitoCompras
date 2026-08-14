package com.bartolito.compras.controller;

import com.bartolito.compras.service.PrediccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/predicciones")
public class PrediccionController {
    @Autowired
    private PrediccionService prediccionService;

    @GetMapping("/calcularEstacionalidadGenericos")
    public void calcularEstacionalidadGenericos() {
        prediccionService.calcularEstacionalidadGenericos();
    }

    @GetMapping("/obtenerEstacionalidadGenericos")
    public List<Map<String, Object>> obtenerEstacionalidadGenericos() {
        return prediccionService.obtenerEstacionalidadGenericos();
    }
}

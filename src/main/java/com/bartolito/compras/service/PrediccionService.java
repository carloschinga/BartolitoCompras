package com.bartolito.compras.service;

import com.bartolito.compras.repository.AnalisisVentasRepository;
import com.bartolito.compras.repository.PrediccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PrediccionService {
    @Autowired
    private PrediccionRepository prediccionRepository;

    public void calcularEstacionalidadGenericos() {
        prediccionRepository.calcularEstacionalidadGenericos();
    }

    public List<Map<String, Object>> obtenerEstacionalidadGenericos() {
        return prediccionRepository.obtenerEstacionalidadGenericos();
    }
}

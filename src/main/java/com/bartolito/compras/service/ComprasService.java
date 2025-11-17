package com.bartolito.compras.service;

import com.bartolito.compras.repository.ComprasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComprasService {

    private final ComprasRepository repository;

    public ComprasService(ComprasRepository repository) {
        this.repository = repository;
    }

    /*public String obtenerComprasSolpeManual(String codtip, String codlab,String codgen,String estr, String pet) {
        return repository.obtenerComprasSolpeManual(codtip,codlab,codgen,estr, pet);
    }*/
    public List<Map<String, Object>> obtenerComprasSolpeManual(String codtip, String codlab, String codgen, String estr, String pet) {
        return repository.obtenerComprasSolpeManual(codtip,codlab,codgen,estr, pet);
    }

}

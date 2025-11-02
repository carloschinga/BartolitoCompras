package com.bartolito.compras.controller;

import com.bartolito.compras.dto.ParametroSolpeManualDTO;
import com.bartolito.compras.service.ComprasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solpe")
public class ComprasController {

    private final ComprasService service;

    public ComprasController(ComprasService service) {
        this.service = service;
    }

    @PostMapping("/obtener-compras-solpe-manual")
    public ResponseEntity<String> obtenerComprasSolpeManual(@RequestBody ParametroSolpeManualDTO parametroDTO) {
        try {
            String result = service.obtenerComprasSolpeManual(
                    parametroDTO.getCodtip(),
                    parametroDTO.getCodlab(),
                    parametroDTO.getCodgen(),
                    parametroDTO.getEstr(),
                    parametroDTO.getPet()
            );

            org.json.JSONArray dataArray = new org.json.JSONArray(result);
            org.json.JSONObject response = new org.json.JSONObject();
            response.put("resultado", "ok");
            response.put("data", dataArray);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.toString());

        } catch (Exception e) {
            org.json.JSONObject error = new org.json.JSONObject();
            error.put("resultado", "error");
            error.put("mensaje", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(error.toString());
        }
    }
    // ✅ Endpoint de prueba
    @GetMapping("/hola")
    public ResponseEntity<String> holaMundo() {
        return ResponseEntity.ok("¡Hola Mundo desde ComprasController!");
    }
}

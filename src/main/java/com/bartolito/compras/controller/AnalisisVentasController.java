package com.bartolito.compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bartolito.compras.dto.BaseOperacionResponse;
import com.bartolito.compras.dto.farmacia.FarmaciaResponse;
import com.bartolito.compras.dto.rotacionProductos.ComboRotacionProductosGeneral;
import com.bartolito.compras.dto.rotacionProductos.RotacionEspecificosSeleccionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionEspecificosSeleccionadosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionObservacionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionGeneralSeleccionRequest;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosEspecificosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosResponse;
import com.bartolito.compras.dto.rotacionProductos.RotacionProductosSeleccionadoResponse;
import com.bartolito.compras.facade.AnalisisVentasFacade;

@RestController
@RequestMapping("/analisisVentas")
public class AnalisisVentasController {

	@Autowired
	private AnalisisVentasFacade analisisVentasFacade;

	@GetMapping("/loadRotacionProductosGeneral")
	public List<RotacionProductosResponse> loadRotacionProductosGeneral() {
		return analisisVentasFacade.loadRotacionProductos();
	}

	@PostMapping("/saveOrUpdateFile")
	public BaseOperacionResponse saveOrUpdateFile(@RequestBody MultipartFile file) {
		return analisisVentasFacade.saveOrUpdate(file);
	}

	@GetMapping("/initCombosRotacionGeneral")
	public ComboRotacionProductosGeneral initCombosRotacionGeneral() {
		return analisisVentasFacade.initCombosRotacionGeneral();
	}

	@PostMapping("/saveRotacionProductoGeneralSeleccion")
	public BaseOperacionResponse saveRotacionProductoGeneralSeleccion(
			@RequestBody List<RotacionGeneralSeleccionRequest> request) {
		return analisisVentasFacade.saveRotacionProductoGeneralSeleccion(request);
	}

	@GetMapping("/loadRotacionProductosGeneralSeleccionados")
	public List<RotacionProductosSeleccionadoResponse> loadRotacionProductosGeneralSeleccionados() {
		return analisisVentasFacade.loadRotacionProductosGeneralSeleccionados();
	}

	@DeleteMapping("/deleteRotacionProductoGeneral/{codpro}")
	public BaseOperacionResponse deleteRotacionProductoGeneral(@PathVariable String codpro) {
		return analisisVentasFacade.deleteRotacionProductoGeneral(codpro);
	}

	@PostMapping("/updateRotacionProductoGeneral")
	public BaseOperacionResponse updateRotacionProductoGeneral(@RequestBody RotacionObservacionRequest request) {
		return analisisVentasFacade.updateRotacionProductoGeneral(request);
	}

	@GetMapping("/loadFarmacias")
	public List<FarmaciaResponse> loadFarmacias() {
		return analisisVentasFacade.loadFarmacias();
	}

	@GetMapping("/loadRotacionProductosEspecifico/{siscod}")
	public List<RotacionProductosEspecificosResponse> loadRotacionProductosEspecifico(@PathVariable Integer siscod) {
		return analisisVentasFacade.loadRotacionProductosEspecifico(siscod);
	}

	@PostMapping("/saveRotacionProductoEspecificosSeleccion")
	public BaseOperacionResponse saveRotacionProductoEspecificosSeleccion(
			@RequestBody List<RotacionEspecificosSeleccionRequest> request) {
		return analisisVentasFacade.saveRotacionProductoEspecificosSeleccion(request);
	}
	
	@GetMapping("/loadRotacionProductosEspecificosSeleccionados/{siscod}")
	public List<RotacionEspecificosSeleccionadosResponse> loadRotacionProductosEspecificosSeleccionados(@PathVariable Integer siscod) {
		return analisisVentasFacade.loadRotacionProductosEspecificosSeleccionados(siscod);
	}
	
	@DeleteMapping("/deleteRotacionProductoEspecificosSeleccion/{rotaespid}")
	public BaseOperacionResponse deleteRotacionProductoEspecificosSeleccion(@PathVariable Integer rotaespid) {
		return analisisVentasFacade.deleteRotacionProductoEspecificosSeleccion(rotaespid);
	}

	@PostMapping("/updateRotacionProductoEspecificosSeleccion")
	public BaseOperacionResponse updateRotacionProductoEspecificosSeleccion(@RequestBody RotacionObservacionRequest request) {
		return analisisVentasFacade.updateRotacionProductoEspecificosSeleccion(request);
	}

}

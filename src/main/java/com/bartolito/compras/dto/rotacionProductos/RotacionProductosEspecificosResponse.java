package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionProductosEspecificosResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1346621996224820834L;

	private String prodId;
	private String prodDesc;
	private String codtip;
	private String destip;
	private String codgen;
	private String desgen;
	private Double stock;
	private Double ventasUltimos;
	private Double indiceRotacion;
	private Double stockPromedioValorizado;
	private Double ultimoStockValorizado;
	private Double tasa; 
	private Double coberturaMensual;
	private String categvta;
	private String enlaceWeb;
	private Integer siscod; 
	private String sisent; 
	
}

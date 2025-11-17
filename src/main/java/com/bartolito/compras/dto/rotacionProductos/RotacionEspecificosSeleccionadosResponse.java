package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionEspecificosSeleccionadosResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6337072490960704426L;

	private Integer rotaespid; 
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
	private String observacion;
	private String enlaceWeb;
	private Integer siscod;

}

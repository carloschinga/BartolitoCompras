package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionEspecificosRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2418178994355789605L;

	private String prodId;
	private String prodDesc;
	private Double indiceRotacion;
	private Double stockPromedioValorizado;
	private Double ultimoStockValorizado;
	private String enlaceWeb; 
	private Integer siscod; 
	
}

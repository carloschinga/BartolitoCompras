package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionProductosRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5165399683388535278L;
	
	private String prodId;
	private String prodDesc;
	private Double indiceRotacion;
	private Double stockPromedioValorizado;
	private Double ultimoStockValorizado;
	private String enlaceWeb; 

}

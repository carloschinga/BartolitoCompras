package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericosResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4708832439751700539L;

	private String codgen;
	private String desgen;

}

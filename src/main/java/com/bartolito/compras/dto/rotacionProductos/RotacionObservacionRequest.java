package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionObservacionRequest implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -7475360429627738704L;

	private String codpro; 
	private Integer rotaespid; 
	private String observacion; 
	
}

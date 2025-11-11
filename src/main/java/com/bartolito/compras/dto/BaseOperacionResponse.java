package com.bartolito.compras.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseOperacionResponse implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 6894959265446265106L;
	private String codigo;
	private String mensaje;
	
	public BaseOperacionResponse(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
}

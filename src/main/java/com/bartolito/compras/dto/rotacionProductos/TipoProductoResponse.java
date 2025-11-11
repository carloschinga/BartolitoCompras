package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoProductoResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3464180701847884534L;
	
	private String codtip;
	private String destip; 

}

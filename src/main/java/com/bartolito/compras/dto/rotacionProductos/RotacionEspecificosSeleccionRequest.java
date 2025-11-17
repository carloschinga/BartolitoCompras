package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionEspecificosSeleccionRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2264479227538292708L;
	
	private String codpro; 
	private String prodDesc; 
	private String codtip; 
	private String destip; 
	private String codgen; 
	private String desgen; 
	private String categvta; 
	private String enlace_web; 
	private Integer siscod; 
	private String observacion; 
	
}

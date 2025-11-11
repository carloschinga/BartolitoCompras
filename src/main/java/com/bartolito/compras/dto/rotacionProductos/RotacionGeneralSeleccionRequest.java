package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionGeneralSeleccionRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5479740513335540398L;
	
	private String codpro; 
	private String codtip; 
	private String codgen; 
	private Double stock; 
	private Double ventas_ultimos; 
	private Double indice_rotacion; 
	private Double stock_promedio_valorizado; 
	private Double ultimo_stock_valorizado; 
	private String categvta; 
	private String observacion; 
	private String prodDesc; 

}

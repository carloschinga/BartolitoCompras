package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComboRotacionProductosGeneral implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6315608876735442419L;
	
	private List<TipoRentabilidadResponse> tipoRentabilidad; 
	private List<GenericosResponse> generico; 
	private List<TipoProductoResponse> tipoProducto; 
}

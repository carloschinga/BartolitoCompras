package com.bartolito.compras.dto.farmacia;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FarmaciaResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3386411833411585043L;

	private Integer siscod;
	private String sisent;

}

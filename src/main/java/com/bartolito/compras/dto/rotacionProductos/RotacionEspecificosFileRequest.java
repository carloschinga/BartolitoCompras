package com.bartolito.compras.dto.rotacionProductos;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotacionEspecificosFileRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1916845526735335807L;

	private MultipartFile file;
	private Integer siscod;

}

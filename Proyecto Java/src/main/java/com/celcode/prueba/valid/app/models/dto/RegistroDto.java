package com.celcode.prueba.valid.app.models.dto;

import javax.validation.constraints.NotBlank;

public class RegistroDto {

	@NotBlank
	private String nombre;
	@NotBlank
	private String apellido;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
}

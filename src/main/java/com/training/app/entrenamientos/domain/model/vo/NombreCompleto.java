package com.training.app.entrenamientos.domain.model.vo;

public record NombreCompleto(String nombre, String apellidos) {
		
	public String nombreCompleto() {
		return nombre + " " + apellidos;
	}
}

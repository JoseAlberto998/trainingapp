package com.training.app.mantenimiento.domain.model;

import java.util.Objects;
import java.util.UUID;

import com.training.app.entrenamientos.domain.model.vo.Email;
import com.training.app.entrenamientos.domain.model.vo.NombreCompleto;

public class Usuario {

	private UUID identificador;
	private Email email;
	private NombreCompleto nombre;

	public Usuario(Email email, NombreCompleto nombre) {
		this.email = email;
		this.nombre = nombre;
	}

	public Usuario(String email, String nombre, String apellidos) {
		this.email = new Email(email);
		this.nombre = new NombreCompleto(nombre, apellidos);
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public void setIdentificador(UUID identificador) {
		this.identificador = identificador;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public NombreCompleto getNombre() {
		return nombre;
	}

	public void setNombre(NombreCompleto nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, identificador, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(identificador, other.identificador)
				&& Objects.equals(nombre, other.nombre);
	}

}

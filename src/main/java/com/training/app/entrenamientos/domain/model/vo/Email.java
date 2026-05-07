package com.training.app.entrenamientos.domain.model.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

	//Public static por si se quiere reutilizar en la aplicacion	
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

	private final String value;

	//A partir de Java 21 se pueden lanzar excepciones desde los constructores por lo que no es necesario el "of"
	public Email(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("El email no puede estar vacio");
		}

		if (!EMAIL_PATTERN.matcher(value).matches()) {
			throw new IllegalArgumentException("Formato de email invalido");
		}

		this.value = value.toLowerCase().trim();
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Email email))
			return false;
		return Objects.equals(value, email.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return value;
	}
}

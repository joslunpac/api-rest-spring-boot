package com.apirestspringboot.model.dto.security;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninDto implements Serializable {

	@NotNull(message = "El username es obligatorio")
	private String username;

	@NotNull(message = "El password es obligatorio")
	private String password;

}

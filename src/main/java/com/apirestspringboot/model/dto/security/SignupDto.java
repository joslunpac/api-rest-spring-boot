package com.apirestspringboot.model.dto.security;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto implements Serializable {

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 carácteres")
	private String nombre;

	@NotBlank(message = "El username es obligatorio")
	@Size(min = 1, max = 100, message = "El username debe tener entre 1 y 100 carácteres")
	private String username;

	@NotBlank(message = "El email es obligatorio")
	@Size(min = 1, max = 100, message = "El email debe tener entre 1 y 100 carácteres")
	@Email
	private String email;

	@NotBlank(message = "El password es obligatorio")
	@Size(min = 4, max = 15, message = "El password debe tener entre 4 y 15 carácteres")
	private String password;

	@Schema(accessMode = AccessMode.READ_ONLY)
	private List<RolDto> roles;

}

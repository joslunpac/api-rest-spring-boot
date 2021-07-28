package com.apirestspringboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.apirestspringboot.exception.ApiForbiddenException;
import com.apirestspringboot.exception.ApiNotFoundException;
import com.apirestspringboot.model.dto.security.JwtDto;
import com.apirestspringboot.model.dto.security.SigninDto;
import com.apirestspringboot.model.dto.security.SignupDto;
import com.apirestspringboot.model.entity.Rol;
import com.apirestspringboot.model.entity.Usuario;
import com.apirestspringboot.model.enumerate.RolEnum;
import com.apirestspringboot.security.jwt.JwtUtils;
import com.apirestspringboot.service.RolService;
import com.apirestspringboot.service.UsuarioService;
import com.apirestspringboot.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "A_" + UsuarioController.ENTIDADES, description = Constantes.OA_ENDPOINT + UsuarioController.ENTIDADES)
public class UsuarioController {

	public static final String ENTIDAD = Constantes.USU;
	public static final String ENTIDADES = Constantes.USU_PLURAL;

	private static final String OA_AUTENTICAR = Constantes.OA_AUTENTICAR + ENTIDAD;
	private static final String OA_REGISTRAR = Constantes.OA_REGISTRAR + ENTIDAD;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolService;

	@Operation(summary = OA_AUTENTICAR)
	@PostMapping(path = "/auth/signin")
	public ResponseEntity<JwtDto> signin(@Valid @RequestBody SigninDto signinDto) {
		// Autenticamos el usuario
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));

		// Una vez autenticado, lo añadimos al contexto de String Security
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Generamos el Token
		String jwt = jwtUtils.generarJwt(authentication);

		// Obtenemos el usuario autenticado con toda su información
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		// Construimos la respuesta
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

	@Operation(summary = OA_REGISTRAR)
	@PostMapping(path = "/auth/signup")
	public ResponseEntity<HttpStatus> signup(@Valid @RequestBody SignupDto signupDto) {
		// Comprobamos que el usuario no exista ya en BD
		if (usuarioService.existsByUsername(signupDto.getUsername()))
			throw new ApiForbiddenException("El username " + signupDto.getUsername() + Constantes.YA_EXISTE);

		if (usuarioService.existsByEmail(signupDto.getEmail()))
			throw new ApiForbiddenException("El email " + signupDto.getEmail() + Constantes.YA_EXISTE);

		// Preparamos los datos del usuario
		String nombre = signupDto.getNombre().toLowerCase();
		String username = signupDto.getUsername().toLowerCase();
		String email = signupDto.getEmail().toLowerCase();
		String password = passwordEncoder.encode(signupDto.getPassword());

		List<Rol> roles = new ArrayList<>();

		if (usuarioService.count() == 0) {
			// Si es el primer usuario en BD, le asignamos los roles [USER] y [ADMIN]
			roles.add(rolService.findByNombre(RolEnum.ROLE_USER)//
					.orElseThrow(() -> new ApiNotFoundException(Constantes.ROL + Constantes.CON_NOMBRE
							+ RolEnum.ROLE_USER.name() + Constantes.NO_ENCONTRADO)));
			roles.add(rolService.findByNombre(RolEnum.ROLE_ADMIN)//
					.orElseThrow(() -> new ApiNotFoundException(Constantes.ROL + Constantes.CON_NOMBRE
							+ RolEnum.ROLE_ADMIN.name() + Constantes.NO_ENCONTRADO)));
		} else {
			// Si ya existen uno o más usuarios en BD, le asignamos el rol [USER],
			// de manera que solo puede existir un usuario con el rol [ADMIN]
			roles.add(rolService.findByNombre(RolEnum.ROLE_USER)//
					.orElseThrow(() -> new ApiNotFoundException(Constantes.ROL + Constantes.CON_NOMBRE
							+ RolEnum.ROLE_USER.name() + Constantes.NO_ENCONTRADO)));
		}

		// Construimos el usuario y lo persistimos
		usuarioService.save(new Usuario(nombre, username, email, password, roles));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}

package com.apirestspringboot.service;

import java.util.Optional;

import com.apirestspringboot.model.entity.Usuario;

public interface UsuarioService {

	long count();

	Optional<Usuario> findByUsername(String username);

	Usuario save(Usuario usuario);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}

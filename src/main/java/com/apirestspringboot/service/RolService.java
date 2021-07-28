package com.apirestspringboot.service;

import java.util.Optional;

import com.apirestspringboot.model.entity.Rol;
import com.apirestspringboot.model.enumerate.RolEnum;

public interface RolService {

	long count();

	Optional<Rol> findByNombre(RolEnum nombre);

	Rol save(Rol rol);

}

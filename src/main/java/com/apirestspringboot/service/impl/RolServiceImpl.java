package com.apirestspringboot.service.impl;

import java.util.Optional;

import com.apirestspringboot.model.entity.Rol;
import com.apirestspringboot.model.enumerate.RolEnum;
import com.apirestspringboot.repository.RolRepository;
import com.apirestspringboot.service.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	RolRepository rolRepository;

	@Override
	public long count() {
		return rolRepository.count();
	}

	@Override
	public Optional<Rol> findByNombre(RolEnum nombre) {
		return rolRepository.findByNombre(nombre);
	}

	@Override
	@Transactional
	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}

}

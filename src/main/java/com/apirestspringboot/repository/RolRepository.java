package com.apirestspringboot.repository;

import java.util.Optional;

import com.apirestspringboot.model.entity.Rol;
import com.apirestspringboot.model.enumerate.RolEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(RolEnum nombre);

}

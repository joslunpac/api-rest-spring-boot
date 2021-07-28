package com.apirestspringboot.security.service;

import com.apirestspringboot.model.entity.Usuario;
import com.apirestspringboot.repository.UsuarioRepository;
import com.apirestspringboot.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                Constantes.USU + Constantes.CON_NOMBRE + username + Constantes.NO_ENCONTRADO));

        return UserDetailsImpl.construir(usuario);
    }

}

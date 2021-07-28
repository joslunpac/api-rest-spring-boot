package com.apirestspringboot.util;

import com.apirestspringboot.model.entity.Rol;
import com.apirestspringboot.model.enumerate.RolEnum;
import com.apirestspringboot.service.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Esta clase se ejecuta cada vez que se ejecute la aplicación, y se encargará
 * de insertar en BD los datos necesarios para el correcto funcionamiento de la
 * misma
 */

@Component
public class precargarDatos implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        if (rolService.count() == 0) {
            // Si aún no existen roles en BD, los insertamos
            Rol rolAdmin = new Rol(RolEnum.ROLE_ADMIN);
            Rol rolUser = new Rol(RolEnum.ROLE_USER);
            rolService.save(rolAdmin);
            rolService.save(rolUser);
        }
    }

}
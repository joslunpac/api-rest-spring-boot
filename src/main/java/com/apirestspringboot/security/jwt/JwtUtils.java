package com.apirestspringboot.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.apirestspringboot.exception.ApiBadRequestException;
import com.apirestspringboot.security.service.UserDetailsImpl;
import com.apirestspringboot.util.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String generarJwt(Authentication authentication) {
        UserDetailsImpl usuarioPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()//
                .setSubject(usuarioPrincipal.getUsername())//
                .setIssuedAt(new Date())//
                .setExpiration(new Date(new Date().getTime() + Constantes.TIEMPO_EXPIRACION))//
                .signWith(SignatureAlgorithm.HS512, Constantes.CLAVE_SECRETA)//
                .compact();
    }

    public String obtenerJwt(HttpServletRequest request) {
        String header = request.getHeader(Constantes.CABECERA);

        if (header != null && header.startsWith(Constantes.PREFIJO_TOKEN))
            return header.replace(Constantes.PREFIJO_TOKEN, "");

        return null;
    }

    public String obtenerUsernameFromJwt(String jwt) {
        return Jwts.parser().setSigningKey(Constantes.CLAVE_SECRETA).parseClaimsJws(jwt).getBody().getSubject();
    }

    public boolean validarJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(Constantes.CLAVE_SECRETA).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            logger.error("Firma JWT no válida: {}", e.getMessage());
            throw new ApiBadRequestException("Firma JWT no válida: {}");
        } catch (MalformedJwtException e) {
            logger.error("Token JWT no válido: {}", e.getMessage());
            throw new ApiBadRequestException("Token JWT no válido: {}");
        } catch (ExpiredJwtException e) {
            logger.error("El token JWT ha expirado: {}", e.getMessage());
            throw new ApiBadRequestException("El token JWT ha expirado: {}");
        } catch (UnsupportedJwtException e) {
            logger.error("El token JWT no es compatible: {}", e.getMessage());
            throw new ApiBadRequestException("El token JWT no es compatible: {}");
        } catch (IllegalArgumentException e) {
            logger.error("La cadena de reclamaciones de JWT está vacía: {}", e.getMessage());
            throw new ApiBadRequestException("La cadena de reclamaciones de JWT está vacía: {}");
        }
    }

}

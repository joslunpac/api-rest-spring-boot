package com.apirestspringboot.security;

import com.apirestspringboot.security.jwt.JwtEntryPoint;
import com.apirestspringboot.security.jwt.JwtFilter;
import com.apirestspringboot.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // Habilitar cors
        http.cors();

        // Deshabilitar CSRF (falsificación de solicitud entre sitios)
        http.csrf().disable();

        // Spring Security no creará ni utilizará ninguna sesión
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Puntos de entrada
        http.authorizeRequests()//
                .antMatchers("/auth/signin").permitAll()//
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")//
                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")//
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")//
                .anyRequest().authenticated();

        // Si un usuario intenta acceder a un recurso sin tener suficientes permisos
        http.exceptionHandling().accessDeniedPage("/login");
        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        // Opcional, si desea probar la API desde un navegador
        http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Permitir que se acceda a swagger sin autenticación
        web.ignoring()//
                // .antMatchers("/v3/api-docs/**")// Usar cuando no personalizamos la ruta
                // .antMatchers("/swagger-ui/**")// Usar cuando no personalizamos la ruta
                .antMatchers("/docs/**")// Usar cuando personalizamos la ruta
                .antMatchers("/swagger/**")// Usar cuando personalizamos la ruta
                .antMatchers("/swagger-ui/**")// Usar cuando personalizamos la ruta
                .antMatchers("/swagger-ui.html")// Usar siempre
        ;
    }

    // Habilitar cors a nivel global
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

}

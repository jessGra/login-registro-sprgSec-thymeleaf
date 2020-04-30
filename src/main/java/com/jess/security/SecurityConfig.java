package com.jess.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jess.security.service.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UsuarioDetailsServiceImpl usuarioDetailsServiceImpl;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/bootstrap/**", "/fontawesome/**", "/img/**").permitAll()
		.antMatchers("/", "/index", "/usuario/registro", "/usuario/registrar", "/fragments").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginProcessingUrl("/signin")
		.loginPage("/login").permitAll()
		.defaultSuccessUrl("/index")
		.usernameParameter("nombreUsuario")
		.passwordParameter("passwordUsuario")
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll()
		.deleteCookies("JSESSIONID")
		.and()
		.rememberMe().tokenValiditySeconds(2592000).key("secret").rememberMeParameter("checkRememberMe");
	}
}

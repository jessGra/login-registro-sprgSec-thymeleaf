package com.jess.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jess.model.Usuario;
import com.jess.service.UsuarioService;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.optenerPorNombre(nombre).orElseThrow(() -> new UsernameNotFoundException(nombre));
		return UsuarioPrincipal.build(usuario);
	}
	
	
}

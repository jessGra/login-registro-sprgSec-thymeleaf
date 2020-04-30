package com.jess.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jess.enums.RolNombre;
import com.jess.model.Rol;
import com.jess.model.Usuario;
import com.jess.service.RolService;
import com.jess.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/registro")
	public String nuevo() {
		return "registro";
	}
	
	@PostMapping("/registrar")
	public ModelAndView registrar(String nombre, String password) {
		ModelAndView mv = new ModelAndView();
		if(Strings.isBlank(nombre)) {
			mv.addObject("error", "El nombre no puede ser vacío");
			mv.setViewName("registro");
			return mv;
		}
		if(Strings.isBlank(password)) {
			mv.addObject("error", "La contraseña no puede ser vacía");
			mv.setViewName("registro");
			return mv;
		}
		if(usuarioService.existeUsuarioPorNombre(nombre)) {
			mv.addObject("error", "Ya existe un usuario con ese nombre");
			mv.setViewName("registro");
			return mv;
		}
		
		String passwordEnc = passwordEncoder.encode(password);
		Usuario usuario = new Usuario(nombre, passwordEnc);
		Rol rol = rolService.optenerPorRolNombre(RolNombre.ROLE_USER).get();
		Set<Rol> roles = new HashSet<>();
		roles.add(rol);
		usuario.setRoles(roles);
		usuarioService.guardar(usuario);
		mv.addObject("registroOK", "Cuenta creada, ingresa tus credenciales para iniciar sesión");
		mv.setViewName("login");
		return mv;
	}
}

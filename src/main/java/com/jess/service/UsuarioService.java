package com.jess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jess.model.Usuario;
import com.jess.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepo;
	
	public List<Usuario> optenerLista(){
		return usuarioRepo.findAll();
	}
	
	public Optional<Usuario> optenerPorId(long id){
		return usuarioRepo.findById(id);
	}
	
	public Optional<Usuario> optenerPorNombre(String nombre){
		return usuarioRepo.findByNombre(nombre);
	}
	
	public void guardar(Usuario usuario) {
		usuarioRepo.save(usuario);
	}
	
	public boolean existeUsuarioPorId(long id) {
		return usuarioRepo.existsById(id);
	}
	
	public boolean existeUsuarioPorNombre(String nombre) {
		return usuarioRepo.existsByNombre(nombre);
	}
}

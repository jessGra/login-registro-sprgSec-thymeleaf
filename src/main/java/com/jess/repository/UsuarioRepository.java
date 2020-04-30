package com.jess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jess.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	boolean existsByNombre(String nombre);
	Optional<Usuario> findByNombre(String nombre);
}

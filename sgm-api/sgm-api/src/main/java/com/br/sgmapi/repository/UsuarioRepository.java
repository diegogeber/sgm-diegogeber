package com.br.sgmapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.sgmapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
	Optional<Usuario> findByEmail(String email);
	
	@Query("Select u from Usuario u where u.nome like %:nome%")
	List<Usuario> findByNome(String nome);
}

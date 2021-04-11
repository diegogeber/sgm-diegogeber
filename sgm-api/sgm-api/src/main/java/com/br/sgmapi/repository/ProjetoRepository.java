package com.br.sgmapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.sgmapi.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
	
	@Query("select p from Projeto p where p.nome like %:nome% ")
	public List<Projeto> findByNome(String nome);

}

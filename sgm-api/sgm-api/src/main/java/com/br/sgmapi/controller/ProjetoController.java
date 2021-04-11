package com.br.sgmapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.sgmapi.dto.ProjetoDto;
import com.br.sgmapi.model.Projeto;
import com.br.sgmapi.service.ProjetoService;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
		
	@Autowired
	private ProjetoService projetoService;
	
	@PostMapping
	public ResponseEntity<String> salvarUsuario(@RequestBody ProjetoDto dto){
		Projeto u = projetoService.salvarProjeto(dto);
		
		return ResponseEntity.ok().body(u.getId().toString());
			
	}
	
	@GetMapping
	public ResponseEntity<List<Projeto>> listarProjetos(
			@RequestParam(value = "nome", required = false) String nome){
		
		return ResponseEntity.ok().body(projetoService.listarProjetos(nome));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarProjetoPorId(@PathVariable("id") Long id ){
		
		Optional<Projeto> u = projetoService.getProjetoById(id);
		
		if(u.isPresent()) {
			return ResponseEntity.ok().body(u.get());
		}else {
			return ResponseEntity.ok().body(new ArrayList<>());
		}
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable("id") Long id , @RequestBody ProjetoDto dto){
		dto.setId(id);
		Projeto p = projetoService.salvarProjeto(dto);
		return ResponseEntity.ok().body(p);
	}

}

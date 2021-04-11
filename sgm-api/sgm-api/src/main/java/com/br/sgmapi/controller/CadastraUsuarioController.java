package com.br.sgmapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.sgmapi.dto.UsuarioDto;
import com.br.sgmapi.model.Usuario;
import com.br.sgmapi.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class CadastraUsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PreAuthorize("permitAll")
	@PostMapping
	public ResponseEntity<String> salvarUsuario(@RequestBody UsuarioDto dto){
		Usuario u = usuarioService.salvarUsuario(dto);
		
		return ResponseEntity.ok().body(u.getId().toString());
			
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(@RequestParam(value = "nome", required = false) String nome){
		
		return ResponseEntity.ok().body(usuarioService.getAll(nome));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable("id") Long id ){
		
		Usuario u = usuarioService.getUser(id);
		
		if(null != u) {
			return ResponseEntity.ok().body(usuarioService.getUser(id));
		}else {
			return ResponseEntity.ok().body(new ArrayList<>());
		}
		
		
	}
	
}

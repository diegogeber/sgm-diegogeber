package com.br.sgmapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.sgmapi.dto.ProjetoDto;
import com.br.sgmapi.model.Projeto;
import com.br.sgmapi.repository.ProjetoRepository;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	public Projeto salvarProjeto(ProjetoDto dto) {
		//Homenagem ao pofexô
		Projeto pojeto = new Projeto();
		BeanUtils.copyProperties(dto, pojeto);
				
		return projetoRepository.save(pojeto);
	}
	
	public List<Projeto> listarProjetos(String nome){
		if(null != nome) {
			return projetoRepository.findByNome(nome);
		}else {
			return projetoRepository.findAll();
		}
		
	}
	
	public Optional<Projeto> getProjetoById(Long id){
		return projetoRepository.findById(id);
	}
		
}

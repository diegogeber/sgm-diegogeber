package com.br.sgmapi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.sgmapi.model.Tributos;

@RestController
@RequestMapping("/tributo")
public class ConsultarTributosController {
	
	@GetMapping
	public Tributos getTributo(
			@RequestParam(value = "tributo", required = true) String tributo,
			@RequestParam(value = "numeroInscricao", required = true) String numeroInscricao,
			@RequestParam(value = "tipoOrganizacao", required = true) String tipoOrganizacao
			){
		Random random = new Random();
		String[] nomes = new String[]{"Matheus S G Rosa","João Paulo","Maria de Fátima","Edison Silva","Adriely Rosa","Mauricio Geber", "Lucimar Santana", "Pedro Souza", "Célia Regina"};
		Tributos retorno = new Tributos();
		retorno.setTributo(tributo);
		retorno.setCompetencia("2021");
		retorno.setEndereco("Rua Mock do Destino, " + Math.abs(random.nextInt()) + " - Centro - Bom Destino");
		retorno.setNumeroInscricao(numeroInscricao);
		retorno.setTipoOrganizacao(tipoOrganizacao);
		retorno.setProprietario(getAleatorio(Arrays.asList(nomes)));
		retorno.setCodigoBarras("001 9 337370000000100 05009 401448 16060680935031");
		retorno.setVencimento("31/12/2021");
		retorno.setValor("R$ 183,78");
		return retorno;
	}
	
	private String getAleatorio(List<String> lista){
		Random random = new Random();
		return lista.get(Math.abs(random.nextInt(lista.size())));
	}


}

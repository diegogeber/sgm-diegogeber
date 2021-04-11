package com.br.sgmapi.model;

//Não é uma entidade propriamente dita, a mesma serve como mock de um serviço externo
public class Tributos {
	
	private String tributo;
	private String numeroInscricao;
	private String competencia;
	private String endereco;
	private String tipoOrganizacao;
	private String codigoBarras;
	private String vencimento;
	private String valor;
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	public String getTipoOrganizacao() {
		return tipoOrganizacao;
	}
	public void setTipoOrganizacao(String tipoOrganizacao) {
		this.tipoOrganizacao = tipoOrganizacao;
	}
	private String proprietario;
	public String getTributo() {
		return tributo;
	}
	public void setTributo(String tributo) {
		this.tributo = tributo;
	}
	public String getNumeroInscricao() {
		return numeroInscricao;
	}
	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	
	
}

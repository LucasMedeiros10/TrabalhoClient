package br.univel.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO de representação dos atributos do Cliente
 * 
 * @author LucasMedeiros
 *
 */

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Date   dataNascimento;
	private String cpf;
	private String rg;
	private TipoOperacao tipoOperacao;

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
}

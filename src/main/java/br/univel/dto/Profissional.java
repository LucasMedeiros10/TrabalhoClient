package br.univel.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO de representação dos atributos do Profissional
 * 
 * @author LucasMedeiros
 *
 */

public class Profissional implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Date   dataNascimento;
	private String login;
	private String senha;
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
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

}

package br.jus.pjedash.entidade;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String login;
	private String email;
	private String senha;
	private String papelSelecionado;
	private String orgaoIdSelecionado;
	private List<String> papeis;
	private List<String> orgaosIds;
	private Boolean ativo;
	private String statusSenha;
	

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@JsonProperty
	public List<String> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<String> papeis) {
		this.papeis = papeis;
	}

	@JsonProperty
	public List<String> getOrgaosIds() {
		return orgaosIds;
	}

	public void setOrgaosIds(List<String> orgaosIds) {
		this.orgaosIds = orgaosIds;
	}

    public String getPapelSelecionado() {
        return papelSelecionado;
    }

    public void setPapelSelecionado(String papelSelecionado) {
        this.papelSelecionado = papelSelecionado;
    }

    public String getOrgaoIdSelecionado() {
        return orgaoIdSelecionado;
    }

    public void setOrgaoIdSelecionado(String orgaoIdSelecionado) {
        this.orgaoIdSelecionado = orgaoIdSelecionado;
    }

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getStatusSenha() {
		return statusSenha;
	}

	public void setStatusSenha(String statusSenha) {
		this.statusSenha = statusSenha;
	}

}

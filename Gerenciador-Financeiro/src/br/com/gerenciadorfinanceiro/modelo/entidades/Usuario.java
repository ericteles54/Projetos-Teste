package br.com.gerenciadorfinanceiro.modelo.entidades;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="usuario.findAll", query="SELECT u FROM Usuario u")
})
public class Usuario {

	@Id @GeneratedValue
	private Long id;
	
	
	private String nome;
	
	
	private String senha;	

	
	private Calendar dataDeCadastro;
	
	private enum Tipo {ADMIN, SIMPLES};

	private Tipo tipo;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Calendar dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}

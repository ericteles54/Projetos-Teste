package modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Conta {

	@Id @GeneratedValue
	private Long id;
	
	@NotNull(message="O banco não pode ser nulo")
	private String banco;
	
	@NotNull(message="O nome da conta não pode ser nulo")
	private String nome;
	
	@NotNull(message="O saldo não pode ser nulo")
	private Double saldo;
	
	@ManyToOne
	private Usuario usuario;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
}

package br.com.gerenciadorfinanceiro.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="despesa.findAll", query="SELECT d FROM Despesa d")
public class Despesa extends Movimentacao{

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Conta conta;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
}

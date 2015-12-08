package br.com.gerenciadorfinanceiro.modelo.entidades;

import java.util.Calendar;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Movimentacao {

	private String nome;	
	
	private Calendar data;	
	
	private Double valor;
	
	private enum Status{ CONSOLIDADA, NAO_CONSOLIDADA};
	
	private Status status;
		
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}	
}

package modelo.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Movimentacao {
	
	@Id @GeneratedValue
	private Long id;
	
	@NotNull(message="A conta não pode ser nula")
	@ManyToOne
	private Conta conta;
	
	@NotNull(message="O nome da despesa não pode ser nulo")
	private String nome;
	
	@NotNull(message="A data da despesa não pode ser nula")
	private Calendar data;
	
	@NotNull(message="O valor da despesa não pode ser nulo")
	private Double valor;
	
	private enum Status{ CONSOLIDADA, NAO_CONSOLIDADA};
	@NotNull(message="O status da despesa não pode ser nulo")
	private Status status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
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

package modelo.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Movimentacao {
	
	@Id @GeneratedValue
	private Long id;
	
	@NotNull(message="A descrição da movimentacao não pode ser nula")
	private String descricao;
	
	@NotNull(message="A data da movimentacao não pode ser nula")
	private Date data;	
	
	@NotNull(message="O valor da movimentacao não pode ser nulo")
	private Double valor;
		
	private enum Status{CONSOLIDADA, NAO_CONSOLIDADA};
	@NotNull(message="O status da movimentacao não pode ser nulo")
	private Status status;
	
	@ManyToOne
	@NotNull(message="A conta vinculada à movimentacao não pode ser nula")
	private Conta conta;

	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

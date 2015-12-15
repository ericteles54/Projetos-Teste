package modelo.repositorios;

import javax.persistence.EntityManager;

import modelo.entidades.Receita;

public class MovimentacaoRepository {

	private EntityManager manager;
	
	public MovimentacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionaMovimentacao(Receita receita) {
		this.manager.persist(receita);
	}
}

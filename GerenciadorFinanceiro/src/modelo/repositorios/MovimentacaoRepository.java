package modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.entidades.Movimentacao;
import modelo.entidades.Receita;

public class MovimentacaoRepository {

	private EntityManager manager;
	
	public MovimentacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Movimentacao movimentacao) {
		this.manager.persist(movimentacao);
	}
	
	public List<Receita> listaTodasReceitas(Long conta_id) {		
		
		TypedQuery<Receita> query = this.manager.createNamedQuery("Receita.buscaTodas", Receita.class);
		query.setParameter("conta_id", conta_id);
		
		List<Receita> movimentacoes;
		try {
			movimentacoes = query.getResultList();
		} catch (Exception e) {
			movimentacoes = null;
		}
		
		return movimentacoes;
	}
}

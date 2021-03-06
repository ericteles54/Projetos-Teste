package modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.entidades.Movimentacao;
import modelo.entidades.Movimentacao.Tipo;
import modelo.entidades.Receita;

public class MovimentacaoRepository {

	private EntityManager manager;
	
	public MovimentacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionaMovimentacao(Movimentacao movimentacao) {
		this.manager.persist(movimentacao);
	}
	
	public void removeMovimentacao(Movimentacao movimentacao) {
		this.manager.remove(movimentacao);
	}
	
	public List<Movimentacao> buscaTodasPorConta(Long conta_id) {
				
		TypedQuery<Movimentacao> query = this.manager.createNamedQuery("Movimentacao.buscaTodasPorConta", Movimentacao.class);
		query.setParameter("conta_id", conta_id);
		
		List<Movimentacao> movimentacoes;
		try {
			movimentacoes = query.getResultList();
		} catch (Exception e) {
			movimentacoes = null;
		}
		
		for(Movimentacao movimentacao : movimentacoes) {
			if(movimentacao instanceof Receita) {
				movimentacao.setTipo(Tipo.RECEITA);
			} else {
				movimentacao.setTipo(Tipo.DESPESA);
			}
				
		}
		
		return movimentacoes;
	}
}

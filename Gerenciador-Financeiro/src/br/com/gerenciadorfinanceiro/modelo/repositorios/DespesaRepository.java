package br.com.gerenciadorfinanceiro.modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gerenciadorfinanceiro.modelo.entidades.Despesa;

public class DespesaRepository {

	private EntityManager manager;
	
	public DespesaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public Despesa procura(Long id) {
		return this.manager.find(Despesa.class, id);
	}
	
	public void adiciona(Despesa despesa) {
		this.manager.persist(despesa);
	}
	
	public void remove(Long id) {
		Despesa despesa = this.procura(id);
		this.manager.remove(despesa);
	}
	
	public Despesa atualiza(Despesa despesa) {
		return this.manager.merge(despesa);
	}
	
	public List<Despesa> getLista() {
		TypedQuery<Despesa> query = 
				this.manager.createNamedQuery("Despesa.findAll", Despesa.class);
		
		return query.getResultList();
	}
	
}

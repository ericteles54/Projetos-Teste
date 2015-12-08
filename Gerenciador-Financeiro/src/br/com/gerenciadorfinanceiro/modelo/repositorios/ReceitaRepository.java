package br.com.gerenciadorfinanceiro.modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gerenciadorfinanceiro.modelo.entidades.Receita;

public class ReceitaRepository {
	
	private EntityManager manager;
	
	public ReceitaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public Receita procura(Long id) {
		return this.manager.find(Receita.class, id);
	}
	
	public void adiciona(Receita receita) {
		this.manager.persist(receita);
	}
	
	public void remove(Long id) {
		Receita receita = this.procura(id);
		this.manager.remove(receita);
	}
	
	public Receita atualiza(Receita receita) {
		return this.manager.merge(receita);
	}
	
	public List<Receita> getLista() {
		TypedQuery<Receita> query = 
				this.manager.createNamedQuery("Receita.findAll", Receita.class);
		
		return query.getResultList();
	}
	
}

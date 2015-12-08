package br.com.gerenciadorfinanceiro.modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gerenciadorfinanceiro.modelo.entidades.Conta;

public class ContaRepository {
	private EntityManager manager;
	
	public ContaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public Conta procura(Long id) {
		return this.manager.find(Conta.class, id);
	}
	
	public void adiciona(Conta conta) {
		this.manager.persist(conta);
	}
	
	public void remove(Long id) {
		Conta conta = this.procura(id);
		/*
		TypedQuery<Despesa> query = this.manager.createNamedQuery("Conta.buscaDespesas", Despesa.class);
		query.setParameter("id", conta.getId());
		
		
		List<Despesa> despesas = query.getResultList();
		DespesaRepository despesaRepository = new DespesaRepository(manager);
		for(Despesa despesa : despesas) {
			despesaRepository.remove(despesa.getId());			
		}
		
		TypedQuery<Receita> query1 = this.manager.createNamedQuery("Conta.buscaReceitas", Receita.class);
		query1.setParameter("id", conta.getId());
		
		List<Receita> receitas = query1.getResultList();
		ReceitaRepository receitaRepository = new ReceitaRepository(manager);
		for(Receita receita : receitas) {
			receitaRepository.remove(receita.getId());			
		}
		*/
		this.manager.remove(conta);

	}
	
	public Conta atualiza(Conta conta) {
		return this.manager.merge(conta);
	}
	
	public List<Conta> getList() {
		TypedQuery<Conta> query =
				this.manager.createNamedQuery("Conta.findAll", Conta.class);
		
		return query.getResultList();
	}
	
	
}

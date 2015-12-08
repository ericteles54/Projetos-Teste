package br.com.gerenciadorfinanceiro.modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.gerenciadorfinanceiro.modelo.entidades.Teste;

public class TesteRepository {

		private EntityManager manager;
		
		public TesteRepository (EntityManager manager) {
			this.manager = manager;
		}
		
		public void adiciona(Teste teste) {
			this.manager.persist(teste);
		}
		
		public List<Teste> buscaTodos() {
			Query query = this.manager.createQuery("select t from Teste t");
			return query.getResultList();
		}
}

package modelo.repositorios;

import javax.persistence.EntityManager;
import modelo.entidades.Conta;

public class ContaRepository {

	private EntityManager manager;
	
	public ContaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Conta conta) {
		this.manager.persist(conta);
	}	
}

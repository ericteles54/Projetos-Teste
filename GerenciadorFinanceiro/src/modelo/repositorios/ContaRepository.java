package modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.entidades.Conta;

public class ContaRepository {

	private EntityManager manager;
	
	public ContaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionaConta(Conta conta) {
		this.manager.persist(conta);
	}
	
	public void removeConta(Conta conta) {
		this.manager.remove(conta);
	}
	
	public List<Conta> listaTodasPorUsuario(Long usuario_id) {
		
		TypedQuery<Conta> query = this.manager.createNamedQuery("Conta.buscaPorUsuario", Conta.class);
		query.setParameter("usuario_id", usuario_id);
		
		List<Conta> contas;
		try {
			contas = query.getResultList();
		} catch (Exception e) {
			contas = null;
		}
		
		return contas;
	}
}

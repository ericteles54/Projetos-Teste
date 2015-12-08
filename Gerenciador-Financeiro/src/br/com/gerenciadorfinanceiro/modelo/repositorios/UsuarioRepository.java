package br.com.gerenciadorfinanceiro.modelo.repositorios;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gerenciadorfinanceiro.modelo.entidades.Usuario;

public class UsuarioRepository {
	
	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public Usuario procura(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public void adiciona(Usuario usuario) {
		this.manager.persist(usuario);
	}
	
	public void remove(Long id) {
		Usuario usuario = this.procura(id);
		/*
		TypedQuery<Conta> query = this.manager.createNamedQuery("Usuario.buscaContas", Conta.class);
		query.setParameter("id", usuario.getId());
		
		List<Conta> contas = query.getResultList();
		ContaRepository contaRepository = new ContaRepository(manager);
		for(Conta conta : contas) {
			contaRepository.remove(conta.getId());			
		}
		*/
		this.manager.remove(usuario);
		

	}
	
	public Usuario atualiza(Usuario usuario) {
		return this.manager.merge(usuario);
	}
	
	public List<Usuario> getLista() {
		TypedQuery<Usuario> query = 
				this.manager.createNamedQuery("Usuario.findAll", Usuario.class);
		
		return query.getResultList();
	}
	
}

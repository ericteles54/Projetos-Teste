package modelo.repositorios;

import javax.persistence.EntityManager;

import modelo.entidades.Usuario;

public class UsuarioRepository {

	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Usuario usuario) {
		this.manager.persist(usuario);
	}
}

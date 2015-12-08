package modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.entidades.Usuario;

public class UsuarioRepository {

	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Usuario usuario) {
		this.manager.persist(usuario);
	}
	
	public List<Usuario> listaUsuarios() {
		Query query = this.manager.createQuery("select u from Usuario u");
		return query.getResultList();
	}
}

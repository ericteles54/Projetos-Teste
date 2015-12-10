package modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.conversores.CryptoConverter;
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
		TypedQuery<Usuario> query = this.manager.createNamedQuery("Usuario.buscaTodos", Usuario.class);
		
		List<Usuario> usuarios;
		try {
			usuarios = query.getResultList();			
		} catch (Exception e) {
			usuarios = null;
		}
				
		return usuarios;
	}
	
	public Usuario buscaUsuarioPorUsername(String username) {		
		TypedQuery<Usuario> query = this.manager.createNamedQuery("Usuario.buscaPorUsername", Usuario.class)
				.setParameter("username", username);
		
		Usuario usuario = new Usuario();
		try {
			usuario = query.getSingleResult();
		} catch (Exception e) {
			
		}		
		
		return usuario;
	}
	
	public boolean autenticaUsuario(String username, String password) {
		
		CryptoConverter cryptoConverter = new CryptoConverter();
		String passwordEncrypt = cryptoConverter.convertToDatabaseColumn(password);
		password = passwordEncrypt;
		
		// Busca no banco de dados o username informado				
		Usuario usuario = this.buscaUsuarioPorUsername(username);
				
		// Verifica usuario e senha informados e retorna home do usuário ou tela de login		
		if (username.equals(usuario.getUsername())
				&& (password.equals(usuario.getPassword()))) {
			
						
			return true;
			
		} else {
			System.out.println("Username: \"" + username + "\" ou password informado são inválidos");
									
			return false;
		}		
	}
}

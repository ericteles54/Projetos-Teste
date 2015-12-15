package modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import modelo.conversores.CryptoConverterPassword;
import modelo.entidades.Conta;
import modelo.entidades.Usuario;

public class UsuarioRepository {

	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adicionaUsuario(Usuario usuario) {
		this.manager.persist(usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		/*
		 * Remove as contas relacionadas ao usuário antes de remover o usuário
		 */
		ContaRepository contaRepository = new ContaRepository(this.manager);
		List<Conta> contas = contaRepository.listaTodasPorUsuario(usuario.getId());
		
		// Remove as contas vinculadas
		for (Conta conta : contas) {
			this.manager.remove(conta);
		}
		
		// Remove o usuário
		this.manager.remove(usuario);
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
	
	public Usuario buscaUsuarioPorId(Long id) {
		
		Query query = this.manager.createNamedQuery("Usuario.buscaPorIdSemPassword").
				setParameter("id", id);
		
		Usuario usuario = new Usuario();
		try {
			Object[] object = (Object[]) query.getSingleResult();
			usuario.setId((Long)object[0]);
			usuario.setNome((String)object[1]);
			usuario.setUsername((String)object[2]);
			usuario.setPassword(null);
		} catch (Exception e) {
		
		}
		
		return usuario;
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
	
	public Long autenticaUsuario(String username, String password) {
		
		CryptoConverterPassword cryptoConverter = new CryptoConverterPassword();
		String passwordEncrypt = cryptoConverter.convertToDatabaseColumn(password);
		password = passwordEncrypt;
		
		// Busca no banco de dados o username informado				
		Usuario usuario = this.buscaUsuarioPorUsername(username);
				
		// Verifica usuario e senha informados e retorna home do usuário ou tela de login		
		if (username.equals(usuario.getUsername())
				&& (password.equals(usuario.getPassword()))) {
			
						
			return usuario.getId();
			
		} else {
			System.out.println("Username: \"" + username + "\" ou password informado são inválidos");
									
			return null;
		}		
	}
}

package controle;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.resteasy.util.Base64;

import modelo.entidades.Usuario;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
@SessionScoped
public class AutenticaUsuarioBean {
	
	private String username = new String();	
	
	private String password = new String();	
	
	public String autentica() {
		
		// Converte senha informada em senha encryptada
		final String ALGORITHM = "AES/ECB/PKCS5Padding";
		final byte[] KEY = "wjf7dnc*3dh1bcfu".getBytes();
		
		// do some Encryption
		Key key = new SecretKeySpec(KEY, "AES");
		
		String passwordEncrypt = new String();			
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
					
			passwordEncrypt = Base64.encodeBytes(c.doFinal(this.password.getBytes()));
			this.password = passwordEncrypt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
				
		// Busca no banco de dados o username informado
		EntityManager manager = this.geEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);		
		Usuario usuario = usuarioRepository.buscaUsuarioPorUsername(this.username);		
				
		// Verifica usuario e senha informados e retorna home do usuário ou tela de login		
		if (this.username.equals(usuario.getUsername())
				&& (this.password.equals(usuario.getPassword()))) {
			
			ExternalContext externalContext = this.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(false);
			session.setAttribute("username", this.username);
			
			return "/home";
			
		} else {
			System.out.println("Username: \"" + this.username + "\" ou password informado são inválidos");
									
			return "/login";
		}		
	}
	
	private ExternalContext getExternalContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		return externalContext;
	}
	
	private EntityManager geEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}	

	



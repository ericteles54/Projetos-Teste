package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.repositorios.UsuarioRepository;

@ManagedBean
@SessionScoped
public class UsuarioAutenticacaoBean {
	
	private String username = new String();	
	
	private String password = new String();	
	
	public String autentica() {
				
		EntityManager manager = this.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);		
		
		// Verifica usuario e senha informados e retorna home do usuário ou tela de login	
		Long id = usuarioRepository.autenticaUsuario(this.username, this.password);
		if (id != null) {
			
			ExternalContext externalContext = this.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(false);
			session.setAttribute("username", this.username);
			session.setAttribute("id", id);
			
						
			return "/area-restrita/home";
			
		} else {
			
			return "/login";
		}		
	}
	
	private ExternalContext getExternalContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		return externalContext;
	}
	
	private EntityManager getEntityManager() {
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

	



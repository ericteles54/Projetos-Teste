package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import modelo.entidades.Usuario;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	
	public void adicionaUsuario() {
		EntityManager manager = this.geEntityManager();
		
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		usuarioRepository.adiciona(this.usuario);
		
		this.usuario = new Usuario();
	}

	private EntityManager geEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

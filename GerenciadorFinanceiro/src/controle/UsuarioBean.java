package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import modelo.entidades.Usuario;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;

	public List<Usuario> getUsuarios() {
		EntityManager manager = this.getEntityManager();	
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		this.usuarios = usuarioRepository.listaUsuarios();		
		
		return this.usuarios;
	}

	private EntityManager getEntityManager() {
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

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}

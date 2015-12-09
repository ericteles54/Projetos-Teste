package controle;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import modelo.entidades.Usuario;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
@RequestScoped
public class CadastraUsuarioBean {
	
	private Usuario usuario = new Usuario();	

	public void adicionaUsuario() {
		EntityManager manager = this.getEntityManager();
		
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		usuarioRepository.adiciona(this.usuario);
		
		FacesMessage mensagem = new FacesMessage(
				"O usuário " + this.usuario.getNome() + " foi adicionado com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		
		this.usuario = new Usuario();		
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
}

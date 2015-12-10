package controle;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import modelo.entidades.Usuario;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
@RequestScoped
public class UsuarioCadastrametnoBean {
	
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
	
	public void validaUsername(ComponentSystemEvent event) {
		
		UIComponent source = event.getComponent();
		UIInput usernameInput = (UIInput) source.findComponent("campo-username");
		
		EntityManager manager = this.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		
		String username = (String)usernameInput.getLocalValue();
		Usuario usuario = usuarioRepository.buscaUsuarioPorUsername(username);
		
		
		if(usuario.getUsername() != null) {
			FacesMessage mensagem = new FacesMessage(
					"O username: \"" + username + "\" já existe.");
			
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(source.getClientId(), mensagem);
			context.renderResponse();				
		}		
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

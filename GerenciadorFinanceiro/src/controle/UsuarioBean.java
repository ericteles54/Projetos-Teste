package controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import modelo.entidades.Usuario;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Usuario usuarioAutenticado;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	
	public UsuarioBean() {
		
		ExternalContext externalContext = this.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		Long id = (Long)session.getAttribute("usuario_id");
		
		EntityManager manager = this.getEntityManager();
		
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		this.setUsuarioAutenticado(usuarioRepository.buscaUsuarioPorId(id));	

	}

	public List<Usuario> getUsuarios() {
		EntityManager manager = this.getEntityManager();	
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		this.usuarios = usuarioRepository.listaUsuarios();
		
		return this.usuarios;
	}
	
	public String registraSaida() {				
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.invalidate();		
		
		return "/login";
	}
	
	public void removeUsuario(Usuario usuario) {
				
		EntityManager manager = this.getEntityManager();	
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		
		usuarioRepository.removeUsuario(usuario);
		
		FacesMessage mensagem = new FacesMessage(
				"O usuário: " + usuario.getNome() + " foi removido com sucesso","");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
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
	
		
	/*
	 * Tratador de eventos da tabela de gerenciamento de Usuarios 
	 * 
	 */
	public void posEdicaoColuna(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("O usuário: " + (String)((Usuario) event.getObject()).getNome() + " foi editado.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
		
	public void cancelamentoEdicaoColuna(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelada edição do usuário: " + (String)((Usuario) event.getObject()).getNome(), "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void iniciaEdicaoColuna(RowEditEvent event) {
        this.usuario.setPassword("");
        FacesMessage msg = new FacesMessage("Editando usuário: " + (String)((Usuario) event.getObject()).getNome(), "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }	
	/*
	 *  Fim Tratador de eventos da tabela de gerenciamento de Usuários
	 */
		
	
	/*
	 * GETTERS AND SETTERS 
	 */
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}
}

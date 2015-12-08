package br.com.gerenciadorfinanceiro.controle;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gerenciadorfinanceiro.modelo.entidades.Usuario;
import br.com.gerenciadorfinanceiro.modelo.repositorios.UsuarioRepository;

@ManagedBean
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	
	private EntityManager getManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}
		
	public void preparaAlteracao() {
		Map<String,String> params =
			FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		this.usuario = usuarioRepository.procura(id);
	}
	
	public void adiciona() {
		EntityManager manager = this.getManager();
		
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		if(this.usuario.getId() == null) {
			usuarioRepository.adiciona(this.usuario);
		} else {
			usuarioRepository.atualiza(this.usuario);
		}
				
		this.usuario = new Usuario();
		this.usuarios = null;
	}
	
	public void remove() {
		Map<String,String> params =
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		usuarioRepository.remove(id);
		
		this.usuarios = null;
	}
	
	public List<Usuario> getUsuarios() {
		if(this.usuarios == null) {
			EntityManager manager = this.getManager();
			UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
			
			this.usuarios = usuarioRepository.getLista();
		}
		return this.usuarios;
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

package controle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.entidades.Conta;
import modelo.entidades.Usuario;
import modelo.repositorios.ContaRepository;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
@SessionScoped
public class ContaBean {

	private Conta conta = new Conta();		

	public void adicionaConta() {
		
		ExternalContext externalContext = this.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);		
		String username = (String)session.getAttribute("username");
		
		EntityManager manager = this.getEntityManager();
				
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		Usuario usuario = usuarioRepository.buscaUsuarioPorUsername(username);
		this.conta.setUsuario(usuario);
		
		ContaRepository contaRepository = new ContaRepository(manager);
		contaRepository.adiciona(this.conta);
				
		FacesMessage mensagem = new FacesMessage(
				"A conta " + this.conta.getBanco() + ": " + this.conta.getNome() + " foi adicionada com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		
		this.conta = new Conta();
	}
	
	private ExternalContext getExternalContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		return externalContext;
	}
	
	private  EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}	
}

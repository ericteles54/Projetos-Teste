package controle;

import java.util.Calendar;
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

import modelo.entidades.Conta;
import modelo.entidades.Usuario;
import modelo.repositorios.ContaRepository;
import modelo.repositorios.UsuarioRepository;

@ManagedBean
@SessionScoped
public class ContaBean {

	private Usuario usuarioAutenticado;
	private Conta conta = new Conta();
	private List<Conta> contas;
	private Conta contaSelecionada = new Conta();
	
	public ContaBean() {
		this.conta.setDataCriacao(Calendar.getInstance().getTime());
		
		ExternalContext externalContext = this.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		Long id = (Long)session.getAttribute("usuario_id");
		
		EntityManager manager = this.getEntityManager();
		
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		this.setUsuarioAutenticado(usuarioRepository.buscaUsuarioPorId(id));
	}

	public void adicionaConta(Usuario usuario) {
						
		this.conta.setUsuario(usuario);
		
		EntityManager manager = this.getEntityManager();
		ContaRepository contaRepository = new ContaRepository(manager);
		contaRepository.adicionaConta(this.conta);
				
		FacesMessage mensagem = new FacesMessage(
				"A conta " + this.conta.getBanco() + ": " + this.conta.getNome() + " foi adicionada com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		
		this.conta = new Conta();
	}
	
	public void removeConta(Conta conta) {
		
		EntityManager manager = this.getEntityManager();	
		ContaRepository contaRepository = new ContaRepository(manager);
		
		contaRepository.removeConta(conta);
		
		FacesMessage mensagem = new FacesMessage(
				"A conta: " + conta.getBanco() + " - " + conta.getNome() + " foi removida com sucesso","");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public List<Conta> listaTodasPorUsuario(Usuario usuario) {
		
		EntityManager manager = this.getEntityManager();
		ContaRepository contaRepository = new ContaRepository(manager);
		
		this.contas = contaRepository.listaTodasPorUsuario(usuario.getId());
		
		return this.contas;
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
	
	/*
	 * Tratador de eventos da tabela de gerenciamento de Contas 
	 * 
	 */
	public void posEdicaoColuna(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("A conta: " + (String)((Conta) event.getObject()).getBanco() + " - " + (String)((Conta) event.getObject()).getNome() + " foi editada.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
		
	public void cancelamentoEdicaoColuna(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelada edição da conta: " + (String)((Conta) event.getObject()).getBanco() + " - " + (String)((Conta) event.getObject()).getNome(), "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void iniciaEdicaoColuna(RowEditEvent event) {        
        FacesMessage msg = new FacesMessage("Editando conta: " + (String)((Conta) event.getObject()).getBanco() + " - " + (String)((Conta) event.getObject()).getNome(), "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String verDetalhesDaConta(Conta conta) {
		
		ExternalContext externalContext = this.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.setAttribute("conta_selecionada", conta);		
		
		return "/area-restrita/movimentacao/cadastra-movimentacao";
	}
	
	/*
	 *  Fim Tratador de eventos da tabela de gerenciamento de Contas
	 */
		
	
	/*
	 * GETTERS AND SETTERS 
	 */
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}	

	public List<Conta> getContas() {
		return contas;
	}
	
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}	
}

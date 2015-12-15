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
import modelo.entidades.Receita;
import modelo.repositorios.MovimentacaoRepository;

@ManagedBean
@SessionScoped
public class MovimentacaoBean {
	
	private Conta contaSelecionada;
	private Receita receita = new Receita();
	
	public MovimentacaoBean() {
		ExternalContext externalContext = this.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		Conta conta = (Conta)session.getAttribute("conta_selecionada");
		
		this.contaSelecionada = conta;
	}
	
	public void adicionaReceita() {
		this.receita.setConta(this.contaSelecionada);
		
		EntityManager manager = this.getEntityManager();
		MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository(manager);
				
		movimentacaoRepository.adicionaMovimentacao(this.receita);
		
		FacesMessage mensagem = new FacesMessage(
				"A receita " + this.receita.getDescricao() + " - Valor:  " + this.receita.getValor() + " foi adicionada com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
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

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}
	
	
}

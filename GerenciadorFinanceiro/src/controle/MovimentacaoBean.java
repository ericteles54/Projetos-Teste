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

import modelo.entidades.Conta;
import modelo.entidades.Despesa;
import modelo.entidades.Movimentacao;
import modelo.entidades.Receita;
import modelo.repositorios.MovimentacaoRepository;

@ManagedBean
@SessionScoped
public class MovimentacaoBean {
	
	private Conta contaSelecionada;
	private Receita receita = new Receita();
	private Despesa despesa = new Despesa();
	private List<Movimentacao> movimentacoes;
	
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
	
	public void adicionaDespesa() {
		this.despesa.setConta(this.contaSelecionada);
		
		EntityManager manager = this.getEntityManager();
		MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository(manager);
				
		movimentacaoRepository.adicionaMovimentacao(this.despesa);
		
		FacesMessage mensagem = new FacesMessage(
				"A despesa " + this.despesa.getDescricao() + " - Valor:  " + this.despesa.getValor() + " foi adicionada com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public void removeMovimentacao(Movimentacao movimentacao) {
		EntityManager manager = this.getEntityManager();
		MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository(manager);
		movimentacaoRepository.removeMovimentacao(movimentacao);
		
		FacesMessage mensagem = new FacesMessage(
				"A movimentacao: " + movimentacao.getDescricao() + " - Valor: " + movimentacao.getValor() + " foi removida com sucesso","");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public List<Movimentacao> buscaTodasPorConta(Conta conta) {
		
		EntityManager manager = this.getEntityManager();
		MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository(manager);
		this.movimentacoes = movimentacaoRepository.buscaTodasPorConta(conta.getId());
		
		return this.movimentacoes;		
	}
	
	
	/*
	 * Tratador de eventos da tabela de gerenciamento de Movimentações
	 * 
	 */
	public void posEdicaoColuna(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("A movimentacao: " + (String)((Movimentacao) event.getObject()).getDescricao() + " - " + (Double)((Movimentacao) event.getObject()).getValor() + " foi editada.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
		
	public void cancelamentoEdicaoColuna(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelada edição da movimentacao: " + (String)((Movimentacao) event.getObject()).getDescricao() + " - " + (Double)((Movimentacao) event.getObject()).getValor(), "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void iniciaEdicaoColuna(RowEditEvent event) {        
        FacesMessage msg = new FacesMessage("Editando Movimentacao: " + (String)((Movimentacao) event.getObject()).getDescricao() + " - " + (Double)((Movimentacao) event.getObject()).getValor(), "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	/*
	 * Fim Tratador de eventos da tabela de gerenciamento de Movimentações 
	 * 
	 */
	
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

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	
}

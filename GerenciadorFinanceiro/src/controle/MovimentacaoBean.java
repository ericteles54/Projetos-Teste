package controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import modelo.entidades.Despesa;
import modelo.entidades.Movimentacao;
import modelo.entidades.Receita;
import modelo.repositorios.MovimentacaoRepository;

@ManagedBean
@SessionScoped
public class MovimentacaoBean {

	private Movimentacao receita = new Receita();
	private Movimentacao despesa = new Despesa();
	
	private List<Receita> receitas;
	
	public void adicionaReceita(Movimentacao movimentacao) {				
		EntityManager manager = this.getEntityManager();
		
		MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository(manager);
		movimentacaoRepository.adiciona(movimentacao);
		
		
		FacesMessage mensagem = new FacesMessage("A despesa foi adicionada com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		
		this.receita = new Receita();
	}
	
	public List<Receita> getReceitas() {
		EntityManager manager = this.getEntityManager();
		MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository(manager);		
		
		this.receitas = movimentacaoRepository.listaTodasReceitas(1L);
		
		return this.receitas;
	}
	
	private  EntityManager getEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		return manager;
	}

	public Movimentacao getReceita() {
		return receita;
	}

	public void setReceita(Movimentacao receita) {
		this.receita = receita;
	}

	public Movimentacao getDespesa() {
		return despesa;
	}

	public void setDespesa(Movimentacao despesa) {
		this.despesa = despesa;
	}

	public void setMovimentacoes(List<Receita> receitas) {
		this.receitas = receitas;
	}
	
	
	
}

package br.com.gerenciadorfinanceiro.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gerenciadorfinanceiro.modelo.entidades.Teste;
import br.com.gerenciadorfinanceiro.modelo.repositorios.TesteRepository;

@ManagedBean
public class TesteBean {
	private Teste teste = new Teste();
	
	public void adicionaTeste(Teste teste) {
		EntityManager manager = this.getEntityManager();
		TesteRepository repository = new TesteRepository(manager);
		
		repository.adiciona(teste);
		this.teste = new Teste();
	}

	public List<Teste> getTestes() {
		EntityManager manager = this.getEntityManager();
		TesteRepository repository = new TesteRepository(manager);
		
		return repository.buscaTodos();
	}
	
	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager)request.getAttribute("EntityManager");
		
		return manager;
	}

	public Teste getTeste() {
		return teste;
	}

	public void setTeste(Teste teste) {
		this.teste = teste;
	}
	
	
}

package managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Eletrodomestico;
import util.JPAUtil;

@ManagedBean
@RequestScoped
public class EletrodomesticoBean {

	private Eletrodomestico eletrodomestico = new Eletrodomestico();
	private List<Eletrodomestico> eletrodomesticos = null;
	
	public void salvar(Eletrodomestico eletrodomestico) {
		EntityManager manager = JPAUtil.getEntityManager();	
		
		manager.getTransaction().begin();
		manager.remove(manager.merge(eletrodomestico));
		manager.getTransaction().commit();
		manager.close();
		
		FacesMessage facesMessage = new FacesMessage("Eletrodomestico salvo com sucesso.");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		
	}
	
	public void remover(Eletrodomestico eletrodomestico) {
		EntityManager manager = JPAUtil.getEntityManager();
		
		manager.getTransaction().begin();
		manager.remove(eletrodomestico);
		manager.getTransaction().commit();
		manager.close();
		
		FacesMessage facesMessage = new FacesMessage("Eletrodomestico removido com sucesso.");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	
	public Eletrodomestico getEletrodomestico() {
		return eletrodomestico;
	}

	public void setEletrodomestico(Eletrodomestico eletrodomestico) {
		this.eletrodomestico = eletrodomestico;
	}

	public List<Eletrodomestico> getEletrodomesticos() {
		if(this.eletrodomesticos == null) {
			EntityManager manager = JPAUtil.getEntityManager();
			Query query = manager.createQuery("select e from Eletrodomestico e", Eletrodomestico.class);
			this.eletrodomesticos = query.getResultList();
			
			return this.eletrodomesticos;
		} else {
			return eletrodomesticos;
		}
	}

	public void setEletrodomesticos(List<Eletrodomestico> eletrodomesticos) {
		this.eletrodomesticos = eletrodomesticos;
	}
	
	
}

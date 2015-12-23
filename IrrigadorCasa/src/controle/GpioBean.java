package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.repositorios.GerenciaEstadoBomba;

@ManagedBean
@SessionScoped
public class GpioBean {

	private GerenciaEstadoBomba bomba = new GerenciaEstadoBomba();
	private String estadoBomba;
	private String estadoValvula;
		
	public void ligaBomba() {
		this.bomba.ligaBomba();
	}
	
	public void desligaBomba() {
		this.bomba.desligaBomba();
	}
	
	public void verificaEstadoBomba() {
		if(this.bomba.bombaLigada()) {
			this.setEstadoBomba("LIGADA");
		} else {
			this.setEstadoBomba("DESLIGADA");
		}
	}
	
	public void verificaEstadoValvula() {
		if(this.bomba.valvulaAberta()) {
			this.setEstadoValvula("ABERTA");
		} else {
			this.setEstadoValvula("FECHADA");
		}
	}
	
	public void fechaGpio() {
		this.bomba.fechaGpio();
	}

	public String getEstadoBomba() {
		return estadoBomba;
	}

	public void setEstadoBomba(String estadoBomba) {
		this.estadoBomba = estadoBomba;
	}

	public String getEstadoValvula() {
		return estadoValvula;
	}

	public void setEstadoValvula(String estadoValvula) {
		this.estadoValvula = estadoValvula;
	}	
}

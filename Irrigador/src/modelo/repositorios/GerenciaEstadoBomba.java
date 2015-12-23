package modelo.repositorios;

import modelo.entidades.Bomba;
import modelo.entidades.Valvula;

public class GerenciaEstadoBomba {
	
	private Bomba bomba;
	private Valvula valvula;
	
	
	public GerenciaEstadoBomba() {
		this.bomba = new Bomba("Bomba");
		this.valvula = new Valvula("Valvula");
	}	
	
	public void ligaBomba() throws InterruptedException {
		this.bomba.getPinoBomba().high();
		Thread.sleep(3000);
		this.valvula.getPinoValvula().high();
	}
	
	public void desligaBomba() throws InterruptedException {
		this.valvula.getPinoValvula().low();
		Thread.sleep(3000);
		this.bomba.getPinoBomba().low();
	}
	
	// Retorna true se a bomba estiver ligada
	public boolean bombaLigada() {
		return this.bomba.getPinoBomba().isHigh();
	}
	
	// Retorna true se a Valvula estiver aberta
	public boolean valvulaAberta() {
		return this.valvula.getPinoValvula().isHigh();	
	}

}

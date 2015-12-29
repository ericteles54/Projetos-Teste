package modelo.repositorios;

import modelo.hardwares.Rele2Canais;

public class GerenciaEstadoBomba {

	private Rele2Canais rele2Canais;
	
	public GerenciaEstadoBomba() {
		this.rele2Canais = new Rele2Canais();
	}
	
	public void ligaBomba() {		
		this.rele2Canais.ligaBomba();		
	}
	
	public void desligaBomba() {
		this.rele2Canais.desligaBomba();	
	}
	
	public boolean bombaLigada() {
		return this.rele2Canais.bombaLigada();		
	}
	
	public boolean valvulaAberta() {
		return this.rele2Canais.valvulaAberta();
	}	
}

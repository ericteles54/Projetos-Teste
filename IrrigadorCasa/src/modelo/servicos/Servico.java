package modelo.servicos;

import modelo.repositorios.GerenciaEstadoBomba;

public class Servico implements Runnable {
	
	private boolean servicoIniciado = true;
		
	@Override
	public void run() {	
				
		while(this.isServicoIniciado()) {
			
			System.out.println("**** Alternando estado da bomba através do serviço *****");
			GerenciaEstadoBomba gb = new GerenciaEstadoBomba();
			if (gb.bombaLigada()) {
				gb.desligaBomba();
			} else {
				gb.ligaBomba();
			}
			
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}		
	}

	public boolean isServicoIniciado() {
		return servicoIniciado;
	}

	public void setServicoIniciado(boolean servicoIniciado) {
		this.servicoIniciado = servicoIniciado;
	}
}

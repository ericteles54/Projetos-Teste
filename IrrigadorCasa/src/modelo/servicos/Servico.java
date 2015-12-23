package modelo.servicos;

public class Servico implements Runnable {
	
	private boolean servicoIniciado = true;
		
	@Override
	public void run() {	
				
		while(this.isServicoIniciado()) {
			System.out.println("*********** Executando serviço através da Thread **********");
			
			
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

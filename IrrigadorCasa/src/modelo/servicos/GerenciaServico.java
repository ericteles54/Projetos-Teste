package modelo.servicos;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import modelo.repositorios.Gpio;

public class GerenciaServico implements SystemEventListener {
	
	private Servico servico = new Servico();	

	@Override
	public boolean isListenerForSource(Object source) {
		// only for application
		return (source instanceof Application);
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		
		if(event instanceof PostConstructApplicationEvent) {
			System.out.println("Executando método de PostConstructApplicationEvent da Classe GerenciaServiço");
			
			System.out.println("Instanciando a GPIO");
			Gpio.getInstance();
			
			System.out.println("Iniciando serviço de monitoramento do sistema");			
			new Thread(this.servico).start();
		}
		
		if(event instanceof PreDestroyApplicationEvent) {
			System.out.println("Executando método de PreDestroyApplicationEvent da Classe GerenciaServiço");			
						
			System.out.println("****** Parando serviço de monitoramento do sistema *******");
			this.servico.setServicoIniciado(false);
			
			System.out.println("Finalizando a GPIO");
			Gpio.getInstance().fechaGpio();
		}		
	}
}

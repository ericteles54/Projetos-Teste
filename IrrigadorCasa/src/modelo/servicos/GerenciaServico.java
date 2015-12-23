package modelo.servicos;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import modelo.repositorios.Gpio;

public class GerenciaServico implements SystemEventListener {
	
	private Servico servico = new Servico();
	private Gpio gpio = new Gpio();


	@Override
	public boolean isListenerForSource(Object source) {
		// only for application
		return (source instanceof Application);
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		
		if(event instanceof PostConstructApplicationEvent) {
			System.out.println("Execução do método de PostConstructApplicationEvent");
			
			/*
			 * Configura os pinos da GPIO na inicialização do sistema.
			 */
			/////////////////////////////////////////////////////////////////////////////
			System.out.println("Configurando GPIO...");
			this.gpio.setBomba(this.gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_01, "Bomba", PinState.LOW));
			this.gpio.setValvula(this.gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_04, "Valvula", PinState.LOW));
			
			this.gpio.getBomba().setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
			this.gpio.getValvula().setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
			////////////////////////////////////////////////////////////////////////////
			
			
			this.iniciaServico();
		}
		
		if(event instanceof PreDestroyApplicationEvent) {
			System.out.println("Execução do método de PreDestroyApplicationEvent");
			
			/*
			 * Desliga a GPIO
			 */
			System.out.println("Desativando a GPIO...");
			this.gpio.getGpio().shutdown();
			
			
			this.paraServico();
		}		
	}
	
	public void iniciaServico() {
		System.out.println("****** Iniciando serviço de monitoramento do sistema *******");
		new Thread(this.servico).start();		
	}
	
	public void paraServico() {
		System.out.println("****** Parando serviço de monitoramento do sistema *******");
		this.servico.setServicoIniciado(false);
	}
}

package modelo.repositorios;

import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import controle.Gpio;

public class GerenciaEstadoBomba {

	private Gpio gpio = new Gpio();
	
	public GerenciaEstadoBomba() {
		/*
		this.gpio.setBomba(this.gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_01, "Bomba", PinState.LOW));
		this.gpio.setValvula(this.gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_04, "Valvula", PinState.LOW));
		
		this.gpio.getBomba().setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		this.gpio.getValvula().setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		*/
	}
	
	public void ligaBomba() {		
		try {
			this.gpio.getBomba().high();
			Thread.sleep(3000);
			this.gpio.getValvula().high();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void desligaBomba() {
		try {		
			this.gpio.getValvula().low();
			Thread.sleep(3000);
			this.gpio.getBomba().low();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public boolean bombaLigada() {
		return this.gpio.getBomba().isHigh();		
	}
	
	public boolean valvulaAberta() {
		return this.gpio.getValvula().isHigh();
	}
	
	public void fechaGpio() {
		this.gpio.getGpio().shutdown();
	}
}

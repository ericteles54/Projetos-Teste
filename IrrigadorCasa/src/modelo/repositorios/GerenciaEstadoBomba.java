package modelo.repositorios;

import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GerenciaEstadoBomba {

	private Gpio gpio = new Gpio();
	
	public GerenciaEstadoBomba() {
		
		this.gpio.setBomba(this.gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_01, "Bomba", PinState.HIGH));
		this.gpio.setValvula(this.gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_04, "Valvula", PinState.HIGH));
		
		this.gpio.getBomba().setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
		this.gpio.getValvula().setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
		
	}
	
	public void ligaBomba() {		
		try {
			this.gpio.getBomba().low();
			Thread.sleep(3000);
			this.gpio.getValvula().low();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void desligaBomba() {
		try {		
			this.gpio.getValvula().high();
			Thread.sleep(3000);
			this.gpio.getBomba().high();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public boolean bombaLigada() {
		return this.gpio.getBomba().isLow();		
	}
	
	public boolean valvulaAberta() {
		return this.gpio.getValvula().isLow();
	}
	
	public void fechaGpio() {
		this.gpio.getGpio().shutdown();
	}
}

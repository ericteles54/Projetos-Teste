package modelo.entidades;

import java.io.Serializable;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Valvula implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 954695667170211082L;	
	
	private GpioPinDigitalOutput pinoValvula;
	
	public Valvula() {
		
	}
	
	public Valvula(String apelido) {
		super();
		
		// Provisiona o pino 02 como OUTPUT e deixa ele desligado
		this.setPinoValvula(Gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_02, apelido, PinState.LOW));
				
		// Configura o pino para ficar desligado quando for feito o Shutdown do pino
		this.getPinoValvula().setShutdownOptions(true, PinState.LOW);
	}

	public GpioPinDigitalOutput getPinoValvula() {
		return pinoValvula;
	}

	public void setPinoValvula(GpioPinDigitalOutput pinoValvula) {
		this.pinoValvula = pinoValvula;
	}	
}

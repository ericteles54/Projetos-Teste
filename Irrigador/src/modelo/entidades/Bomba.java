package modelo.entidades;

import java.io.Serializable;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Bomba implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 944884168300173672L;
	
	private GpioPinDigitalOutput pinoBomba;
	
	public Bomba() {
		
	}
	
	public Bomba(String apelido) {
		super();
		
		// Provisiona o pino 01 como OUTPUT e deixa ele desligado
		this.setPinoBomba(Gpio.getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_01, apelido, PinState.LOW));
		
		// Configura o pino para ficar desligado quando for feito o Shutdown do pino
		this.getPinoBomba().setShutdownOptions(true, PinState.LOW);
	}

	public GpioPinDigitalOutput getPinoBomba() {
		return pinoBomba;
	}

	public void setPinoBomba(GpioPinDigitalOutput pinoBomba) {
		this.pinoBomba = pinoBomba;
	}	
}

package modelo.repositorios;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Gpio {

	private static Gpio uniqueInstance;
	
	private GpioController gpio;
	private GpioPinDigitalOutput bomba;
	private GpioPinDigitalOutput valvula;
	private GpioPinDigitalInput fluxoAgua;
	private GpioPinDigitalOutput trigPinDistanceSensor;
	private GpioPinDigitalInput echoPinDistanceSensor;
	
	
	private Gpio() {
		this.gpio = GpioFactory.getInstance();
		
		// Configurando a GPIO
		this.setBomba(this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Bomba", PinState.HIGH));
		this.setValvula(this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Valvula", PinState.HIGH));
			
		this.getBomba().setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
		this.getValvula().setShutdownOptions(true, PinState.HIGH, PinPullResistance.OFF);
				
        this.setTrigPinDistanceSensor(this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "trigPin", PinState.LOW));
        this.setEchoPinDistanceSensor(this.gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, "echoPin"));
                
        this.getTrigPinDistanceSensor().setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        this.getEchoPinDistanceSensor().setShutdownOptions(true);
	}
	
	public static synchronized Gpio getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Gpio();
		}
		
		return uniqueInstance;
	}
	
	public void fechaGpio() {
		this.gpio.shutdown();
	}	
	
	public GpioController getGpio() {
		return gpio;
	}

	public GpioPinDigitalOutput getBomba() {
		return bomba;
	}

	public void setBomba(GpioPinDigitalOutput bomba) {
		this.bomba = bomba;
	}

	public GpioPinDigitalOutput getValvula() {
		return valvula;
	}

	public void setValvula(GpioPinDigitalOutput valvula) {
		this.valvula = valvula;
	}

	public GpioPinDigitalInput getFluxoAgua() {
		return fluxoAgua;
	}

	public void setFluxoAgua(GpioPinDigitalInput fluxoAgua) {
		this.fluxoAgua = fluxoAgua;
	}

	public GpioPinDigitalOutput getTrigPinDistanceSensor() {
		return trigPinDistanceSensor;
	}

	public void setTrigPinDistanceSensor(GpioPinDigitalOutput trigPinDistanceSensor) {
		this.trigPinDistanceSensor = trigPinDistanceSensor;
	}

	public GpioPinDigitalInput getEchoPinDistanceSensor() {
		return echoPinDistanceSensor;
	}

	public void setEchoPinDistanceSensor(GpioPinDigitalInput echoPinDistanceSensor) {
		this.echoPinDistanceSensor = echoPinDistanceSensor;
	}	
}

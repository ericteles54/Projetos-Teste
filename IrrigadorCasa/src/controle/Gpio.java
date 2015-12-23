package controle;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Gpio {

	private static GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput bomba;
	private GpioPinDigitalOutput valvula;
	private GpioPinDigitalInput fluxoAgua;
	
	
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
}

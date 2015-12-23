package modelo.entidades;

import java.io.Serializable;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class Gpio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2806041493351462983L;
	
	private static GpioController gpio;
	
	public Gpio() {
		// Criate gpio controller
		Gpio.gpio = GpioFactory.getInstance();
	}	
	
	public void finalizaGpio() {
		Gpio.getGpio().shutdown();
	}

	public static GpioController getGpio() {
		return gpio;
	}

	public static void setGpio(GpioController gpio) {
		Gpio.gpio = gpio;
	}
}

package modelo.entidades;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class SensorFluxo implements Runnable {

	private GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalInput sensorFluxo = this.gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, "sensorFluxo", PinPullResistance.PULL_DOWN);
	
	public int contador = 0;
	
	private boolean rodando = true;
	
	@Override
	public void run() {
		while(this.rodando) {
			
			// Utiliza um listener para registrar todas as vezes que o pino fica em HIGH durante o tempo
			// de execução da função
			this.sensorFluxo.addListener(new GpioPinListenerDigital() {
				
				@Override
				public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
					if(event.getState().isHigh()) {
						contador++;
					}
				}
			});
		}
	}
	
	public void setRodando(boolean rodando) {
		this.rodando = rodando;
	}
}

package modelo.entidades;

import java.util.concurrent.TimeUnit;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/*
 * 
 * Essa classe foi criada a partir do exemplo em python do endereço abaixo
 * https://www.modmypi.com/blog/hc-sr04-ultrasonic-range-sensor-on-the-raspberry-pi
 * 
 * 
 */
public class HCSR04 {

	GpioController gpio = GpioFactory.getInstance();
	
	// Configura os pinos
	GpioPinDigitalOutput trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "trigger", PinState.LOW);
	GpioPinDigitalInput  echo    = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, "echo", PinPullResistance.PULL_DOWN);
	
	public long getDistancia() {
		System.out.println("Medição de distância em progresso...");
		
		this.trigger.low();
		
		System.out.println("Aguardando tempo do pino...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Iniciando a leitura
		System.out.println("Iniciando a leitura");			
		try {
			
			// Envia o sinal no pino Trigger
			// Coloca o trigger em HIGH por 10uS (8 ultrasound bursts at 40 kHz)
			this.trigger.high();
			TimeUnit.MICROSECONDS.sleep(10);
			this.trigger.low();
			
			
			// Escuta o pino de ECHO
			// O sensor emitirá uma onda sonora que ao encontrar um obstáculo 
			// rebaterá de volta em direção ao módulo, sendo que o neste tempo 
			// de emissão e recebimento do sinal o pino ECHO ficará em nivel alto. 
			// Logo o calcula da distância pode ser feito de acordo com o tempo em 
			// que o pino ECHO permaneceu em nível alto após o pino Trigger ter 
			// sido colocado em nível alto.
			long inicio = 0;
			long fim = 0;
			
			while (this.echo.isLow()) {
				inicio = System.nanoTime();
			}
			while (this.echo.isHigh()) {
				fim = System.nanoTime();
			}
			
			// Calcula a diferença entre "fim" e "inicio"
			long duracao_pulso = fim - inicio;
			
			
			// Calcula distância
			long distancia = 17150 * duracao_pulso;
			
			// Round
			distancia = Math.round(distancia);
			
			System.out.println("Distancia: " + distancia);			
			
			return distancia;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
}

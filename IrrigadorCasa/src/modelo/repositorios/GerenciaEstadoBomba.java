package modelo.repositorios;

public class GerenciaEstadoBomba {

	private Gpio gpio;
	
	public GerenciaEstadoBomba() {
		this.gpio = Gpio.getInstance();
	}
	
	public void ligaBomba() {		
		try {
			this.gpio.getBomba().low();
			Thread.sleep(5000);
			this.gpio.getValvula().low();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void desligaBomba() {
		try {		
			this.gpio.getValvula().high();
			Thread.sleep(5000);
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
}

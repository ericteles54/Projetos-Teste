package modelo.hardwares;

public class Rele2Canais {

	private Gpio gpio;
	
	public Rele2Canais() {
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

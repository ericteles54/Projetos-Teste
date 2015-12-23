package modelo.repositorios;

public class Reservatorio {
	private int aguaNoReservatorio;
	
	private DistanceMonitor monitor = new DistanceMonitor();
	
	
	public int calculaNivelDeAguaReservatorio() {
		// lógica para calcular o nível de água do reservatório através do sensor
		try {
			this.aguaNoReservatorio = Math.round(this.monitor.measureDistance());
		} catch (Exception e1) {
			System.out.println("Exceção na classe Reservatorio.");
            System.err.println( e1 );
		}
		
		try {
            Thread.sleep( DistanceMonitor.getWaitDurationInMillis() );
        } catch (InterruptedException ex) {
            System.err.println( "Interrupt during trigger" );
        }
		/*
		try {
			System.out.println("Disparando funcao para medir distancia pela classe Reservatorio");
			System.out.printf("%1$d,%2$.3f%n", System.currentTimeMillis(), this.monitor.measureDistance());
		} catch( Exception e ) {
			System.out.println("Exceção na classe Reservatorio.");
            System.err.println( e );
        }
		
        try {
            Thread.sleep( DistanceMonitor.getWaitDurationInMillis() );
        } catch (InterruptedException ex) {
            System.err.println( "Interrupt during trigger" );
        }
		*/
		
		return this.aguaNoReservatorio;
	}
}

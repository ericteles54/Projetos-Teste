package modelo.repositorios;

public class Reservatorio {
	private int medidaAguaReservatorioCm;
	
	private DistanceMonitor monitor = new DistanceMonitor();
	
	
	public int calculaNivelDeAguaReservatorio() {
		// lógica para calcular o nível de água do reservatório através do sensor
		long percentual = 0;
		
		try {
			int menor = Integer.MAX_VALUE;
			int maior = 0;
						
			
			int[] leituras = new int[5];
			for(int i = 0; i < 5; i++) {
				// Aguarda 2 segundos antes de fazer a leitura
				Thread.sleep(2000);
				
				leituras[i] = Math.round(this.monitor.measureDistance());
				
				if (leituras[i] < menor) {
					menor = leituras[i];
				}
				if (leituras[i] > maior) {
					maior = leituras[i];
				}
			}			
			/*
			System.out.println("Leituras: "
					+ "L1: " + leituras[0] + " - " 
					+ "L2: " + leituras[1] + " - " 
					+ "L3: " + leituras[2] + " - "
					+ "L4: " + leituras[3] + " - "
					+ "L5: " + leituras[4] + " - "
			);
			*/			
			
			int somatorio = 0;
			for (int leitura : leituras) {
				somatorio = somatorio + leitura;
			}
			this.medidaAguaReservatorioCm = (somatorio - maior - menor) / 3;
						
			// Calcula o percentual
			percentual = 100 - (3*(this.medidaAguaReservatorioCm - 15));
						
			
		} catch (Exception e1) {
			System.out.println("Exceção na classe Reservatorio.");
            System.err.println( e1 );
		}
		
		try {
            Thread.sleep( DistanceMonitor.getWaitDurationInMillis() );
        } catch (InterruptedException ex) {
            System.err.println( "Interrupt during trigger" );
        }		
		
		return Math.round(percentual);		
	}
}

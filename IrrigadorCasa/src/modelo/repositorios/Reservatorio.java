package modelo.repositorios;

public class Reservatorio {
	private int medidaAguaReservatorioCm;
	
	private DistanceMonitor monitor = new DistanceMonitor();
	
	
	public int calculaNivelDeAguaReservatorio() {
		// lógica para calcular o nível de água do reservatório através do sensor
		long percentual = 0;
		
		try {
			//this.aguaNoReservatorio = Math.round(this.monitor.measureDistance());
			//System.out.println("Leitura do sensor de distancia: " + this.aguaNoReservatorio);
			int menor = Integer.MAX_VALUE;
			int maior = 0;
						
			System.out.println("Iniciando medicoes: ");
			
			int l1 = Math.round(this.monitor.measureDistance());
			if (l1 < menor) {  
	                menor = l1;  
	        }
			if (l1 > maior) {  
	                maior = l1;  
	        }  
			Thread.sleep(1000);
			
			int l2 = Math.round(this.monitor.measureDistance());
			if (l2 < menor) {  
                menor = l2;  
			}
			if (l2 > maior) {  
				maior = l2;  
			}
			Thread.sleep(1000);			
			
			int l3 = Math.round(this.monitor.measureDistance());
			if (l3 < menor) {  
                menor = l3;  
			}
			if (l3 > maior) {  
                maior = l3;  
			}  
			Thread.sleep(1000);
			
			int l4 = Math.round(this.monitor.measureDistance());
			if (l4 < menor) {  
                menor = l4;  
			}
			if (l4 > maior) {  
                maior = l4;  
			}  
			Thread.sleep(1000);
			
			int l5 = Math.round(this.monitor.measureDistance());
			if (l5 < menor) {  
                menor = l5;  
			}
			if (l5 > maior) {  
                maior = l5;  
			}  
			
			System.out.println("L1: " + l1 + " - " 
					+ "L2: " + l2 + " - " 
					+ "L3: " + l3 + " - "
					+ "L4: " + l4 + " - "
					+ "L5: " + l5 + " - "
			);
			
			this.medidaAguaReservatorioCm = (l1 + l2 + l3 + l4 + l5 - maior - menor) / 3;
			System.out.println("media: " + this.medidaAguaReservatorioCm);
			
			
			// Calcula o percentual
			percentual = 100 - (3*(this.medidaAguaReservatorioCm - 15));
			
			
			
			System.out.println("Finalizando medicoes");
			
			
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

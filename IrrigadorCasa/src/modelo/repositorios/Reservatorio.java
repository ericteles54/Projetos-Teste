package modelo.repositorios;

import modelo.hardwares.SensorDistanciaHCSR04;

public class Reservatorio {	
	
	private SensorDistanciaHCSR04 sensorDeDistancia;
	
	public Reservatorio() {
		this.sensorDeDistancia = new SensorDistanciaHCSR04();
	}
	
	
	public int getPercentualDeAgua() {
		float medidaAguaReservatorioCm = 0;
		float percentualAguaReservatorio = 0;
		
		try {
			// Realiza 5 leituras do sensor, elimina o maior e menor valor e
			// faz a média com os 3 valores que sobram
			
			float menor = Float.MAX_VALUE;
			float maior = 0;
						
			float[] leituras = new float[5];
			for(int i = 0; i < 5; i++) {
				// Aguarda 2 segundos antes de fazer a leitura
				Thread.sleep(2000);
				
				leituras[i] = this.sensorDeDistancia.measureDistance();
				
				if (leituras[i] < menor) {
					menor = leituras[i];
				}
				if (leituras[i] > maior) {
					maior = leituras[i];
				}
			}					
			
			float somatorio = 0;
			for (float leitura : leituras) {
				somatorio = somatorio + leitura;
			}
			medidaAguaReservatorioCm = (somatorio - maior - menor) / 3;						
			
		} catch (Exception e1) {
			System.out.println("Exceção na classe Reservatorio.");
            System.err.println( e1 );
		}
		
		
		try {
            Thread.sleep( SensorDistanciaHCSR04.getWaitDurationInMillis() );
        } catch (InterruptedException ex) {
            System.err.println( "Interrupt during trigger" );
        }		
		
		
		// Calcula o percentual
		//percentualAguaReservatorio = 100 - (3*(medidaAguaReservatorioCm - 15));			
		percentualAguaReservatorio = ((48 - medidaAguaReservatorioCm) / 33) * 100;
		
		return Math.round(percentualAguaReservatorio);		
	}
}

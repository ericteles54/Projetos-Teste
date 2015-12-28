package modelo.entidades;

public class Reservatorio {
	private int aguaNoReservatorio;
	
	public int calculaNivelDeAguaReservatorio() throws InterruptedException {
		// lógica para calcular o nível de água do reservatório através do sensor		
		//this.aguaNoReservatorio = 67;
		
		// acertar inicializações
		int maior = 0;
		int menor = Integer.MAX_VALUE;
		
		int[] leituras = new int[5];
		for(int leitura : leituras) {
			// Aguarda 2 segundos antes de fazer a leitura
			Thread.sleep(2000);
			
			// executa função que le o sensor
			//leitura = recebe a função
			
			// Verifica se maior ou menor
			//if() {
				
			//}
			
			
		}
		
		this.aguaNoReservatorio = 0;
		
		for(int leitura : leituras) {
			this.aguaNoReservatorio = this.aguaNoReservatorio + leitura;
		}
		this.aguaNoReservatorio = (this.aguaNoReservatorio - menor - maior) / leituras.length ;
		
		return this.aguaNoReservatorio;
	}

}

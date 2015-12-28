package modelo.entidades;

public class ImplementaSensorFluxo {
	
	// Usar tempo de leitura em milisegundos
	public int lerFluxo(int tempoLeitura) {
		SensorFluxo sensorFluxo = new SensorFluxo();
		
		new Thread(sensorFluxo).start();
		try {
			Thread.sleep(tempoLeitura);
			sensorFluxo.setRodando(false);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		int rotacoes = sensorFluxo.contador;
		
		// Escrever codigo para converter rotacoes em litros em funcao do tempo de leitura
		return rotacoes;
	}
}

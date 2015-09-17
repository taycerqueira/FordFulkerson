package algoritmo;

public class FordFulkerson {
	
	private int quant_vertices;
	private int[][] matrizAdjacencia;
	private boolean[] visitados;
	private int[][] grafoResidual;
	private int inicio;
	private int destino;

	public FordFulkerson(int quant, int matriz[][], int inicio, int destino){
		this.quant_vertices = quant;
		this.matrizAdjacencia = matriz;
		this.grafoResidual = new int[quant + 1][quant + 1];
		this.inicio = inicio;
		this.destino = destino;
		visitados = new boolean[quant + 1];
	}
	
	//Executa o algoritmo e retorna o fluxo máximo
	public int run(){
		
		int fluxoMaximo = 0;
		
		//Faz uma cópia da matriz do grafo no grafo residual
        for (int v1 = 1; v1 <= this.quant_vertices; v1++){
        	
            for (int v2 = 1; v2 <= this.quant_vertices; v2++){
            	
                grafoResidual[v1][v2] = matrizAdjacencia[v1][v2];
                
            }
        }
		
		return fluxoMaximo;
		
	}

}

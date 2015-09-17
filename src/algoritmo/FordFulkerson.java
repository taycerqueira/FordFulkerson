package algoritmo;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
	
	private int quant_vertices;
	private int[][] matrizAdjacencia; 
	private Queue<Integer> fila; 
	private boolean[] visitados;
	private int[] caminho; 
	private int[][] grafoResidual;
	private int inicio;
	private int destino;

	public FordFulkerson(int quant, int matriz[][], int inicio, int destino){
		this.quant_vertices = quant;
		this.matrizAdjacencia = matriz;
		this.grafoResidual = new int[quant][quant];
		this.inicio = inicio;
		this.destino = destino;
		this.caminho = new int[quant];
		this.visitados = new boolean[quant];
		this.fila = new LinkedList<Integer>();
	}
	
	//Executa o algoritmo e retorna o fluxo máximo
	public int run(){
		
		int fluxoMaximo = 0;
		
		//Faz uma cópia da matriz do grafo no grafo residual
        for (int v1 = 0; v1 < this.quant_vertices; v1++){
        	
            for (int v2 = 0; v2 < this.quant_vertices; v2++){
            	
                grafoResidual[v1][v2] = matrizAdjacencia[v1][v2];
                
            }
        }
        
        //Busca um caminho até que todos os caminhos sejam percorridos
        while (buscaCaminho())
        {
            int fluxo = Integer.MAX_VALUE; //Seta um valor suficiente grande para iniciar a variável
            int i;
            
            //Percorre o caminho e salva a aresta de menor capacidade para definir o valor do fluxo
            for (int v = this.destino; v != this.inicio; v = this.caminho[v])
            {
                i = this.caminho[v];
                if(this.grafoResidual[i][v] < fluxo){
                	fluxo = this.grafoResidual[i][v];
                }

            }
            //Atualiza os valores no grafo residual
            for (int v = this.destino; v != this.inicio; v = caminho[v])
            {
                i = caminho[v];
                grafoResidual[i][v] -= fluxo;
                grafoResidual[v][i] += fluxo;
            }
            fluxoMaximo += fluxo;	
        }
        
		return fluxoMaximo;
		
	}
	
	//Faz uma busca em largura no grafo (representado pela matriz de adjacência)
	public boolean buscaCaminho(){
        boolean existeCaminho = false;
        
        //"reseto" as variáveis
        for(int v = 0; v < this.quant_vertices; v++){
            caminho[v] = -1;
            visitados[v] = false;
        }
 
        fila.add(this.inicio); //Adiciono o vértice inicial na fila (FIFO)
        //this.caminho[this.inicio] = -1;
        visitados[this.inicio] = true;
 
        while (!fila.isEmpty()){ //Enquanto a fila não estiver vazia...
        	
            int vertice = fila.remove(); //tira o primeiro vértice adicionado
            
            for(int i = 0; i < this.quant_vertices; i++){
            	//Se existir uma ligação entre os vértices, a capacidade for maior que zero e ainda não foi visitado
            	if (this.grafoResidual[vertice][i] > 0 &&  !this.visitados[i]){
                    this.caminho[i] = vertice; //Adiciono ele no vetor que guarda o caminho
                    fila.add(i); //Adiciono o vértice no final da fila
                    this.visitados[i] = true; //Marco ele como visitado
                }
            	
            }

        }
        if(this.visitados[this.destino]){ //Verifica se um caminho até o destino foi formado
            existeCaminho = true;
        }
        
        return existeCaminho;
        
	}

}

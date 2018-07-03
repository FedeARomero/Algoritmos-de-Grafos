package paquete;

public class GrafoDirigido {

	private Nodo[] nodos;
	private Arista[] aristas;
	private int cantidadAristas;
	private Integer[][] adyacencia;
	private boolean[][] adyacenciaNoPonderada;
	private final static int INFINITO = 200000;
	
	public GrafoDirigido() {
		// TODO Auto-generated constructor stub
	}
	
	public GrafoDirigido(int n, int a) {
		this.nodos = new Nodo[n];
		this.aristas = new Arista[a];
		for (int i = 0; i < nodos.length; i++)
			this.nodos[i] = new Nodo(i, i+1);
		this.cantidadAristas = 0;
		this.adyacencia = new Integer[n][n];
		this.adyacenciaNoPonderada = new boolean[n][n];
	}
	
	public void agregarArista(int origen, int destino, int peso) {
		this.adyacencia[origen-1][destino-1] = peso;
		this.adyacenciaNoPonderada[origen-1][destino-1] = true;
		this.aristas[this.cantidadAristas++] = new Arista(this.nodos[origen-1], this.nodos[destino-1], peso);
	}
	
	public void verMatriz() {
		for (int i = 0; i < adyacencia.length; i++) {
			for (int j = 0; j < adyacencia.length; j++)
				System.out.print(String.format("%-3s ", this.adyacencia[i][j] == null ? "-" : String.valueOf(this.adyacencia[i][j])));
			System.out.println();
		}
	}
	
	public Integer[][] floyd(){
		Integer[][] anterior = new Integer[this.nodos.length][this.nodos.length];
		Integer[][] actual = new Integer[this.nodos.length][this.nodos.length];
		
		for (int i = 0; i < nodos.length; i++)
			for (int j = 0; j < nodos.length; j++)
				anterior[i][j] = this.adyacencia[i][j] != null ? this.adyacencia[i][j] : INFINITO;
				
		for (int nodo = 0; nodo < nodos.length; nodo++) {
			for (int i = 0; i < actual.length; i++) {
				actual[nodo][i] = anterior[nodo][i];
				actual[i][nodo] = anterior[i][nodo];
				actual[i][i] = 0;
			}
			
			for (int i = 0; i < actual.length; i++)
				for (int j = 0; j < actual.length; j++)
					if( nodo != i && nodo != j && i != j )
						actual[i][j] = Math.min(anterior[i][j], anterior[i][nodo] + anterior[nodo][j]);
			
			anterior = actual.clone();
		}
				
		return anterior;
	}
	
	public boolean[][] warshall() {
		boolean[][] anterior = new boolean[this.nodos.length][this.nodos.length];
		boolean[][] actual = new boolean[this.nodos.length][this.nodos.length];
		
		for (int i = 0; i < nodos.length; i++)
			for (int j = 0; j < nodos.length; j++)
				anterior[i][j] = this.adyacenciaNoPonderada[i][j];
		
		for (int nodo = 0; nodo < nodos.length; nodo++) {
			for (int i = 0; i < actual.length; i++) {
				actual[nodo][i] = anterior[nodo][i];
				actual[i][nodo] = anterior[i][nodo];
				actual[i][i] = false;
			}
			
			for (int i = 0; i < actual.length; i++)
				for (int j = 0; j < actual.length; j++)
					if( nodo != i && nodo != j && i != j )
						actual[i][j] = anterior[i][j] || ( anterior[i][nodo] && anterior[nodo][j] );
			
			anterior = actual.clone();
		}
		
		return anterior;
	}
}

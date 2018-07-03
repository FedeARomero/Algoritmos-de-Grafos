package paquete;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Grafo {

	private Nodo[] nodos;
	private Arista[] aristas;
	private int cantidadAristas;
	private MatrizSimetrica adyacencia;
	private final static int INFINITO = 200000;
	
	public Grafo() {
		
	}
	
	public Grafo(int n, int a) {
		this.nodos = new Nodo[n];
		this.aristas = new Arista[a];
		for (int i = 0; i < nodos.length; i++)
			this.nodos[i] = new Nodo(i, i+1);
		this.cantidadAristas = 0;
		this.adyacencia = new MatrizSimetrica(n);
	}
	
	public void agregarArista(int origen, int destino, int peso) {
		this.adyacencia.setFC(origen-1, destino-1, peso);
		this.aristas[this.cantidadAristas++] = new Arista(this.nodos[origen-1], this.nodos[destino-1], peso);
	}
	
	public void verMatriz() {
		this.adyacencia.verMatrizSimetrica();
	}
	
	public boolean[] recorridoDFS(int inicial) {
		boolean[] visitados = new boolean[this.nodos.length];
		Stack<Nodo> pila = new Stack<>();
		
		pila.push(this.nodos[inicial-1]);
		visitados[inicial-1] = true;
		
		while( !pila.isEmpty() ){
			int index = pila.peek().posicion();
			boolean apile = false;
			
			for (int i = 0; i < nodos.length && !apile; i++)
			if( i != index && this.adyacencia.getFC(index, i) != null && visitados[i] == false ) {
				pila.push(this.nodos[i]);
				visitados[i] = true;
				apile = true;
			}
			
			if( !apile )
				pila.pop();
		}
		
		return visitados;
	}
	
	public int[] recorridoBFS(int inicial) {
		int[] distancias = new int[this.nodos.length];
		Queue<Nodo> cola = new LinkedList<>();
		
		Arrays.fill(distancias, -1);
		
		cola.add(this.nodos[inicial-1]);
		distancias[inicial-1] = 0;
		
		while( !cola.isEmpty() ) {
			int index = cola.peek().posicion();
			
			for (int i = 0; i < nodos.length; i++) {
				if( i!=index && this.adyacencia.getFC(index, i)!=null && distancias[i] == -1 ) {
					cola.add(this.nodos[i]);
					distancias[i] = distancias[index] + 1;
				}
			}
			
			cola.poll();
		}
		
		
		return distancias;
	}
	
	public int[] dijkstra(int inicial) {
		int[] distancias = new int[this.nodos.length];
		Arrays.fill(distancias, INFINITO);
		
		Integer[] anterior = new Integer[this.nodos.length];
		Arrays.fill(anterior, null);
		
		boolean[] visitados = new boolean[this.nodos.length];
		Arrays.fill(visitados, false);
		
		distancias[inicial-1] = 0;
		visitados[inicial-1] = true;
		anterior[inicial-1] = inicial-1;
		
		PriorityQueue<Integer> monticulo = new PriorityQueue<>();
		monticulo.add(inicial-1);
		
		while( !monticulo.isEmpty() ) {
			int index = monticulo.poll();
			visitados[index] = true;
			
			for (int i = 0; i < nodos.length; i++)
				if( this.adyacencia.getFC(index, i) != null && distancias[i] > distancias[index] + this.adyacencia.getFC(index, i) ) {
						distancias[i] = distancias[index] + this.adyacencia.getFC(index, i);
						anterior[i] = index;
						monticulo.add(i);
					}
		}

		for (int i = 0; i < anterior.length; i++) {
			System.out.println(this.nodos[anterior[i]] + "-->" + this.nodos[i]);
		}
		
		return distancias;
	}
	
	public Arista[] prim() {
		boolean[] visitados = new boolean[this.nodos.length];
		int cantidadAtistas = 0;
		Arista[] aristasPrim = new Arista[this.nodos.length - 1];
		PriorityQueue<Arista> monticulo = new PriorityQueue<>(new Arista());
		Arista[] vecinos = vecinos(0);
		
		visitados[0] = true;
		for (int i = 0; i < vecinos.length; i++)
			monticulo.add(vecinos[i]);
		
		while( cantidadAtistas != (this.nodos.length - 1) ) {
			Arista actual = monticulo.poll();
			int pos = actual.destino().posicion();
			
			if( visitados[pos] == false ) {
				visitados[pos] = true;
				aristasPrim[cantidadAtistas++] = actual;
				
				vecinos = vecinos(pos);
				for (int i = 0; i < vecinos.length; i++)
				if ( visitados[vecinos[i].destino().posicion()] == false )
					monticulo.add(vecinos[i]);
			}
		}
		monticulo.clear();
		return aristasPrim;
	}
	
	private Arista[] vecinos(int nodo) {
		int cantidadVecinos = 0;
		
		for (int i = 0; i < this.nodos.length; i++)
		if( this.adyacencia.getFC(nodo, i) != null )
			cantidadVecinos++;
		
		Arista[] vecinos = new Arista[cantidadVecinos];
		cantidadVecinos = 0;
		
		for (int i = 0; i < this.nodos.length; i++)
		if( this.adyacencia.getFC(nodo, i) != null )
			vecinos[cantidadVecinos++] = new Arista(this.nodos[nodo], this.nodos[i], this.adyacencia.getFC(nodo, i).intValue());
		
		return vecinos;
	}
	
	public Arista[] kruskal() {
		int cantidaAristas = 0;
		Arista[] aristasKruskal = new Arista[this.nodos.length - 1];
		int[] padre = new int[this.nodos.length];
		
		for (int i = 0; i < padre.length; i++)
			padre[i] = i;
		
		Arrays.sort(this.aristas, new Arista());
		
		int i = 0;
		while( cantidaAristas != (this.nodos.length - 1) ) {
			int origen = this.aristas[i].origen().posicion();
			int destino = this.aristas[i].destino().posicion();
			if( !sameRoot(padre, origen, destino) ) {
				aristasKruskal[cantidaAristas++] = this.aristas[i];
				union(padre, origen, destino);
			}
			i++;
		}
		
		return aristasKruskal;
	}
	
	private int find(int[] root, int i) {
		return i == root[i] ? i : ( root[i] = find(root, root[i]) );
	}
	
	private void union(int[] root, int i, int j) {
		root[ find(root, i) ] = find(root, j);
	}
	
	private boolean sameRoot(int[]root, int i, int j) {
		return find(root, i) == find(root, j);
	}
	
	
}

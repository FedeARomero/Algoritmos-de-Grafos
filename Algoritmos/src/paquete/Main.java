package paquete;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafo grafo = new Grafo(8, 14);
		grafo.agregarArista(1, 2, 8);
		grafo.agregarArista(1, 4, 1);
		grafo.agregarArista(1, 8, 2);
		grafo.agregarArista(2, 3, 2);
		grafo.agregarArista(2, 7, 8);
		grafo.agregarArista(3, 7, 5);
		grafo.agregarArista(3, 8, 3);
		grafo.agregarArista(4, 6, 2);
		grafo.agregarArista(4, 8, 5);
		grafo.agregarArista(5, 6, 3);
		grafo.agregarArista(5, 8, 2);
		grafo.agregarArista(6, 7, 5);
		grafo.agregarArista(6, 8, 9);
		grafo.agregarArista(7, 8, 4);
		
		grafo.verMatriz();
	
		Arista[] kruskal = grafo.kruskal();
		System.out.println("Arbol");
		for (int i = 0; i < kruskal.length; i++) {
			System.out.println(kruskal[i]);
		}
		
		Arista[] prim = grafo.kruskal();
		System.out.println("Arbol");
		for (int i = 0; i < prim.length; i++) {
			System.out.println(prim[i]);
		}
	}

}

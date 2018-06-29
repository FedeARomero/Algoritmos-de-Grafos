# Algoritmos-de-Grafos

# Algoritmo de PRIM
funciones necesarias:

Aristas[] vecinos(int nodo) --> Retorna un vector de aristas que tienen como origen a 'nodo'

Pseudo-Codigo:

PRIM

    vector booleano de visitados
    cantidad de Aristas = 0
    vector de aristas de prim (tamanio: cantidad de nodos -1)
    vector de aristas vecinas
    monticulo

    pongo al nodo inicial como visitado
    agrego al monticulo los vecinos del nodo inicial

    mientras ( cantidad de aristas de prim sea distinto a la cantidad de nodos -1 )
        actual = saco arista del monticulo

        si nodo destino de actual no fue visitado
            agrego acual al vector de aristas de prim
            incremento cantidad de aristas de prim

            pongo al nodo destino como visitado
            agrego al monticulo los vecinos del nodo destino
    fin mientras

    limpio el monticulo
    retorno el vector de aristas de prim

# Algoritmo de KRUSKAL
funciones necesarias:

int find( vector, indice) --> retorna el nodo raiz del arbol al que pertenece el nodo 'indice'

void union( vector, indice i, indice j) --> hace que nodo 'i' y nodo 'j' pertenezcan al mismo arbol

boolean sameRoot( vector, indice i, indice j) verifica si nodo 'i' y nodo 'j' pertenecen al mismo arbol (las raices son el mismo nodo)

Pseudo-Codigo:

KRUSKAL

    cantidad de Aristas = 0
    vector de aristas de kruskal (tamanio: cantidad de nodos -1)
    vector de raices (tamanio: cantidad de nodo)

    inicailizo el vector de raices
    por cada elemento del vector de raices
        raiz(i) = i --> cada nodo es un arbol y dicho nodo es la raiz

    ordeno el vector de aristas del grafo

    mientras ( cantidad de aristas de kruskal sea distinto a la cantidad de nodos -1 )
        por cada arista obtengo el nodo origen y nodo destino
        si origen y destino no tiene la misma raiz
            agrego la arista al vector de aristas de kruskal
            incremento la cantidad de Aristas
        sigo con la siguiente arista del grafo
    fin mientras

    retorno el vector de aristas de kruskal

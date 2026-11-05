package model;

import java.util.HashSet;
import java.util.Set;

import interfaces.Solver;

public class Goloso implements Solver {
	private Set<Integer> conjuntoDominante;

	public Set<Integer> getConjuntoDominante() {
		return this.conjuntoDominante;
	}

	public Goloso() {
		this.conjuntoDominante = new HashSet<Integer>();
	}

	@Override
    public void resolver(Grafo grafo) {
		
        limpiarEjecucionAnterior();
        
        int totalVertices = grafo.tamano();
        
        if (totalVertices == 0) {
            return;
        }

       
        boolean[] cubierto = new boolean[totalVertices];
        int cantidadCubiertos = 0;

        
        while (cantidadCubiertos < totalVertices) {
            int mejorVertice = seleccionarMejorVertice(grafo, cubierto);
            
            registrarEnSolucion(mejorVertice);
            
            cantidadCubiertos += marcarComoCubierto(grafo, mejorVertice, cubierto);
        }
    }

	private void registrarEnSolucion(int mejorVertice) {
		this.conjuntoDominante.add(mejorVertice);
	}
	
	private int seleccionarMejorVertice(Grafo grafo, boolean[] cubierto) {
        int mejorVertice = -1;
        int maxNuevosCubiertos = -1;

        for (int i = 0; i < grafo.tamano(); i++) {
            int potencialesCubiertos = contarNuevosCubiertos(grafo, i, cubierto);

            if (potencialesCubiertos > maxNuevosCubiertos) {
                maxNuevosCubiertos = potencialesCubiertos;
                mejorVertice = i;
            }
        }

        return mejorVertice;
    }
		
    private int contarNuevosCubiertos(Grafo grafo, int vertice, boolean[] cubierto) {
        int nuevosCubiertos = 0;
        
        //Se evalua el vertice
        if (!cubierto[vertice]) {
            nuevosCubiertos++;
        }

        //Se evalua los vecinos
        for (int vecino : grafo.vecinos(vertice)) {
            if (!cubierto[vecino]) {
                nuevosCubiertos++;
            }
        }

        return nuevosCubiertos;
    }
    
    private int marcarComoCubierto(Grafo grafo, int vertice, boolean[] cubierto) {
        int nuevosCubiertos = 0;
        
        //Se marca el vertice
        if (!cubierto[vertice]) {
            cubierto[vertice] = true;
            nuevosCubiertos++;
        }
        
        //Se marca a sus vecinos
        for (int vecino : grafo.vecinos(vertice)) {
            if (!cubierto[vecino]) {
                cubierto[vecino] = true;
                nuevosCubiertos++;
            }
        }

        return nuevosCubiertos;
    }

    
		
	private void limpiarEjecucionAnterior() {
		this.conjuntoDominante.clear();
	}
}

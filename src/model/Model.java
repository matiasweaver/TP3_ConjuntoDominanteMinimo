package model;

import java.util.Set;

import algoritmos.Goloso;
import grafo.Grafo;
import interfaces.Solver;

public class Model {
	private Solver solver;
	private Grafo grafo;

	public void cargarArchivoDesdeJSON(String rutaArchivo) {
		
	}
	
	public Grafo getGrafo() {
		return this.grafo;
	}
	
	public void setSolver(Solver nuevoSolver) {
		this.solver = nuevoSolver;
	}

	public void ejecutarAlgoritmo() {
		if(this.solver != null && this.grafo != null) {
			this.solver.resolver(this.grafo);
		}
	}

	public Set<Integer> getResultado() {
		if(this.solver != null) {
			return this.solver.getConjuntoDominante();
		}
		return null;
	}
}
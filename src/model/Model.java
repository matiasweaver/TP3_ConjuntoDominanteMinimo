package model;

import java.util.Set;

import interfaces.Solver;

import json.ArchivoJSON;

import json.AristaJSON;

import json.GrafoJSON;

public class Model {
	private Solver solver;
	private Grafo grafo;

	public void cargarArchivoDesdeJSON(String rutaArchivo) {
        GrafoJSON datosLeidos = ArchivoJSON.leerArchivo(rutaArchivo);
        
        if (datosLeidos != null) {
            
            this.grafo = new Grafo(datosLeidos.getCantidadNodos());
            
            if (datosLeidos.getAristas() != null) {
                for (AristaJSON arista : datosLeidos.getAristas()) {
                    this.grafo.agregarArista(arista.getNodoA(), arista.getNodoB());
                }
            }
        }
	}
	
	public Grafo getGrafo() {
		return this.grafo;
	}
	
    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
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
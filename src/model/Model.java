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
		// 1. Usamos tu nueva clase para leer el archivo
        GrafoJSON datosLeidos = ArchivoJSON.leerArchivo(rutaArchivo);
        
        // 2. Verificamos que la lectura no haya fallado
        if (datosLeidos != null) {
            
            // 3. Instanciamos el grafo real de tu capa de negocio
            this.grafo = new Grafo(datosLeidos.getCantidadNodos());
            
            // 4. Cargamos las aristas
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
	
	// 1. INYECCIÓN MANUAL DEL GRAFO (Nuevo) // Para que el presenter pueda construirlo y enviarlo al model
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
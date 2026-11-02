package algoritmos;

import java.util.Set;
import grafo.Grafo;
import interfaces.Solver;

public class Backtracking implements Solver {
	private Set<Integer> conjuntoDominante;
	private long tiempoEjecucion;
	
	public Set<Integer> getConjuntoDominante(){
		return this.conjuntoDominante;
	}
	public long getTiempoEjecucion() {
		return this.tiempoEjecucion;
	}
	
	public void resolver(Grafo g) {
		
	}
	
	private void explorar(Grafo g, int nodoActual, Set<Integer> conjuntoParcial) {
		
	}
}
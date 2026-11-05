package interfaces;

import java.util.Set;

import model.Grafo;

public interface Solver {
	Set<Integer> getConjuntoDominante();
	
	void resolver(Grafo g);
}
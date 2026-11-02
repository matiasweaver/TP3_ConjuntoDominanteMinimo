package interfaces;

import java.util.Set;
import grafo.Grafo;

public interface Solver {
	Set<Integer> getConjuntoDominante();
	void resolver(Grafo g);
}
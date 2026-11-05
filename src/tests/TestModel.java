package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashSet;

import java.util.Set;

import model.Grafo;

import model.Model;

import interfaces.Solver;

public class TestModel {

	@Test
	public void testSetYGetGrafo() {
		Model model = new Model();
		Grafo g = new Grafo(5);
		
		model.setGrafo(g);
		
		assertNotNull(model.getGrafo());
		assertEquals(5, model.getGrafo().tamano());
	}

	@Test
	public void testEjecutarAlgoritmoDelegaAlSolverCorrectamente() {
		Model model = new Model();
		Grafo g = new Grafo(3);
		model.setGrafo(g);

		Solver solverPrueba = new Solver() {
			private Set<Integer> resultadoFalso = new HashSet<Integer>();

			@Override
			public void resolver(Grafo g) {
				resultadoFalso.add(1); 
			}

			@Override
			public Set<Integer> getConjuntoDominante() {
				return resultadoFalso;
			}
		};

		model.setSolver(solverPrueba);
		model.ejecutarAlgoritmo();
		
		Set<Integer> resultado = model.getResultado();
		
		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(1));
	}
	
	@Test
	public void testEjecutarAlgoritmoSinGrafoNoFalla() {
		Model model = new Model();

		try {
			model.ejecutarAlgoritmo();
			assertNull(model.getResultado());
		} catch (Exception e) {
			fail("El modelo no debería lanzar excepción al ejecutar vacío.");
		}
	}
}
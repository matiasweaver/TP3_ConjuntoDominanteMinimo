package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;

import model.Model;
import presenter.Presenter;
import view.ConjuntoGeneradorMinimoGUI;
import grafo.Grafo;

public class PresenterTest {

	// 1. CLASE MOCK: Finge ser la interfaz gráfica para no abrir ventanas durante el test
	class VistaMock extends ConjuntoGeneradorMinimoGUI {
		public String ultimoErrorMostrado = null;
		public boolean pantallaLimpiada = false;
		
		@Override
		public void mostrarError(String mensaje) { 
			this.ultimoErrorMostrado = mensaje; 
		}
		
		@Override
		public void limpiarPantalla() { 
			this.pantallaLimpiada = true; 
		}
		
		@Override
		public void mostrarGrafos(Grafo g, Set<Integer> sol1, Set<Integer> sol2) { 
			// No hacemos nada visualmente
		}
	}
	
	private Model modelo;
	private VistaMock vistaMock;
	private Presenter presenter;

	@Before
	public void setUp() {
		// Este método se ejecuta automáticamente antes de cada @Test
		modelo = new Model();
		vistaMock = new VistaMock();
		presenter = new Presenter(modelo, vistaMock);
	}

	@Test
	public void testEjecutarAlgoritmoSinGrafoNotificaErrorALaVista() {
		// No cargamos ningún grafo en el modelo e intentamos ejecutar
		presenter.ejecutarAlgoritmo();
		
		// Verificamos que el Presenter le haya ordenado a la vista mostrar este error exacto
		assertEquals("Primero selecciona un archivo JSON.", vistaMock.ultimoErrorMostrado);
	}

	@Test
	public void testLimpiarVaciaElModeloYActualizaLaVista() {
		// Simulamos que el usuario había cargado un grafo de 5 vértices
		modelo.setGrafo(new Grafo(5));
		
		// El usuario presiona el botón limpiar
		presenter.limpiar();
		
		// Verificamos que el Presenter vació el modelo
		assertNull(modelo.getGrafo());
		// Verificamos que el Presenter le ordenó a la vista limpiarse
		assertTrue(vistaMock.pantallaLimpiada);
	}
}
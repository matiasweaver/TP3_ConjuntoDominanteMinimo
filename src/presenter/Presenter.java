package presenter;

import java.util.HashSet;
import java.util.Set;

import algoritmos.Backtracking;
import algoritmos.Goloso;
import model.Model;
import view.ConjuntoGeneradorMinimoGUI;

public class Presenter {
	private Model model;
	private ConjuntoGeneradorMinimoGUI view;
	private String rutaArchivo;
	
	public Presenter(Model model, ConjuntoGeneradorMinimoGUI view) {
        this.model = model;
        this.view = view;
    }
	
	public void archivoSeleccionado(String ruta) {
		this.rutaArchivo = ruta;
			
		try {
			this.model.cargarArchivoDesdeJSON(ruta);
			this.view.mostrarMensajeExito("Grafo cargado exitosamente. Nodos: " + this.model.getGrafo().tamano());
			
		}catch(Exception ex){
			view.mostrarError("No se pudo cargar el archivo: " + ex.getMessage());
			this.rutaArchivo = null;
		}
	}
	
	private Set<Integer> instanciarYEjecutarGoloso() {
		Goloso goloso = new Goloso();
		model.setSolver(goloso);
		model.ejecutarAlgoritmo();
		return model.getResultado();
	}
	
	private Set<Integer> instanciarYEjecutarBacktracking() {
		Backtracking backtracking = new Backtracking();
		model.setSolver(backtracking);
		model.ejecutarAlgoritmo();
		return model.getResultado();
	}
	
	public void ejecutarAlgoritmo() {
		if(model.getGrafo() == null) {
			view.mostrarError("Primero selecciona un archivo JSON.");
			return;
		}
		
		//guardamos el resultado para mostrarlo
		Set<Integer> solucionGoloso = instanciarYEjecutarGoloso();
		//Set<Integer> solucionBacktracking = instanciarYEjecutarBacktracking();
		
		Set<Integer> solucionBacktracking = new HashSet<Integer>(); // Mock temporal hasta implementar backtracking
		
		view.mostrarGrafos(model.getGrafo(), solucionGoloso, solucionBacktracking);
	}
	
	public void limpiar() {
		this.rutaArchivo = null;
        this.model.setGrafo(null); // Vaciamos el grafo de la memoria
        
        // Tendrás que agregar este método en ConjuntoGeneradorMinimoGUI
        // para que vacíe los paneles y limpie el recuadro de texto de la ruta.
        this.view.limpiarPantalla();
	}
	
	public void ejecutarTestStress() {
		
	}
}

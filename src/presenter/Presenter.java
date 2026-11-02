package presenter;

import java.util.Set;

import algoritmos.Backtracking;
import algoritmos.Goloso;
import model.Model;
import view.ConjuntoGeneradorMinimoGUI;

public class Presenter {
	private Model model;
	private ConjuntoGeneradorMinimoGUI view;
	private String rutaArchivoActual;
	
	public Presenter(Model model, ConjuntoGeneradorMinimoGUI view) {
        this.model = model;
        this.view = view;
    }
	
	private void archivoSeleccionado(String ruta) {
		this.rutaArchivoActual = ruta;
			
		try {
			
		}catch(Exception ex){
			view.mostrarError("No se pudo cargar el archivo: " + ex.getMessage());
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
		if(rutaArchivoActual == null) {
			view.mostrarError("Primero selecciona un archivo JSON.");
			return;
		}
		
		Set<Integer> solucionGoloso = instanciarYEjecutarGoloso();
		Set<Integer> solucionBacktracking = instanciarYEjecutarBacktracking();
		
		
		view.mostrarGrafos(model.getGrafo(), solucionGoloso, solucionBacktracking);
	}
	
	public void limpiar() {
		
	}
	
	public void ejecutarTestStress() {
		
	}
}

package json;

import java.util.List;

public class GrafoJSON {
	private int cantidadNodos;
	private List<AristaJSON> aristas;

	public int getCantidadNodos(){
		return cantidadNodos; 
	}
	public void setCantidadNodos(int cantidadNodos) {
		this.cantidadNodos = cantidadNodos; 
	}

	public List<AristaJSON> getAristas() { 
		return aristas; 
	}
	public void setAristas(List<AristaJSON> aristas) { 
		this.aristas = aristas; 
	}
	
}
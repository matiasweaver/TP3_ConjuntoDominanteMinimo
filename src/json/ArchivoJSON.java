package json;

import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

public class ArchivoJSON {
	public static GrafoJSON leerArchivo(String ruta) {
		Gson gson = new Gson();
		
		try (Reader reader = new FileReader(ruta)) {
			//lee el JSON y lo transforma en nuestro objeto GrafoJSON
			return gson.fromJson(reader, GrafoJSON.class);
			
		} catch (Exception e) {
			System.err.println("Error al leer el archivo JSON: " + e.getMessage());
			return null;
		}
	}
}

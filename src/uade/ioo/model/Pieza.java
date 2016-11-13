package uade.ioo.model;

public class Pieza {

	private String nombre;
	private String path;

	public Pieza(String nombre, String path) {
		this.nombre = nombre;
		this.path = path;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPath() {
		return path;
	}

}

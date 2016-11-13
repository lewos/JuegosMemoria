package uade.ioo.model;

import java.util.List;

public class Rompecabezas extends Juego {

	public Rompecabezas(String nombre, List<Nivel> niveles) {
		super(nombre, niveles);
	}

	@Override
	public boolean esAccionValida(String origen, String destino) {
		return origen.equals(destino);
	}

}

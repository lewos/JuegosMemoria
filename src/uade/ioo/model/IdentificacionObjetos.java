package uade.ioo.model;

import java.util.List;

public class IdentificacionObjetos  extends Juego {
	
	public IdentificacionObjetos(String nombre, List<Nivel> niveles) {
		super(nombre, niveles);
	}

	@Override
	public boolean esAccionValida(String origen, String destino) {
		return origen.equals(destino);
	}
}

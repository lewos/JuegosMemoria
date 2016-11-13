package uade.ioo.model;

import java.util.List;

public class Nivel {

	private int numero;
	private List<Pieza> piezas;
	private int movimientosDisponibles;

	public Nivel(int numero, List<Pieza> piezas) {
		this.numero = numero;
		this.piezas = piezas;
		this.movimientosDisponibles = piezas.size();
	}

	public int getNumero() {
		return numero;
	}

	public boolean nivelCompleto() {
		return this.movimientosDisponibles == 0;
	}

	public void decrementarMovimientosDisponibles() {
		this.movimientosDisponibles--;
	}

	public List<Pieza> getPiezas() {
		return piezas;
	}
}

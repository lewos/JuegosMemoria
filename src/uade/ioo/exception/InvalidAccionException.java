package uade.ioo.exception;

public class InvalidAccionException extends Exception {

	private static final long serialVersionUID = 6032482897855932616L;
	private String origen;
	private String destino;

	public InvalidAccionException(String origen, String destino) {
		this.origen = origen;
		this.destino = destino;
	}

	@Override
	public String getMessage() {
		return "La accion con origen " + origen + "no corresponde con " + destino;
	}

}

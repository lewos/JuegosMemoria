package uade.ioo.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 6696874544364849225L;
	private String errorMessage;

	public ValidationException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}

}

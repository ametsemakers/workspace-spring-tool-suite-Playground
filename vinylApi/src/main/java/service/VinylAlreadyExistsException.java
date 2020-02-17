package service;

public class VinylAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public VinylAlreadyExistsException(String message) {
		super(message);
	}

}

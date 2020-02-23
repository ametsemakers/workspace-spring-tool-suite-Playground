package service;

public class SongAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SongAlreadyExistsException(String message) {
		super(message);
	}
}

package service;

public class NoSongsOnVinylException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSongsOnVinylException(String message) {
		super(message);
	}
}

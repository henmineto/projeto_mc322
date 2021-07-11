package com.unicamp.mc322.projeto.jogo;

public class LORException extends Exception {

	private static final long serialVersionUID = -6376205821266776846L;

	public LORException() {
		super();
    }

	public LORException(String message) {
        super(message);
    }

	 
	public LORException(String message, Throwable cause) {
		super(message, cause);
    }
	 
	public LORException(Throwable cause) {
        super(cause);
    }

}

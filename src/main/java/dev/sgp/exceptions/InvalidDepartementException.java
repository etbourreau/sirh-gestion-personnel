package dev.sgp.exceptions;

public class InvalidDepartementException extends RuntimeException {

	public InvalidDepartementException() {
		super();
	}

	public InvalidDepartementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidDepartementException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDepartementException(String message) {
		super(message);
	}

	public InvalidDepartementException(Throwable cause) {
		super(cause);
	}

}

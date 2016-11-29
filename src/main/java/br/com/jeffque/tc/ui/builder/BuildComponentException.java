package br.com.jeffque.tc.ui.builder;

public class BuildComponentException extends RuntimeException {
	private static final long serialVersionUID = 4104288189360417249L;

	public BuildComponentException() {
	}

	public BuildComponentException(String message) {
		super(message);
	}

	public BuildComponentException(Throwable cause) {
		super(cause);
	}

	public BuildComponentException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildComponentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

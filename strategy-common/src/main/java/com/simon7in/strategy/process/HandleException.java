/**
 * 
 */
package com.simon7in.strategy.process;


/**
 * 
 */
public class HandleException extends RuntimeException {
	private static final long serialVersionUID = -534595870007770753L;

	private String errorCode = "UNKNOWN_ERROR";

	public HandleException() {
		super();
	}

	public HandleException(String message, Throwable cause) {
		super(message, cause);
	}

	public HandleException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public HandleException(String message) {
		super(message);
	}


	public HandleException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public HandleException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}

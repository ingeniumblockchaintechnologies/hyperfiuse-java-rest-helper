package ro.ibt.hyperfiuse.resthelper.exceptions;

public class RestResponseException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2086664416955781730L;

	private String code;

	public RestResponseException(String message) {

		super(message);
	}

	public RestResponseException(String message, Throwable cause) {

		super(message, cause);
	}

	public RestResponseException(String message, String code, Throwable cause) {

		super(message, cause);
		this.code = code;
	}

	public RestResponseException(String message, String code) {

		super(message);
		this.code = code;
	}

	public String getCode() {

		return this.code;
	}
}

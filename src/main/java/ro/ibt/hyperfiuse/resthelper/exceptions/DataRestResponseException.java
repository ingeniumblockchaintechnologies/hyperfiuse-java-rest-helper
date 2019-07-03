package ro.ibt.hyperfiuse.resthelper.exceptions;

public class DataRestResponseException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2086664416955781730L;

	private String code;

	public DataRestResponseException(String message) {

		super(message);
	}

	public DataRestResponseException(String message, Throwable cause) {

		super(message, cause);
	}

	public DataRestResponseException(String message, String code, Throwable cause) {

		super(message, cause);
		this.code = code;
	}

	public DataRestResponseException(String message, String code) {

		super(message);
		this.code = code;
	}

	public String getCode() {

		return this.code;
	}
}

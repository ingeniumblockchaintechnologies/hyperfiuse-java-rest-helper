package ro.ibt.hyperfiuse.resthelper.rest.auth;

import ro.ibt.hyperfiuse.resthelper.rest.models.RestResponse;

public class OAuthRestResponse extends RestResponse
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8555194988202842240L;

	/**
	 * Error message - must go
	 */
	private String error;

	/**
	 * @return the error
	 */
	public String getError() {

		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {

		this.error = error;
	}
}

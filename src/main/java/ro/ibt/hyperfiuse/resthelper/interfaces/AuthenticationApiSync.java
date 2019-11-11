package ro.ibt.hyperfiuse.resthelper.interfaces;

import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.auth.OAuthToken;

public interface AuthenticationApiSync {

	/**
	 * Get an oAuth token based on the authorization code obtain after /authorize.
	 * Associated API: POST /token call
	 * 
	 * @param authorizationCode obtained after /authorize
	 * @return
	 * @throws RestResponseException
	 */
	public OAuthToken getToken(String authorizationCode) throws RestResponseException;

	/**
	 * Get an oAuth token based on user name and password. Associated API: POST
	 * /token
	 * 
	 * @param username The user name to get the token for
	 * @param password The associated password for the user name
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public OAuthToken getToken(String username, String password) throws RestResponseException;

}

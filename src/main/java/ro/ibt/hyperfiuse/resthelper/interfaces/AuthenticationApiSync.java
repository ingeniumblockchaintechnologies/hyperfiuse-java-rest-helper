package ro.ibt.hyperfiuse.resthelper.interfaces;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ro.ibt.hyperfiuse.resthelper.exceptions.DataRestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.auth.OAuthToken;

public interface AuthenticationApiSync
{
	/**
	 * Get an oAuth token based on the authorization code obtain after /authorise call
	 * 
	 * @param authorizationCode
	 * @return OAuthToken
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public OAuthToken getToken(String authorizationCode) throws DataRestResponseException;

	/**
	 * Get an oAuth token based on username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public OAuthToken getToken(String username, String password) throws DataRestResponseException;
}

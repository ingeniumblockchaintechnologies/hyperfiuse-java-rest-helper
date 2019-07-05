package ro.ibt.hyperfiuse.resthelper.implementations;

import org.asynchttpclient.BoundRequestBuilder;

import com.google.gson.Gson;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.exceptions.DataRestResponseException;
import ro.ibt.hyperfiuse.resthelper.interfaces.AuthorizationApiSync;
import ro.ibt.hyperfiuse.resthelper.rest.auth.OAuthToken;

public class AuthorizationApiSyncImplementation extends ApiImplementation implements AuthorizationApiSync
{
	/**
	 * Implementation constructor, we need to inject the HTTP client. It is recommended to reuse to HTTP client along the entire application
	 */
	public AuthorizationApiSyncImplementation(HyperFiuseApi root) {

		super(root);
	}

	@Override
	public OAuthToken getToken(String authorizationCode) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getAuthorizationNodeUrl().concat("/token"));

		// define request header
		postRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");

		// define request parameters
		postRequest.addFormParam("client_id", getRoot().getConfiguration().getClientId());
		postRequest.addFormParam("client_secret", getRoot().getConfiguration().getClientSecret());
		postRequest.addFormParam("grant_type", "authorization_code");
		postRequest.addFormParam("code", authorizationCode);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, OAuthToken.class);
	}

	@Override
	public OAuthToken getToken(String username, String password) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getAuthorizationNodeUrl().concat("/token"));

		// define request header
		postRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");

		// define request parameters
		postRequest.addFormParam("client_id", getRoot().getConfiguration().getClientId());
		postRequest.addFormParam("client_secret", getRoot().getConfiguration().getClientSecret());
		postRequest.addFormParam("grant_type", "password");
		postRequest.addFormParam("username", username);
		postRequest.addFormParam("password", password);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, OAuthToken.class);
	}
}

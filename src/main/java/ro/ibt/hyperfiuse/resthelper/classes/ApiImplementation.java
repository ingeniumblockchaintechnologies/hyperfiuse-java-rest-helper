package ro.ibt.hyperfiuse.resthelper.classes;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Response;

import com.google.gson.Gson;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.data.DataRestResponse;

public abstract class ApiImplementation
{
	/**
	 * Root/parent instance that holds the OAuthToken and Configuration instance.
	 */
	private HyperFiuseApi root;

	protected HyperFiuseApi getRoot() {

		return root;
	}

	/**
	 * Creates new API instance.
	 * 
	 * @param root Root/parent instance that holds the OAuthToken and Configuration instance.
	 */
	public ApiImplementation(HyperFiuseApi root) {

		this.root = root;
	}

	/**
	 * Execute a web sync request
	 * 
	 * @param request
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	protected String executeSyncRequest(BoundRequestBuilder request) throws RestResponseException {

		// Execute the request
		Future<Response> responseFuture = request.execute();

		// Attempt response extraction
		Response responseBound = null;

		try {

			responseBound = responseFuture.get();
		}
		catch (InterruptedException e) {

			throw new RestResponseException(e.getMessage(), "-1", e);
		}
		catch (ExecutionException e) {

			throw new RestResponseException(e.getMessage(), "-2", e);
		}

		// do we have a great expected response or some bad stuff ?
		if (responseBound.getStatusCode() != 200) {

			// if body is missing
			if (responseBound.getResponseBody() == null || responseBound.getResponseBody().equals("")) {

				// throw an error with the status code and text received
				throw new RestResponseException(responseBound.getStatusText(), String.valueOf(responseBound.getStatusCode()));
			}
			else {

				// if we have a body and status code != 200 we look for code and message
				DataRestResponse dataRestResponse = (new Gson()).fromJson(responseBound.getResponseBody(), DataRestResponse.class);

				// check response if successfully create
				if (dataRestResponse == null) {

					// received response is not a standard code,message JSON as expected from server
					throw new RestResponseException("Invalid error response ", "-3");
				}
				else {

					// throw an error with the proper server message
					throw new RestResponseException(dataRestResponse.getMessage(), dataRestResponse.getCode());
				}
			}
		}

		// if we have a missing body but a 200 status code
		if (responseBound.getResponseBody() == null || responseBound.getResponseBody().equals("")) {

			// we have a no-no situation
			throw new RestResponseException("Web response is missing a body", "-4");
		}

		return responseBound.getResponseBody();
	}
}

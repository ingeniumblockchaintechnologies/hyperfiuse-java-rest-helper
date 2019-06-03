package ro.ibt.hyperfiuse.resthelper.implementations;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Response;

import com.google.gson.Gson;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.interfaces.DataApiSync;
import ro.ibt.hyperfiuse.resthelper.rest.models.ResponseObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.ResponseObjectList;

public class DataApiSyncImplementation extends ApiImplementation implements DataApiSync
{
	/**
	 * Implementation constructor, we need to inject the HTTP client. It is recommended to reuse to HTTP client along the entire application
	 */
	public DataApiSyncImplementation(HyperFiuseApi root) {

		super(root);
	}

	@Override
	public ResponseObjectList listJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip, int limit) throws InterruptedException, ExecutionException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema"));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// Execute the POST request
		Future<Response> responseFuture = getRequest.execute();

		// Attempt response extraction
		Response responseBound = responseFuture.get();

		// return response object
		return (new Gson()).fromJson(responseBound.getResponseBody(), ResponseObjectList.class);
	}

	@Override
	public ResponseObject getJsonSchema(String token, String entityName, boolean integrityCheck, boolean gZip) throws InterruptedException, ExecutionException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema").concat("/").concat(entityName));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));

		// Execute the POST request
		Future<Response> responseFuture = getRequest.execute();

		// Attempt response extraction
		Response responseBound = responseFuture.get();

		// return response object
		return (new Gson()).fromJson(responseBound.getResponseBody(), ResponseObject.class);
	}

}

package ro.ibt.hyperfiuse.resthelper.implementations;

import org.asynchttpclient.BoundRequestBuilder;

import com.google.gson.Gson;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.exceptions.DataRestResponseException;
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
	public ResponseObjectList listJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema"));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		String requestResponse = executeSyncRequest(getRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, ResponseObjectList.class);
	}

	@Override
	public ResponseObject getJsonSchema(String token, String schemaName, boolean integrityCheck, boolean gZip) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema").concat("/").concat(schemaName));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));

		String requestResponse = executeSyncRequest(getRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, ResponseObject.class);
	}

	@Override
	public ResponseObject createJsonSchema(String token, String schemaName, String jsonSchema) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema").concat("/").concat(schemaName));

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// set body
		postRequest.setBody(jsonSchema);

		// Execute the POST request
		String requestResponse = executeSyncRequest(postRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, ResponseObject.class);
	}

	@Override
	public ResponseObject updateJsonSchema(String token, String schemaName, String jsonSchema) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema").concat("/").concat(schemaName));

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body
		putRequest.setBody(jsonSchema);

		// Execute the POST request
		String requestResponse = executeSyncRequest(putRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, ResponseObject.class);
	}
}

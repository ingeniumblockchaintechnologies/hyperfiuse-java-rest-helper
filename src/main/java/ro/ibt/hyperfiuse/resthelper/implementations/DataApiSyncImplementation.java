package ro.ibt.hyperfiuse.resthelper.implementations;

import java.util.List;

import org.asynchttpclient.BoundRequestBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.exceptions.DataRestResponseException;
import ro.ibt.hyperfiuse.resthelper.interfaces.DataApiSync;
import ro.ibt.hyperfiuse.resthelper.rest.models.DataRequest;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;
import ro.ibt.querybuildercreator.models.RuleGroup;

public class DataApiSyncImplementation extends ApiImplementation implements DataApiSync
{
	/**
	 * Implementation constructor, we need to inject the HTTP client. It is recommended to reuse to HTTP client along the entire application
	 */
	public DataApiSyncImplementation(HyperFiuseApi root) {

		super(root);
	}

	@Override
	public RecordObjectList listEntitiesJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException {

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
		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObject getEntityJsonSchema(String token, String entityName, boolean integrityCheck, boolean gZip) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema").concat("/").concat(entityName));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));

		String requestResponse = executeSyncRequest(getRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject createEntityJsonSchema(String token, String entityName, String jsonSchema) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema").concat("/").concat(entityName));

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// set body
		postRequest.setBody(jsonSchema);

		// Execute the POST request
		String requestResponse = executeSyncRequest(postRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateEntityJsonSchema(String token, String entityName, String jsonSchema) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration().getDataNodeUrl().concat("/catalog/_JsonSchema").concat("/").concat(entityName));

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body
		putRequest.setBody(jsonSchema);

		// Execute the POST request
		String requestResponse = executeSyncRequest(putRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject insertRecord(String token, String entityName, Object record) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getDataNodeUrl().concat("/data").concat("/").concat(entityName));

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// set body
		postRequest.setBody((new Gson()).toJson(new DataRequest(record)));

		// Execute the POST request
		String requestResponse = executeSyncRequest(postRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObjectList listRecords(String token, String schemaName, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/data").concat("/").concat(schemaName));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		String requestResponse = executeSyncRequest(getRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObject getRecord(String token, String entityName, String recordId, boolean integrityCheck, boolean gZip) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/data").concat("/").concat(entityName).concat("/").concat(recordId));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));

		String requestResponse = executeSyncRequest(getRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateRecord(String token, String entityName, String recordId, String jsonRecord) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration().getDataNodeUrl().concat("/data").concat("/").concat(entityName).concat("/").concat(recordId));

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body - add above layer fields "data": ...
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("data", new JsonParser().parse(jsonRecord).getAsJsonObject());
		putRequest.setBody(jsonObject.toString());

		// Execute the POST request
		String requestResponse = executeSyncRequest(putRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateRecord(String token, String entityName, String recordId, Object objectRecord) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration().getDataNodeUrl().concat("/data").concat("/").concat(entityName).concat("/").concat(recordId));

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body
		putRequest.setBody((new Gson()).toJson(new DataRequest(objectRecord)));

		// Execute the POST request
		String requestResponse = executeSyncRequest(putRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObjectList filter(String token, String entityName, String queryJson, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getDataNodeUrl().concat("/data/advancedFilter").concat("/").concat(entityName));

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// define request parameters
		postRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		postRequest.addQueryParam("gZip", String.valueOf(gZip));
		postRequest.addQueryParam("skip", String.valueOf(skip));
		postRequest.addQueryParam("limit", String.valueOf(limit));

		// set body - add above layer fields "data": ...
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("data", new JsonParser().parse(queryJson).getAsJsonObject());
		postRequest.setBody(jsonObject.toString());

		// execute request
		String requestResponse = executeSyncRequest(postRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObjectList filter(String token, String entityName, RuleGroup queryObject, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getDataNodeUrl().concat("/data/advancedFilter").concat("/").concat(entityName));

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// define request parameters
		postRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		postRequest.addQueryParam("gZip", String.valueOf(gZip));
		postRequest.addQueryParam("skip", String.valueOf(skip));
		postRequest.addQueryParam("limit", String.valueOf(limit));

		// set body - add above layer fields "data": ...
		postRequest.setBody((new Gson()).toJson(new DataRequest(queryObject)));

		// execute request
		String requestResponse = executeSyncRequest(postRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObjectList insertBulk(String token, String entityName, List<?> recordsList) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration().getDataNodeUrl().concat("/data/bulk").concat("/").concat(entityName));

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// set body - add above layer fields "data": ...
		postRequest.setBody((new Gson()).toJson(new DataRequest(recordsList)));

		// execute request
		String requestResponse = executeSyncRequest(postRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObject requestSync(String token, String entityName, String recordId) throws DataRestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration().getDataNodeUrl().concat("/data/reqsync").concat("/").concat(entityName).concat("/").concat(recordId));

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body
		putRequest.setBody((new Gson()).toJson(new DataRequest(null)));

		// Execute the PUT request
		String requestResponse = executeSyncRequest(putRequest);

		// return response object
		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}
}

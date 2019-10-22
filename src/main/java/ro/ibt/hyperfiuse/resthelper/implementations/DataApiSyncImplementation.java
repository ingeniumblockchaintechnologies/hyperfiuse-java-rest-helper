package ro.ibt.hyperfiuse.resthelper.implementations;

import java.util.List;

import org.asynchttpclient.BoundRequestBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.enums.RecordPermission;
import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.interfaces.DataApiSync;
import ro.ibt.hyperfiuse.resthelper.rest.models.DataRequest;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;
import ro.ibt.querybuildercreator.models.RuleGroup;

public class DataApiSyncImplementation extends ApiImplementation implements DataApiSync {
	/**
	 * Implementation constructor, we need to inject the HTTP client. It is
	 * recommended to reuse to HTTP client along the entire application
	 */
	public DataApiSyncImplementation(HyperFiuseApi root) {

		super(root);
	}

	@Override
	public RecordObjectList listEntitiesJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip,
			int limit) throws RestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient()
				.prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema"));

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
	public RecordObject getEntityJsonSchema(String token, String entityName, boolean integrityCheck, boolean gZip)
			throws RestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema").concat("/").concat(entityName));

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
	public RecordObject createEntityJsonSchema(String token, String entityName, String jsonSchema)
			throws RestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema").concat("/").concat(entityName));

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
	public RecordObject updateEntityJsonSchema(String token, String entityName, String jsonSchema)
			throws RestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema").concat("/").concat(entityName));

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
	public RecordObject insertRecord(String token, String entityName, Object record) throws RestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data").concat("/").concat(entityName));

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
	public RecordObjectList listRecords(String token, String schemaName, boolean integrityCheck, boolean gZip, int skip,
			int limit) throws RestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data").concat("/").concat(schemaName));

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
	public RecordObject getRecord(String token, String entityName, String recordId, boolean integrityCheck,
			boolean gZip) throws RestResponseException {

		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/data").concat("/").concat(entityName).concat("/").concat(recordId));

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
	public RecordObject updateRecord(String token, String entityName, String recordId, String jsonRecord)
			throws RestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/data").concat("/").concat(entityName).concat("/").concat(recordId));

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
	public RecordObject updateRecord(String token, String entityName, String recordId, Object objectRecord)
			throws RestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/data").concat("/").concat(entityName).concat("/").concat(recordId));

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
	public RecordObjectList filter(String token, String entityName, String queryJson, boolean integrityCheck,
			boolean gZip, int skip, int limit) throws RestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/data/advancedFilter").concat("/").concat(entityName));

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
	public RecordObjectList filter(String token, String entityName, RuleGroup queryObject, boolean integrityCheck,
			boolean gZip, int skip, int limit) throws RestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/data/advancedFilter").concat("/").concat(entityName));

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
	public RecordObjectList insertBulk(String token, String entityName, List<?> recordsList)
			throws RestResponseException {

		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data/bulk").concat("/").concat(entityName));

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
	public RecordObject requestSync(String token, String entityName, String recordId) throws RestResponseException {

		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/data/reqsync").concat("/").concat(entityName).concat("/").concat(recordId));

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

	@Override
	public String uploadServerLicence(String token) throws RestResponseException {
		//TODO
		return null;
	}

	@Override
	public String getServerCode(String token) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/services/core/v1/api/system/serverCode"));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		String requestResponse = executeSyncRequest(getRequest);

		// return response object
		return requestResponse;
	}

	@Override
	public String getSysInfo(String token) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/sysInfo"));

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		String requestResponse = executeSyncRequest(getRequest);

		// return response object
		return requestResponse;
	}

	@Override
	public RecordObjectList getEntityHistory(String token, String entityName, String recordId, Integer version,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient()
				.prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat(
						"/services/core/v1/api/system/history/".concat(entityName).concat("/").concat(recordId)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// define request query params
		getRequest.addQueryParam("version", String.valueOf(version));
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// execute request
		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);

	}

	@Override
	public RecordObjectList getEntityInvalid(String token, String entityName, String recordId, Boolean integrityCheck,
			Integer skip, Integer limit) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient()
				.prepareGet(getRoot().getConfiguration().getDataNodeUrl()
						.concat("/services/core/v1/api/system/invalid/".concat(entityName) .concat("/").concat(recordId)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// define request query params
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// execute request
		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObject addEvent(String token, RecordPermission recordPermission, RecordObject record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addEvent"));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);
		
		postRequest.addQueryParam("recordPermission", recordPermission.toString());

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateEvent(String token, String recordId, RecordPermission recordPermission, Object record)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/updateEvent/" .concat(recordId)));

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);
		
		putRequest.addQueryParam("recordPermission", recordPermission.toString());

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		String requestResponse = executeSyncRequest(putRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObjectList getEventList(String token, String ownerId, Boolean forceReload)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/listEvents/" .concat(ownerId)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		if (forceReload == true) {
			getRequest.addQueryParam("forceReload", String.valueOf(forceReload));
		}

		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}
}

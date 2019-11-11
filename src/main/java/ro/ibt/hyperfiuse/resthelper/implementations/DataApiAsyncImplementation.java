package ro.ibt.hyperfiuse.resthelper.implementations;

import java.util.List;

import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.enums.RecordPermission;
import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.interfaces.DataApiAsync;
import ro.ibt.hyperfiuse.resthelper.interfaces.DataApiSync;
import ro.ibt.hyperfiuse.resthelper.rest.models.DataRequest;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;
import ro.ibt.querybuildercreator.models.RuleGroup;

public class DataApiAsyncImplementation extends ApiImplementation implements DataApiAsync {

	public DataApiAsyncImplementation(HyperFiuseApi root) {
		super(root);
	}

	@Override
	public ListenableFuture<Response> listEntitiesJsonSchemas(String token, boolean integrityCheck, boolean gZip,
			int skip, int limit) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema");

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getEntityJsonSchema(String token, String entityName, boolean integrityCheck,
			boolean gZip) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema")
				.concat("/").concat(entityName);

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> createEntityJsonSchema(String token, String entityName, String jsonSchema)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema")
				.concat("/").concat(entityName);

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// set body
		postRequest.setBody(jsonSchema);

		// return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> updateEntityJsonSchema(String token, String entityName, String jsonSchema)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/catalog/_JsonSchema")
				.concat("/").concat(entityName);

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body
		putRequest.setBody(jsonSchema);

		// return
		return executeAsyncRequest(putRequest);

	}

	@Override
	public ListenableFuture<Response> insertRecord(String token, String entityName, Object record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data").concat("/")
				.concat(entityName);

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// set body
		postRequest.setBody((new Gson()).toJson(new DataRequest(record)));

		// return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> listRecords(String token, String schemaName, boolean integrityCheck, boolean gZip,
			int skip, int limit) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data").concat("/")
				.concat(schemaName);

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getRecord(String token, String entityName, String recordId,
			boolean integrityCheck, boolean gZip) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data").concat("/")
				.concat(entityName).concat("/").concat(recordId);

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// define request parameters
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("gZip", String.valueOf(gZip));

		// return
		return executeAsyncRequest(getRequest);

	}

	@Override
	public ListenableFuture<Response> updateRecord(String token, String entityName, String recordId, String jsonRecord)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data").concat("/")
				.concat(entityName).concat("/").concat(recordId);

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body - add above layer fields "data": ...
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("data", new JsonParser().parse(jsonRecord).getAsJsonObject());
		putRequest.setBody(jsonObject.toString());

		// return
		return executeAsyncRequest(putRequest);

	}

	@Override
	public ListenableFuture<Response> updateRecord(String token, String entityName, String recordId,
			Object objectRecord) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data").concat("/")
				.concat(entityName).concat("/").concat(recordId);

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body
		putRequest.setBody((new Gson()).toJson(new DataRequest(objectRecord)));

		// return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> filter(String token, String entityName, String queryJson, boolean integrityCheck,
			boolean gZip, int skip, int limit) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data/advancedFilter")
				.concat("/").concat(entityName);

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

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

		// return
		return executeAsyncRequest(postRequest);

	}

	@Override
	public ListenableFuture<Response> filter(String token, String entityName, RuleGroup queryObject,
			boolean integrityCheck, boolean gZip, int skip, int limit) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data/advancedFilter")
				.concat("/").concat(entityName);

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

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

		// return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> insertBulk(String token, String entityName, List<?> recordsList)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data/bulk").concat("/")
				.concat(entityName);

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		postRequest.addHeader("Content-type", "application/json");

		// set body - add above layer fields "data": ...
		postRequest.setBody((new Gson()).toJson(new DataRequest(recordsList)));

		// return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> requestSync(String token, String entityName, String recordId)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/data/reqsync")
				.concat("/").concat(entityName).concat("/").concat(recordId);

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));
		putRequest.addHeader("Content-type", "application/json");

		// set body
		putRequest.setBody((new Gson()).toJson(new DataRequest(null)));

		// return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> uploadServerLicence(String token) throws RestResponseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListenableFuture<Response> getServerCode(String token) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/services/core/v1/api/system/serverCode");

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getSysInfo(String token) throws RestResponseException {
		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/sysInfo");

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Authorization", "Bearer ".concat(" ".concat(token)));

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getEntityHistory(String token, String entityName, String recordId,
			Integer version, Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/history/".concat(entityName).concat("/").concat(recordId));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// define request query params
		getRequest.addQueryParam("version", String.valueOf(version));
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getEntityInvalid(String token, String entityName, String recordId,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/invalid/".concat(entityName).concat("/").concat(recordId));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// define request query params
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> addEvent(String token, RecordPermission recordPermission, RecordObject record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addEvent");

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		postRequest.addQueryParam("recordPermission", recordPermission.toString());

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> updateEvent(String token, String recordId, RecordPermission recordPermission,
			Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/updateEvent/".concat(recordId));

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		putRequest.addQueryParam("recordPermission", recordPermission.toString());

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> getEventList(String token, String ownerId, Boolean forceReload)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/listEvents/".concat(ownerId));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		if (forceReload == true) {
			getRequest.addQueryParam("forceReload", String.valueOf(forceReload));
		}

		// return
		return executeAsyncRequest(getRequest);

	}
}

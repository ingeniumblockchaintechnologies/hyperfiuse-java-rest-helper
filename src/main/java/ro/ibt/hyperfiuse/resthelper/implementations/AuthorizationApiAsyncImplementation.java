package ro.ibt.hyperfiuse.resthelper.implementations;

import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.interfaces.AuthorizationApiAsync;
import ro.ibt.hyperfiuse.resthelper.rest.models.DataRequest;

public class AuthorizationApiAsyncImplementation extends ApiImplementation implements AuthorizationApiAsync {

	public AuthorizationApiAsyncImplementation(HyperFiuseApi hyperFiuseApi) {
		super(hyperFiuseApi);
	}

	@Override
	public ListenableFuture<Response> generateLicenseKeys(String token, Object record) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/license/generateLicenseKeys");

		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// return
		return executeAsyncRequest(postRequest);

	}

	@Override
	public ListenableFuture<Response> createNode(String token, Object record) throws RestResponseException {
		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/license/createNode");

		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> updateNode(String token, String recordId, Object record)
			throws RestResponseException {
		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/license/updateNode/".concat(recordId));

		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> getNodeSettings(String token, String nodeId) throws RestResponseException {
		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/license/nodeSettings/".concat(nodeId));

		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("nodeId", nodeId);

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> downloadPublicLicense(String token, String nodeId) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/license/download/".concat(nodeId));

		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getEntityHistory(String token, String entityName, String recordId,
			Integer version, Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/history/".concat(entityName).concat("/").concat(recordId));

		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// define request query params
		getRequest.addQueryParam("version", String.valueOf(version));
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getEntityInvalid(String token, String entityName, String recordId,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/invalid/".concat(entityName).concat("/").concat(recordId));

		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// define request query params
		getRequest.addQueryParam("integrityCheck", String.valueOf(integrityCheck));
		getRequest.addQueryParam("skip", String.valueOf(skip));
		getRequest.addQueryParam("limit", String.valueOf(limit));

		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> entityList(String token, String entityName, JsonElement jsonQueryBody,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/list/".concat(entityName));

		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// define request query params
		if (integrityCheck == true) {
			postRequest.addQueryParam("forceReload", String.valueOf(integrityCheck));
		}
		postRequest.addQueryParam("skip", String.valueOf(skip));
		postRequest.addQueryParam("limit", String.valueOf(limit));

		// set body element
		postRequest.setBody(jsonQueryBody.toString());

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> createUser(String token, Object record) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/createUser");

		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> updateUserProfile(String token, String recordId, Object record)
			throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/system/updateUserProfile/".concat(recordId));

		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> userChangePassword(String token, Object record) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/changePassword");

		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> userUpdatePassword(String token, Object record) throws RestResponseException {

		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/updatePassword");

		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> addGroup(String token, Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addGroup");

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> updateGroup(String token, String recordId, Object record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/updateGroup/".concat(recordId));

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> addEvent(String token, Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addEvent");

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> updateEvent(String token, String recordId, Object record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/updateEvent/".concat(recordId));

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// execute and return
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

		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> addSubNetwork(String token, Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addSubNetwork");

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> updateSubNetwork(String token, String recordId, Object record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/updateSubNetwork/".concat(recordId));

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> addOAuthSettings(String token, Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/addOAuthSettings");

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);

	}

	@Override
	public ListenableFuture<Response> updateOAuthSettings(String token, String recordId, Object record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/system/updateOAuthSettings/".concat(recordId));

		// Bound Request
		BoundRequestBuilder putRequest = prepareRequest(getRoot().getAsyncHttpClient(), "PUT", url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(putRequest);
	}

	@Override
	public ListenableFuture<Response> getEntityPermisions(String token, String entityName)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/Group/".concat(entityName));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> addGroupEntityPermisions(String token, String entityName, Object record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/Group/".concat(entityName));

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> getGroupEntityPermisions(String token, String entityName, String groupId)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/Group/".concat(entityName));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("groupId", groupId);

		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> deleteGroupEntityPermisions(String token, String entityName, Object record)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/User/".concat(entityName));

		// Bound Request
		BoundRequestBuilder deleteRequest = prepareRequest(getRoot().getAsyncHttpClient(), "DELETE", url);

		// define request header
		deleteRequest.addHeader("Content-Type", "application/json");
		deleteRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		deleteRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(deleteRequest);
	}

	@Override
	public ListenableFuture<Response> addUserEntityPermisions(String token, String entityName, String recordId,
			Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat(
				"/services/core/v1/api/security/_Permissions/User/".concat(entityName).concat("/").concat(recordId));

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);
	}

	@Override
	public ListenableFuture<Response> getUserEntityPermisions(String token, String entityName, String recordId)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat(
				"/services/core/v1/api/security/_Permissions/User/".concat(entityName).concat("/").concat(recordId));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		// execute and return
		return executeAsyncRequest(getRequest);

	}

	@Override
	public ListenableFuture<Response> deleteUserEntityPermisions(String token, String entityName, String recordId,
			Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat(
				"/services/core/v1/api/security/_Permissions/User/".concat(entityName).concat("/").concat(recordId));

		// Bound Request
		BoundRequestBuilder deleteRequest = prepareRequest(getRoot().getAsyncHttpClient(), "DELETE", url);

		// define request header
		deleteRequest.addHeader("Content-Type", "application/json");
		deleteRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		deleteRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(deleteRequest);
	}

	@Override
	public ListenableFuture<Response> addEntityRequestPermissionFromOwner(String token, String entityName,
			String ownerId, Object record) throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl().concat(
				"/services/core/v1/api/security/_Permissions/Request/".concat(entityName).concat("/").concat(ownerId));

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		// execute and return
		return executeAsyncRequest(postRequest);

	}

	@Override
	public ListenableFuture<Response> getSentPermisions(String token, String entityName, Boolean pending)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/Request/Sent/".concat(entityName));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("pending", String.valueOf(pending));

		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getReceivedPermisions(String token, String entityName, Boolean pending)
			throws RestResponseException {

		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/Request/Received/".concat(entityName));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("pending", String.valueOf(pending));

		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> getGivenPermisions(String token, String entityName) throws RestResponseException {
		
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/Request/Given/".concat(entityName));

		// Bound Request
		BoundRequestBuilder getRequest = prepareRequest(getRoot().getAsyncHttpClient(), "GET", url);
		
		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);
		
		// execute and return
		return executeAsyncRequest(getRequest);
	}

	@Override
	public ListenableFuture<Response> addGivePermisionsToUser(String token, String entityName, String recordId,
			Object record) throws RestResponseException {
		
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/security/_Permissions/Given/".concat(entityName).concat("/")
						.concat(recordId));

		// Bound Request
		BoundRequestBuilder postRequest = prepareRequest(getRoot().getAsyncHttpClient(), "POST", url);
		
		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);
		
		// execute and return
		return executeAsyncRequest(postRequest);
	}

}

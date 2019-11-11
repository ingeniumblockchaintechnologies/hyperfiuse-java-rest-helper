package ro.ibt.hyperfiuse.resthelper.implementations;

import org.asynchttpclient.BoundRequestBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;
import ro.ibt.hyperfiuse.resthelper.classes.ApiImplementation;
import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.interfaces.AuthorizationApiSync;
import ro.ibt.hyperfiuse.resthelper.rest.auth.OAuthToken;
import ro.ibt.hyperfiuse.resthelper.rest.models.DataRequest;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;

public class AuthorizationApiSyncImplementation extends ApiImplementation implements AuthorizationApiSync {
	/**
	 * Implementation constructor, we need to inject the HTTP client. It is
	 * recommended to reuse to HTTP client along the entire application
	 */
	public AuthorizationApiSyncImplementation(HyperFiuseApi root) {

		super(root);
	}

	@Override
	public RecordObject generateLicenseKeys(String token, Object record) throws RestResponseException {
		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/license/generateLicenseKeys");
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);

	}

	@Override
	public RecordObject createNode(String token, Object record) throws RestResponseException {
		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/license/createNode");
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(url);

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateNode(String token, String recordId, Object record) throws RestResponseException {
		// Bound Request
		String url = getRoot().getConfiguration().getDataNodeUrl()
				.concat("/services/core/v1/api/license/updateNode/".concat(recordId));
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(url);

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		String requestResponse = executeSyncRequest(putRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject getNodeSettings(String token, String nodeId) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/license/nodeSettings/".concat(nodeId)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("nodeId", nodeId);

		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject downloadPublicLicense(String token, String nodeId) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/license/download/".concat(nodeId)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
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
				.prepareGet(getRoot().getConfiguration().getDataNodeUrl().concat(
						"/services/core/v1/api/system/invalid/".concat(entityName).concat("/").concat(recordId)));

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
	public RecordObjectList entityList(String token, String entityName, JsonElement jsonQueryBody,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/list/".concat(entityName)));

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

		// execute request
		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObject createUser(String token, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/createUser"));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateUserProfile(String token, String recordId, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/system/updateUserProfile/".concat(recordId)));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject userChangePassword(String token, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/changePassword"));

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		String requestResponse = executeSyncRequest(putRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject userUpdatePassword(String token, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/updatePassword"));

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		String requestResponse = executeSyncRequest(putRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject addGroup(String token, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addGroup"));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateGroup(String token, String recordId, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/updateGroup/".concat(recordId)));

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		String requestResponse = executeSyncRequest(putRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject addEvent(String token, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addEvent"));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateEvent(String token, String recordId, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/updateEvent/".concat(recordId)));

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

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
				.getDataNodeUrl().concat("/services/core/v1/api/system/listEvents/".concat(ownerId)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		if (forceReload == true) {
			getRequest.addQueryParam("forceReload", String.valueOf(forceReload));
		}

		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObjectList.class);
	}

	@Override
	public RecordObject addSubNetwork(String token, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addSubNetwork"));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateSubNetwork(String token, String recordId, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/updateSubNetwork/".concat(recordId)));

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		String requestResponse = executeSyncRequest(putRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject addOAuthSettings(String token, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/system/addOAuthSettings"));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject updateOAuthSettings(String token, String recordId, Object record) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder putRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/system/updateOAuthSettings/".concat(recordId)));

		// define request header
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		putRequest.setBody(body);

		String requestResponse = executeSyncRequest(putRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public String getEntityPermisions(String token, String entityName) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().preparePut(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/security/_Permissions/Group/".concat(entityName)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		String requestResponse = executeSyncRequest(getRequest);

		return requestResponse;
	}

	@Override
	public String addGroupEntityPermisions(String token, String entityName, Object record)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/security/_Permissions/Group/".concat(entityName)));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return requestResponse;
	}

	@Override
	public String getGroupEntityPermisions(String token, String entityName, String groupId)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/security/_Permissions/Group/".concat(entityName)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("groupId", groupId);

		String requestResponse = executeSyncRequest(getRequest);

		return requestResponse;
	}

	@Override
	public String deleteGroupEntityPermisions(String token, String entityName, Object record)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder deleteRequest = getRoot().getAsyncHttpClient().prepareDelete(getRoot().getConfiguration()
				.getDataNodeUrl().concat("/services/core/v1/api/security/_Permissions/User/".concat(entityName)));

		// define request header
		deleteRequest.addHeader("Content-Type", "application/json");
		deleteRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		deleteRequest.setBody(body);

		String requestResponse = executeSyncRequest(deleteRequest);
		return requestResponse;
	}

	@Override
	public String addUserEntityPermisions(String token, String entityName, String recordId, Object record)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient().preparePost(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/security/_Permissions/User/"
						.concat(entityName).concat("/").concat(recordId)));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return requestResponse;
	}

	@Override
	public String getUserEntityPermisions(String token, String entityName, String recordId)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient().prepareGet(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/security/_Permissions/User/"
						.concat(entityName).concat("/").concat(recordId)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		String requestResponse = executeSyncRequest(getRequest);

		return requestResponse;
	}

	@Override
	public String deleteUserEntityPermisions(String token, String entityName, String recordId, Object record)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder deleteRequest = getRoot().getAsyncHttpClient().prepareDelete(
				getRoot().getConfiguration().getDataNodeUrl().concat("/services/core/v1/api/security/_Permissions/User/"
						.concat(entityName).concat("/").concat(recordId)));

		// define request header
		deleteRequest.addHeader("Content-Type", "application/json");
		deleteRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		deleteRequest.setBody(body);

		String requestResponse = executeSyncRequest(deleteRequest);
		return requestResponse;
	}

	@Override
	public String addEntityRequestPermissionFromOwner(String token, String entityName, String ownerId, Object record)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient()
				.preparePost(getRoot().getConfiguration().getDataNodeUrl()
						.concat("/services/core/v1/api/security/_Permissions/Request/".concat(entityName).concat("/")
								.concat(ownerId)));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return requestResponse;
	}

	@Override
	public RecordObject getSentPermisions(String token, String entityName, Boolean pending)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient()
				.prepareGet(getRoot().getConfiguration().getDataNodeUrl()
						.concat("/services/core/v1/api/security/_Permissions/Request/Sent/".concat(entityName)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("pending", String.valueOf(pending));

		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject getReceivedPermisions(String token, String entityName, Boolean pending)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient()
				.prepareGet(getRoot().getConfiguration().getDataNodeUrl()
						.concat("/services/core/v1/api/security/_Permissions/Request/Received/".concat(entityName)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		getRequest.addQueryParam("pending", String.valueOf(pending));

		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public RecordObject getGivenPermisions(String token, String entityName) throws RestResponseException {
		// Bound Request
		BoundRequestBuilder getRequest = getRoot().getAsyncHttpClient()
				.prepareGet(getRoot().getConfiguration().getDataNodeUrl()
						.concat("/services/core/v1/api/security/_Permissions/Request/Given/".concat(entityName)));

		// define request header
		getRequest.addHeader("Content-Type", "application/json");
		getRequest.addHeader("Authorization", "Bearer " + token);

		String requestResponse = executeSyncRequest(getRequest);

		return (new Gson()).fromJson(requestResponse, RecordObject.class);
	}

	@Override
	public String addGivePermisionsToUser(String token, String entityName, String recordId, Object record)
			throws RestResponseException {
		// Bound Request
		BoundRequestBuilder postRequest = getRoot().getAsyncHttpClient()
				.preparePost(getRoot().getConfiguration().getDataNodeUrl()
						.concat("/services/core/v1/api/security/_Permissions/Given/".concat(entityName).concat("/")
								.concat(recordId)));

		// define request header
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Authorization", "Bearer " + token);

		// set body element
		String body = (new Gson()).toJson(new DataRequest(record));
		postRequest.setBody(body);

		String requestResponse = executeSyncRequest(postRequest);

		return requestResponse;
	}

}

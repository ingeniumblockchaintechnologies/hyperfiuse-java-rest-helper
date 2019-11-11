package ro.ibt.hyperfiuse.resthelper.interfaces;

import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import com.google.gson.JsonElement;

import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;

public interface AuthorizationApiAsync  
{

	/**
	 * Generate the license key for a node. Associated API: POST
	 * /license/generateLicenseKeys
	 * 
	 * @param token      User token
	 * @param licenseKey POJO record to be used to generate the license key
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> generateLicenseKeys(String token, Object record) throws RestResponseException;

	/**
	 * Insert a node settings record. Associated API: POST /license/createNode
	 * 
	 * @param token  User token
	 * @param record POJO record used to generate license keys
	 * @return
	 * @throws RestResponseException
	 */
	public ListenableFuture<Response> createNode(String token, Object record) throws RestResponseException;

	/**
	 * Update the node settings record with the specified recordId. Associated API:
	 * PUT /license/updateNode/{recordId}
	 * 
	 * @param token    User token
	 * @param recordId The record id of the record to be modified
	 * @param record   POJO record to be used to modify the node settings
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> updateNode(String token, String recordId, Object record) throws RestResponseException;

	/**
	 * Get the node settings record for the specified node id. Associated API: GET
	 * /license/nodeSettings/{nodeId}
	 * 
	 * @param token  User token
	 * @param nodeId The id of the node settings to be retrieved
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getNodeSettings(String token, String nodeId) throws RestResponseException;

	/**
	 * Download the public license file for the specified node id. Associated API:
	 * /license/download/{nodeId}
	 * 
	 * @param token  User token
	 * @param nodeId The id of the node settings to download
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> downloadPublicLicense(String token, String nodeId) throws RestResponseException;

	/**
	 * List a history of all the past versions of the specified entity name and
	 * record id. Associated API:/system/history/{EntityName}/{recordId}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table to get the invalid version
	 * @param recordId       The id of the record to get the invalid version
	 * @param version        Used to mention the version of the record to be
	 *                       retrieved
	 * @param integrityCheck Perform read with record integrity check
	 * @param skip           Number of records to skip
	 * @param limit          Max records to retrieve
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getEntityHistory(String token, String entityName, String recordId, Integer version,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException;

	/**
	 * List the invalid version of the specified entity name and record id.
	 * Associated API: GET /system/invalid/{EntityName}/{recordId}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table
	 * @param recordId       The record id of the record
	 * @param integrityCheck Perform read with record integrity check
	 * @param skip           Number of records to skip
	 * @param limit          Max records to retrieve
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getEntityInvalid(String token, String entityName, String recordId, Boolean integrityCheck,
			Integer skip, Integer limit) throws RestResponseException;

	/**
	 * List all the available records for the specified entity name filtered by a
	 * JSON filter passed as body request. Associated API: POST
	 * /system/list/{EntityName}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table to list the available records
	 *                       for
	 * @param jsonQueryBody  JSON filter
	 * @param integrityCheck Perform read with record integrity check
	 * @param skip           Number of records to skip
	 * @param limit          Max records to retrieve
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> entityList(String token, String entityName, JsonElement jsonQueryBody,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException;

	/**
	 * Create a new user and his credentials. Associated API: POST
	 * /system/createUser
	 * 
	 * @param token  User token
	 * @param record POJO record to be used to create the user
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> createUser(String token, Object record) throws RestResponseException;

	/**
	 * Update the user's profile with the recordId given as parameter.
	 * 
	 * @param token    User token
	 * @param recordId The id of the user profile to be updated
	 * @param record   Record POJO record to be used to create the user
	 * @return
	 * @throws RestResponseException
	 */
	public ListenableFuture<Response> updateUserProfile(String token, String recordId, Object record) throws RestResponseException;

	/**
	 * A user can change his password by providing the old password. Associated API:
	 * PUT /system/changePassword
	 * 
	 * @param token  User token
	 * @param record POJO record to be used to change the user password
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> userChangePassword(String token, Object record) throws RestResponseException;

	/**
	 * An admin user can change another user's password by providing the user's
	 * ownerId and the new password. Associated API: PUT
	 * /system/updateUserProfile/{recordId}
	 * 
	 * @param token  User token
	 * @param record POJO record to be to update the user data
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> userUpdatePassword(String token, Object record) throws RestResponseException;

	/**
	 * Create a new group of users. Associated API: POST /system/addGroup
	 * 
	 * @param token  User token
	 * @param record POJO record to be used to add group data
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addGroup(String token, Object record) throws RestResponseException;

	/**
	 * Update the details of a group of users with recordId given as parameter.
	 * Associated API: PUT /system/updateGroup/{recordId}
	 * 
	 * @param token    User token
	 * @param recordId The id of the group record
	 * @param record   POJO record to be used to update a group with recordId
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> updateGroup(String token, String recordId, Object record) throws RestResponseException;

	/**
	 * Create a new event. An event occurs when a new record is inserted or when an
	 * existing record is updated. The type of an event can be HTTP, TCP, WS or
	 * RABBITMQ. Associated API: POST /system/addEvent
	 * 
	 * @param token  User token
	 * @param record POJO record to be used to add a new event
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addEvent(String token, Object record) throws RestResponseException;

	/**
	 * Update the event with recordId given as parameter. Associated API: PUT
	 * /system/updateEvent/{recordId}
	 * 
	 * @param token    User token
	 * @param recordId The id of the event record to be updated
	 * @param record   POJO record to be be used to update the specified event by
	 *                 record id
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> updateEvent(String token, String recordId, Object record) throws RestResponseException;

	/**
	 * List all events for the user with ownerId given as parameter. The endpoint
	 * has a query parameter, forceReload - used to force the cache to reload. This
	 * parameter is not required. Associated API: GET /system/listEvents/{ownerId}
	 * 
	 * @param token       User token
	 * @param ownerId     The id of the owner user
	 * @param forceReload Used to force the cache to reload. Default has false
	 *                    value.
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getEventList(String token, String ownerId, Boolean forceReload) throws RestResponseException;

	/**
	 * Create a new subnetwork configuration. Associated API: POST
	 * /system/addSubNetwork
	 * 
	 * @param token  User token
	 * @param record POJO record to be used to add a subnetwork
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addSubNetwork(String token, Object record) throws RestResponseException;

	/**
	 * Update the subnetwork configuration with recordId given as parameter.
	 * Associated API: PUT /system/updateSubNetwork/{recordId}
	 * 
	 * @param token    User token
	 * @param recordId The id of the subnetwork record to be updated
	 * @param record   POJO record to be used to update the subnetwork record
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> updateSubNetwork(String token, String recordId, Object record) throws RestResponseException;

	/**
	 * Create a new OAuth credentials record. Associated API: POST
	 * /system/addOAuthSettings
	 * 
	 * @param token  User token
	 * @param record POJO record to be used to create new OAuth credentials
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addOAuthSettings(String token, Object record) throws RestResponseException;

	/**
	 * Update the OAuth credentials record with recordId given as parameter.
	 * Associated API: PUT /system/updateOAuthSettings/{recordId}
	 * 
	 * @param token    User token
	 * @param recordId The id of the OAuth record to be updated
	 * @param record   POJO record to be used to update the OAuth record identified
	 *                 by recordId
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> updateOAuthSettings(String token, String recordId, Object record) throws RestResponseException;

	/**
	 * Get all permissions for the specified entity. Associated API: GET
	 * /security/_Permissions/{EntityName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getEntityPermisions(String token, String entityName) throws RestResponseException;

	/**
	 * Add permissions to a group or a list of groups on the entity with EntityName
	 * given as parameter. Associated API: POST
	 * /security/_Permissions/Group/{EntityName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to add group(s) permissions
	 * @param record     POJO record to be used to add permissions to a group or
	 *                   list of groups
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addGroupEntityPermisions(String token, String entityName, Object record) throws RestResponseException;

	/**
	 * Get permissions of all groups or a specific group on the entity with
	 * EntityName given as parameter. The endpoint has a query parameter, groupId -
	 * used to mention the specific group for which the permissions should be
	 * retrieved. This parameter is not required. Associated API: GET
	 * /security/_Permissions/Group/{EntityName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to get group(s) permissions
	 * @param groupId    The id of the group to get permissions to
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getGroupEntityPermisions(String token, String entityName, String groupId)
			throws RestResponseException;

	/**
	 * Delete permissions of a list of groups on the entity with EntityName given as
	 * parameter. Associated API: DELETE /security/_Permissions/Group/{EntityName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to delete permissions to a list of
	 *                   groups
	 * @param record     POJO record to be used to delete permissions to a list of
	 *                   groups
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> deleteGroupEntityPermisions(String token, String entityName, Object record)
			throws RestResponseException;

	/**
	 * Add permissions to a specific user or group with recordId given as parameter
	 * on the entity with EntityName given as parameter on other users or groups
	 * (specified in the body request) data. recordId URL parameter is either an
	 * ownerId of a user or a recordId of a group. Associated API: POST
	 * /security/_Permissions/User/{EntityName}/{recordId}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table
	 * @param recordId   Is either an ownerId of a user or a recordId of a group
	 * @param record     POJO record to be used to add user entity permissions
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addUserEntityPermisions(String token, String entityName, String recordId, Object record)
			throws RestResponseException;

	/**
	 * Get permissions of a specific user or group on the entity with EntityName
	 * given as parameter. recordId URL parameter is either an ownerId of a user or
	 * a recordId of a group. Associated API: GET
	 * /security/_Permissions/User/{EntityName}/{recordId}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table
	 * @param recordId   The id can be either an ownerId of a user or a recordId of
	 *                   a group
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getUserEntityPermisions(String token, String entityName, String recordId)
			throws RestResponseException;

	/**
	 * Delete permissions of a specific user or group on the entity with EntityName
	 * given as parameter. recordId URL parameter is either an ownerId of a user or
	 * a recordId of a group. Associated API: DELETE
	 * /security/_Permissions/User/{EntityName}/{recordId}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table
	 * @param recordId   The id can be either an ownerId of a user or a recordId of
	 *                   a group
	 * @param record     POJO record to be used to delete user entity permissions
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> deleteUserEntityPermisions(String token, String entityName, String recordId, Object record)
			throws RestResponseException;

	/**
	 * The logged in user requests permissions from the user with ownerId given as
	 * parameter on the specified entity. Associated API: POST
	 * /security/_Permissions/Request/{EntityName}/{ownerId}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to request permissions to
	 * @param ownerId    The id of the owner to request entity permissions from
	 * @param record     POJO record to be used to request permissions
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addEntityRequestPermissionFromOwner(String token, String entityName, String ownerId, Object record)
			throws RestResponseException;

	/**
	 * List all sent requested permissions of the logged in user on the specified
	 * entity. The endpoint has a query parameter, pending - used to mention if the
	 * operation status is pending approval or not. This parameter is not required.
	 * Associated API: GET /security/_Permissions/Request/Sent/{EntityName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to list sent requested permissions
	 *                   of the logged in user
	 * @param pending    Used to mention if the operation status is pending approval
	 *                   or not
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getSentPermisions(String token, String entityName, Boolean pending)
			throws RestResponseException;

	/**
	 * List all received requested permissions of the logged in user on the
	 * specified entity. The endpoint has a query parameter, pending - used to
	 * mention if the operation status is pending or not. This parameter is not
	 * required. Associated API: GET
	 * /security/_Permissions/Request/Received/{EntityName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to list received requested
	 *                   permissions of the logged in user
	 * @param pending    Used to mention if the operation status is pending approval
	 *                   or not
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getReceivedPermisions(String token, String entityName, Boolean pending)
			throws RestResponseException;

	/**
	 * List all given permissions of the logged in user on the entity received as
	 * parameter. Associated API: GET /security/_Permissions/Given/{EntityName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to list given requested
	 *                   permissions of the logged in user
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> getGivenPermisions(String token, String entityName) throws RestResponseException;

	/**
	 * Give permissions to the user with recordId given as parameter that requested
	 * permissions on the specified entity for the records owned by the logged in
	 * user. Associated API: POST
	 * /security/_Permissions/Given/{EntityName}/{recordId}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table
	 * @param recordId   The id of the user to give permissions on a specified
	 *                   entity to
	 * @param record     POJO record to be
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public ListenableFuture<Response> addGivePermisionsToUser(String token, String entityName, String recordId, Object record)
			throws RestResponseException;


}

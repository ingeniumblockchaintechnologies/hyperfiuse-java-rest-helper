package ro.ibt.hyperfiuse.resthelper.interfaces;

import java.util.List;

import ro.ibt.hyperfiuse.resthelper.enums.RecordPermission;
import ro.ibt.hyperfiuse.resthelper.exceptions.RestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;
import ro.ibt.querybuildercreator.models.RuleGroup;

public interface DataApiSync {
	/**
	 * List all JSON schemas defined for all entities. Associated API: GET
	 * /_catalog/_JsonSchema
	 * 
	 * @param token          User token
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip           Archive response
	 * @param skip           Number of records to skip
	 * @param limit          Max records to retrieve
	 * @return ResponseObjectList
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObjectList listEntitiesJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip,
			int limit) throws RestResponseException;

	/**
	 * List JSON schema details for specified entity. Associated API: GET
	 * /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table to retrieve from the system
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip           Archive response
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject getEntityJsonSchema(String token, String entityName, boolean integrityCheck, boolean gZip)
			throws RestResponseException;

	/**
	 * Create a entity/table/collection based on a JSON string schema. Associated
	 * API: POST /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to be created
	 * @param jsonSchema JSON schema on which base the entity is created
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject createEntityJsonSchema(String token, String entityName, String jsonSchema)
			throws RestResponseException;

	/**
	 * Update a entity/table/collection based on a JSON string schema. Associated
	 * API: PUT /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to be updated
	 * @param jsonSchema Updated version of JSON schema
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject updateEntityJsonSchema(String token, String entityName, String jsonSchema)
			throws RestResponseException;

	/**
	 * Insert a record into the specified entity. The body is validated and
	 * sanitized by the JSON schema. Associated API: POST /data/{schemaName}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table to insert a record into
	 * @param record     POJO record to be added in the entity/table
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject insertRecord(String token, String entityName, Object record) throws RestResponseException;

	/**
	 * List all available records for the specified entity. Associated API: GET
	 * /data/{schemaName}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table to list records from
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip           Archive response
	 * @param skip           Number of records to skip
	 * @param limit          Max records to retrieve
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObjectList listRecords(String token, String entityName, boolean integrityCheck, boolean gZip, int skip,
			int limit) throws RestResponseException;

	/**
	 * List a specific record for the specified entity and record id. Associated
	 * API: GET /data/{EntityName}/{recordId}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table to retrieve the record from
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip           Archive response
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject getRecord(String token, String entityName, String recordId, boolean integrityCheck,
			boolean gZip) throws RestResponseException;

	/**
	 * Update a record from the specified entity and record id. The body is
	 * validated and sanitized by the JSON schema. Record to be updated must be
	 * provided in JSON format. Associated API: PUT /data/{EntityName}/{recordId}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity/table containing the record to be
	 *                   updated
	 * @param recordId   The id of the record to be updated
	 * @param jsonRecord String JSON version of the record to be updated
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject updateRecord(String token, String entityName, String recordId, String jsonRecord)
			throws RestResponseException;

	/**
	 * Update a record from the specified entity and record id. The body is
	 * validated and sanitized by the JSON schema. Record to be updated should be
	 * provided in a POJO. Associated API: PUT /data/{EntityName}/{recordId}
	 * 
	 * @param token        User token
	 * @param entityName   Name of the entity/table containing the record to be
	 *                     updated
	 * @param recordId     The id of the record to be updated
	 * @param objectRecord POJO version of the record to be updated
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject updateRecord(String token, String entityName, String recordId, Object objectRecord)
			throws RestResponseException;

	/**
	 * Perform a filtered query on an entity. String query is created following
	 * JQuery QueryBuilder https://querybuilder.js.org/demo.html. Associated API:
	 * POST /data/advancedFilter/{EntityName}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table on which the query is
	 *                       performed
	 * @param queryJson      JSON version of the QueryBuilder query
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip           Archive response
	 * @param skip           Number of records to skip
	 * @param limit          Max records to retrieve
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObjectList filter(String token, String entityName, String queryJson, boolean integrityCheck,
			boolean gZip, int skip, int limit) throws RestResponseException;

	/**
	 * Perform a filtered query on an entity. The query object is created following
	 * JQuery QueryBuilder https://querybuilder.js.org/demo.html and using
	 * jquery-querybuilder-query-creator library . Associated API: POST
	 * /data/advancedFilter/{EntityName}
	 * 
	 * @param token          User token
	 * @param entityName     Name of the entity/table on which the query is
	 *                       performed
	 * @param queryObject    POJO version of the QueryBuilder query
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip           Archive response
	 * @param skip           Number of records to skip
	 * @param limit          Max records to retrieve
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObjectList filter(String token, String entityName, RuleGroup queryObject, boolean integrityCheck,
			boolean gZip, int skip, int limit) throws RestResponseException;

	/**
	 * Insert a bulk of records in a particular entity. The body is validated and
	 * sanitized by the JSON schema. Associated API: POST /data/bulk/{EntityName}
	 * 
	 * @param token       User token
	 * @param entityName  Name of the entity/table where the records will be
	 *                    inserted
	 * @param recordsList A list of POJO objects where each element contains the
	 *                    object version of the record to be inserted
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObjectList insertBulk(String token, String entityName, List<?> recordsList)
			throws RestResponseException;

	/**
	 * This API will be executed on partial nodes, when a particular record is not
	 * found. This API call will retrieve the record from a full node. Associated
	 * API: PUT /data/reqsync/{EntityName}/{recordId}
	 * 
	 * @param token      User token
	 * @param entityName Name of the entity containing the record to be synchronized
	 *                   from a full node
	 * @param recordId   The id of the record to be synchronized
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject requestSync(String token, String entityName, String recordId) throws RestResponseException;

	/**
	 * Upload a valid license for the entire system. The body is form-data-type and
	 * the parameters must include the license file. Send and Download should be
	 * selected instead of just Send. Associated API: POST /system/serverLicense
	 * 
	 * @param token User token
	 * @return
	 * @throws RestResponseException
	 */
	public String uploadServerLicence(String token) throws RestResponseException;

	/**
	 * Get the serial code for the server. Associated API: GET /system/serverCode
	 * 
	 * @param token User token
	 * @return
	 * @throws RestResponseException
	 */
	public String getServerCode(String token) throws RestResponseException;

	/**
	 * List the system information. Associated API: GET /system/sysInfo
	 * 
	 * @param token User token
	 * @return
	 * @throws RestResponseException
	 */
	public String getSysInfo(String token) throws RestResponseException;

	/**
	 * List all the past versions of the specified entity name and record id.
	 * Associated API: GET /system/history/{EntityName}/{recordId}
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
	public RecordObjectList getEntityHistory(String token, String entityName, String recordId, Integer version,
			Boolean integrityCheck, Integer skip, Integer limit) throws RestResponseException;

	/**
	 * List the invalid versions of the specified entity name and record id.
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
	public RecordObjectList getEntityInvalid(String token, String entityName, String recordId, Boolean integrityCheck,
			Integer skip, Integer limit) throws RestResponseException;

	/**
	 * Create a new event. An event occurs when a new record is inserted or when an
	 * existing record is updated. Associated API: POST /system/addEvent
	 * 
	 * @param token            User token
	 * @param recordPermission used to mention if the record can be accessed by everyone (PUBLIC), only by the users who have the permission to access it (PERMISSIONED), or only by the admin (PRIVATE). This parameter is not required.
	 * @param record POJO record to be be used to add the specified event
	 * @return
	 * @throws RestResponseException
	 */
	public RecordObject addEvent(String token, RecordPermission recordPermission, RecordObject record) throws RestResponseException;

	/**
	 * Update the event with record id given as parameter. Associated API: PUT
	 * /system/updateEvent/{recordId}
	 * 
	 * @param token    User token
	 * @param recordId The id of the event record to be updated
	 * @param recordPermission used to mention if the record can be accessed by everyone (PUBLIC), only by the users who have the permission to access it (PERMISSIONED), or only by the admin (PRIVATE). This parameter is not required.
	 * @param record   POJO record to be be used to update the specified event by
	 *                 record id
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObject updateEvent(String token, String recordId, RecordPermission recordPermission, Object record)
			throws RestResponseException;

	/**
	 * List all the events with the owner id given as parameter. Associated API: GET
	 * /system/listEvents/{ownerId}
	 * 
	 * @param token       User token
	 * @param ownerId     The id of the owner user
	 * @param forceReload Used to force the cache to reload. Default has false
	 *                    value.
	 * @return
	 * @throws RestResponseException The exception which is thrown if something did
	 *                               not go as expected
	 */
	public RecordObjectList getEventList(String token, String ownerId, Boolean forceReload)
			throws RestResponseException;

}

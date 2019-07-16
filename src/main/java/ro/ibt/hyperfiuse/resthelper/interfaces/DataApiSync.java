package ro.ibt.hyperfiuse.resthelper.interfaces;

import java.util.List;

import ro.ibt.hyperfiuse.resthelper.exceptions.DataRestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;
import ro.ibt.querybuildercreator.models.RuleGroup;

public interface DataApiSync
{
	/**
	 * List all JSON schemas defined for all entities. Associated API: GET /_catalog/_JsonSchema
	 * 
	 * @param token User token
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @param skip Number of records to skip
	 * @param limit Max records to retrieve
	 * @return ResponseObjectList
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObjectList listEntitiesJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * List JSON schema details for specified entity. Associated API: GET /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table to retrieve from the system
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject getEntityJsonSchema(String token, String entityName, boolean integrityCheck, boolean gZip) throws DataRestResponseException;

	/**
	 * Create a entity/table/collection based on a JSON string schema. Associated API: POST /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table to be created
	 * @param jsonSchema JSON schema on which base the entity is created
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject createEntityJsonSchema(String token, String entityName, String jsonSchema) throws DataRestResponseException;

	/**
	 * Update a entity/table/collection based on a JSON string schema. Associated API: PUT /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table to be updated
	 * @param jsonSchema Updated version of JSON schema
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject updateEntityJsonSchema(String token, String entityName, String jsonSchema) throws DataRestResponseException;

	/**
	 * Insert a record into the specified entity. The body is validated and sanitized by the JSON schema. Associated API: POST /data/{schemaName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table to insert a record into
	 * @param record POJO record to be added in the entity/table
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject insertRecord(String token, String entityName, Object record) throws DataRestResponseException;

	/**
	 * List all available records for the specified entity. Associated API: GET /data/{schemaName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table to list records from
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @param skip Number of records to skip
	 * @param limit Max records to retrieve
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObjectList listRecords(String token, String entityName, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * List a specific record for the specified entity and record id. Associated API: GET /data/{EntityName}/{recordId}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table to retrieve the record from
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject getRecord(String token, String entityName, String recordId, boolean integrityCheck, boolean gZip) throws DataRestResponseException;

	/**
	 * Update a record from the specified entity and record id. The body is validated and sanitized by the JSON schema. Record to be updated must be provided in JSON format. Associated API: PUT /data/{EntityName}/{recordId}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table containing the record to be updated
	 * @param recordId The id of the record to be updated
	 * @param jsonRecord String JSON version of the record to be updated
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject updateRecord(String token, String entityName, String recordId, String jsonRecord) throws DataRestResponseException;

	/**
	 * Update a record from the specified entity and record id. The body is validated and sanitized by the JSON schema. Record to be updated should be provided in a POJO. Associated API: PUT /data/{EntityName}/{recordId}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table containing the record to be updated
	 * @param recordId The id of the record to be updated
	 * @param objectRecord POJO version of the record to be updated
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject updateRecord(String token, String entityName, String recordId, Object objectRecord) throws DataRestResponseException;

	/**
	 * Perform a filtered query on an entity. String query is created following JQuery QueryBuilder https://querybuilder.js.org/demo.html. Associated API: POST /data/advancedFilter/{EntityName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table on which the query is performed
	 * @param queryJson JSON version of the QueryBuilder query
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @param skip Number of records to skip
	 * @param limit Max records to retrieve
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObjectList filter(String token, String entityName, String queryJson, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * Perform a filtered query on an entity. The query object is created following JQuery QueryBuilder https://querybuilder.js.org/demo.html and using jquery-querybuilder-query-creator library . Associated API: POST /data/advancedFilter/{EntityName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table on which the query is performed
	 * @param queryObject POJO version of the QueryBuilder query
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @param skip Number of records to skip
	 * @param limit Max records to retrieve
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObjectList filter(String token, String entityName, RuleGroup queryObject, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * Insert a bulk of records in a particular entity. The body is validated and sanitized by the JSON schema. Associated API: POST /data/bulk/{EntityName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity/table where the records will be inserted
	 * @param recordsList A list of POJO objects where each element contains the object version of the record to be inserted
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObjectList insertBulk(String token, String entityName, List<?> recordsList) throws DataRestResponseException;

	/**
	 * This API will be executed on partial nodes, when a particular record is not found. This API call will retrieve the record from a full node. Associated API: PUT /data/reqsync/{EntityName}/{recordId}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity containing the record to be synchronized from a full node
	 * @param recordId The id of the record to be synchronized
	 * @return
	 * @throws DataRestResponseException The exception which is thrown if something did not go as expected
	 */
	public RecordObject requestSync(String token, String entityName, String recordId) throws DataRestResponseException;
}

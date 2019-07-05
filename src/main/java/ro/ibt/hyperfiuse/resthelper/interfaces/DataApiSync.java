package ro.ibt.hyperfiuse.resthelper.interfaces;

import ro.ibt.hyperfiuse.resthelper.classes.JsonRule;
import ro.ibt.hyperfiuse.resthelper.exceptions.DataRestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.RecordObjectList;

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
	 * @throws DataRestResponseException
	 */
	public RecordObjectList listJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * List JSON schema details for specified schema/table. Associated API: GET /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token User token
	 * @param schemaName Name of the schema/table to retrieve from the system
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObject getJsonSchema(String token, String schemaName, boolean integrityCheck, boolean gZip) throws DataRestResponseException;

	/**
	 * Create a schema/table/collection based on a JSON string schema. Associated API: POST /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token
	 * @param schemaName
	 * @param jsonSchema
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObject createJsonSchema(String token, String schemaName, String jsonSchema) throws DataRestResponseException;

	/**
	 * Update a schema/table/collection based on a JSON string schema. Associated API: PUT /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token
	 * @param schemaName
	 * @param jsonSchema
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObject updateJsonSchema(String token, String schemaName, String jsonSchema) throws DataRestResponseException;

	/**
	 * Insert a record into the specified schema. The body is validated and sanitized by the JSON schema. Associated API: POST /data/{schemaName}
	 * 
	 * @param token
	 * @param schemaName
	 * @param record
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObject insertRecord(String token, String schemaName, Object record) throws DataRestResponseException;

	/**
	 * List all available records for the specified schema. Associated API: GET /data/{schemaName}
	 * 
	 * @param token
	 * @param schemaName
	 * @param integrityCheck
	 * @param gZip
	 * @param skip
	 * @param limit
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObjectList listRecords(String token, String schemaName, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * List a specific record for the specified schema and record id. Associated API: GET /data/{EntityName}/{recordId}
	 * 
	 * @param token
	 * @param schemaName
	 * @param integrityCheck
	 * @param gZip
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObject getRecord(String token, String schemaName, String recordId, boolean integrityCheck, boolean gZip) throws DataRestResponseException;

	/**
	 * Update a record from the specified schema and record id. The body is validated and sanitized by the JSON schema. Record to be updated must be provided in JSON format. Associated API: PUT /data/{EntityName}/{recordId}
	 * 
	 * @param token
	 * @param schemaName
	 * @param recordId
	 * @param jsonRecord
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObject updateRecord(String token, String schemaName, String recordId, String jsonRecord) throws DataRestResponseException;

	/**
	 * Update a record from the specified schema and record id. The body is validated and sanitized by the JSON schema. Record to be updated should be provided in a POJO. Associated API: PUT /data/{EntityName}/{recordId}
	 * 
	 * @param token
	 * @param schemaName
	 * @param recordId
	 * @param objectRecord
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObject updateRecord(String token, String schemaName, String recordId, Object objectRecord) throws DataRestResponseException;

	/**
	 * Perform a filtered query on a schema. String query is created following JQuery QueryBuilder https://querybuilder.js.org/demo.html. Associated API: POST /data/{EntityName}/{recordId}
	 * 
	 * @param token
	 * @param schemaName
	 * @param queryJson
	 * @param integrityCheck
	 * @param gZip
	 * @param skip
	 * @param limit
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObjectList filter(String token, String schemaName, String queryJson, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * Perform a filtered query on a schema. JsonRule object creates a query following rules of JQuery QueryBuilder https://querybuilder.js.org/demo.html. Associated API: POST /data/{EntityName}/{recordId}
	 * 
	 * @param token
	 * @param schemaName
	 * @param queryBuilder
	 * @param integrityCheck
	 * @param gZip
	 * @param skip
	 * @param limit
	 * @return
	 * @throws DataRestResponseException
	 */
	public RecordObjectList filter(String token, String schemaName, JsonRule queryBuilder, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

}

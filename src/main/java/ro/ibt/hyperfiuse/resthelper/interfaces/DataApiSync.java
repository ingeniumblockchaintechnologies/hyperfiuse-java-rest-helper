package ro.ibt.hyperfiuse.resthelper.interfaces;

import ro.ibt.hyperfiuse.resthelper.exceptions.DataRestResponseException;
import ro.ibt.hyperfiuse.resthelper.rest.models.ResponseObject;
import ro.ibt.hyperfiuse.resthelper.rest.models.ResponseObjectList;

public interface DataApiSync
{
	/**
	 * List all JSON schemas defined for all entities. Associated API: /_catalog/_JsonSchema
	 * 
	 * @param token User token
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @param skip Number of records to skip
	 * @param limit Max records to retrieve
	 * @return ResponseObjectList
	 * @throws DataRestResponseException
	 */
	public ResponseObjectList listJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip, int limit) throws DataRestResponseException;

	/**
	 * List JSON schema details for specified schema/table. Associated API: /_catalog/_JsonSchema/{schemaName}
	 * 
	 * @param token User token
	 * @param schemaName Name of the schema/table to retrieve from the system
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @return
	 * @throws DataRestResponseException
	 */
	public ResponseObject getJsonSchema(String token, String schemaName, boolean integrityCheck, boolean gZip) throws DataRestResponseException;

	/**
	 * Create a schema/table/collection based on a JSON string schema
	 * 
	 * @param token
	 * @param schemaName
	 * @param jsonSchema
	 * @return
	 * @throws DataRestResponseException
	 */
	public ResponseObject createJsonSchema(String token, String schemaName, String jsonSchema) throws DataRestResponseException;

	/**
	 * Update a schema/table/collection based on a JSON string schema
	 * 
	 * @param token
	 * @param schemaName
	 * @param jsonSchema
	 * @return
	 * @throws DataRestResponseException
	 */
	public ResponseObject updateJsonSchema(String token, String schemaName, String jsonSchema) throws DataRestResponseException;
}

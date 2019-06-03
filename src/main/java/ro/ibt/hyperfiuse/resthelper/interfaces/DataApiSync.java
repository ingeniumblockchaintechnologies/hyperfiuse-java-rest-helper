package ro.ibt.hyperfiuse.resthelper.interfaces;

import java.util.concurrent.ExecutionException;

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
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public ResponseObjectList listJsonSchemas(String token, boolean integrityCheck, boolean gZip, int skip, int limit) throws InterruptedException, ExecutionException;

	/**
	 * List JSON schema details for specified entity. Associated API: /_catalog/_JsonSchema/{entityName}
	 * 
	 * @param token User token
	 * @param entityName Name of the entity to retrieve from the system
	 * @param integrityCheck Perform read with record integrity check
	 * @param gZip Archive response
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public ResponseObject getJsonSchema(String token, String entityName, boolean integrityCheck, boolean gZip) throws InterruptedException, ExecutionException;
}

package ro.ibt.hyperfiuse.resthelper.rest.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import ro.ibt.hyperfiuse.resthelper.rest.data.DataRestResponse;

public class RecordObject extends DataRestResponse
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7671405045652381955L;

	@SerializedName("_id")
	private String id;

	private Metadata metadata;

	private Object data;

	private boolean validated;

	private String permission;

	/**
	 * @return the id
	 */
	public String getId() {

		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {

		this.id = id;
	}

	/**
	 * @return the metadata
	 */
	public Metadata getMetadata() {

		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Metadata metadata) {

		this.metadata = metadata;
	}

	/**
	 * @return the validated
	 */
	public boolean isValidated() {

		return validated;
	}

	/**
	 * @param validated the validated to set
	 */
	public void setValidated(boolean validated) {

		this.validated = validated;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {

		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {

		this.permission = permission;
	}

	/**
	 * Retrieve entity, converted to client class which has to be provided in the parameter. In case of a failed conversion a ClassCastException is raised
	 * 
	 * @param classOfEntity
	 * @return
	 * @return
	 */
	public <T extends Object> T getData(Class<T> classOfSchema) {

		// retrieve the entity from the object
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(data), classOfSchema);
	}

	/**
	 * Retrieve entity, converted to client class which has to be provided in the parameter. In case of a failed conversion a ClassCastException is raised
	 * 
	 * @param classOfEntity
	 * @return
	 * @return
	 */
	public String getData() {

		// retrieve the entity JSON string from the object
		return (new Gson()).toJson(data);
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {

		this.data = data;
	}
}

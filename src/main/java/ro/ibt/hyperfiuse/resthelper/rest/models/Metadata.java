package ro.ibt.hyperfiuse.resthelper.rest.models;

import com.google.gson.annotations.SerializedName;

import ro.ibt.hyperfiuse.resthelper.enums.StatusType;

public class Metadata
{
	@SerializedName("_id")
	private String id;

	private String blockId;

	private String previousBlockId;

	private String recordId;

	private String ownerId;

	private Integer version;

	private String previousRecordBlockId;

	private String transactionId;

	private String schemaForType;

	private StatusType status;

	private String createdBy;

	private String createdAt;

	private String modifiedBy;

	private String modifiedAt;

	private String nodeId;

	private boolean systemEntity;

	private String recordSignature;

	private String recordPermission;

	private String networkIdentifier;

	private String dataType;

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
	 * @return the blockId
	 */
	public String getBlockId() {

		return blockId;
	}

	/**
	 * @param blockId the blockId to set
	 */
	public void setBlockId(String blockId) {

		this.blockId = blockId;
	}

	/**
	 * @return the previousBlockId
	 */
	public String getPreviousBlockId() {

		return previousBlockId;
	}

	/**
	 * @param previousBlockId the previousBlockId to set
	 */
	public void setPreviousBlockId(String previousBlockId) {

		this.previousBlockId = previousBlockId;
	}

	/**
	 * @return the recordId
	 */
	public String getRecordId() {

		return recordId;
	}

	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(String recordId) {

		this.recordId = recordId;
	}

	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {

		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {

		this.ownerId = ownerId;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {

		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {

		this.version = version;
	}

	/**
	 * @return the previousRecordBlockId
	 */
	public String getPreviousRecordBlockId() {

		return previousRecordBlockId;
	}

	/**
	 * @param previousRecordBlockId the previousRecordBlockId to set
	 */
	public void setPreviousRecordBlockId(String previousRecordBlockId) {

		this.previousRecordBlockId = previousRecordBlockId;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {

		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {

		this.transactionId = transactionId;
	}

	/**
	 * @return the schemaForType
	 */
	public String getSchemaForType() {

		return schemaForType;
	}

	/**
	 * @param schemaForType the schemaForType to set
	 */
	public void setSchemaForType(String schemaForType) {

		this.schemaForType = schemaForType;
	}

	/**
	 * @return the status
	 */
	public StatusType getStatus() {

		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {

		this.status = status;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {

		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {

		this.createdBy = createdBy;
	}

	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {

		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {

		this.createdAt = createdAt;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {

		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {

		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedAt
	 */
	public String getModifiedAt() {

		return modifiedAt;
	}

	/**
	 * @param modifiedAt the modifiedAt to set
	 */
	public void setModifiedAt(String modifiedAt) {

		this.modifiedAt = modifiedAt;
	}

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {

		return nodeId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {

		this.nodeId = nodeId;
	}

	/**
	 * @return the systemEntity
	 */
	public boolean isSystemEntity() {

		return systemEntity;
	}

	/**
	 * @param systemEntity the systemEntity to set
	 */
	public void setSystemEntity(boolean systemEntity) {

		this.systemEntity = systemEntity;
	}

	/**
	 * @return the recordSignature
	 */
	public String getRecordSignature() {

		return recordSignature;
	}

	/**
	 * @param recordSignature the recordSignature to set
	 */
	public void setRecordSignature(String recordSignature) {

		this.recordSignature = recordSignature;
	}

	/**
	 * @return the recordPermission
	 */
	public String getRecordPermission() {

		return recordPermission;
	}

	/**
	 * @param recordPermission the recordPermission to set
	 */
	public void setRecordPermission(String recordPermission) {

		this.recordPermission = recordPermission;
	}

	/**
	 * @return the networkIdentifier
	 */
	public String getNetworkIdentifier() {

		return networkIdentifier;
	}

	/**
	 * @param networkIdentifier the networkIdentifier to set
	 */
	public void setNetworkIdentifier(String networkIdentifier) {

		this.networkIdentifier = networkIdentifier;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {

		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {

		this.dataType = dataType;
	}

}

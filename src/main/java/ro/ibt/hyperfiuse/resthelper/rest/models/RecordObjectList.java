package ro.ibt.hyperfiuse.resthelper.rest.models;

import java.util.ArrayList;
import java.util.List;

import ro.ibt.hyperfiuse.resthelper.rest.data.DataRestResponse;

public class RecordObjectList extends DataRestResponse
{
	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -5586921056857723190L;

	/**
	 * Data field containing the list of received objects
	 */
	List<RecordObject> data;

	/**
	 * @return the data
	 */
	public List<RecordObject> getData() {

		return data != null ? data : (data = new ArrayList<>());
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<RecordObject> data) {

		this.data = data;
	}
}

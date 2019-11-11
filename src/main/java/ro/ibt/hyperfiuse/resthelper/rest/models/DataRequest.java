package ro.ibt.hyperfiuse.resthelper.rest.models;

public class DataRequest
{
	private Object data;

	/**
	 * Default constructor
	 */
	public DataRequest() {

		super();
	}

	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public DataRequest(Object data) {

		this.data = data;
	}

	/**
	 * @return the data
	 */
	public Object getData() {

		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {

		this.data = data;
	}
}

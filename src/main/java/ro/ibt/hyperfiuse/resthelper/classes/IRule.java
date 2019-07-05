package ro.ibt.hyperfiuse.resthelper.classes;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * 
 * @author: hewei
 * @time:2017/10/31 13:10 ---------------------------------------------------------------------------
 */
public interface IRule
{
	/**
	 * Getter method for property <tt>id</tt>.
	 * 
	 * @return property value of id
	 * @author hewei
	 */
	String getId();

	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id value to be assigned to property id
	 * @author hewei
	 */
	void setId(String id);

	/**
	 * Getter method for property <tt>field</tt>.
	 * 
	 * @return property value of field
	 * @author hewei
	 */
	String getField();

	/**
	 * Setter method for property <tt>field</tt>.
	 * 
	 * @param field value to be assigned to property field
	 * @author hewei
	 */
	void setField(String field);

	/**
	 * Getter method for property <tt>type</tt>.
	 * 
	 * @return property value of type
	 * @author hewei
	 */
	String getType();

	/**
	 * Setter method for property <tt>type</tt>.
	 * 
	 * @param type value to be assigned to property type
	 * @author hewei
	 */
	void setType(String type);

	/**
	 * Getter method for property <tt>input</tt>.
	 * 
	 * @return property value of input
	 * @author hewei
	 */
	String getInput();

	/**
	 * Setter method for property <tt>input</tt>.
	 * 
	 * @param input value to be assigned to property input
	 * @author hewei
	 */
	void setInput(String input);

	/**
	 * Getter method for property <tt>operator</tt>.
	 * 
	 * @return property value of operator
	 * @author hewei
	 */
	String getOperator();

	/**
	 * Setter method for property <tt>operator</tt>.
	 * 
	 * @param operator value to be assigned to property operator
	 * @author hewei
	 */
	void setOperator(String operator);

	/**
	 * Getter method for property <tt>value</tt>.
	 * 
	 * @return property value of value
	 * @author hewei
	 */
	Object getValue();

	/**
	 * Setter method for property <tt>value</tt>.
	 * 
	 * @param value value to be assigned to property value
	 * @author hewei
	 */
	void setValue(Object value);

	/**
	 * Getter method for property <tt>data</tt>.
	 * 
	 * @return property value of value
	 * @author hewei
	 */
	Object getData();

	/**
	 * Setter method for property <tt>data</tt>.
	 * 
	 * @param value value to be assigned to property value
	 * @author hewei
	 */
	void setData(Object value);
}

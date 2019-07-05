package ro.ibt.hyperfiuse.resthelper.classes;

import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * 
 * @author: hewei
 * @time:2017/10/31 13:10 ---------------------------------------------------------------------------
 */
public interface IGroup
{
	/**
	 * Getter method for property <tt>condition</tt>.
	 * 
	 * @return property value of condition
	 * @author hewei
	 */
	String getCondition();

	/**
	 * Setter method for property <tt>condition</tt>.
	 * 
	 * @param condition value to be assigned to property condition
	 * @author hewei
	 */
	void setCondition(String condition);

	/**
	 * Getter method for property <tt>not</tt>.
	 * 
	 * @return property value of not
	 * @author hewei
	 */
	Boolean getNot();

	/**
	 * Setter method for property <tt>not</tt>.
	 * 
	 * @param not value to be assigned to property not
	 * @author hewei
	 */
	void setNot(Boolean not);

	/**
	 * Getter method for property <tt>rules</tt>.
	 * 
	 * @return property value of rules
	 * @author hewei
	 */
	List<JsonRule> getRules();

	/**
	 * Setter method for property <tt>rules</tt>.
	 * 
	 * @param rules value to be assigned to property rules
	 * @author hewei
	 */
	void setRules(List<JsonRule> rules);

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

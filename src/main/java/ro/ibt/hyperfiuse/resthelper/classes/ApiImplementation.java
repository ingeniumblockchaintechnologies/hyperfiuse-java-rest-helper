package ro.ibt.hyperfiuse.resthelper.classes;

import ro.ibt.hyperfiuse.resthelper.HyperFiuseApi;

public abstract class ApiImplementation
{
	/**
	 * Root/parent instance that holds the OAuthToken and Configuration instance.
	 */
	private HyperFiuseApi root;

	protected HyperFiuseApi getRoot() {

		return root;
	}

	/**
	 * Creates new API instance.
	 * 
	 * @param root Root/parent instance that holds the OAuthToken and Configuration instance.
	 */
	public ApiImplementation(HyperFiuseApi root) {

		this.root = root;
	}
}

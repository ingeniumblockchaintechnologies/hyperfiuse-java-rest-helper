package ro.ibt.hyperfiuse.resthelper;

import java.io.IOException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;

import ro.ibt.hyperfiuse.resthelper.classes.Configuration;
import ro.ibt.hyperfiuse.resthelper.implementations.AuthenticationApiSyncImplementation;
import ro.ibt.hyperfiuse.resthelper.implementations.DataApiSyncImplementation;
import ro.ibt.hyperfiuse.resthelper.interfaces.AuthenticationApiSync;
import ro.ibt.hyperfiuse.resthelper.interfaces.DataApiSync;

public class HyperFiuseApi
{
	/**
	 * Configuration instance with default settings (to be reset if required).
	 */
	private Configuration configuration;

	/**
	 * Sync version of authentication node API
	 */
	private AuthenticationApiSync authenticationApiSync;

	/**
	 * Sync version of data node API
	 */
	private DataApiSync dataApiSync;

	/**
	 * Async Http client - will be used for server HTTP calls, must be reused within the application
	 */
	private AsyncHttpClient asyncHttpClient;

	/**
	 * Constructor init jobs
	 */
	public HyperFiuseApi(Configuration configuration) {

		// default configuration setup
		this.configuration = configuration;

		// TODO validate configurations
		// private String clientId = "";
		// private String clientSecret = "";
		// private String dataNodeUrl = "";
		// private String authenticationNodeUrl = "";
		// create the HTTP client object
		setUpAsyncHttpClient();

		// set api's
		this.authenticationApiSync = new AuthenticationApiSyncImplementation(this);
		this.dataApiSync = new DataApiSyncImplementation(this);
	}

	private void setUpAsyncHttpClient() {

		// set async HTTP client builder
		DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config();

		// set up read time out
		clientBuilder.setReadTimeout(configuration.getReadTimeout());

		// set up connection time out
		clientBuilder.setConnectTimeout(configuration.getConnectTimeout());

		// set User Agent
		clientBuilder.setUserAgent(String.format("HyperFiuse Java Helper %s", configuration.getVersion()));

		// TODO extend with more configurable options for HTTP client

		// create the HTTP client object
		this.asyncHttpClient = Dsl.asyncHttpClient(clientBuilder);
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {

		return configuration;
	}

	/**
	 * @return the authenticationApiSync
	 */
	public AuthenticationApiSync getAuthenticationApiSync() {

		return authenticationApiSync;
	}

	/**
	 * @return the dataApiSync
	 */
	public DataApiSync getDataApiSync() {

		return dataApiSync;
	}

	/**
	 * @return the asyncHttpClient
	 */
	public AsyncHttpClient getAsyncHttpClient() {

		return asyncHttpClient;
	}

	/**
	 * Close AsyncHttpClient connections
	 */
	public void closeClient() {

		if (this.asyncHttpClient != null && !this.asyncHttpClient.isClosed()) {

			try {

				this.asyncHttpClient.close();
			}
			catch (IOException e) {

				// never happens
			}
		}
	}
}

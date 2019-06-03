package ro.ibt.hyperfiuse.resthelper.classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configuration settings.
 */
public class Configuration
{

	/**
	 * Client identifier.
	 */
	private String clientId = "";

	/**
	 * Client secret.
	 */
	private String clientSecret = "";

	/**
	 * Data node api url.
	 */
	private String dataNodeUrl = "";

	/**
	 * Authorization node api url.
	 */
	private String authorizationNodeUrl = "";

	/**
	 * [INTERNAL USAGE ONLY] Switch debug mode: log all request and response data
	 */
	private boolean debugMode = false;

	/**
	 * Connection Timeout.
	 */
	private int connectTimeout = 6000;

	/**
	 * Connection Read Timeout.
	 */
	private int readTimeout = 3000;

	/**
	 * SDK Version
	 */
	private String version = "0.1";

	/**
	 * Get SDK Version
	 * 
	 * @return String HyperFiuse Version
	 */
	public String getVersion() {

		if (version == null) {

			version = readHyperFiuseVersion();
		}

		return version;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {

		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {

		this.clientId = clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {

		return clientSecret;
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {

		this.clientSecret = clientSecret;
	}

	/**
	 * @return the dataNodeUrl
	 */
	public String getDataNodeUrl() {

		return dataNodeUrl;
	}

	/**
	 * @param dataNodeUrl the dataNodeUrl to set
	 */
	public void setDataNodeUrl(String dataNodeUrl) {

		this.dataNodeUrl = dataNodeUrl;
	}

	/**
	 * @return the authNodeUrl
	 */
	public String getAuthorizationNodeUrl() {

		return authorizationNodeUrl;
	}

	/**
	 * @param authNodeUrl the authNodeUrl to set
	 */
	public void setAuthorizationNodeUrl(String authorizationNodeUrl) {

		this.authorizationNodeUrl = authorizationNodeUrl;
	}

	/**
	 * @return the debugMode
	 */
	public boolean isDebugMode() {

		return debugMode;
	}

	/**
	 * @param debugMode the debugMode to set
	 */
	public void setDebugMode(boolean debugMode) {

		this.debugMode = debugMode;
	}

	/**
	 * @return the connectTimeout
	 */
	public int getConnectTimeout() {

		return connectTimeout;
	}

	/**
	 * @param connectTimeout the connectTimeout to set
	 */
	public void setConnectTimeout(int connectTimeout) {

		this.connectTimeout = connectTimeout;
	}

	/**
	 * @return the readTimeout
	 */
	public int getReadTimeout() {

		return readTimeout;
	}

	/**
	 * @param readTimeout the readTimeout to set
	 */
	public void setReadTimeout(int readTimeout) {

		this.readTimeout = readTimeout;
	}

	/**
	 * Read HyperFiuse version from HyperFiuse properties
	 * 
	 * @return String HyperFiuse Version
	 */
	private String readHyperFiuseVersion() {

		try {

			Properties properties = new Properties();
			InputStream inputStream = getClass().getResourceAsStream("hyperfiuse.properties");
			properties.load(inputStream);
			return properties.getProperty("version");
		}
		catch (IOException ex) {

			Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
		}

		return "";
	}
}

package ro.ibt.hyperfiuse.resthelper.rest.auth;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * OAuth entity.
 */
public class OAuthToken extends OAuthRestResponse implements Serializable
{

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -3084672971089820851L;

	/**
	 * Token type.
	 */
	@SerializedName("token_type")
	private String tokenType;

	/**
	 * Value of token.
	 */
	@SerializedName("access_token")
	private String accessToken;

	/**
	 * Denotes how long the token is valid, in seconds.
	 */
	@SerializedName("expires_in")
	private int expiresIn;

	/**
	 * Refresh token.
	 */
	@SerializedName("refresh_token")
	private String refreshToken;

	/**
	 * Creation time.
	 */
	@SerializedName("create_time")
	private Long createTime;

	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {

		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {

		this.createTime = createTime;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {

		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {

		this.tokenType = tokenType;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {

		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {

		this.accessToken = accessToken;
	}

	/**
	 * @return the expiresIn
	 */
	public int getExpiresIn() {

		return expiresIn;
	}

	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {

		this.expiresIn = expiresIn;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {

		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {

		this.refreshToken = refreshToken;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

	/**
	 * Instantiates new OAuthToken object.
	 */
	public OAuthToken() {

		this.createTime = System.currentTimeMillis();
	}

	/**
	 * Checks if current token is expired.
	 *
	 * @return Returns true if token has expired, or false if token is still valid.
	 */
	public Boolean IsExpired() {

		return (System.currentTimeMillis() >= (createTime + (expiresIn * 1000)));
	}
}

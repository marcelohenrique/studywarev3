package br.com.guarasoft.studyware.auth;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@ManagedBean(name = "sigin")
public class Sigin {

	/*
	 * Default HTTP transport to use to make HTTP requests.
	 */
	private static final HttpTransport TRANSPORT = new NetHttpTransport();

	/*
	 * Default JSON factory to use to deserialize JSON.
	 */
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

	/*
	 * Gson object to serialize JSON responses to requests to this servlet.
	 */
	// private static final Gson GSON = new Gson();

	/*
	 * Creates a client secrets object from the client_secrets.json file.
	 */
	private GoogleClientSecrets clientSecrets;

	/*
	 * This is the Client ID that you generated in the API Console.
	 */
	private String clientId;

	/*
	 * This is the Client Secret that you generated in the API Console.
	 */
	private String clientSecret;

	@ManagedProperty(value = "#{sessionAuth}")
	private SessionAuth sessionAuth;

	private String code;

	@PostConstruct
	private void init() {
		try {
			Reader reader = new FileReader(FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("WEB-INF/")
					+ "/client_secrets.json");
			clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);

			clientId = clientSecrets.getWeb().getClientId();
			clientSecret = clientSecrets.getWeb().getClientSecret();
		} catch (IOException e) {
			throw new Error("No client_secrets.json found", e);
		}
	}

	public String login() {
		// Only connect a user that is not already connected.
		String tokenData = sessionAuth.getToken();
		if (tokenData == null) {
			try {
//				HttpServletRequest req = (HttpServletRequest) FacesContext
//						.getCurrentInstance().getExternalContext().getRequest();
//				ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
//				getContent(req.getInputStream(), resultStream);
//				String code = new String(resultStream.toByteArray(), "UTF-8");

				// Upgrade the authorization code into an access and refresh
				// token.
				GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
						TRANSPORT, JSON_FACTORY, clientId, clientSecret, code,
						"postmessage").execute();

				// You can read the Google user ID in the ID token.
				// This sample does not use the user ID.
				// GoogleIdToken idToken = tokenResponse.parseIdToken();
				// String gplusId = idToken.getPayload().getSubject();

				// Store the token in the session for later use.
				sessionAuth.setToken(tokenResponse.toString());
			} catch (TokenResponseException e) {
				throw new Error("Failed to upgrade the authorization code.", e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new Error("Failed to read token data from Google. "
						+ e.getMessage(), e);
			}
		}
		return "main";
	}

	/*
	 * Read the content of an InputStream.
	 * 
	 * @param inputStream the InputStream to be read.
	 * 
	 * @return the content of the InputStream as a ByteArrayOutputStream.
	 * 
	 * @throws IOException
	 */
	private void getContent(InputStream inputStream,
			ByteArrayOutputStream outputStream) throws IOException {
		// Read the response into a buffered stream
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream))) {
			int readChar;
			while ((readChar = reader.read()) != -1) {
				outputStream.write(readChar);
			}
		}
		// reader.close();
	}

	public String logout() {
		// Only disconnect a connected user.
		String tokenData = sessionAuth.getToken();
		if (tokenData != null) {
			try {
				// Build credential from stored token data.
				GoogleCredential credential = new GoogleCredential.Builder()
						.setJsonFactory(JSON_FACTORY)
						.setTransport(TRANSPORT)
						.setClientSecrets(clientId, clientSecret)
						.build()
						.setFromTokenResponse(
								JSON_FACTORY.fromString(tokenData,
										GoogleTokenResponse.class));
				// Execute HTTP GET request to revoke current token.
				HttpResponse revokeResponse = TRANSPORT
						.createRequestFactory()
						.buildGetRequest(
								new GenericUrl(
										String.format(
												"https://accounts.google.com/o/oauth2/revoke?token=%s",
												credential.getAccessToken())))
						.execute();
				// Reset the user's session.
				sessionAuth.setToken(null);
			} catch (IOException e) {
				// For whatever reason, the given token was invalid.
				e.printStackTrace();
			}
		}
		return "index";
	}

	public SessionAuth getSessionAuth() {
		return sessionAuth;
	}

	public void setSessionAuth(SessionAuth sessionAuth) {
		this.sessionAuth = sessionAuth;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

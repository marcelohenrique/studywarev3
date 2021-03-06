package br.com.guarasoft.studyware.acesso.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.google.api.services.plus.model.Person.Emails;

import br.com.guarasoft.studyware.usuario.casodeuso.CadastrarUsuario;
import br.com.guarasoft.studyware.usuario.casodeuso.CadastrarUsuarioImpl;
import br.com.guarasoft.studyware.usuario.casodeuso.LoginUsuario;
import br.com.guarasoft.studyware.usuario.casodeuso.LoginUsuarioImpl;
import br.com.guarasoft.studyware.usuario.excecao.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.excecao.UsuarioInativo;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@Named("signin")
@SessionScoped
public class AcessoController implements Serializable {

	private static final long serialVersionUID = 6601991947556607654L;

	/*
	 * Default HTTP transport to use to make HTTP requests.
	 */
	private static final HttpTransport TRANSPORT = new NetHttpTransport();

	/*
	 * Default JSON factory to use to deserialize JSON.
	 */
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

	private static final String APPLICATION_NAME = "Studyware";

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

	private @Inject SessionAuth sessionAuth;

	private String code;

	private @Inject UsuarioGateway usuarioGateway;

	private LoginUsuario loginUsuario;

	private CadastrarUsuario cadastrarUsuario;

	@PostConstruct
	private void init() {
		try {
			Reader reader = new FileReader(
					FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF/")
							+ "/client_secrets.json");
			this.clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);

			this.clientId = this.clientSecrets.getWeb().getClientId();
			this.clientSecret = this.clientSecrets.getWeb().getClientSecret();

			this.loginUsuario = new LoginUsuarioImpl(this.usuarioGateway);
			this.cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
		} catch (IOException e) {
			throw new Error("No client_secrets.json found", e);
		}
	}

	public String login() {
		// Only connect a user that is not already connected.
		String tokenData = this.sessionAuth.getToken();
		if (tokenData == null) {
			try {
				// Upgrade the authorization code into an access and refresh
				// token.
				GoogleAuthorizationCodeTokenRequest request = new GoogleAuthorizationCodeTokenRequest(TRANSPORT,
						JSON_FACTORY, this.clientId, this.clientSecret, this.code, "postmessage");
				GoogleTokenResponse tokenResponse = request.execute();

				// You can read the Google user ID in the ID token.
				// This sample does not use the user ID.
				// GoogleIdToken idToken = tokenResponse.parseIdToken();
				// String gplusId = idToken.getPayload().getSubject();

				// Build credential from stored token data.
				GoogleCredential credential = new GoogleCredential.Builder().setJsonFactory(JSON_FACTORY)
						.setTransport(TRANSPORT).setClientSecrets(this.clientId, this.clientSecret).build()
						.setFromTokenResponse(tokenResponse);

				// Create a new authorized API client.
				Plus service = new Plus.Builder(TRANSPORT, JSON_FACTORY, credential)
						.setApplicationName(APPLICATION_NAME).build();

				Person mePerson = service.people().get("me").execute();

				for (Emails email : mePerson.getEmails()) {
					if ("account".equals(email.getType())) {
						Usuario usuario = this.loginUsuario.autentica(email.getValue());
						this.sessionAuth.setUsuario(usuario);
						break;
					}
				}

				// Store the token in the session for later use.
				this.sessionAuth.setToken(tokenResponse.toString());
			} catch (TokenResponseException e) {
				throw new Error("Failed to upgrade the authorization code.", e);
			} catch (IOException e) {
				throw new Error("Failed to read token data from Google. " + e.getMessage(), e);
			} catch (EmailNaoEncontrado e) {
				this.cadastrarUsuario.executar(e.getUsuario());
				return "pages/forbidden";
			} catch (UsuarioInativo e) {
				return "pages/forbidden";
			}
		}
		return "pages/main";
	}

	public String logout() {
		// Only disconnect a connected user.
		String tokenData = this.sessionAuth.getToken();
		if (tokenData != null) {
			try {
				// Build credential from stored token data.
				GoogleCredential credential = new GoogleCredential.Builder().setJsonFactory(JSON_FACTORY)
						.setTransport(TRANSPORT).setClientSecrets(this.clientId, this.clientSecret).build()
						.setFromTokenResponse(JSON_FACTORY.fromString(tokenData, GoogleTokenResponse.class));
				// Execute HTTP GET request to revoke current token.
				TRANSPORT.createRequestFactory().buildGetRequest(new GenericUrl(String
						.format("https://accounts.google.com/o/oauth2/revoke?token=%s", credential.getAccessToken())))
						.execute();
				// Reset the user's session.
				this.sessionAuth.setToken(null);
			} catch (IOException e) {
				// For whatever reason, the given token was invalid.
				e.printStackTrace();
			}
		}
		return "/index";
	}

	public SessionAuth getSessionAuth() {
		return this.sessionAuth;
	}

	public void setSessionAuth(SessionAuth sessionAuth) {
		this.sessionAuth = sessionAuth;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

package kursa4.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizationResponse {

    private int Authorization;
    private String login;
    private String Token;

    public AuthorizationResponse() {
    }

    public AuthorizationResponse(int authorization, String login, String token) {
        Authorization = authorization;
        this.login = login;
        Token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(int authorization) {
        Authorization = authorization;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}

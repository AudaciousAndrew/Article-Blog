package kursa4.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizationResponse {

    private int Authorization;
    private String Token;

    public AuthorizationResponse() {
    }

    public AuthorizationResponse(int authorization, String token) {
        Authorization = authorization;
        Token = token;
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

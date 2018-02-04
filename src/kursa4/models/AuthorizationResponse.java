package kursa4.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizationResponse {

    private boolean Authorization;
    private String Token;

    public AuthorizationResponse(boolean authorization, String token) {
        Authorization = authorization;
        Token = token;
    }

    public boolean isAuthorization() {
        return Authorization;
    }

    public void setAuthorization(boolean authorization) {
        Authorization = authorization;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}

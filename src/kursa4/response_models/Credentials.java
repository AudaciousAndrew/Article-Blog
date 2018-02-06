package kursa4.response_models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials {

    private String login , password;

    public Credentials() {
    }

    public Credentials(String login) {
        this.login = login;
    }

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

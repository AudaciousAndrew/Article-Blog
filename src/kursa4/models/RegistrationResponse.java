package kursa4.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegistrationResponse {

    private int Registration;
    private String errorMsg;

    public RegistrationResponse() {
    }

    public RegistrationResponse(int registration, String errorMsg) {
        Registration = registration;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getRegistration() {
        return Registration;
    }

    public void setRegistration(int registration) {
        Registration = registration;
    }
}

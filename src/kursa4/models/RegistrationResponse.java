package kursa4.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegistrationResponse {

    private boolean Registration;
    private String errorMsg;

    public RegistrationResponse() {
    }

    public RegistrationResponse(boolean registration, String errorMsg) {
        Registration = registration;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isRegistration() {
        return Registration;
    }

    public void setRegistration(boolean registration) {
        Registration = registration;
    }
}

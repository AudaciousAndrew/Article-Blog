package kursa4.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class voteResponse {

    private String vote;
    private String errorMsg;

    public voteResponse() {
    }

    public voteResponse(String vote, String errorMsg) {
        this.vote = vote;
        this.errorMsg = errorMsg;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

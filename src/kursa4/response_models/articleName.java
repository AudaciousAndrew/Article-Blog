package kursa4.response_models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class articleName {
    private String name;

    public articleName() {
    }

    public articleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

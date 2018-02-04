package kursa4.models;

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

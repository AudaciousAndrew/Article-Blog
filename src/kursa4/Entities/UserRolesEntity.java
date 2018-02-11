package kursa4.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "user_roles" , schema = "public" , catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "UserRoles.readAll" , query = "select p from UserRolesEntity p") ,
        @NamedQuery(name = "UserRoles.deleteAll" , query = "delete from UserRolesEntity p")
})
@XmlRootElement
public class UserRolesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id" , nullable = false , unique = true)
    private int user_role_id;


    @ManyToOne
    @JoinColumn( name = "login" , referencedColumnName = "login" , nullable = false)
    private UsersEntity userByLogin;


    @Basic
    @Column(name = "role" , nullable = false)
    private String role;

    public UserRolesEntity(UsersEntity userByLogin, String role) {
        this.userByLogin = userByLogin;
        this.role = role;
    }



    public UserRolesEntity() {
    }



    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    public UsersEntity getUserByLogin() {
        return userByLogin;
    }

    public void setUserByLogin(UsersEntity userByLogin) {
        this.userByLogin = userByLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRolesEntity{" +
                "user_role_id=" + user_role_id +
                ", userByLogin=" + userByLogin +
                ", role='" + role + '\'' +
                '}';
    }
}

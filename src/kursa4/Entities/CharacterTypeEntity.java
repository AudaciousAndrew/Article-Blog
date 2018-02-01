package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "character_type", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Ctype.readAll" , query = "select p from CharacterTypeEntity p"),
        @NamedQuery(name = "Ctype.deleteAll" , query = "delete from CharacterTypeEntity p")
})
public class CharacterTypeEntity {

    @Id
    @Column(name = "ctype_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ctypeId;

    @Basic
    @Column(name = "ctype_name" , nullable = false , length = 50)
    private String ctypeName;

    @Basic
    @Column(name = "csubtype_name" , length = 50)
    private String csubtypeName;

    @Basic
    @Column(name = "ctype_desc" , unique = true)
    private String ctypeDesc;

    @Basic
    @Column(name = "rating" , nullable = false )
    private int rating = 0;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" , nullable = false)
    private UsersEntity userByUserId;

    public UsersEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UsersEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public int getCtypeId() {
        return ctypeId;
    }

    public void setCtypeId(int ctypeId) {
        this.ctypeId = ctypeId;
    }


    public String getCtypeName() {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName) {
        this.ctypeName = ctypeName;
    }


    public String getCsubtypeName() {
        return csubtypeName;
    }

    public void setCsubtypeName(String csubtypeName) {
        this.csubtypeName = csubtypeName;
    }


    public String getCtypeDesc() {
        return ctypeDesc;
    }

    public void setCtypeDesc(String ctypeDesc) {
        this.ctypeDesc = ctypeDesc;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterTypeEntity that = (CharacterTypeEntity) o;

        if (ctypeId != that.ctypeId) return false;
        if (rating != that.rating) return false;
        if (ctypeName != null ? !ctypeName.equals(that.ctypeName) : that.ctypeName != null) return false;
        if (csubtypeName != null ? !csubtypeName.equals(that.csubtypeName) : that.csubtypeName != null) return false;
        if (ctypeDesc != null ? !ctypeDesc.equals(that.ctypeDesc) : that.ctypeDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ctypeId;
        result = 31 * result + (ctypeName != null ? ctypeName.hashCode() : 0);
        result = 31 * result + (csubtypeName != null ? csubtypeName.hashCode() : 0);
        result = 31 * result + (ctypeDesc != null ? ctypeDesc.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }
}

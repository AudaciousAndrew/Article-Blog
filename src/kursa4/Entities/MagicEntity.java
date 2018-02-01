package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "magic", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Magic.readAll" , query = "select p from MagicEntity p"),
        @NamedQuery(name = "Magic.deleteAll" , query = "delete from MagicEntity p"),

})
public class MagicEntity {

    @Id
    @Column(name = "m_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mId;

    @Basic
    @Column(name = "m_type" , nullable = false , unique = true)
    private String mType;

    @Basic
    @Column(name = "m_desc" , unique = true)
    private String mDesc;

    @Basic
    @Column(name = "rating" , nullable = false)
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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }


    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }


    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
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

        MagicEntity that = (MagicEntity) o;

        if (mId != that.mId) return false;
        if (rating != that.rating) return false;
        if (mType != null ? !mType.equals(that.mType) : that.mType != null) return false;
        if (mDesc != null ? !mDesc.equals(that.mDesc) : that.mDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mType != null ? mType.hashCode() : 0);
        result = 31 * result + (mDesc != null ? mDesc.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }
}

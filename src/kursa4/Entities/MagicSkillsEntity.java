package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "magic_skills", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "MagicSkills.readAll" , query = "Select p from MagicSkillsEntity p") ,
        @NamedQuery(name = "MagicSkills.deleteAll" , query = "delete from MagicSkillsEntity p")
})
public class MagicSkillsEntity {
    @Id
    @Column(name = "mskill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mskillId;

    @Basic
    @Column(name = "mskill_name", nullable = false, unique = true)
    private String mskillName;
    @Basic
    @Column(name = "mskill_level")
    private String mskillLevel;

    @Basic
    @Column(name = "mskill_desc", unique = true)
    private String mskillDesc;

    @Basic
    @Column(name = "rating", nullable = false)
    private int rating = 0;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" , nullable = false)
    private UsersEntity userByUserId;


    @ManyToOne
    @JoinColumn(name = "m_id" , referencedColumnName = "m_id" , nullable = false)
    private MagicEntity magicByMagicId;

    public int getMskillId() {
        return mskillId;
    }

    public void setMskillId(int mskillId) {
        this.mskillId = mskillId;
    }


    public String getMskillName() {
        return mskillName;
    }

    public void setMskillName(String mskillName) {
        this.mskillName = mskillName;
    }


    public String getMskillLevel() {
        return mskillLevel;
    }

    public void setMskillLevel(String mskillLevel) {
        this.mskillLevel = mskillLevel;
    }


    public String getMskillDesc() {
        return mskillDesc;
    }

    public void setMskillDesc(String mskillDesc) {
        this.mskillDesc = mskillDesc;
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

        MagicSkillsEntity that = (MagicSkillsEntity) o;

        if (mskillId != that.mskillId) return false;
        if (rating != that.rating) return false;
        if (mskillName != null ? !mskillName.equals(that.mskillName) : that.mskillName != null) return false;
        if (mskillLevel != null ? !mskillLevel.equals(that.mskillLevel) : that.mskillLevel != null) return false;
        if (mskillDesc != null ? !mskillDesc.equals(that.mskillDesc) : that.mskillDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mskillId;
        result = 31 * result + (mskillName != null ? mskillName.hashCode() : 0);
        result = 31 * result + (mskillLevel != null ? mskillLevel.hashCode() : 0);
        result = 31 * result + (mskillDesc != null ? mskillDesc.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }

    public UsersEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UsersEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public MagicEntity getMagicByMagicId() {
        return magicByMagicId;
    }

    public void setMagicByMagicId(MagicEntity magicByMagicId) {
        this.magicByMagicId = magicByMagicId;
    }
}

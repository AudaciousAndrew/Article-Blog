package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "technology", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Technology.readAll" , query = "Select p from TechnologyEntity p") ,
        @NamedQuery(name = "Technology.deleteAll" , query = "delete from TechnologyEntity p")
})
public class TechnologyEntity {
    @Id
    @Column(name = "mTech_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mtechId;

    @Basic
    @Column(name = "mTech_name", nullable = false, unique = true)
    private String mtechName;

    @Basic
    @Column(name = "mTech_type", nullable = false)
    private String mtechType;

    @Basic
    @Column(name = "mTech_desc", unique = true)
    private String mtechDesc;

    @Basic
    @Column(name = "rating", nullable = false)
    private int rating = 0;

    @ManyToOne
    @JoinColumn(name = "character_id" , referencedColumnName = "character_id")
    private CharacterEntity characterByCharacterId;

    @ManyToOne
    @JoinColumn(name = "nation_id" , referencedColumnName = "nation_ID")
    private NationEntity nationByNationId;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id", nullable = false)
    private UsersEntity usersByUserId;


    public int getMtechId() {
        return mtechId;
    }

    public void setMtechId(int mtechId) {
        this.mtechId = mtechId;
    }


    public String getMtechName() {
        return mtechName;
    }

    public void setMtechName(String mtechName) {
        this.mtechName = mtechName;
    }


    public String getMtechType() {
        return mtechType;
    }

    public void setMtechType(String mtechType) {
        this.mtechType = mtechType;
    }


    public String getMtechDesc() {
        return mtechDesc;
    }

    public void setMtechDesc(String mtechDesc) {
        this.mtechDesc = mtechDesc;
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

        TechnologyEntity that = (TechnologyEntity) o;

        if (mtechId != that.mtechId) return false;
        if (rating != that.rating) return false;
        if (mtechName != null ? !mtechName.equals(that.mtechName) : that.mtechName != null) return false;
        if (mtechType != null ? !mtechType.equals(that.mtechType) : that.mtechType != null) return false;
        if (mtechDesc != null ? !mtechDesc.equals(that.mtechDesc) : that.mtechDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mtechId;
        result = 31 * result + (mtechName != null ? mtechName.hashCode() : 0);
        result = 31 * result + (mtechType != null ? mtechType.hashCode() : 0);
        result = 31 * result + (mtechDesc != null ? mtechDesc.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "character_id")
    public CharacterEntity getCharacterByCharacterId() {
        return characterByCharacterId;
    }

    public void setCharacterByCharacterId(CharacterEntity characterByCharacterId) {
        this.characterByCharacterId = characterByCharacterId;
    }

    @ManyToOne
    @JoinColumn(name = "nation_id", referencedColumnName = "nation_id")
    public NationEntity getNationByNationId() {
        return nationByNationId;
    }

    public void setNationByNationId(NationEntity nationByNationId) {
        this.nationByNationId = nationByNationId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}

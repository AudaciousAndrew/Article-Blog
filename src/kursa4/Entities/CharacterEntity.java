package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "character", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Character.readAllMen" ,query = "Select p from CharacterEntity p where p.gender='мужчина'") ,
        @NamedQuery(name = "Character.readAllWomen" ,query = "Select p from CharacterEntity p where p.gender='женщина'") ,
        @NamedQuery(name = "Character.readAll" , query =  "select p from CharacterEntity p ") ,
        @NamedQuery(name = "Character.deleteAll" , query = "delete from CharacterTypeEntity p")
})
public class CharacterEntity {

    @Id
    @Column(name = "character_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int characterId;

    @Basic
    @Column(name = "name" , nullable = false)
    private String name;

    @Basic
    @Column(name = "gender" , nullable = false)
    private String gender;

    @Basic
    @Column(name = "rating" , nullable = false)
    private int rating = 0;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" , nullable = false)
    private UsersEntity userByUserId;

    @ManyToOne
    @JoinColumn(name = "m_id" , referencedColumnName = "m_id" )
    private MagicEntity magicByMagicId;

    @ManyToOne
    @JoinColumn(name = "nation_id" , referencedColumnName = "nation_id" )
    private NationEntity nationByNationId;

    @ManyToOne
    @JoinColumn(name = "episode_id" , referencedColumnName = "episode_id" , nullable = false)
    private EpisodeEntity episodeByEpisodeId;

    @ManyToOne
    @JoinColumn(name = "ctype_id" , referencedColumnName = "ctype_id" , nullable = false)
    private CharacterTypeEntity ctypeByCtypeId;

    public CharacterTypeEntity getCtypeByCtypeId() {
        return ctypeByCtypeId;
    }

    public void setCtypeByCtypeId(CharacterTypeEntity ctypeByCtypeId) {
        this.ctypeByCtypeId = ctypeByCtypeId;
    }

    public EpisodeEntity getEpisodeByEpisodeId() {
        return episodeByEpisodeId;
    }

    public void setEpisodeByEpisodeId(EpisodeEntity episodeByEpisodeId) {
        this.episodeByEpisodeId = episodeByEpisodeId;
    }

    public NationEntity getNationByNationId() {
        return nationByNationId;
    }

    public void setNationByNationId(NationEntity nationByNationId) {
        this.nationByNationId = nationByNationId;
    }

    public MagicEntity getMagicByMagicId() {
        return magicByMagicId;
    }

    public void setMagicByMagicId(MagicEntity magicByMagicId) {
        this.magicByMagicId = magicByMagicId;
    }

    public UsersEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UsersEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

        CharacterEntity that = (CharacterEntity) o;

        if (characterId != that.characterId) return false;
        if (rating != that.rating) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = characterId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }
}

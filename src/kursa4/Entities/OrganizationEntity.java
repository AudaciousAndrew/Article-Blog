package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "organization", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Organization.readAll" , query = "Select p from OrganizationEntity p") ,
        @NamedQuery(name = "Organization.deleteAll" , query = "delete from OrganizationEntity p")
})
public class OrganizationEntity {
    @Id
    @Column(name = "org_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orgId;

    @Basic
    @Column(name = "org_name", nullable = false)
    private String orgName;

    @Basic
    @Column(name = "org_desc", unique = true)
    private String orgDesc;

    @Basic
    @Column(name = "rating", nullable = false)
    private int rating = 0;


    @ManyToOne
    @JoinColumn(name = "founder" , referencedColumnName = "character_id", nullable = false)
    private CharacterEntity characterByFounder;

    @ManyToOne
    @JoinColumn(name = "leader" , referencedColumnName = "character_id", nullable = false, unique = true)
    private CharacterEntity characterByLeader;

    @ManyToOne
    @JoinColumn(name = "episode_id" , referencedColumnName = "episode_id", nullable = false)
    private EpisodeEntity episodeByEpisodeId;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id", nullable = false)
    private UsersEntity usersByUserId;

    @ManyToOne
    @JoinColumn(name = "HQ" , referencedColumnName = "place_id")
    private PlaceEntity HQ;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
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

        OrganizationEntity that = (OrganizationEntity) o;

        if (orgId != that.orgId) return false;
        if (rating != that.rating) return false;
        if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
        if (orgDesc != null ? !orgDesc.equals(that.orgDesc) : that.orgDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orgId;
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (orgDesc != null ? orgDesc.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }

    public CharacterEntity getCharacterByFounder() {
        return characterByFounder;
    }

    public void setCharacterByFounder(CharacterEntity characterByFounder) {
        this.characterByFounder = characterByFounder;
    }

    public CharacterEntity getCharacterByLeader() {
        return characterByLeader;
    }

    public void setCharacterByLeader(CharacterEntity characterByLeader) {
        this.characterByLeader = characterByLeader;
    }

    public EpisodeEntity getEpisodeByEpisodeId() {
        return episodeByEpisodeId;
    }

    public void setEpisodeByEpisodeId(EpisodeEntity episodeByEpisodeId) {
        this.episodeByEpisodeId = episodeByEpisodeId;
    }

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }


    public PlaceEntity getHQ() {
        return HQ;
    }

    public void setHQ(PlaceEntity HQ) {
        this.HQ = HQ;
    }
}

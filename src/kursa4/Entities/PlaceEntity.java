package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "place", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Place.readAll" , query = "Select p from PlaceEntity p") ,
        @NamedQuery(name = "Place.deleteAll" , query = "delete from PlaceEntity p")
})
public class PlaceEntity {
    @Id
    @Column(name = "place_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placeId;

    @Basic
    @Column(name = "place_name", unique = true)
    private String placeName;

    @Basic
    @Column(name = "details", unique = true)
    private String details;

    @Basic
    @Column(name = "status", nullable = false)
    private boolean status;

    @Basic
    @Column(name = "regime")
    private String regime;

    @Basic
    @Column(name = "rating")
    private int rating = 0;

    @ManyToOne
    @JoinColumn(name = "location_id" , referencedColumnName = "place_id")
    private PlaceEntity placeByLocationId;

    @ManyToOne
    @JoinColumn(name = "episode_id" , referencedColumnName = "episode_id")
    private EpisodeEntity episodeByEpisodeId;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id")
    private UsersEntity usersByUserId;


    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }


    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
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

        PlaceEntity that = (PlaceEntity) o;

        if (placeId != that.placeId) return false;
        if (status != that.status) return false;
        if (rating != that.rating) return false;
        if (placeName != null ? !placeName.equals(that.placeName) : that.placeName != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (regime != null ? !regime.equals(that.regime) : that.regime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = placeId;
        result = 31 * result + (placeName != null ? placeName.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (regime != null ? regime.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "place_id")
    public PlaceEntity getPlaceByLocationId() {
        return placeByLocationId;
    }

    public void setPlaceByLocationId(PlaceEntity placeByLocationId) {
        this.placeByLocationId = placeByLocationId;
    }

    @ManyToOne
    @JoinColumn(name = "episode_id", referencedColumnName = "episode_id")
    public EpisodeEntity getEpisodeByEpisodeId() {
        return episodeByEpisodeId;
    }

    public void setEpisodeByEpisodeId(EpisodeEntity episodeByEpisodeId) {
        this.episodeByEpisodeId = episodeByEpisodeId;
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

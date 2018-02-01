package kursa4.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "episode", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Episode.readAll" , query = "select p from EpisodeEntity p"),
        @NamedQuery(name = "Episode.deleteAll" , query = "delete  from EpisodeEntity p")
})
public class EpisodeEntity {

    @Id
    @Column(name = "episode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int episodeId;

    @Basic
    @Column(name = "e_name" , nullable = false , unique = true , length = 100)
    private String eName;

    @Basic
    @Column(name = "release_date" , nullable = false)
    private Date releaseDate;

    @Basic
    @Column(name = "e_number" , nullable = false)
    private int eNumber;

    @Basic
    @Column(name = "e_desc" , unique = true)
    private String eDesc;

    @Basic
    @Column(name = "rating" , nullable = false)
    private int rating = 0;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    private BookEntity bookByBookId;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" , nullable = false)
    private UsersEntity userByUserId;


    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }


    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }


    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    public int geteNumber() {
        return eNumber;
    }

    public void seteNumber(int eNumber) {
        this.eNumber = eNumber;
    }


    public String geteDesc() {
        return eDesc;
    }

    public void seteDesc(String eDesc) {
        this.eDesc = eDesc;
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

        EpisodeEntity that = (EpisodeEntity) o;

        if (episodeId != that.episodeId) return false;
        if (eNumber != that.eNumber) return false;
        if (rating != that.rating) return false;
        if (eName != null ? !eName.equals(that.eName) : that.eName != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (eDesc != null ? !eDesc.equals(that.eDesc) : that.eDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = episodeId;
        result = 31 * result + (eName != null ? eName.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + eNumber;
        result = 31 * result + (eDesc != null ? eDesc.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }


    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    public UsersEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UsersEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}

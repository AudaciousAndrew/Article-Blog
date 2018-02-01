package kursa4.Entities;

import javax.persistence.*;

@Entity(name= "book")
@Table(name = "book", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Book.readAll" , query = "Select p from book p") ,
        @NamedQuery(name = "Book.deleteAll" , query = "delete from book p")
})
public class BookEntity {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Basic
    @Column(name = "b_name" , nullable = false , unique = true , length = 30)
    private String bName;

    @Basic
    @Column(name = "b_desc" , unique = true)
    private String bDesc;

    @Basic
    @Column(name = "rating" , nullable = false)
    private int rating = 0;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" , nullable = false)
    private UsersEntity userByUserId;

    public BookEntity(String bName, int rating, UsersEntity userByUserId) {
        this.bName = bName;
        this.rating = rating;
        this.userByUserId = userByUserId;
    }

    public BookEntity() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }


    public String getbDesc() {
        return bDesc;
    }

    public void setbDesc(String bDesc) {
        this.bDesc = bDesc;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UsersEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UsersEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (rating != that.rating) return false;
        if (bName != null ? !bName.equals(that.bName) : that.bName != null) return false;
        if (bDesc != null ? !bDesc.equals(that.bDesc) : that.bDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bName != null ? bName.hashCode() : 0);
        result = 31 * result + (bDesc != null ? bDesc.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }
}

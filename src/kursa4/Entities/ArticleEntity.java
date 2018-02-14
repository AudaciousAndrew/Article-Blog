package kursa4.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "article", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Article.readAll" , query = "SELECT u from ArticleEntity u where u.verified = true order by u.id desc ") ,
        @NamedQuery(name = "Article.deleteAll" , query = "delete from ArticleEntity p")
})
@XmlRootElement
public class ArticleEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    @Basic
    @Column(name = "article_name" , nullable = false , unique = true , length = 200)
    private String articleName;

    @Basic
    @Column(name = "article_type" , nullable = false)
    private String articleType;

    @Basic
    @Column(name = "article_desc" , nullable = false , unique = true)
    private String articleDesc;

    @Basic
    @Column(name = "rating")
    private int rating = 0;

    @Basic
    @Column(name = "verified")
    private boolean verified = false;

    @Basic
    @Column(name = "author")
    private String author;

    @Basic
    @Column(name = "article_smalldesc")
    private String small_desc;

    public ArticleEntity() {
    }


    public ArticleEntity(String articleName, String articleType, String articleDesc, String author, String small_desc) {
        this.articleName = articleName;
        this.articleType = articleType;
        this.articleDesc = articleDesc;
        this.author = author;
        this.small_desc = small_desc;
    }

    public String getSmall_desc() {
        return small_desc;
    }

    public void setSmall_desc(String small_desc) {
        this.small_desc = small_desc;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }


    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", articleType='" + articleType + '\'' +
                ", articleDesc='" + articleDesc + '\'' +
                ", rating=" + rating +
                ", verified=" + verified +
                ", author='" + author + '\'' +
                '}';
    }
}

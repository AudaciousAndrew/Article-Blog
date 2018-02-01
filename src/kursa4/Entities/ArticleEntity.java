package kursa4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "article", schema = "public", catalog = "postgres")
@NamedQueries({
        @NamedQuery(name = "Article.readAll" , query = "SELECT u from ArticleEntity u") ,
        @NamedQuery(name = "Article.deleteAll" , query = "delete from ArticleEntity p")
})
public class ArticleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    @Basic
    @Column(name = "article_name" , nullable = false , unique = true , length = 200)
    private String articleName;

    @Basic
    @Column(name = "article_desc" , nullable = false , unique = true)
    private String articleDesc;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" , nullable = false)
    private UsersEntity userByUserId;



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

        ArticleEntity that = (ArticleEntity) o;

        if (articleId != that.articleId) return false;
        if (articleName != null ? !articleName.equals(that.articleName) : that.articleName != null) return false;
        if (articleDesc != null ? !articleDesc.equals(that.articleDesc) : that.articleDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleId;
        result = 31 * result + (articleName != null ? articleName.hashCode() : 0);
        result = 31 * result + (articleDesc != null ? articleDesc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", articleDesc='" + articleDesc + '\'' +
                ", userByUserId=" + userByUserId +
                '}';
    }
}

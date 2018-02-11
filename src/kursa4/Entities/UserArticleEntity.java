package kursa4.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_article" , schema = "public" , catalog = "postgres")
public class UserArticleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_article_id")
    private int user_article_id;

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "article_name")
    private String article_name;

    @Basic
    @Column(name = "vote" , nullable = false)
    private boolean vote;

    public UserArticleEntity() {
    }

    public UserArticleEntity(String login, String article_name, boolean vote) {
        this.login = login;
        this.article_name = article_name;
        this.vote = vote;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public int getUser_article_id() {
        return user_article_id;
    }

    public void setUser_article_id(int user_article_id) {
        this.user_article_id = user_article_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserArticleEntity that = (UserArticleEntity) o;

        if (user_article_id != that.user_article_id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return article_name != null ? article_name.equals(that.article_name) : that.article_name == null;
    }

    @Override
    public int hashCode() {
        int result = user_article_id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (article_name != null ? article_name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserArticleEntity{" +
                "user_article_id=" + user_article_id +
                ", login='" + login + '\'' +
                ", article_name='" + article_name + '\'' +
                '}';
    }
}

package kursa4.DAO;



import kursa4.Entities.ArticleEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ArticleDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public ArticleDAO() {
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Article.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public List<ArticleEntity> readByAuthor(String author){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p join p.userByUserId " +
                        "where p.userByUserId.login ='"+author+"'"
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }
    public List<ArticleEntity> readByDesc(String desc){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p where p.articleDesc like '%" + desc + "%'"
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public List<ArticleEntity> readByName(String name){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p where p.articleName like '%" + name + "%'"
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public List<ArticleEntity> readAll(){
        TypedQuery<ArticleEntity> query = em.createNamedQuery("Article.readAll" , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public ArticleEntity create(ArticleEntity article){
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        return article;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(ArticleEntity.class , id));
        em.getTransaction().commit();

    }

    public ArticleEntity update(ArticleEntity article){
        em.getTransaction().begin();
        em.merge(article);
        em.getTransaction().commit();
        return article;
    }

    public ArticleEntity read(int id){
        return em.find(ArticleEntity.class , id);
    }
}

package kursa4.DAO;



import kursa4.Entities.ArticleEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ArticleDAO {

//
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
//    EntityManager em = emf.createEntityManager();

    @PersistenceContext(name = "NewPersistenceUnit")
    private EntityManager em;

    public ArticleDAO() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAll(){
      //  em.getTransaction().begin();
        Query query = em.createNamedQuery("Article.deleteAll");
        query.executeUpdate();
      //  em.getTransaction().commit();
    }

    public List<ArticleEntity> readByType(String type){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "select p from ArticleEntity p where p.articleType ='" + type+"' and p.verified = true"
                ,ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public ArticleEntity readByAuthor(String author){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p join p.userByUserId " +
                        "where p.userByUserId.login ='"+author+"' and p.verified = true"
                , ArticleEntity.class);
        ArticleEntity article = query.getSingleResult();
        return article;
    }
    public List<ArticleEntity> readByDesc(String desc){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p where p.articleDesc like '%" + desc + "%' and p.verified = true"
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public ArticleEntity readByName(String name){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p where p.articleName like '%" + name + "%' and p.verified = true"
                , ArticleEntity.class);
        ArticleEntity article = query.getSingleResult();
        return article;
    }

    public List<ArticleEntity> topTen(){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity  p where p.verified = true order by p.rating desc "
                , ArticleEntity.class).setMaxResults(10);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public List<ArticleEntity> readAll(){
        TypedQuery<ArticleEntity> query = em.createNamedQuery("Article.readAll" , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public List<ArticleEntity> readVerified(){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity  p where p.verified = true"
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public List<ArticleEntity> readUnverified(){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity  p where p.verified = false "
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateVerified(String name){
       // em.getTransaction().begin();
        Query query = em.createQuery(
                "update ArticleEntity p set p.verified = true where p.articleName = '"+name+"'");
        query.executeUpdate();
       // em.getTransaction().commit();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArticleEntity create(ArticleEntity article){
      //  em.getTransaction().begin();
        em.persist(article);
       // em.getTransaction().commit();
        return article;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(int id){
       // em.getTransaction().begin();
        em.remove(em.find(ArticleEntity.class , id));
        //em.getTransaction().commit();

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArticleEntity update(ArticleEntity article){
       // em.getTransaction().begin();
        em.merge(article);
       // em.getTransaction().commit();
        return article;
    }

    public ArticleEntity read(int id){
        return em.find(ArticleEntity.class , id);
    }
}

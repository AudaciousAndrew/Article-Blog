package kursa4.DAO;



import kursa4.Entities.ArticleEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ArticleDAO {

    @PersistenceContext(name = "NewPersistenceUnit")
    private EntityManager em;

    public ArticleDAO() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAll(){
        Query query = em.createNamedQuery("Article.deleteAll");
        query.executeUpdate();
    }

    public List<ArticleEntity> readByAuthor(String author){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "select p from ArticleEntity p where  p.author = '"+author+"'"
                ,ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public Number countByAuthor(String author){
        Query query = em.createQuery(
                "select count(p.articleName) from ArticleEntity p where  p.author = '"+author+"'");
        Number articlesNumber = (Number) query.getSingleResult();
        return articlesNumber;
    }


    public List<ArticleEntity> readByTypeAndOffset(String type , int offset){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "select p from ArticleEntity p where p.articleType ='" + type+"' and p.verified = true order by p.articleId desc"
                ,ArticleEntity.class).setMaxResults(10).setFirstResult((offset-1)*10);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }


    public List<ArticleEntity> readByDesc(String desc){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p where p.articleDesc like '%" + desc + "%' and p.verified = true"
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public ArticleEntity readByName(String name){
        try {
            TypedQuery<ArticleEntity> query = em.createQuery(
                    "SELECT p from ArticleEntity p where p.articleName like '%" + name + "%' and p.verified = true"
                    , ArticleEntity.class);
            ArticleEntity article = query.getSingleResult();
            return article;
        }catch (NoResultException e){
            return null;
        }
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
        Query query = em.createQuery(
                "update ArticleEntity p set p.verified = true where p.articleName = '"+name+"'");
        query.executeUpdate();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArticleEntity create(ArticleEntity article){
        em.persist(article);
        return article;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(int id){
        em.remove(em.find(ArticleEntity.class , id));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArticleEntity update(ArticleEntity article){
        em.merge(article);
        return article;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int deleteByName(String name){
        try{
        Query query= em.createQuery("delete from ArticleEntity p where p.articleName = '"+name+"'");
        query.executeUpdate();
        return 1;
        }catch (NoResultException e){
            return 0;
        }
    }

    public ArticleEntity read(int id){
        return em.find(ArticleEntity.class , id);
    }
}

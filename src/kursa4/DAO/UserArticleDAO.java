package kursa4.DAO;

import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UserArticleEntity;
import kursa4.response_models.articleName;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class UserArticleDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();



    public boolean ExistsByAuthorAndName(String login , articleName name){
        try{

        TypedQuery<ArticleEntity> query = em.createQuery(
                "select p from UserArticleEntity p where " +
                        "p.login ='"+login+"' and p.article_name='"+name.getName()+"'"
                , ArticleEntity.class);
        ArticleEntity article = query.getSingleResult();
        if(article == null) return false;
        else return true;
        }catch (NoResultException e){
            return false;
        }
    }

    public UserArticleEntity create(UserArticleEntity article){
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        return article;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(UserArticleEntity.class , id));
        em.getTransaction().commit();

    }

    public UserArticleEntity update(UserArticleEntity article){
        em.getTransaction().begin();
        em.merge(article);
        em.getTransaction().commit();
        return article;
    }

    public UserArticleEntity read(int id){
        return em.find(UserArticleEntity.class , id);
    }
}



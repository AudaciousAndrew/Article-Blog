package kursa4.DAO;

import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UserArticleEntity;
import kursa4.response_models.articleName;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;

@Stateless
public class UserArticleDAO {

    @PersistenceContext(name = "NewPersistenceUnit")
    private EntityManager em;


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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserArticleEntity create(UserArticleEntity article){
        em.persist(article);
        return article;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(int id){
        em.remove(em.find(UserArticleEntity.class , id));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserArticleEntity update(UserArticleEntity article){
        em.merge(article);
        return article;
    }

    public UserArticleEntity read(int id){
        return em.find(UserArticleEntity.class , id);
    }
}



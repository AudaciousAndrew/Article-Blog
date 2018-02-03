package kursa4.DAO;

import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UserRolesEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class UserRolesDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public UserRolesDAO() {
    }

    public List<UserRolesEntity> readByLogin (String login){
        TypedQuery<UserRolesEntity> query = em.createQuery(
                "SELECT p from UserRolesEntity p  where p.userByLogin.login ='"+login+"'"
                , UserRolesEntity.class);
        List<UserRolesEntity> UserRoles = query.getResultList();
        return UserRoles;
    }
    public List<ArticleEntity> readByAuthor(String author){
        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT p from ArticleEntity p join p.userByUserId " +
                        "where p.userByUserId.login ='"+author+"'"
                , ArticleEntity.class);
        List<ArticleEntity> articles = query.getResultList();
        return articles;
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("UserRoles.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public List<UserRolesEntity> readAll(){
        TypedQuery<UserRolesEntity> query = em.createNamedQuery("UserRoles.readAll" , UserRolesEntity.class);
        List<UserRolesEntity> rolesEntities = query.getResultList();
        return rolesEntities;
    }

    public UserRolesEntity create(UserRolesEntity userRolesEntity){
        em.getTransaction().begin();
        em.persist(userRolesEntity);
        em.getTransaction().commit();
        return userRolesEntity;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(UserRolesEntity.class , id));
        em.getTransaction().commit();

    }

    public UserRolesEntity update(UserRolesEntity userRolesEntity){
        em.getTransaction().begin();
        em.merge(userRolesEntity);
        em.getTransaction().commit();
        return userRolesEntity;
    }

    public UserRolesEntity read(int id){

        return em.find(UserRolesEntity.class , id);
    }
}

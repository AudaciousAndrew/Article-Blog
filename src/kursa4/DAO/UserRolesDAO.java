package kursa4.DAO;

import kursa4.Entities.ArticleEntity;
import kursa4.Entities.UserRolesEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import java.util.List;

@Stateless
public class UserRolesDAO {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
//    EntityManager em = emf.createEntityManager();

    @PersistenceContext(name = "NewPersistenceUnit")
    private EntityManager em;

    public UserRolesDAO() {
    }

    public List<UserRolesEntity> readByLogin (String login){
        TypedQuery<UserRolesEntity> query = em.createQuery(
                "SELECT p from UserRolesEntity p  where p.userByLogin.login ='"+login+"'"
                , UserRolesEntity.class);
        List<UserRolesEntity> UserRoles = query.getResultList();
        return UserRoles;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAll(){
       // em.getTransaction().begin();
        Query query = em.createNamedQuery("UserRoles.deleteAll");
        query.executeUpdate();
       // em.getTransaction().commit();
    }

    public List<UserRolesEntity> readAll(){
        TypedQuery<UserRolesEntity> query = em.createNamedQuery("UserRoles.readAll" , UserRolesEntity.class);
        List<UserRolesEntity> rolesEntities = query.getResultList();
        return rolesEntities;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserRolesEntity create(UserRolesEntity userRolesEntity){
       // em.getTransaction().begin();
        em.persist(userRolesEntity);
       // em.getTransaction().commit();
        return userRolesEntity;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(int id){
       // em.getTransaction().begin();
        em.remove(em.find(UserRolesEntity.class , id));
       // em.getTransaction().commit();

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserRolesEntity update(UserRolesEntity userRolesEntity){
       // em.getTransaction().begin();
        em.merge(userRolesEntity);
       // em.getTransaction().commit();
        return userRolesEntity;
    }

    public UserRolesEntity read(int id){
        return em.find(UserRolesEntity.class , id);
    }
}

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

    @PersistenceContext(name = "NewPersistenceUnit")
    private EntityManager em;

    public UserRolesDAO() {
    }

    public List<UserRolesEntity> readByLogin (String login){
        try {
            TypedQuery<UserRolesEntity> query = em.createQuery(
                    "SELECT p from UserRolesEntity p  where p.userByLogin.login ='" + login + "'"
                    , UserRolesEntity.class);
            List<UserRolesEntity> UserRoles = query.getResultList();
            return UserRoles;
        }catch (NoResultException e){
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAll(){
        Query query = em.createNamedQuery("UserRoles.deleteAll");
        query.executeUpdate();
    }

    public List<UserRolesEntity> readAll(){
        TypedQuery<UserRolesEntity> query = em.createNamedQuery("UserRoles.readAll" , UserRolesEntity.class);
        List<UserRolesEntity> rolesEntities = query.getResultList();
        return rolesEntities;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserRolesEntity create(UserRolesEntity userRolesEntity){
        em.persist(userRolesEntity);
        return userRolesEntity;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(int id){
        em.remove(em.find(UserRolesEntity.class , id));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserRolesEntity update(UserRolesEntity userRolesEntity){
        em.merge(userRolesEntity);
        return userRolesEntity;
    }

    public UserRolesEntity read(int id){
        return em.find(UserRolesEntity.class , id);
    }
}

package kursa4.DAO;

import kursa4.Entities.NationEntity;

import javax.persistence.*;
import java.util.List;

public class NationDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public NationDAO() {
    }

    public List<NationEntity> readByMagic(String MagicType){
        TypedQuery<NationEntity> query = em.createQuery(
                "SELECT p from NationEntity p join p.magicByMId " +
                        "where p.magicByMId.mType ='"+MagicType+"'"
                , NationEntity.class);
        return query.getResultList();
    }

    public List<NationEntity> readByUser(String UserName){
        TypedQuery<NationEntity> query = em.createQuery(
                "SELECT p from NationEntity p join p.usersByUserId " +
                        "where p.usersByUserId.login ='"+UserName+"'"
                , NationEntity.class);
        return query.getResultList();
    }

    public List<NationEntity> readByName(String name){
        TypedQuery<NationEntity> query = em.createQuery(
                "select p from NationEntity p where p.nationName ='"+name+"'"
                ,NationEntity.class);
        return query.getResultList();
    }

    public List<NationEntity> readByPlace(int place){
        TypedQuery<NationEntity> query = em.createQuery(
                "SELECT p from NationEntity p where p.placeId ="+place
                ,NationEntity.class);
        return query.getResultList();
    }

    public List<NationEntity> readByRegime(String regime){
        TypedQuery<NationEntity> query = em.createQuery(
                "SELECT p from NationEntity p where p.regime ='"+regime+"'"
                ,NationEntity.class);
        return query.getResultList();
    }

    public List<NationEntity> readByRating(int rating){
        TypedQuery<NationEntity> query = em.createQuery(
                "SELECT p from NationEntity p where p.rating ="+rating
                ,NationEntity.class);
        return query.getResultList();
    }

    public List<NationEntity> readByDesc(String desc){
        TypedQuery<NationEntity> query = em.createQuery(
                "SELECT p from NationEntity p where p.nDesc like '%"+desc+"%'"
                ,NationEntity.class);
        return query.getResultList();
    }

    public NationEntity create(NationEntity nation){
        em.persist(nation);
        return nation;
    }

    public NationEntity read(int id){
        return em.find(NationEntity.class , id);
    }

    public void update(NationEntity nation){
        em.getTransaction().begin();
        em.merge(nation);
        em.getTransaction().commit();
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(NationEntity.class , id));
        em.getTransaction().commit();
    }

    public List<NationEntity> readAll(){
        TypedQuery<NationEntity> query = em.createNamedQuery(
                "Nation.readAll"
                ,NationEntity.class);
        return query.getResultList();
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Nation.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}

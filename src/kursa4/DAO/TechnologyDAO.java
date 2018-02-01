package kursa4.DAO;

import kursa4.Entities.TechnologyEntity;

import javax.persistence.*;
import java.util.List;

public class TechnologyDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public TechnologyDAO() {
    }

    public List<TechnologyEntity> readByUser(String UserName){
        TypedQuery<TechnologyEntity> query = em.createQuery(
                "SELECT p from TechnologyEntity p join p.usersByUserId " +
                        "where p.usersByUserId.login ='"+UserName+"'"
                , TechnologyEntity.class);
        return query.getResultList();
    }

    public List<TechnologyEntity> readByNation(String nation){
        TypedQuery<TechnologyEntity> query = em.createQuery(
                "SELECT p from TechnologyEntity p join p.nationByNationId " +
                        "where p.nationByNationId.nationName ='"+nation+"'"
                , TechnologyEntity.class);
        return query.getResultList();
    }

    public List<TechnologyEntity> readByCharacter(String character){
        TypedQuery<TechnologyEntity> query = em.createQuery(
                "SELECT p from TechnologyEntity p join p.characterByCharacterId " +
                        "where p.characterByCharacterId.name ='"+character+"'"
                , TechnologyEntity.class);
        return query.getResultList();
    }

    public List<TechnologyEntity> readByRating(int rating){
        TypedQuery<TechnologyEntity> query = em.createQuery(
                "SELECT p from TechnologyEntity p where p.rating ="+rating
                ,TechnologyEntity.class);
        return query.getResultList();
    }

    public List<TechnologyEntity> readByDesc(String desc){
        TypedQuery<TechnologyEntity> query = em.createQuery(
                "SELECT p from TechnologyEntity p where p.mtechDesc like '%"+desc+"%'"
                ,TechnologyEntity.class);
        return query.getResultList();
    }

    public List<TechnologyEntity> readByType(String type){
        TypedQuery<TechnologyEntity> query = em.createQuery(
                "select p from TechnologyEntity p where p.mtechType ='"+type+"'"
                ,TechnologyEntity.class);
        return query.getResultList();

    }
    public List<TechnologyEntity> readByName(String name){
        TypedQuery<TechnologyEntity> query = em.createQuery(
                "select p from TechnologyEntity p where p.mtechName ='"+name+"'"
                ,TechnologyEntity.class);
        return query.getResultList();
    }

    public TechnologyEntity create(TechnologyEntity tech){
        em.persist(tech);
        return tech;
    }

    public TechnologyEntity read(int id){
        return em.find(TechnologyEntity.class , id);
    }

    public void update(TechnologyEntity tech){
        em.getTransaction().begin();
        em.merge(tech);
        em.getTransaction().commit();
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(TechnologyEntity.class , id));
        em.getTransaction().commit();
    }

    public List<TechnologyEntity> readAll(){
        TypedQuery<TechnologyEntity> query = em.createNamedQuery(
                "Technology.readAll"
                ,TechnologyEntity.class);
        return query.getResultList();
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Technology.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}

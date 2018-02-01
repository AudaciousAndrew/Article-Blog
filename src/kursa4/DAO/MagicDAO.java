package kursa4.DAO;

import kursa4.Entities.MagicEntity;

import javax.persistence.*;
import java.util.List;

public class MagicDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public MagicDAO() {
    }

    public List<MagicEntity> readByAuthor(String author){
        TypedQuery<MagicEntity> query = em.createQuery(
                "select p from MagicEntity p join p.userByUserId " +
                        "where p.userByUserId.login ='"+author+"'"
            ,MagicEntity.class);
        return query.getResultList();
    }

    public List<MagicEntity> readByDesc(String desc){
        TypedQuery<MagicEntity> query = em.createQuery(
                "select p from MagicEntity p where p.mDesc like '%"+desc+"%'"
                , MagicEntity.class);
        return query.getResultList();
    }

    public List<MagicEntity> readByType(String type){
        TypedQuery<MagicEntity> query = em.createQuery(
                "select p from MagicEntity p where p.mType ='"+type+"'"
                ,MagicEntity.class);
        return query.getResultList();
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Magic.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public List<MagicEntity> readAll(){
        TypedQuery<MagicEntity> query = em.createNamedQuery("Magic.readAll" , MagicEntity.class);
        return query.getResultList();
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(MagicEntity.class,id));
        em.getTransaction().commit();
    }

    public MagicEntity read(int id){
        return em.find(MagicEntity.class , id);
    }

    public MagicEntity create(MagicEntity magic){
        em.getTransaction().begin();
        em.persist(magic);
        em.getTransaction().commit();
        return magic;
    }

    public MagicEntity update(MagicEntity magic){
        em.getTransaction().begin();
        em.merge(magic);
        em.getTransaction().commit();
        return magic;
    }
}

package kursa4.DAO;

import kursa4.Entities.EpisodeEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class EpisodeDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public EpisodeDAO() {
    }

    public List<EpisodeEntity> readByBook(String book){
        TypedQuery<EpisodeEntity> query = em.createQuery(
                "select p from EpisodeEntity p join p.bookByBookId " +
                        "where p.bookByBookId.bName ='"+book+"'"
                ,EpisodeEntity.class );
        return query.getResultList();
    }

    public List<EpisodeEntity> readByAuthor(String author){
        TypedQuery<EpisodeEntity> query = em.createQuery(
                "select p from EpisodeEntity p join p.userByUserId " +
                        "where p.userByUserId.login ='"+author+"'"
                ,EpisodeEntity.class);
        return query.getResultList();
    }

    public List<EpisodeEntity> readByDesc(String desc){
        TypedQuery<EpisodeEntity> query = em.createQuery(
                "select p from EpisodeEntity p where p.eDesc like '%"+desc+"%'"
                ,EpisodeEntity.class);
        return query.getResultList();
    }

    public List<EpisodeEntity> readByDate(String date) throws java.text.ParseException{
        TypedQuery<EpisodeEntity> query = em.createQuery(
                "select p from EpisodeEntity p where p.releaseDate ='"+new SimpleDateFormat("yyyy-MM-dd").parse(date)+"'"
                ,EpisodeEntity.class);
        return query.getResultList();
    }

    public List<EpisodeEntity> readByNumber(int number){
        TypedQuery<EpisodeEntity> query = em.createQuery(
                "select p from EpisodeEntity p where p.eNumber ="+number
                ,EpisodeEntity.class);
        return query.getResultList();
    }

    public List<EpisodeEntity> readByName(String name){
        TypedQuery<EpisodeEntity> query = em.createQuery(
                "select p from EpisodeEntity p where p.eName ='"+name+"'"
                ,EpisodeEntity.class);
        return query.getResultList();
    }

    public List<EpisodeEntity> readAll(){
        TypedQuery<EpisodeEntity> query = em.createNamedQuery(
                "Episode.readAll"
                ,EpisodeEntity.class);
        return query.getResultList();
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(EpisodeEntity.class , id));
        em.getTransaction().commit();
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Episode.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public EpisodeEntity create(EpisodeEntity episode){
        em.getTransaction().begin();
        em.persist(episode);
        em.getTransaction().commit();
        return episode;
    }

    public EpisodeEntity read(int id){
        return em.find(EpisodeEntity.class , id);
    }

    public EpisodeEntity update(EpisodeEntity episode){
        em.getTransaction().begin();
        em.merge(episode);
        em.getTransaction().commit();
        return episode;
    }
}

package kursa4.DAO;

import kursa4.Entities.PlaceEntity;

import javax.persistence.*;
import java.util.List;

public class PlaceDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public PlaceDAO() {
    }

    public List<PlaceEntity> readByEpisodeName(String EpisodeName){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p join p.episodeByEpisodeId " +
                        "where p.episodeByEpisodeId.eName like '%"+EpisodeName+"%'"
                , PlaceEntity.class);
        return query.getResultList();
    }

    public List<PlaceEntity> readByEpisodeNumber(int EpisodeNumber){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p join p.episodeByEpisodeId " +
                        "where p.episodeByEpisodeId.eNumber  ="+EpisodeNumber
                , PlaceEntity.class);
        return query.getResultList();
    }

    public List<PlaceEntity> readByLocation(String location){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p join p.placeByLocationId " +
                        "where p.placeByLocationId.placeName ='"+location+"'"
                , PlaceEntity.class);
        return query.getResultList();
    }

    public List<PlaceEntity> readByUser(String UserName){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p join p.usersByUserId " +
                        "where p.usersByUserId.login ='"+UserName+"'"
                , PlaceEntity.class);
        return query.getResultList();
    }


    public List<PlaceEntity> readByStatus(boolean status){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p where p.status ="+status
                ,PlaceEntity.class);
        return query.getResultList();
    }

    public List<PlaceEntity> readByRegime(String regime){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p where p.regime ='"+regime+"'"
                ,PlaceEntity.class);
        return query.getResultList();
    }

    public List<PlaceEntity> readByRating(int rating){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p where p.rating ="+rating
                ,PlaceEntity.class);
        return query.getResultList();
    }

    public List<PlaceEntity> readByDetails(String desc){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "SELECT p from PlaceEntity p where p.details like '%"+desc+"%'"
                ,PlaceEntity.class);
        return query.getResultList();
    }

    public List<PlaceEntity> readByName(String name){
        TypedQuery<PlaceEntity> query = em.createQuery(
                "select p from PlaceEntity p where p.placeName ='"+name+"'"
                ,PlaceEntity.class);
        return query.getResultList();
    }

    public PlaceEntity create(PlaceEntity place){
        em.persist(place);
        return place;
    }

    public PlaceEntity read(int id){
        return em.find(PlaceEntity.class , id);
    }

    public void update(PlaceEntity place){
        em.getTransaction().begin();
        em.merge(place);
        em.getTransaction().commit();
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(PlaceEntity.class , id));
        em.getTransaction().commit();
    }

    public List<PlaceEntity> readAll(){
        TypedQuery<PlaceEntity> query = em.createNamedQuery(
                "Place.readAll"
                ,PlaceEntity.class);
        return query.getResultList();
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Place.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}

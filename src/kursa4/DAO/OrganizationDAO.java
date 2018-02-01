package kursa4.DAO;

import kursa4.Entities.OrganizationEntity;

import javax.persistence.*;
import java.util.List;

public class OrganizationDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public OrganizationDAO() {
    }

    public List<OrganizationEntity> readByEpisodeName(String EpisodeName){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p join p.episodeByEpisodeId " +
                        "where p.episodeByEpisodeId.eName like '%"+EpisodeName+"%'"
                , OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByEpisodeNumber(int EpisodeNumber){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p join p.episodeByEpisodeId " +
                        "where p.episodeByEpisodeId.eNumber  ="+EpisodeNumber
                , OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByFounder(String Founder){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p join p.characterByFounder " +
                        "where p.characterByFounder.name ='"+Founder+"'"
                , OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByLeader(String Leader){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p join p.characterByLeader " +
                        "where p.characterByLeader.name ='"+Leader+"'"
                , OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByHQ(String HQ){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p join p.HQ " +
                        "where p.HQ.placeName ='"+HQ+"'"
                , OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByUser(String UserName){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p join p.usersByUserId " +
                        "where p.usersByUserId.login ='"+UserName+"'"
                , OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByName(String name){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "select p from OrganizationEntity p where p.orgName ='"+name+"'"
                ,OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByRating(int rating){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p where p.rating ="+rating
                ,OrganizationEntity.class);
        return query.getResultList();
    }

    public List<OrganizationEntity> readByDesc(String desc){
        TypedQuery<OrganizationEntity> query = em.createQuery(
                "SELECT p from OrganizationEntity p where p.orgDesc like '%"+desc+"%'"
                ,OrganizationEntity.class);
        return query.getResultList();
    }

    public OrganizationEntity create(OrganizationEntity org){
        em.persist(org);
        return org;
    }

    public OrganizationEntity read(int id){
        return em.find(OrganizationEntity.class , id);
    }

    public void update(OrganizationEntity org){
        em.getTransaction().begin();
        em.merge(org);
        em.getTransaction().commit();
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(OrganizationEntity.class , id));
        em.getTransaction().commit();
    }

    public List<OrganizationEntity> readAll(){
        TypedQuery<OrganizationEntity> query = em.createNamedQuery(
                "Organization.readAll"
                ,OrganizationEntity.class);
        return query.getResultList();
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Organization.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}

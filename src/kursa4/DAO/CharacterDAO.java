package kursa4.DAO;

import kursa4.Entities.CharacterEntity;

import javax.persistence.*;
import java.util.List;

public class CharacterDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public CharacterDAO() {
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Character.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public List<CharacterEntity> readByEpisodeName(String EpisodeName){
        TypedQuery<CharacterEntity> query = em.createQuery(
                "SELECT p from CharacterEntity p join p.episodeByEpisodeId " +
                        "where p.episodeByEpisodeId.eName like '%"+EpisodeName+"%'"
                , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }

    public List<CharacterEntity> readByEpisodeNumber(int EpisodeNumber){
        TypedQuery<CharacterEntity> query = em.createQuery(
                "SELECT p from CharacterEntity p join p.episodeByEpisodeId " +
                        "where p.episodeByEpisodeId.eNumber  ="+EpisodeNumber
                , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }

    public List<CharacterEntity> readByMagic(String magic){
        TypedQuery<CharacterEntity> query = em.createQuery(
                "SELECT p from CharacterEntity p join p.magicByMagicId " +
                        "where p.magicByMagicId.mType ='"+magic+"'"
                , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }

    public List<CharacterEntity> readByNation(String nation){
        TypedQuery<CharacterEntity> query = em.createQuery(
                "SELECT p from CharacterEntity p join p.nationByNationId " +
                        "where p.nationByNationId.nationName = '"+nation+"'"
                , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }

    public List<CharacterEntity> readByCtype(String type){
        TypedQuery<CharacterEntity> query = em.createQuery(
                "SELECT p from CharacterEntity p join p.ctypeByCtypeId " +
                        "where p.ctypeByCtypeId.ctypeName = '"+type+"'"
                , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }

    public List<CharacterEntity> readByAuthor(String author){
        TypedQuery<CharacterEntity> query = em.createQuery(
                "SELECT p from CharacterEntity p join p.userByUserId " +
                        "where p.userByUserId.login = '"+author+"'"
                , CharacterEntity.class);
        List<CharacterEntity> character = query.getResultList();
        return character;
    }

    public List<CharacterEntity> readByName(String name){
        TypedQuery<CharacterEntity> query = em.createQuery(
                "SELECT p from CharacterEntity  p where p.name = '"+name+"'"
                , CharacterEntity.class);
        List<CharacterEntity> character = query.getResultList();
        return character;
    }

    public List<CharacterEntity> readAllWomen(){
        TypedQuery<CharacterEntity> query = em.createNamedQuery("Character.readAllWomen" , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }


    public List<CharacterEntity> readAllMen(){
        TypedQuery<CharacterEntity> query = em.createNamedQuery("Character.readAllMen" , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }


    public List<CharacterEntity> readAll(){
        TypedQuery<CharacterEntity> query = em.createNamedQuery("Character.readAll" , CharacterEntity.class);
        List<CharacterEntity> characters = query.getResultList();
        return characters;
    }

    public CharacterEntity create(CharacterEntity character){
        em.getTransaction().begin();
        em.persist(character);
        em.getTransaction().commit();
        return character;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(CharacterEntity.class , id));
        em.getTransaction().commit();
    }

    public CharacterEntity update(CharacterEntity charater){
        em.getTransaction().begin();
        em.merge(charater);
        em.getTransaction().commit();
        return  charater;
    }

    public CharacterEntity read(int id){
        return em.find(CharacterEntity.class , id);
    }
}

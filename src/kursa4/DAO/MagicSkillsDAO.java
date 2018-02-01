package kursa4.DAO;

import kursa4.Entities.MagicSkillsEntity;

import javax.persistence.*;
import java.util.List;

public class MagicSkillsDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public MagicSkillsDAO() {
    }

    public List<MagicSkillsEntity> readByName(String name){
        TypedQuery<MagicSkillsEntity> query = em.createQuery(
                "select p from MagicSkillsEntity p where p.mskillName ='"+name+"'"
                ,MagicSkillsEntity.class);
        return query.getResultList();
    }

    public List<MagicSkillsEntity> readByMagic(String MagicType){
        TypedQuery<MagicSkillsEntity> query = em.createQuery(
                "SELECT p from MagicSkillsEntity p join p.magicByMagicId " +
                        "where p.magicByMagicId.mType ='"+MagicType+"'"
                , MagicSkillsEntity.class);
        return query.getResultList();
    }

    public List<MagicSkillsEntity> readByUser(String UserName){
        TypedQuery<MagicSkillsEntity> query = em.createQuery(
                "SELECT p from MagicSkillsEntity p join p.userByUserId " +
                        "where p.userByUserId.login ='"+UserName+"'"
                , MagicSkillsEntity.class);
        return query.getResultList();
    }

    public List<MagicSkillsEntity> readByDesc(String desc){
        TypedQuery<MagicSkillsEntity> query = em.createQuery(
                "SELECT p from MagicSkillsEntity p where p.mskillDesc like '%"+desc+"%'"
                ,MagicSkillsEntity.class);
        return query.getResultList();
    }

    public List<MagicSkillsEntity> readByLevel(String level){
        TypedQuery<MagicSkillsEntity> query = em.createQuery(
                "SELECT p from MagicSkillsEntity p where p.mskillLevel ='"+level+"'"
                ,MagicSkillsEntity.class);
        return query.getResultList();
    }

    public List<MagicSkillsEntity> readByRating(int rating){
        TypedQuery<MagicSkillsEntity> query = em.createQuery(
                "SELECT p from MagicSkillsEntity p where p.rating ="+rating
                ,MagicSkillsEntity.class);
        return query.getResultList();
    }

    public MagicSkillsEntity create(MagicSkillsEntity skill){
        em.persist(skill);
        return skill;
    }

    public MagicSkillsEntity read(int id){
        return em.find(MagicSkillsEntity.class , id);
    }

    public void update(MagicSkillsEntity skill){
        em.getTransaction().begin();
        em.merge(skill);
        em.getTransaction().commit();
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(em.find(MagicSkillsEntity.class , id));
        em.getTransaction().commit();
    }

    public List<MagicSkillsEntity> readAll(){
        TypedQuery<MagicSkillsEntity> query = em.createNamedQuery(
                "MagicSkills.readAll"
                ,MagicSkillsEntity.class);
        return query.getResultList();
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("MagicSkills.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }
}

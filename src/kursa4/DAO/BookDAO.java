package kursa4.DAO;

import kursa4.Entities.BookEntity;

import javax.persistence.*;
import java.util.List;

public class BookDAO {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public BookDAO() {
    }

    public void deleteAll(){
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Book.deleteAll");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public List<BookEntity> readByDesc(String desc){
        TypedQuery<BookEntity> query = em.createQuery(
                "SELECT p from book p where p.bDesc like '%"+desc+"%'"
                ,BookEntity.class);
        List<BookEntity> books = query.getResultList();
        return books;
    }

    public List<BookEntity> readByName(String name){
        TypedQuery<BookEntity> query = em.createQuery(
                "SELECT p from book p where p.bName like '%"+name+"%'"
                , BookEntity.class);
        List<BookEntity> books = query.getResultList();
        return books;
    }

    public List<BookEntity> readAll(){
        TypedQuery<BookEntity> query = em.createNamedQuery("Book.readAll" , BookEntity.class);
        List<BookEntity> books = query.getResultList();
        return books;
    }

    public BookEntity create(BookEntity book){
        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();
        return book;
    }
    public void delete(int id){
        try{
            em.getTransaction().begin();
            em.remove(em.find(BookEntity.class , id));
            em.flush();
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public BookEntity update(BookEntity book){
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
        return book;
    }

    public BookEntity read(int id){
        return em.find(BookEntity.class , id);
    }



}

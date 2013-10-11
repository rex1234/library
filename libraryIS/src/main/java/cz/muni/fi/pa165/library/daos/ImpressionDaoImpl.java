package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Impression;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author MiškoHu
 */
public class ImpressionDaoImpl implements ImpressionDao {
    
    private EntityManagerFactory emf;

    public ImpressionDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }    
    
    public void createImpression(Impression impression){
        checkImpressionAttributes(impression);
        if(impression.getId() != null) {
            throw new IllegalArgumentException("cannot create impression with assigned id");
        }        
        
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        em.persist(impression);
        em.getTransaction().commit();
    }

    public List<Impression> findAllImpressions() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT i FROM Impression i");
        return query.getResultList();
    }

    public Impression findImpressionById(Long id) {
        if (id == null) {
            throw new NullPointerException("id is null");
        }    
        
        EntityManager em = emf.createEntityManager();
        return em.find(Impression.class, id);
    }

    public void deleteImpression(Impression impression) {
        if (impression == null) {
            throw new NullPointerException("impression is null");
        }
        if (impression.getId() == null) {
            throw new IllegalArgumentException("cannot delete impression with no id");
        }       
        
        EntityManager em = emf.createEntityManager();
        Impression i = em.find(Impression.class, impression.getId());
        em.getTransaction().begin();
        Impression toRemove = em.merge(impression);
        em.remove(toRemove);
        em.getTransaction().commit();
        impression.setId(null);
        System.out.println(toRemove.getId());
    }

    public void updateImpression(Impression impression) {
        EntityManager em = emf.createEntityManager();
        Impression i = em.find(Impression.class, impression.getId());
        em.getTransaction().begin();
        i.setAuthor(impression.getAuthor());
        i.setDepartment(impression.getDepartment());
        i.setIsbn(impression.getIsbn());
        i.setName(impression.getName());
        i.setRelaseDate(impression.getRelaseDate());        
        em.getTransaction().commit();        
    }
    private void checkImpressionAttributes(Impression impression) throws IllegalArgumentException, NullPointerException {
        if(impression == null) {
            throw new NullPointerException("impression is null");
        }
        if(impression.getIsbn()== null) {
            throw new IllegalArgumentException("loan.isbn cannot be null");
        }
        if(impression.getName()== null) {
            throw new IllegalArgumentException("loan.name cannot be null");
        }
        if(impression.getAuthor()== null) {
            throw new IllegalArgumentException("loan.author cannot be null");
        }
        if(impression.getRelaseDate()== null) {
            throw new IllegalArgumentException("loan.relaseDate cannot be null");
        }
        if(impression.getDepartment()== null) {
            throw new IllegalArgumentException("loan.department cannot be null");
        }
        
        
    }
     
}

package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Impression;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MiškoHu
 */
@Repository
@Transactional
public class ImpressionDaoImpl implements ImpressionDao {

    @PersistenceContext
    private EntityManager em;

    public void createImpression(Impression impression) {
        checkImpressionAttributes(impression);
        if (impression.getId() != null) {
            throw new IllegalArgumentException("Cannot create impression with assigned ID.");
        }
        em.persist(impression);
    }

    public List<Impression> findAllImpressions() {        
        Query query = em.createQuery("SELECT i FROM Impression i");
        return query.getResultList();
    }

    public Impression findImpressionById(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null.");
        }       
        return em.find(Impression.class, id);
    }

    public void deleteImpression(Impression impression) {
        if (impression == null) {
            throw new NullPointerException("Impression is null.");
        }
        if (impression.getId() == null) {
            throw new IllegalArgumentException("Cannot delete impression with no ID.");
        }       
        Impression toRemove = em.merge(impression);
        em.remove(toRemove);
        impression.setId(null);        
    }

    public void updateImpression(Impression impression) {
        checkImpressionAttributes(impression);
        em.merge(impression);
    }
   
    private void checkImpressionAttributes(Impression impression)
            throws IllegalArgumentException, NullPointerException {

        if (impression == null) {
            throw new NullPointerException("Impression is null.");
        }
        if (impression.getIsbn() == null) {
            throw new IllegalArgumentException("Impression.isbn cannot be null.");
        }
        if (impression.getName() == null) {
            throw new IllegalArgumentException("Impression.name cannot be null.");
        }
        if (impression.getAuthor() == null) {
            throw new IllegalArgumentException("Impression.author cannot be null.");
        }
        if (impression.getRelaseDate() == null) {
            throw new IllegalArgumentException("Impression.relaseDate cannot be null.");
        }
        if (impression.getDepartment() == null) {
            throw new IllegalArgumentException("Impression.department cannot be null.");
        }
    }
}

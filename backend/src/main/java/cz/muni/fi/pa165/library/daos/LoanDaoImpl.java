package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Loan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matej
 */
@Repository
public class LoanDaoImpl implements LoanDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    public void setEntityManager(EntityManager em) {
        entityManager = em;
    }      

    public void createLoan(Loan loan) {
        checkLoanAttributes(loan);
        if (loan.getId() != null) {
            throw new IllegalArgumentException("cannot create loan with assigned id");
        }
        entityManager.persist(loan);

    }

    public List<Loan> findAllLoans() {
        Query query = entityManager.createQuery("SELECT l FROM Loan l");
        return query.getResultList();
    }

    public Loan findLoanById(Long id) {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        return entityManager.find(Loan.class, id);
    }

    public void deleteLoan(Loan loan) {
        if (loan == null) {
            throw new NullPointerException("loan is null");
        }
        if (loan.getId() == null) {
            throw new IllegalArgumentException("cannot delete loan with no id");
        }
        Loan toRemove = entityManager.merge(loan);
        entityManager.remove(toRemove);
        loan.setId(null);
        System.out.println(toRemove.getId());
    }

    public void updateLoan(Loan loan) {
        checkLoanAttributes(loan);
        entityManager.merge(loan);
    }

    private void checkLoanAttributes(Loan loan) throws IllegalArgumentException, NullPointerException {
        if (loan == null) {
            throw new NullPointerException("loan is null");
        }
        if (loan.getBooks() == null) {
            throw new IllegalArgumentException("loan.books cannot be null");
        }
        if (loan.getCustomer() == null) {
            throw new IllegalArgumentException("loan.customer cannot be null");
        }
        if (loan.getFrom() == null) {
            throw new IllegalArgumentException("loan.fromDate cannot be null");
        }
        if (loan.getTo() == null) {
            throw new IllegalArgumentException("loan.toDate cannot be null");
        }

    }
}

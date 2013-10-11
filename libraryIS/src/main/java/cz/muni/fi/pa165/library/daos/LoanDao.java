package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Loan;
import java.util.List;

/**
 *
 * @author Mjartan
 */
public interface LoanDao {
    void createLoan(LoanDao book);
    List<Loan> findAllLoans();
    Loan findBookById(Long id);
    void deleteLoan(Loan loan);
    void updateLoan(Loan loan);    
}

package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Loan;
import java.util.List;

/**
 *
 * @author Matej
 */
public interface LoanDao {
    
    /** 
        * Inserts loan into DB
        * @param loan to be stored
    */
    
    void createLoan(Loan loan);
    
     /** 
        * Returns all loans from DB
        * @return  List of all loans found in DB
    */
    
    List<Loan> findAllLoans();
    
     /** 
        * Finds Loan with given ID
        * @param long ID of loan to be found
        * @return loan with given ID or NULL if search was unsuccessful
    */
    
    Loan findLoanById(Long id);
    
    /** 
        * Deletes loan in DB
        * @param loan to be deleted
    */
    
    void deleteLoan(Loan loan);
    
     /** 
        * updates loan in DB
        * @param loan to be updated
    */
    
    void updateLoan(Loan loan);    
    
}


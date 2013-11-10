/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.dtos.LoanTO;
import java.util.List;

/**
 *
 * @author Matej
 */
public interface LoanService {
    
    /** 
        * Inserts loan into DB
        * @param loan to be stored
    */
    
    void createLoan(LoanTO loan);
    
     /** 
        * Returns all loans from DB
        * @return  List of all loans found in DB
    */
    
    List<LoanTO> findAllLoans();
    
     /** 
        * Finds Loan with given ID
        * @param long ID of loan to be found
        * @return loan with given ID or NULL if search was unsuccessful
    */
    
    LoanTO findLoanById(Long id);
    
    /** 
        * Deletes loan in DB
        * @param loan to be deleted
    */
    
    void deleteLoan(LoanTO loan);
    
     /** 
        * updates loan in DB
        * @param loan to be updated
    */
    
    void updateLoan(LoanTO loan);    
    
    
}

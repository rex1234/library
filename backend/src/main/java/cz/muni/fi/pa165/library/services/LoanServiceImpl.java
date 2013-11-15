/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.LoanDao;
import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matej
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;
    
    @Autowired
    private Convertor convertor;

    public void createLoan(LoanTO loanTO) {
        Loan loanEntity = convertor.convert(loanTO);
        loanDao.createLoan(loanEntity);
        loanTO.setId(loanEntity.getId());
    }

    public List<LoanTO> findAllLoans() {
        List<LoanTO> loanTOs = new ArrayList<LoanTO>();
        List<Loan> loanEntities = loanDao.findAllLoans();
        for (Loan loan : loanEntities) {
            loanTOs.add(convertor.convert(loan));
        }
        return loanTOs;
    }

    public LoanTO findLoanById(Long id) {
        return convertor.convert(loanDao.findLoanById(id));
    }

    public void deleteLoan(LoanTO loanTO) {
        loanDao.deleteLoan(convertor.convert(loanTO));
        loanTO.setId(null);
    }

    public void updateLoan(LoanTO loanTO) {
        loanDao.updateLoan(convertor.convert(loanTO));
    }

    public void setDao(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    public List<LoanTO> findLoansForCustomer(CustomerTO customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<LoanTO> findLoansForBook(BookTO book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void returnBook(LoanTO loan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

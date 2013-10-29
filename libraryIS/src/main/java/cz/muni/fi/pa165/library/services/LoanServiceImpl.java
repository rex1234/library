/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.LoanDao;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matej
 */

    @Service
    public class LoanServiceImpl implements LoanService {

        @Autowired
        private LoanDao loanDao;

        public void createLoan(LoanTO loanTO) {
            Loan loanEntity = Convertor.convert(loanTO);
            loanDao.createLoan(loanEntity);
            loanTO.setId(loanEntity.getId());
        }

        public List<LoanTO> findAllLoans() {
            List<LoanTO> loanTOs = new ArrayList<LoanTO>();
            List<Loan> loanEntities = loanDao.findAllLoans();
            for (Loan loan : loanEntities) {
                loanTOs.add(Convertor.convert(loan));
            }
            return loanTOs;
        }

        public LoanTO findLoanById(Long id) {
            return Convertor.convert(loanDao.findLoanById(id));
        }

        public void deleteLoan(LoanTO loanTO) {
            loanDao.deleteLoan(Convertor.convert(loanTO));
            loanTO.setId(null);
        }

        public void updateLoan(LoanTO loanTO) {
            loanDao.updateLoan(Convertor.convert(loanTO));
        }
    }
    

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.dtos.CustomerTO;
import java.util.List;

/**
 *
 * @author Matej
 */
public interface CustomerService {
    
    /** 
        * Inserts Customer into DB
        * @param Customer to be stored
    */
    void createCustomer(CustomerTO customer);
    
    /** 
        * Returns all customers from DB
        * @return  List of all Customers found in DB
    */
    List<CustomerTO> findAllCustomers();
    
    
    /** 
        * Finds Customer with given ID
        * @param long ID of customer to be found
        * @return Customer with given ID or NULL if search was unsuccessful
    */
    CustomerTO findCustomerById(Long id);
    
    /** 
        * Deletes Customer in DB
        * @param Customer to be deleted
    */
    void deleteCustomer(CustomerTO customer);
    
    /** 
        * updates Customer in DB
        * @param Customer to be updated
    */
    void updateCustomer(CustomerTO customer);   
    
}

package cz.muni.fi.pa165.library.daos;

/**
 *
 * @author vit.mica
 */
 
import cz.muni.fi.pa165.library.entities.Customer;
import java.util.List;

public interface CustomerDao {
    
    /** 
        * Inserts Customer into DB
        * @param Customer to be stored
    */
    void createCustomer(Customer customer);
    
    /** 
        * Returns all customers from DB
        * @return  List of all Customers found in DB
    */
    List<Customer> findAllCustomers();
    
    
    /** 
        * Finds Customer with given ID
        * @param long ID of customer to be found
        * @return Customer with given ID or NULL if search was unsuccesful
    */
    Customer findCustomerById(Long id);
    
    /** 
        * Deletes Customer in DB
        * @param Customer to be deleted
    */
    void deleteCustomer(Customer customer);
    
    /** 
        * updates Customer in DB
        * @param Customer to be updated
    */
    void updateCustomer(Customer customer);   
}


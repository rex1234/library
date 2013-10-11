package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Customer;
import java.util.List;

/**
 *
 * @author Mjartan
 */
public interface CustomerDao {
    void createCustomer(Customer book);
    List<Customer> findAllCustomers();
    Customer findBookById(Long id);
    void deleteCustomer(Customer customer);
    void updateCustomer(Customer customer);    
}

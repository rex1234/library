package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.*;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vit.mica
 */

 
 
public class CustomerDaoImpl implements CustomerDao {
   
    private EntityManager em;
 
    public CustomerDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    // Just to be back-compatible
    public CustomerDaoImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
        em = emf.createEntityManager();
    }
    
 
    public void createCustomer(Customer customer) {
        if(customer == null){
            throw new IllegalArgumentException("customer is null.");
        }
        if(customer.getId() != null){
            throw new IllegalArgumentException("Customer ID already set.");
        }
        if(customer.getName() == null || customer.getName().isEmpty()){
            throw new IllegalArgumentException("Customer name is empty");
        }
        if(customer.getAddress() == null || customer.getAddress().isEmpty()){
            throw new IllegalArgumentException("customer address or empty");
        }
        
        em.persist(customer);
        
    }
   
    public List<Customer> findAllCustomers() {        
        Query query = em.createQuery("SELECT a FROM Customer a");
        return query.getResultList();        
    }
 
    public Customer findCustomerById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("id is null");
        }
        return em.find(Customer.class, id);     
    }
 
    public void deleteCustomer(Customer customer) {
        if(customer == null){
            throw new IllegalArgumentException("customer is null.");
        }
        if(customer.getId() == null){
            throw new IllegalArgumentException("Customer ID is null");
        }
        
        Customer toBeDeleted = em.merge(customer);
        if(toBeDeleted == null){
            throw new IllegalArgumentException("Customer doesn exist" + customer);
        }
        em.remove(toBeDeleted);
        toBeDeleted.setId(null);
    }
 
    public void updateCustomer(Customer customer) {
        if(customer == null){
            throw new IllegalArgumentException("customer is null.");
        }
        if(customer.getName() == null || customer.getName().isEmpty()){
            throw new IllegalArgumentException("Customer name is empty");
        }
        if(customer.getAddress() == null || customer.getAddress().isEmpty()){
            throw new IllegalArgumentException("customer address or empty");
        }
        
        Customer finded = em.find(Customer.class, customer.getId());
        if(finded == null){
             throw new IllegalArgumentException("Customer does not exist : " + customer);
        }
        
        em.merge(customer);
    }
   
}

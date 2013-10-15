package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.daos.*;
import cz.muni.fi.pa165.library.entities.*;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vit.mica
 */

 
 
public class CustomerDaoImpl implements CustomerDao {
   
    private EntityManagerFactory emf;
 
    public CustomerDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public CustomerDaoImpl() {
        emf = Persistence.createEntityManagerFactory("LibraryPU");
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
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
    }
   
    public List<Customer> findAllCustomers() {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT a FROM Customer a");
        return query.getResultList();        
    }
 
    public Customer findCustomerById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("id is null");
        }
        
        EntityManager em = emf.createEntityManager();

        return em.find(Customer.class, id);     
    }
 
    public void deleteCustomer(Customer customer) {
        if(customer == null){
            throw new IllegalArgumentException("customer is null.");
        }
        if(customer.getId() == null){
            throw new IllegalArgumentException("Customer ID is null");
        }
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Customer toBeDeleted = em.find(Customer.class, customer.getId());
        if(toBeDeleted == null){
            throw new IllegalArgumentException("Customer doesn exist" + customer);
        }
        em.remove(toBeDeleted);
        em.getTransaction().commit();
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
        
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Customer finded = em.find(Customer.class, customer.getId());
        if(finded == null){
             throw new IllegalArgumentException("Customer does not exist : " + customer);
        }
        
        em.merge(customer);
        em.getTransaction().commit();
    }
   
}

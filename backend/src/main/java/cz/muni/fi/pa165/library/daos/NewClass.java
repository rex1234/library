/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.joda.time.LocalDate;

/**
 *
 * @author MiškoHu
 */
public class NewClass {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
        EntityManager em = emf.createEntityManager();
        ImpressionDaoImpl dao = new ImpressionDaoImpl();
        dao.setEntityManager(em);

        Customer c2 = new Customer();
        c2.setAddress("Praha");
        c2.setName("Majo");
        c2.setIsDeleted(false);
        em.getTransaction().begin();
        em.persist(c2);
        em.getTransaction().commit();

        Customer c3 = new Customer();
        c3.setAddress("Vranov");
        c3.setName("Linda");
        c3.setIsDeleted(false);
        em.getTransaction().begin();
        em.persist(c3);
        em.getTransaction().commit();

        Impression i = new Impression();
        i.setAuthor("Carl May");
        i.setDepartment(Department.CHILDREN);
        i.setIsbn("123");
        i.setName("Poklad");
        i.setRelaseDate(new LocalDate(2012, 10, 5));
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();

        Impression i2 = new Impression();
        i2.setAuthor("Colfer");
        i2.setDepartment(Department.CHILDREN);
        i2.setIsbn("546");
        i2.setName("Artemis");
        i2.setRelaseDate(new LocalDate(2012, 10, 5));
        em.getTransaction().begin();
        em.persist(i2);
        em.getTransaction().commit();

        Book b1 = new Book();
        b1.setCondition("jednotka");
        b1.setImpression(i);
        em.getTransaction().begin();
        em.persist(b1);
        em.getTransaction().commit();

        Book b2 = new Book();
        b2.setCondition("dojka");
        b2.setImpression(i);
        em.getTransaction().begin();
        em.persist(b2);
        em.getTransaction().commit();

        Book b3 = new Book();
        b3.setCondition("trei");
        b3.setImpression(i2);
        em.getTransaction().begin();
        em.persist(b3);
        em.getTransaction().commit();

        Book b4 = new Book();
        b4.setCondition("styri");
        b4.setImpression(i2);
        em.getTransaction().begin();
        em.persist(b4);
        em.getTransaction().commit();


        Loan l = new Loan();
        l.setBook(b2);
        l.setConditionReturned("super");
        l.setCustomer(c2);
        l.setFromDate(new LocalDate(2010, 1, 1));
        l.setToDate(new LocalDate(2010, 1, 20));
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();

        Loan l2 = new Loan();
        l2.setBook(b2);
        l2.setConditionReturned("super");
        l2.setCustomer(c2);
        l2.setFromDate(new LocalDate(2010, 1, 25));
        l2.setToDate(new LocalDate(2010, 2, 1));
        em.getTransaction().begin();
        em.persist(l2);
        em.getTransaction().commit();

        Loan l3 = new Loan();
        l3.setBook(b4);
        l3.setConditionReturned("super");
        l3.setCustomer(c2);
        l3.setFromDate(new LocalDate(2010, 3, 1));
        l3.setToDate(new LocalDate(2010, 2, 1));
        em.getTransaction().begin();
        em.persist(l3);
        em.getTransaction().commit();

        Loan l4 = new Loan();
        l4.setBook(b4);
        l4.setConditionReturned("super");
        l4.setCustomer(c2);
        l4.setFromDate(new LocalDate(2010, 1, 1));
        em.getTransaction().begin();
        em.persist(l4);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        dao.deleteImpression(i2);
        em.getTransaction().commit();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.daos.BookDaoImpl;
import cz.muni.fi.pa165.library.daos.ImpressionDao;
import cz.muni.fi.pa165.library.daos.ImpressionDaoImpl;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author MiškoHu
 */
public class ImpressionDaoImplTest {

    private ImpressionDao dao;    
  
    private EntityManager em;

    public ImpressionDaoImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationTestContext.xml");
        dao = context.getBean(ImpressionDao.class);        
    }

    @Test
    public void testCreateImpression() {
     
        Impression impression = getTestImpression();
        dao.createImpression(impression);
       
        assertNotNull(impression.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullImpression() {
       
        dao.createImpression(null);
       
    }

    @Test(expected = DataAccessException.class)
    public void testCreateImpressionWithId() {
        Impression impression = getTestImpression();
        impression.setId(5L);
       
        dao.createImpression(impression);
      
    }

    @Test(expected = DataAccessException.class)
    public void testCreateImpressionWithNullName() {
        Impression impression = getTestImpression();
        impression.setName(null);
   
        dao.createImpression(impression);
    
    }

    @Test(expected = DataAccessException.class)
    public void testCreateImpressionWithNullIsbn() {
        Impression impression = getTestImpression();
        impression.setIsbn(null);
      
        dao.createImpression(impression);
    
    }

    @Test(expected = DataAccessException.class)
    public void testCreateImpressionWithNullAuthor() {
        Impression impression = getTestImpression();
        impression.setAuthor(null);
       
        dao.createImpression(impression);
     
    }

    @Test(expected = DataAccessException.class)
    public void testCreateImpressionWithNullDate() {
        Impression impression = getTestImpression();
        impression.setRelaseDate(null);

        dao.createImpression(impression);
      
    }

    @Test(expected = DataAccessException.class)
    public void testCreateImpressionWithNullDepartment() {
        Impression impression = getTestImpression();
        impression.setDepartment(null);
  
        dao.createImpression(impression);
        
    }

    @Test
    public void testFindAllImpressions() {
        List<Impression> impressions = new ArrayList<Impression>() {
            {
                add(getTestImpression());
                add(createTestImpression("124", "Varime spolu", "Smolko",
                        new LocalDate(2010, 11, 8), Department.COOKING));
                add(createTestImpression("125", "Man of Steel", "John Smith",
                        new LocalDate(2013, 1, 1), Department.FICTION));
            }
        };
        for (Impression impression : impressions) {
           
            dao.createImpression(impression);
         
        }
        compareImpressions(impressions, dao.findAllImpressions());
    }

    @Test
    public void testFindImpressionById() {
        Impression impression = getTestImpression();
     
        dao.createImpression(impression);
      
        Impression found = dao.findImpressionById(impression.getId());
        compareImpressions(impression, found);
    }

    @Test
    public void testDeleteImpression() {
        Impression impression = getTestImpression();
      
        dao.createImpression(impression);
        
        Long oldID = impression.getId();
        dao.deleteImpression(impression);
     
        assertNull(impression.getId());
        assertNull(dao.findImpressionById(oldID));
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteImpressionWithNullId() {
        Impression impression = getTestImpression();
     
        dao.createImpression(impression);
       
        impression.setId(null);
     
        dao.deleteImpression(impression);
    
    }

    @Test
    public void testUpdateImpression() {
        Impression impression = getTestImpression();
     
        dao.createImpression(impression);
      
        impression.setName("Ferko");
        impression.setIsbn("555");
        impression.setAuthor("Anton Mravec");
     
        dao.updateImpression(impression);
      
        assertEquals(impression, dao.findImpressionById(impression.getId()));
    }

    private static Impression getTestImpression() {
        return createTestImpression("123", "Ako islo vajce na vandrovku",
                "Michal Kekely", new LocalDate(2001, 11, 8), Department.CHILDREN);
    }

    private static Impression createTestImpression(String isbn, String name,
            String author, LocalDate releaseDate, Department department) {

        Impression impression = new Impression();
        impression.setIsbn(isbn);
        impression.setName(name);
        impression.setAuthor(author);
        impression.setRelaseDate(releaseDate);
        impression.setDepartment(department);
        return impression;
    }

    private void compareImpressions(Impression i1, Impression i2) {
        assertEquals(i1.getName(), i2.getName());
        assertEquals(i1.getIsbn(), i2.getIsbn());
        assertEquals(i1.getAuthor(), i2.getAuthor());
        assertEquals(i1.getRelaseDate(), i2.getRelaseDate());
        assertEquals(i1.getDepartment(), i2.getDepartment());
    }

    private void compareImpressions(List<Impression> i1, List<Impression> i2) {
        Collections.sort(i1, impressionIdCmp);
        Collections.sort(i2, impressionIdCmp);
        assertEquals(i1.size(), i2.size());
        for (int i = 0; i < i1.size(); i++) {
            compareImpressions(i1.get(i), i2.get(i));
        }
    }
    private static Comparator<Impression> impressionIdCmp = new Comparator<Impression>() {
        public int compare(Impression o1, Impression o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}

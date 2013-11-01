/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.ImpressionDao;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.services.ImpressionServiceImpl;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.List;
import java.util.Map;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Matej
 */
public class ImpressionServiceImplTest {

    private ApplicationContext context;
    private ImpressionDao impressionDao;
    private Map<Long, Impression> impressionMap;
    private Impression testImpression1;
    private Impression testImpression2;

    public ImpressionServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationTestContext.xml");

        testImpression1 = new Impression();
        testImpression1.setIsbn("123456");
        testImpression1.setAuthor("Fero Mrkvicka");
        testImpression1.setName("Vajce");
        testImpression1.setRelaseDate(new LocalDate(2000, 1, 1));
        testImpression1.setDepartment(Department.CHILDREN);

        testImpression2 = new Impression();
        testImpression2.setIsbn("111111");
        testImpression2.setAuthor("J.K.R.");
        testImpression2.setName("HP");
        testImpression2.setRelaseDate(new LocalDate(2009, 12, 1));
        testImpression2.setDepartment(Department.FICTION);



        impressionDao = mock(ImpressionDao.class);
        mockUpdate();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateImpression() {


        ImpressionServiceImpl impressionService = context.getBean(ImpressionServiceImpl.class);


        ImpressionTO testImpression1TO = Convertor.convert(testImpression1);

        impressionService.createImpression(testImpression1TO);

        assertNotNull("Impression ID should be set", testImpression1TO.getId());
        mockUpdate();
        ImpressionTO impressionTO = impressionService.findImpressionById(testImpression1TO.getId());

        assertEquals(testImpression1TO, impressionTO);
        assertEquals(testImpression1TO.getId(), impressionTO.getId());
        assertEquals(testImpression1TO.getName(), impressionTO.getName());
        assertEquals(testImpression1TO.getAuthor(), impressionTO.getAuthor());
        assertEquals(testImpression1TO.getIsbn(), impressionTO.getIsbn());
        assertEquals(testImpression1TO.getDepartment(), impressionTO.getDepartment());
        assertEquals(testImpression1TO.getRelaseDate(), impressionTO.getRelaseDate());


    }
    
    @Test
    public void testFindImpressionById() {
        ImpressionServiceImpl impressionService = context.getBean(ImpressionServiceImpl.class);

        ImpressionTO testImpression1TO = Convertor.convert(testImpression1);
        ImpressionTO testImpression2TO = Convertor.convert(testImpression2);

        impressionService.createImpression(testImpression1TO);
        impressionService.createImpression(testImpression2TO);


        ImpressionTO impressionTO = impressionService.findImpressionById(testImpression1TO.getId());
        assertEquals(testImpression1TO.getId(), impressionTO.getId());
        assertEquals(testImpression1TO.getName(), impressionTO.getName());
        assertEquals(testImpression1TO.getAuthor(), impressionTO.getAuthor());
        assertEquals(testImpression1TO.getIsbn(), impressionTO.getIsbn());
        assertEquals(testImpression1TO.getDepartment(), impressionTO.getDepartment());
        assertEquals(testImpression1TO.getRelaseDate(), impressionTO.getRelaseDate());

        ImpressionTO impressionTO2 = impressionService.findImpressionById(testImpression2TO.getId());
        assertEquals(testImpression2TO.getId(), impressionTO2.getId());
        assertEquals(testImpression2TO.getName(), impressionTO2.getName());
        assertEquals(testImpression2TO.getAuthor(), impressionTO2.getAuthor());
        assertEquals(testImpression2TO.getIsbn(), impressionTO2.getIsbn());
        assertEquals(testImpression2TO.getDepartment(), impressionTO2.getDepartment());
        assertEquals(testImpression2TO.getRelaseDate(), impressionTO2.getRelaseDate());
    }

    @Test
    public void testFindAllImpressions() {

        ImpressionServiceImpl impressionService = context.getBean(ImpressionServiceImpl.class);

        ImpressionTO testImpression1TO = Convertor.convert(testImpression1);
        ImpressionTO testImpression2TO = Convertor.convert(testImpression2);

        impressionService.createImpression(testImpression1TO);
        impressionService.createImpression(testImpression2TO);
        mockUpdate();

        List<ImpressionTO> impressionsTO = impressionService.findAllImpressions();
        assertEquals(2, impressionsTO.size());
        assertEquals(testImpression1TO, impressionsTO.get(0));
        assertEquals(testImpression2TO, impressionsTO.get(1));
    }


    @Test
    public void testDeleteImpression() {

        ImpressionServiceImpl impressionService = context.getBean(ImpressionServiceImpl.class);

        ImpressionTO testImpression1TO = Convertor.convert(testImpression1);
        ImpressionTO testImpression2TO = Convertor.convert(testImpression2);

        impressionService.createImpression(testImpression1TO);
        impressionService.createImpression(testImpression2TO);
        mockUpdate();

        List<ImpressionTO> impressionsTO = impressionService.findAllImpressions();
        assertEquals(2, impressionsTO.size());

        impressionService.deleteImpression(testImpression1TO);
        mockUpdate();
        impressionsTO = impressionService.findAllImpressions();
        assertEquals(1, impressionsTO.size());

        impressionService.deleteImpression(testImpression2TO);
        mockUpdate();
        impressionsTO = impressionService.findAllImpressions();
        assertEquals(0, impressionsTO.size());

    }
    
    @Test
    public void testUpdateImpression() {

        ImpressionServiceImpl impressionService = context.getBean(ImpressionServiceImpl.class);
        ImpressionTO testImpression1TO = Convertor.convert(testImpression1);
        impressionService.createImpression(testImpression1TO);

        testImpression1TO.setIsbn("654321");
        testImpression1TO.setName("Lev a Macka");
        testImpression1TO.setAuthor("Christensen");
        testImpression1TO.setRelaseDate(new LocalDate(2005, 10, 10));
        testImpression1TO.setDepartment(Department.POETRY);

        testImpression1 = Convertor.convert(testImpression1TO);
        mockUpdate();
        impressionService.updateImpression(testImpression1TO);
        mockUpdate();
        ImpressionTO impressionTO = impressionService.findImpressionById(testImpression1TO.getId());

        assertEquals(testImpression1TO.getId(), impressionTO.getId());
        assertEquals(testImpression1TO.getName(), impressionTO.getName());
        assertEquals(testImpression1TO.getAuthor(), impressionTO.getAuthor());
        assertEquals(testImpression1TO.getIsbn(), impressionTO.getIsbn());
        assertEquals(testImpression1TO.getDepartment(), impressionTO.getDepartment());
        assertEquals(testImpression1TO.getRelaseDate(), impressionTO.getRelaseDate());

    }

    

    private void mockUpdate() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                impressionMap.put(testImpression1.getId(), testImpression1);
                return null;
            }
        }).when(impressionDao).createImpression(testImpression1);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                impressionMap.put(testImpression1.getId(), testImpression1);
                return null;
            }
        }).when(impressionDao).updateImpression(testImpression1);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                impressionMap.remove(testImpression1.getId());
                return null;
            }
        }).when(impressionDao).deleteImpression(testImpression1);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                impressionMap.remove(testImpression2.getId());
                return null;
            }
        }).when(impressionDao).deleteImpression(testImpression2);


    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library;

import static cz.muni.fi.pa165.library.ImpressionDaoImplTest.createTestImpression;
import static cz.muni.fi.pa165.library.ImpressionDaoImplTest.getTestImpression;
import cz.muni.fi.pa165.library.daos.ImpressionDao;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.services.ImpressionService;
import cz.muni.fi.pa165.library.services.ImpressionServiceImpl;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.joda.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Mjartan
 */
@RunWith(PowerMockRunner.class)
public class ImpressionServiceImplTest {
    
    @Mock
    ImpressionDao imDao;
    
    @InjectMocks
    private ImpressionService imService = new ImpressionServiceImpl();
    
    @Test
    public void testFindImpression() {
        when(imDao.findImpressionById(22l)).thenReturn(getTestImpression());
        compareImpressions(getTestImpressionTO(), imService.findImpressionById(22L));
    }
    
    @Test
    public void testFindAllImpressions() {
        List<Impression> ims = new ArrayList<Impression>() {{
            add(createTestImpression(5L, "Hevier"));
            add(createTestImpression(10L, "Rufus"));
            add(createTestImpression(15L, "Stur"));            
        }};
        
        ArrayList<ImpressionTO> imTos = new ArrayList<ImpressionTO>() {{
            add(createTestImpressionTO(5L, "Hevier"));
            add(createTestImpressionTO(10L, "Rufus"));
            add(createTestImpressionTO(15L, "Stur"));    
        }};
        when(imDao.findAllImpressions()).thenReturn(ims);
        compareImpressions(imTos, imService.findAllImpressions());
    }
    
    @Test
    public void testDeleteCustomer() {
        imService.deleteImpression(getTestImpressionTO());
        verify(imDao).deleteImpression(getTestImpression());
    }
    
    @Test
    public void testUpdateCustomer() {
        imService.updateImpression(getTestImpressionTO());
        verify(imDao).updateImpression(getTestImpression());
    }
      
    static ImpressionTO getTestImpressionTO() {
        return Convertor.convert(getTestImpression());
    }
      
    private static void compareImpressions(ImpressionTO i1, ImpressionTO i2) {
        assertEquals(i1.getName(), i2.getName());
        assertEquals(i1.getIsbn(), i2.getIsbn());
        assertEquals(i1.getAuthor(), i2.getAuthor());
        assertEquals(i1.getRelaseDate(), i2.getRelaseDate());
        assertEquals(i1.getDepartment(), i2.getDepartment());
    }
    
    private static Impression createTestImpression(Long id, String author) {
        Impression i = new Impression();
        i.setAuthor(author);
        i.setId(id);
        i.setDepartment(Department.MAGAZINES);
        i.setRelaseDate(new LocalDate(1990,1,1));
        i.setName("A");
        return i;
    }
    
    private static ImpressionTO createTestImpressionTO(Long id, String author) {
        return Convertor.convert(createTestImpression(id, author));
    }
    
    private void compareImpressions(List<ImpressionTO> i1, List<ImpressionTO> i2) {
        Collections.sort(i1, impressionIdCmp);
        Collections.sort(i2, impressionIdCmp);
        assertEquals(i1.size(), i2.size());
        for (int i = 0; i < i1.size(); i++) {
            compareImpressions(i1.get(i), i2.get(i));
        }
    }
    private static Comparator<ImpressionTO> impressionIdCmp = new Comparator<ImpressionTO>() {
        public int compare(ImpressionTO o1, ImpressionTO o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.CustomerDao;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.services.CustomerService;
import cz.muni.fi.pa165.library.services.CustomerServiceImpl;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author vit.mica
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Convertor.class)
public class CustomerServiceImplTest extends TestCase {
   
    @Mock
    private CustomerDao customerDAO;
    
    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();
    
    @Mock
    private Customer customer;
    
    @Mock
    private CustomerTO customerTO;
    
    
    @Before
    @Override
    public void setUp() {
        PowerMockito.mockStatic(Convertor.class);
        when(Convertor.convertCustomerEntityToTO(null)).thenReturn(null);
        when(Convertor.convertCustomerTOToEntity(null)).thenReturn(null);
        when(Convertor.convertCustomerEntityToTO(customer)).thenReturn(customerTO);
        when(Convertor.convertCustomerTOToEntity(customerTO)).thenReturn(customer);
    }
    
       @Test(expected=Exception.class)
    public void testCreateNullCustomer() {
        doThrow(new Exception("null argument")).when(customerDAO).createCustomer(null);
        customerService.createCustomer(null);
    }
    
    @Test(expected=Exception.class)
    public void testCreateCustomerWrongName() {
        when(customer.getName()).thenReturn(null);
        doThrow(new Exception("null argument")).when(customerDAO).createCustomer(null);
        customerService.createCustomer(customerTO);
        
        when(customer.getName()).thenReturn("");
        doThrow(new Exception("null argument")).when(customerDAO).createCustomer(null);
        customerService.createCustomer(customerTO);
    }
    
    @Test(expected=DataAccessException.class)
    public void testFindCustomerByNullId() {
        when(customerDAO.findCustomerById(null)).thenThrow(new DataAccessException("null customer id") {}); 
        
        customerService.findCustomerById(null);
    }
    
    @Test(expected=DataAccessException.class) 
    public void testFindCustomerWithWrongId() {
        when(customerDAO.findCustomerById(new Long(-1))).thenThrow(new DataAccessException("illegal customer id") {});
        customerService.findCustomerById(new Long(-1));
    }

    @Test
    public void testFindCustomerByCorrectId() {
        when(customerDAO.findCustomerById(1l)).thenReturn(customer);
        assertEquals(customerTO, customerService.findCustomerById(1l));
    }
    
    @Test
    public void testUpdateCustomerWithNullID() {
       doThrow(new DataAccessException("null customer id") {}).when(customerDAO).updateCustomer(customer);
       customerService.updateCustomer(null);
    }
    
    @Test
    public void testDeleteCustomer() {
        customerService.deleteCustomer(customerTO);
        verify(customerDAO).deleteCustomer(customer);
    }
    
    @Test
    public void testUpdateCustomer() {
        customerService.updateCustomer(customerTO);
        verify(customerDAO).updateCustomer(customer);
    }
    
}

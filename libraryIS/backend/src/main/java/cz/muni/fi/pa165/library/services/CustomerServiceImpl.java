package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.CustomerDao;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matej
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Convertor convertor;

    public void createCustomer(CustomerTO customerTO) {
        Customer customerEntity = convertor.convert(customerTO);
        customerDao.createCustomer(customerEntity);
        customerTO.setId(customerEntity.getId());
    }

    public List<CustomerTO> findAllCustomers() {
        List<CustomerTO> customerTOs = new ArrayList<CustomerTO>();
        List<Customer> customerEntities = customerDao.findAllCustomers();
        for (Customer customer : customerEntities) {
            customerTOs.add(convertor.convert(customer));
        }
        return customerTOs;
    }

    public CustomerTO findCustomerById(Long id) {
        return convertor.convert(customerDao.findCustomerById(id));
    }

    public void deleteCustomer(CustomerTO customerTO) {
        customerTO.setIsDeleted(true);
        customerDao.updateCustomer(convertor.convert(customerTO));
    }

    public void updateCustomer(CustomerTO customerTO) {
        customerDao.updateCustomer(convertor.convert(customerTO));
    }

    public Convertor getConvertor() {
        return convertor;
    }

    public void setConvertor(Convertor convertor) {
        this.convertor = convertor;
    }

    public List<CustomerTO> findCustomers(String name) {
        List<CustomerTO> customerTOs = new ArrayList<CustomerTO>();
        List<Customer> customerEntities = customerDao.findCustomersByName(name);
        for (Customer customer : customerEntities) {
            customerTOs.add(convertor.convert(customer));
        }
        return customerTOs;
    }
}

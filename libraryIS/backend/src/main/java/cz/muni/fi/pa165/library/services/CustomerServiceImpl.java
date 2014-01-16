package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.CustomerDao;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matej
 */
@Service("userDetailsService") 
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Convertor convertor;

    @Secured({"ROLE_ADMIN"})
    public void createCustomer(CustomerTO customerTO) {
        Customer customerEntity = convertor.convert(customerTO);
        customerDao.createCustomer(customerEntity);
        customerTO.setId(customerEntity.getId());
    }

    @Secured({"ROLE_ADMIN"})
    public List<CustomerTO> findAllCustomers() {
        List<CustomerTO> customerTOs = new ArrayList<CustomerTO>();
        List<Customer> customerEntities = customerDao.findAllCustomers();
        for (Customer customer : customerEntities) {
            customerTOs.add(convertor.convert(customer));
        }
        return customerTOs;
    }

    @Secured({"ROLE_ADMIN"})
    public CustomerTO findCustomerById(Long id) {
        return convertor.convert(customerDao.findCustomerById(id));
    }

    @Secured({"ROLE_ADMIN"})
    public void deleteCustomer(CustomerTO customerTO) {
        customerTO.setIsDeleted(true);
        customerDao.updateCustomer(convertor.convert(customerTO));
    }

    @Secured({"ROLE_ADMIN"})
    public void updateCustomer(CustomerTO customerTO) {
        customerDao.updateCustomer(convertor.convert(customerTO));
    }

    public Convertor getConvertor() {
        return convertor;
    }

    public void setConvertor(Convertor convertor) {
        this.convertor = convertor;
    }

    @Secured({"ROLE_ADMIN"})
    public List<CustomerTO> findCustomers(String name) {
        List<CustomerTO> customerTOs = new ArrayList<CustomerTO>();
        List<Customer> customerEntities = customerDao.findCustomersByName(name);
        for (Customer customer : customerEntities) {
            customerTOs.add(convertor.convert(customer));
        }
        return customerTOs;
    }

    @Transactional(readOnly = true)
    public UserDetails findCustomerByUsername(String username) {

        Customer cust = customerDao.findCustomerWithUsername(username);
        if (cust == null) {
            throw new UsernameNotFoundException("user with name" + username + " not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
        return new User(cust.getUserName(), cust.getPassword(), true, true, true, true, authorities);
    }
}

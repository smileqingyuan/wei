package wei.smile.service;

import org.junit.Before;
import org.junit.Test;
import wei.smile.model.Customer;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author smilewei on 2018/8/12.
 * @since 1.0.0
 */
public class CustomerServiceTest {

    @Before
    public void init(){

    }

    @Test
    public void getCustomerList() throws Exception {

        CustomerService customerService = new CustomerService();
        List<Customer> customerList = customerService.getCustomerList("");
        System.out.println(customerList);

    }

    @Test
    public void getCustomer() throws Exception {
    }

    @Test
    public void createCustomer() throws Exception {
    }

    @Test
    public void updateCustomer() throws Exception {
    }

    @Test
    public void deleteCustomer() throws Exception {
    }

}
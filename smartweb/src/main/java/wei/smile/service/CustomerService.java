package wei.smile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wei.smile.helper.DataBaseHelper;
import wei.smile.model.Customer;
import wei.smile.util.PropsUtil;

import java.util.List;
import java.util.Properties;

/**
 * @author smilewei on 2018/8/12.
 * @since 1.0.0
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);



    public List<Customer> getCustomerList(String keyword) {
        String sql = "SELECT * FROM customer";
        return DataBaseHelper.queryEntityList(Customer.class,sql);
    }


    public Customer getCustomer(long id) {

        return null;
    }


    public boolean createCustomer(Customer customer) {
        return false;
    }


    public boolean updateCustomer(Customer customer){
        return false;
    }

    public boolean deleteCustomer(long id){
        return false;
    }


}

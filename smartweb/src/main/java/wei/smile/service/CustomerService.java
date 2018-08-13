package wei.smile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wei.smile.helper.DataBaseHelper;
import wei.smile.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * 客户服务
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
        String sql = "SELECT * FROM customer WHERE id ="+id;
        return DataBaseHelper.queryEntity(Customer.class,sql,id);
    }


    public boolean createCustomer(Map<String,Object> fieldMap) {
        return DataBaseHelper.insertEntity(Customer.class,fieldMap);
    }


    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        return DataBaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    public boolean deleteCustomer(long id){
        return DataBaseHelper.deleteEntity(Customer.class,id);
    }


}

package wei.smile.controller;

import wei.smile.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smilewei on 2018/8/12.
 * @since 1.0.0
 */
@WebServlet(value = "/customer_delete",loadOnStartup = 0)
public class CustomerDeleteServlet extends HttpServlet{

    private CustomerService customerService = null;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean b = customerService.deleteCustomer(Long.parseLong(id));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

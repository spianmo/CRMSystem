package factory;


import service.CustomerService;
import service.EmployeeService;
import service.FeedbackService;
import service.ProduceService;
import service.TaskService;
import service.TradeService;
import service.UserService;
import service.impl.CustomerServiceImpl;
import service.impl.EmployeeServiceImpl;
import service.impl.FeedbackServiceImpl;
import service.impl.ProduceServiceImpl;
import service.impl.TaskServiceImpl;
import service.impl.TradeServiceImpl;
import service.impl.UserServiceImpl;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author Finger
 * @Date 11/15/2020
 **/
public class ServiceFactory {
    public static CustomerService getCustomerServiceInstance() {
        return new CustomerServiceImpl();
    }

    public static EmployeeService getEmployeeServiceInstance() {
        return new EmployeeServiceImpl();
    }

    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }

    public static FeedbackService getFeedbackServiceInstance() {
        return new FeedbackServiceImpl();
    }

    public static TaskService getTaskServiceInstance() {
        return new TaskServiceImpl();
    }

    public static ProduceService getProduceServiceInstance() {
        return new ProduceServiceImpl();
    }

    public static TradeService getTradeServiceInstance() {
        return new TradeServiceImpl();
    }
}

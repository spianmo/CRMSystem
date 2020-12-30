package factory;


import dao.CustomerDao;
import dao.EmployeeDao;
import dao.FeedbackDao;
import dao.ProduceDao;
import dao.TaskDao;
import dao.UserDao;
import dao.impl.CustomerDaoImpl;
import dao.impl.EmployeeDaoImpl;
import dao.impl.FeedbackDaoImpl;
import dao.impl.ProduceDaoImpl;
import dao.impl.TaskDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * @ClassName DaoFactory
 * @Description TODO
 * @Author Finger
 * @Date 11/15/2020
 **/
public class DaoFactory {
    public static CustomerDao getCustomerDaoInstance(){
        return new CustomerDaoImpl();
    }

    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

    public static EmployeeDao getEmployeeDaoInstance(){
        return new EmployeeDaoImpl();
    }

    public static FeedbackDao getFeedbackDaoInstance(){
        return new FeedbackDaoImpl();
    }

    public static TaskDao getTaskDaoInstance(){
        return new TaskDaoImpl();
    }

    public static ProduceDao getProduceDaoInstance(){
        return new ProduceDaoImpl();
    }
}

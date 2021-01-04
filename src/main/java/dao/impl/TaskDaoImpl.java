package dao.impl;

import dao.TaskDao;
import entity.Task;
import org.intellij.lang.annotations.Language;
import utils.jdbc.BeanHandler;
import utils.jdbc.BeanListHandler;
import utils.jdbc.Db;

import java.util.List;

/**
 * @ClassName TaskDaoImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public class TaskDaoImpl implements TaskDao {
    @Override
    public List<Task> selectAllTask() {
        @Language("SQL") String sql = "SELECT * FROM t_task";
        return Db.executeQuery(sql, new BeanListHandler<>(Task.class));
    }

    @Override
    public Task selectTaskById(int id) {
        @Language("SQL") String sql = "SELECT * FROM t_task WHERE task_id = ?";
        return Db.executeQuery(sql, new BeanHandler<>(Task.class), id);
    }

    @Override
    public List<Task> selectTaskByEmployeeId(int employeeId) {
        @Language("SQL") String sql = "SELECT * FROM t_task WHERE employee_id = ?";
        return Db.executeQuery(sql, new BeanListHandler<>(Task.class), employeeId);
    }

    @Override
    public int insertTask(Task task) {
        @Language("SQL") String sql = "INSERT INTO t_task (task_time, customer_num, employee_id) VALUES (?,?,?)";
        return Db.executeUpdate(sql,task.getTaskTime(),task.getCustomerNum(),task.getEmployeeId());
    }

    @Override
    public int deleteTask(int taskId) {
        @Language("SQL") String sql = "DELETE FROM t_task WHERE task_id = ?";
        return Db.executeUpdate(sql, taskId);
    }

    @Override
    public int updateTask(Task task) {
        @Language("SQL") String sql = "UPDATE t_task SET task_time = ?, customer_num = ?, employee_id = ? WHERE task_id = ?";
        return Db.executeUpdate(sql,task.getTaskTime(),task.getCustomerNum(),task.getEmployeeId());
    }
}

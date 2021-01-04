package dao;

import entity.Task;

import java.util.List;

public interface TaskDao {
    List<Task> selectAllTask();
    Task selectTaskById(int id);
    List<Task> selectTaskByEmployeeId(int employeeId);
    int insertTask(Task task);
    int deleteTask(int taskId);
    int updateTask(Task task);
}

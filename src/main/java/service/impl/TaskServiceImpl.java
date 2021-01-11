package service.impl;

import java.util.List;

import dao.TaskDao;
import entity.Task;
import factory.DaoFactory;
import service.TaskService;

/**
 * @ClassName TaskServiceImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public class TaskServiceImpl implements TaskService {
    TaskDao mTaskDao = DaoFactory.getTaskDaoInstance();
    @Override
    public List<Task> selectAllTask() {
        return mTaskDao.selectAllTask();
    }

    @Override
    public Task selectTaskById(int id) {
        return mTaskDao.selectTaskById(id);
    }

    @Override
    public List<Task> selectTaskByEmployeeId(int employeeId) {
        return mTaskDao.selectTaskByEmployeeId(employeeId);
    }

    @Override
    public boolean insertTask(Task task) {
        return mTaskDao.insertTask(task) == 1;
    }

    @Override
    public boolean deleteTask(int taskId) {
        return mTaskDao.deleteTask(taskId) == 1;
    }

    @Override
    public boolean updateTask(Task task) {
        return mTaskDao.updateTask(task) == 1;
    }
}

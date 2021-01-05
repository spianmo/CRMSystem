package dao;

import entity.Task;

import java.util.List;

public interface TaskDao {
    /**
     * 查询所有计划
     * @return 返回所有的计划
     */
    List<Task> selectAllTask();

    /**
     * 根据计划Id查询计划
     * @param id 计划Id
     * @return 返回根据计划ID查询的计划实体集
     */
    Task selectTaskById(int id);

    /**
     * 根据员工ID查询计划
     * @param employeeId 员工ID
     * @return 返回根据员工ID查询计划实体集
     */
    List<Task> selectTaskByEmployeeId(int employeeId);

    /**
     * 插入一条新的计划记录
     * @param task 需要新增的计划对象
     * @return 返回影响行数
     */
    int insertTask(Task task);

    /**
     * 根据计划ID删除计划
     * @param taskId 需要删除的计划ID
     * @return 返回影响行数
     */
    int deleteTask(int taskId);

    /**
     * 更新一条计划记录
     * @param task 需要更新的计划对象
     * @return 返回影响行数
     */
    int updateTask(Task task);
}

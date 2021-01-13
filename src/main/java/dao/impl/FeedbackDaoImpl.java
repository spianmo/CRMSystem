package dao.impl;

import org.intellij.lang.annotations.Language;

import java.util.List;

import dao.FeedbackDao;
import entity.Feedback;
import entity.vo.FeedbackVo;
import utils.jdbc.BeanHandler;
import utils.jdbc.BeanListHandler;
import utils.jdbc.Db;

/**
 * @ClassName FeedbackDaoImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public class FeedbackDaoImpl implements FeedbackDao {
    /**
     * 根据产品ID查询反馈
     *
     * @param produceId 产品ID
     * @return 根据产品ID查出的订单
     */
    @Override
    public List<Feedback> selectFeedbackByProduceId(int produceId) {
        @Language("SQL") String sql = "SELECT * FROM t_feedback WHERE produce_id = ?";
        return Db.executeQuery(sql, new BeanListHandler<>(Feedback.class), produceId);
    }

    /**
     * 根据顾客ID查询反馈
     *
     * @param customerId 顾客ID
     * @return 根据顾客ID查出的反馈
     */
    @Override
    public List<FeedbackVo> selectFeedbackByCustomerId(int customerId) {
        @Language("SQL") String sql = "SELECT t1.*,t3.name AS employee_name,t4.name AS produce_name,t2.name AS customer_name FROM t_feedback t1 LEFT JOIN t_customer t2 ON t1.customer_id = t2.customer_id LEFT JOIN t_employee t3 ON t2.employee_id = t3.employee_id LEFT JOIN  t_produce t4 ON t1.produce_id = t4.produce_id WHERE t1.customer_id = ?";
        return Db.executeQuery(sql, new BeanListHandler<>(FeedbackVo.class), customerId);
    }

    @Override
    public List<FeedbackVo> selectFeedbackByEmployeeId(int employeeId) {
        @Language("SQL") String sql = "SELECT t1.*,t3.name AS employee_name,t4.name AS produce_name,t2.name AS customer_name FROM t_feedback t1 LEFT JOIN t_customer t2 ON t1.customer_id = t2.customer_id LEFT JOIN t_employee t3 ON t2.employee_id = t3.employee_id LEFT JOIN  t_produce t4 ON t1.produce_id = t4.produce_id WHERE t2.employee_id = ?";
        return Db.executeQuery(sql, new BeanListHandler<>(FeedbackVo.class), employeeId);
    }

    @Override
    public Feedback selectFeedbackById(int feedbackId) {
        @Language("SQL") String sql = "SELECT * FROM t_feedback WHERE feedback_id = ?";
        return Db.executeQuery(sql, new BeanHandler<>(Feedback.class), feedbackId);
    }

    /**
     * 插入一条新的反馈记录
     *
     * @param feedback 需要新增的反馈对象
     * @return 返回影响行数
     */
    @Override
    public int insertFeedback(Feedback feedback) {
        @Language("SQL") String sql = "INSERT INTO t_feedback(produce_id, customer_id, content, create_time, deal_status) VALUES (?,?,?,?,?);";
        return Db.executeUpdate(sql, feedback.getProduceId(), feedback.getCustomerId(), feedback.getContent(), feedback.getCreateTime(), feedback.getDealStatus());
    }

    /**
     * 更新某一条反馈记录
     *
     * @param feedback 需要更新的反馈对象
     * @return 返回影响行数
     */
    @Override
    public int updateFeedback(Feedback feedback) {
        @Language("SQL") String sql = "UPDATE t_feedback SET produce_id = ?, customer_id = ?, content = ?, create_time = ?, deal_status = ? WHERE feedback_id = ?";
        return Db.executeUpdate(sql, feedback.getProduceId(), feedback.getCustomerId(), feedback.getContent(), feedback.getCreateTime(), feedback.getDealStatus(), feedback.getFeedbackId());
    }

    /**
     * 根据反馈ID删除反馈
     *
     * @param feedbackId 需要删除的反馈ID
     * @return 返回影响行数
     */
    @Override
    public int deleteFeedback(int feedbackId) {
        @Language("SQL") String sql = "DELETE FROM t_feedback WHERE feedback_id = ?";
        return Db.executeUpdate(sql, feedbackId);
    }
}

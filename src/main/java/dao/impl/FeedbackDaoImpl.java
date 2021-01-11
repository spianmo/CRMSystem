package dao.impl;

import org.intellij.lang.annotations.Language;

import java.util.List;

import dao.FeedbackDao;
import entity.Feedback;
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
     * 根据产品ID查询订单
     *
     * @param produceId 产品ID
     * @return 根据产品ID查出的订单
     */
    @Override
    public List<Feedback> selectFeedbackByProduceId(int produceId) {
        @Language("SQL") String sql="SELECT * FROM t_feedback WHERE produce_id = ?";
        return Db.executeQuery(sql,new BeanListHandler<>(Feedback.class),produceId);
    }

    /**
     * 根据顾客ID查询订单
     *
     * @param customerId 顾客ID
     * @return 根据顾客ID查出的订单
     */
    @Override
    public List<Feedback> selectFeedbackByCustomerId(int customerId) {
        @Language("SQL") String sql="SELECT * FROM t_feedback WHERE customer_id = ?";
        return Db.executeQuery(sql,new BeanListHandler<>(Feedback.class),customerId);
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
        return Db.executeUpdate(sql, feedback.getProduceId(),feedback.getCustomerId(),
                feedback.getContent(),feedback.getCreateTime(),feedback.getDealStatus());
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
        return Db.executeUpdate(sql,feedback.getProduceId(),feedback.getCustomerId(),feedback.getFeedbackId(),
                feedback.getContent(),feedback.getCreateTime(),feedback.getDealStatus(),feedback.getFeedbackId());
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
        return Db.executeUpdate(sql,feedbackId);
    }
}

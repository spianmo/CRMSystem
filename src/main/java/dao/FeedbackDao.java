package dao;

import entity.Feedback;

import java.util.List;

/**
 * 反馈实体操作的Dao数据访问层
 *
 * @author Shinonon
 */
public interface FeedbackDao {
    /**
     * 根据产品ID查询订单
     * @param produceId 产品ID
     * @return 根据产品ID查出的订单
     */
    List<Feedback> selectFeedbackByProduceId(int produceId);

    /**
     * 根据顾客ID查询订单
     * @param customerId 顾客ID
     * @return 根据顾客ID查出的订单
     */
    List<Feedback> selectFeedbackByCustomerId(int customerId);

    /**
     * 插入一条新的反馈记录
     * @param feedback 需要新增的反馈对象
     * @return 返回影响行数
     */
    int insertFeedback(Feedback feedback);

    /**
     * 更新某一条反馈记录
     * @param feedback  需要更新的反馈对象
     * @return 返回影响行数
     */
    int updateFeedback(Feedback feedback);

    /**
     * 根据反馈ID删除反馈
     * @param feedbackId 需要删除的反馈ID
     * @return 返回影响行数
     */
    int deleteFeedback(int feedbackId);

}

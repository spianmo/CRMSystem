package service;

import java.util.List;

import entity.Feedback;
import entity.vo.FeedbackVo;

public interface FeedbackService {
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
    List<FeedbackVo> selectFeedbackByCustomerId(int customerId);

    /**
     * 根据员工ID查询订单
     * @param customerId 顾客ID
     * @return 根据顾客ID查出的订单
     */
    List<FeedbackVo> selectFeedbackByEmployeeId(int customerId);
    /**
     * 插入一条新的反馈记录
     * @param feedback 需要新增的反馈对象
     * @return 返回影响行数
     */
    boolean insertFeedback(Feedback feedback);

    /**
     * 更新某一条反馈记录
     * @param feedback  需要更新的反馈对象
     * @return 返回影响行数
     */
    boolean updateFeedback(Feedback feedback);

    Feedback selectFeedbackById(int feedbackId);

    /**
     * 根据反馈ID删除反馈
     * @param feedbackId 需要删除的反馈ID
     * @return 返回影响行数
     */
    boolean deleteFeedback(int feedbackId);

}

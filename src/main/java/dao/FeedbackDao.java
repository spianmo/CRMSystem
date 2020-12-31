package dao;

import entity.Feedback;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName FeedbackDao
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public interface FeedbackDao {
    List<Feedback> selectFeedbackByProduceId(int produceId) throws SQLException;
    List<Feedback> selectFeedbackByCustomerId(int CustomerId) throws SQLException;
    int insertFeedback(Feedback feedback) throws SQLException;
    int updateFeedback(Feedback feedback) throws  SQLException;
    int deleteFeedback(int feedbackId) throws SQLException;






}





















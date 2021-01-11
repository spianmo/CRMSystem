package service.impl;

import java.util.List;

import dao.FeedbackDao;
import entity.Feedback;
import factory.DaoFactory;
import service.FeedbackService;

/**
 * @ClassName FeedbackServiceImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public class FeedbackServiceImpl implements FeedbackService {

    FeedbackDao mFeedbackDao = DaoFactory.getFeedbackDaoInstance();

    @Override
    public List<Feedback> selectFeedbackByProduceId(int produceId) {
        return mFeedbackDao.selectFeedbackByProduceId(produceId);
    }

    @Override
    public List<Feedback> selectFeedbackByCustomerId(int customerId) {
        return mFeedbackDao.selectFeedbackByCustomerId(customerId);
    }

    @Override
    public boolean insertFeedback(Feedback feedback) {
        return mFeedbackDao.insertFeedback(feedback) == 1;
    }

    @Override
    public boolean updateFeedback(Feedback feedback) {
        return mFeedbackDao.updateFeedback(feedback) == 1;
    }

    @Override
    public boolean deleteFeedback(int feedbackId) {
        return mFeedbackDao.deleteFeedback(feedbackId) == 1;
    }
}

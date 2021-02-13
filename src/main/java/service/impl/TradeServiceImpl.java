package service.impl;

import java.util.List;

import dao.TradeDao;
import entity.Trade;
import entity.vo.TradeVo;
import factory.DaoFactory;
import service.TradeService;

/**
 * @ClassName TradeServiceImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/31/2020
 **/
public class TradeServiceImpl implements TradeService {
    TradeDao mTradeDao = DaoFactory.getTradeDaoInstance();

    @Override
    public List<Trade> selectAllTrade() {
        return mTradeDao.selectAllTrade();
    }

    @Override
    public List<TradeVo> selectTradeByCustomerId(int customerId) {
        return mTradeDao.selectTradeByCustomerId(customerId);
    }

    @Override
    public List<TradeVo> selectAllTradeVo() {
        return mTradeDao.selectAllTradeVo();
    }


    @Override
    public List<Trade> selectTradeByEmployeeId(int employeeId) {
        return mTradeDao.selectTradeByEmployeeId(employeeId);
    }

    @Override
    public List<Trade> selectTradeByProductId(int produceId) {
        return mTradeDao.selectTradeByProductId(produceId);
    }

    @Override
    public Trade selectTradeById(int tradeId) {
        return mTradeDao.selectTradeById(tradeId);
    }

    @Override
    public boolean insertTrade(Trade trade) {
        return mTradeDao.insertTrade(trade) == 1;
    }

    @Override
    public boolean deleteTrade(int tradeId) {
        return mTradeDao.deleteTrade(tradeId) == 1;
    }
}

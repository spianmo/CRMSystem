package service;

import java.util.List;

import entity.Trade;

/**
 * @author Finger
 */
public interface TradeService {
    /**
     * 查询所有订单
     * @return 返回所有订单
     */
    List<Trade> selectAllTrade();

    /**
     * 查询某一客户的所有订单
     * @param customerId 客户ID
     * @return 返回某一客户的所有订单
     */
    List<Trade> selectTradeByCustomerId(int customerId);
    /**
     * 根据员工ID查询订单
     * @param employeeId 员工ID
     * @return 返回根据员工ID查询订单实体集
     */
    List<Trade> selectTradeByEmployeeId(int employeeId);

    /**
     * 查询某一产品的所有订单
     * @param produceId 产品ID
     * @return 返回某一产品的所有订单
     */
    List<Trade> selectTradeByProductId(int produceId);

    /**
     * 根据订单ID查询订单
     * @param tradeId 订单ID
     * @return 返回指定订单
     */
    Trade selectTradeById(int tradeId);

    /**
     * 插入订单
     * @param trade 待插入的订单
     * @return 影响行数
     */
    boolean insertTrade(Trade trade);

    /**
     * 删除订单
     * @param tradeId 待删除的订单ID
     * @return 影响行数
     */
    boolean deleteTrade(int tradeId);
}

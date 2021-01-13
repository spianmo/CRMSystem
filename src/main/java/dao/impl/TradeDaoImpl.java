package dao.impl;

import org.intellij.lang.annotations.Language;

import java.util.List;

import dao.TradeDao;
import entity.Trade;
import entity.vo.TradeVo;
import utils.jdbc.BeanHandler;
import utils.jdbc.BeanListHandler;
import utils.jdbc.Db;

/**
 * @ClassName TradeDaoImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/31/2020
 **/
public class TradeDaoImpl implements TradeDao {
    @Override
    public List<Trade> selectAllTrade() {
        @Language("SQL") String sql = "SELECT * FROM t_trade";
        return Db.executeQuery(sql, new BeanListHandler<>(Trade.class));
    }

    @Override
    public List<TradeVo> selectTradeByCustomerId(int customerId) {
        @Language("SQL") String sql = "SELECT t1.trade_id,t2.name AS produce_name,t1.customer_id,t1.amount,t1.produce_num,t1.trade_time FROM t_trade t1 LEFT JOIN t_produce t2 ON t1.produce_id = t2.produce_id WHERE t1.customer_id = ?";
        return Db.executeQuery(sql, new BeanListHandler<>(TradeVo.class), customerId);
    }

    @Override
    public List<Trade> selectTradeByEmployeeId(int employeeId) {
        @Language("SQL") String sql = "SELECT * FROM t_trade WHERE t_employee.employee_id =?";
        return Db.executeQuery(sql, new BeanListHandler<>(Trade.class),employeeId);
    }

    @Override
    public List<Trade> selectTradeByProductId(int produceId) {
        @Language("SQL") String sql = "SELECT * FROM t_trade WHERE t_trade.produce_id = ?";
        return Db.executeQuery(sql, new BeanListHandler<>(Trade.class), produceId);
    }

    @Override
    public Trade selectTradeById(int tradeId) {
        @Language("SQL") String sql = "SELECT * FROM t_trade WHERE t_trade.trade_id = ?";
        return Db.executeQuery(sql, new BeanHandler<>(Trade.class), tradeId);
    }

    @Override
    public int insertTrade(Trade trade) {
        @Language("SQL") String sql = "INSERT INTO t_trade (customer_id, produce_id, amount, produce_num,trade_time) VALUES(?,?,?,?,?)";
        return Db.executeUpdate(sql, trade.getCustomerId(), trade.getProduceId(), trade.getAmount(), trade.getProduceNum(),trade.getTradeTime());
    }

    @Override
    public int deleteTrade(int tradeId) {
        @Language("SQL") String sql = "DELETE FROM t_trade WHERE trade_id = ?";
        return Db.executeUpdate(sql, tradeId);
    }
}

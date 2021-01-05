package dao.impl;

import dao.TradeDao;
import entity.Trade;
import org.intellij.lang.annotations.Language;
import utils.jdbc.BeanHandler;
import utils.jdbc.BeanListHandler;
import utils.jdbc.Db;

import java.util.List;

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
    public List<Trade> selectTradeByCustomerId(int customerId) {
        @Language("SQL") String sql = "SELECT * FROM t_trade WHERE t_trade.customer_id";
        return Db.executeQuery(sql, new BeanListHandler<>(Trade.class), customerId);
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
        @Language("SQL") String sql = "INSERT INTO t_trade (customer_id, produce_id, trade_id, amount, produce_num) VALUES(?,?,?,?,?)";
        return Db.executeUpdate(sql, trade.getCustomerId(), trade.getProduceId(), trade.getTradeId(), trade.getAmount(), trade.getProduceNum());
    }

    @Override
    public int deleteTrade(int tradeId) {
        @Language("SQL") String sql = "DELETE FROM t_trade WHERE trade_id = ?";
        return Db.executeUpdate(sql, tradeId);
    }
}

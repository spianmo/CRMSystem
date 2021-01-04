package dao.impl;

import dao.ProduceDao;
import entity.Produce;
import org.intellij.lang.annotations.Language;
import utils.jdbc.BeanHandler;
import utils.jdbc.BeanListHandler;
import utils.jdbc.Db;

import java.util.List;

/**
 * @ClassName ProduceDaoImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public class ProduceDaoImpl implements ProduceDao {
    @Override
    public List<Produce> selectAllProduce() {
        @Language("SQL") String sql = "SELECT * FROM t_produce";
        return Db.executeQuery(sql, new BeanListHandler<>(Produce.class));
    }

    @Override
    public Produce selectProduceById(int id) {
        @Language("SQL") String sql = "SELECT * FROM t_produce WHERE produce_id";
        return Db.executeQuery(sql, new BeanHandler<>(Produce.class),id);
    }

    @Override
    public List<Produce> selectProduceByProduceType(String type) {
        @Language("SQL") String sql = "SELECT * FROM t_produce WHERE produce_type";
        return Db.executeQuery(sql, new BeanListHandler<>(Produce.class),type);
    }

    @Override
    public int insertProduce(Produce produce) {
        @Language("SQL") String sql = "INSERT INTO t_produce(name, price, produce_date, produce_type) VALUES (?,?,?,?);";
        return Db.executeUpdate(sql, produce.getName(), produce.getPrice(), produce.getProduceDate(), produce.getProduceType());
    }

    @Override
    public int updateProduce(Produce produce) {
        @Language("SQL") String sql = "UPDATE t_produce SET name = ?, price = ?, produce_date = ?, produce_type = ?";
        return Db.executeUpdate(sql, produce.getName(), produce.getPrice(), produce.getProduceDate(), produce.getProduceType());
    }

    @Override
    public int deleteProduceById(int produceId) {
        @Language("SQL") String sql = "DELETE FROM t_produce WHERE produce_id = ?";
        return Db.executeUpdate(sql, produceId);
    }
}

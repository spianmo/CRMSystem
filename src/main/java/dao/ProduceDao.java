package dao;

import entity.Produce;

import java.util.List;

public interface ProduceDao {
    List<Produce> selectAllProduce();
    Produce selectProduceById(int id);
    List<Produce> selectProduceByProduceType(String type);
    int insertProduce(Produce produce);
    int updateProduce(Produce produce);
    int deleteProduceById(int produceId);

}

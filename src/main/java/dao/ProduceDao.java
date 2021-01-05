package dao;

import entity.Produce;

import java.util.List;

public interface ProduceDao {
    /**
     * 查询所有产品
     * @return  返回所有产品
     */
    List<Produce> selectAllProduce();

    /**
     * 根据产品Id查询产品
     * @param id 产品Id
     * @return 返回根据产品Id查询的产品实体集
     */
    Produce selectProduceById(int id);

    /**
     * 根据产品名字查询产品
     * @param type  产品名字
     * @return 返回根据产品名字查询的产品实体集
     */
    List<Produce> selectProduceByProduceType(String type);

    /**
     * 插入一条新的产品记录
     * @param produce 需要新增的产品对象
     * @return 返回影响行数
     */
    int insertProduce(Produce produce);

    /**
     * 更新某一条产品记录
     * @param produce 需要更新的产品对象
     * @return 返回影响行数
     */
    int updateProduce(Produce produce);

    /**
     * 根据产品Id删除产品
     * @param produceId 需要删除的产品Id
     * @return 返回影响行数
     */
    int deleteProduceById(int produceId);

}

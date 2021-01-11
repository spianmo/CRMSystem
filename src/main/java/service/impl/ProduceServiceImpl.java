package service.impl;

import java.util.List;

import dao.ProduceDao;
import entity.Produce;
import factory.DaoFactory;
import service.ProduceService;

/**
 * @ClassName ProduceServiceImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public class ProduceServiceImpl implements ProduceService {

    ProduceDao mProduceDao = DaoFactory.getProduceDaoInstance();
    @Override
    public List<Produce> selectAllProduce() {
        return mProduceDao.selectAllProduce();
    }

    @Override
    public Produce selectProduceById(int id) {
        return mProduceDao.selectProduceById(id);
    }

    @Override
    public List<Produce> selectProduceByProduceType(String type) {
        return mProduceDao.selectProduceByProduceType(type);
    }

    @Override
    public boolean insertProduce(Produce produce) {
        return mProduceDao.insertProduce(produce) == 1;
    }

    @Override
    public boolean updateProduce(Produce produce) {
        return mProduceDao.updateProduce(produce) == 1;
    }

    @Override
    public boolean deleteProduceById(int produceId) {
        return mProduceDao.deleteProduceById(produceId) == 1;
    }
}

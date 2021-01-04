package dao;

import org.junit.jupiter.api.Test;

import factory.DaoFactory;

import static org.junit.jupiter.api.Assertions.*;

class TradeDaoTest {

    TradeDao tradeDao = DaoFactory.getTradeDaoInstance();

    @Test
    void selectAllTrade() {
        System.out.println(tradeDao.selectAllTrade());
    }

    @Test
    void selectTradeByCustomerId() {
    }

    @Test
    void selectTradeByProductId() {
    }

    @Test
    void selectTradeById() {
    }

    @Test
    void insertTrade() {
    }

    @Test
    void deleteTrade() {
    }
}
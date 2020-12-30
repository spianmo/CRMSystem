package dao;

import org.junit.jupiter.api.Test;

import factory.DaoFactory;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    void findUserByAccount() {
        System.out.println(userDao.findUserByAccount("kirito"));
    }
}
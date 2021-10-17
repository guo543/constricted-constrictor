package edu.purdue.dao;


import edu.purdue.model.User;
import org.junit.Test;

import java.sql.SQLException;

public class TestUserDao {

    @Test
    public void testGetUser() {
        try {
            UserDao userDao = new UserDao();

            User u = new User();

            u.setUsername("test1");
            u.setPassword("123456");

            User user = userDao.getUser(u);

            System.out.println(user);

            assert user != null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser() {
        try {
            UserDao userDao = new UserDao();

            User u = new User();

            u.setUsername("test1");
            u.setPassword("123456");

            boolean result = userDao.addUser(u);

            System.out.println(result);

            assert !result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package edu.purdue.dao;


import edu.purdue.model.User;
import org.junit.Test;

import java.sql.SQLException;

public class TestUserDao {

    private UserDao userDao;

    {
        try {
            userDao = new UserDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TestUserDao() throws SQLException {
    }

    @Test
    public void testGetUser() {
        try {
            User u = new User();

            u.setUsername("test1");
            u.setPassword("123456");

            User user = userDao.getUser(u);

            System.out.println(user);

            assert user != null;

            System.out.println(user.getHighScores());

            assert !user.getHighScores().getScores().isEmpty();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser() {

        User u = new User();

        u.setUsername("test1");
        u.setPassword("123456");
        u.setPassword("aaa@bbb.ccc");

        boolean result = userDao.addUser(u);

        System.out.println(result);

        assert !result;
    }

/*    @Test
    public void testModifyUser() {
        try {
            User u = new User();

            u.setUsername("test1");
            u.setPassword("1234567");

            User user = userDao.getUser(u);

            System.out.println(user);

            User modifiedUser = userDao.modifyUser(user, "test1", "123456", "aaa@bbb.com");

            System.out.println(modifiedUser);

            assert modifiedUser != null;

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }*/
}

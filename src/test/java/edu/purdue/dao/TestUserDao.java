package edu.purdue.dao;


import org.junit.Test;

import java.sql.SQLException;

public class TestUserDao {

    @Test
    public void testGetUser() {
        try {
            UserDao userDao = new UserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

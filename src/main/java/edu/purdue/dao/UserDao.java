package edu.purdue.dao;

import com.alibaba.druid.pool.DruidDataSource;
import edu.purdue.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserDao {

    private static Properties properties;
    private DruidDataSource druidDataSource;
    private Connection connection;

    static {
        properties = new Properties();
        InputStream is = UserDao.class.getClassLoader().getResourceAsStream("conf/jdbc.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserDao() throws SQLException {
        druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getProperty("url"));
        druidDataSource.setDriverClassName(properties.getProperty("driver"));
        druidDataSource.setUsername(properties.getProperty("username"));
        druidDataSource.setPassword(properties.getProperty("password"));

        connection = druidDataSource.getConnection();
    }

    public boolean addUser(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO user(username, password, email) VALUES(?, ?, ?)");

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public User getUser(User user) throws SQLException{
        User u = new User();
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT username, password, email FROM user where username=? AND password=?");

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            u.setUsername(resultSet.getString("username"));
            u.setPassword(resultSet.getString("password"));
            u.setEmail(resultSet.getString("email"));
        }
        return u.getUsername() == null ? null : u;
    }
}

package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    static UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {

        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE if not exists users" +
                    " (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT , name VARCHAR(60), " +
                    "lastName VARCHAR(60), age TINYINT)");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(" DROP TABLE IF EXISTS users");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO users(name , lastName, age)" +
                            "VALUES(?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USER WHERE id=?");
            preparedStatement.setLong(1, id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try(Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");

                userList.add(new User(id, name, lastName, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users");
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

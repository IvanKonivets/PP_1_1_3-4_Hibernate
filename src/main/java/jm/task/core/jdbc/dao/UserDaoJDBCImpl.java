package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR (45), lastName VARCHAR (45), age INT (3))";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USERS";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            int rows = statement.executeUpdate();
            System.out.println("Добавлен пользователь: " + name + " " + lastName + " (" + rows + " строк изменено)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Пользователь с ID " + id + " удален (" + rows + " строк изменено)");
            } else {
                System.out.println("Пользователь с ID " + id + " не найден");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USERS";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            int rows = statement.executeUpdate(sql);
            System.out.println("Таблица USERS очищена. Удалено записей: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

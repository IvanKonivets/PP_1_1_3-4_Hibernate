package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        //Создание таблицы User(ов)
        userService.createUsersTable();
        //Добавление 4 User(ов) в таблицу
        userService.saveUser("Ivan", "Konivets", (byte) 30);
        System.out.println("User с именем Ivan добавлен в бд");
        userService.saveUser("Roman", "Ivanov", (byte) 35);
        System.out.println("User с именем Roman добавлен в бд");
        userService.saveUser("Oleg", "Petrov", (byte) 35);
        System.out.println("User с именем Oleg добавлен в бд");
        userService.saveUser("Anton", "Sidorov", (byte) 35);
        System.out.println("User с именем Anton добавлен в бд");
        // Получение всех User из базы и вывод в консоль
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        // Удаление таблицы
        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        System.out.println("User с именем " + userService.getAllUsers().get(0).getName() + " добавлен в базу данных");
        userService.saveUser("Stepan", "Sidorov", (byte) 30);
        System.out.println("User с именем " + userService.getAllUsers().get(1).getName() + " добавлен в базу данных");
        userService.saveUser("Elena", "Nabatnikova", (byte) 38);
        System.out.println("User с именем " + userService.getAllUsers().get(2).getName() + " добавлен в базу данных");
        userService.saveUser("Steve", "Bushemi", (byte) 63);
        System.out.println("User с именем " + userService.getAllUsers().get(3).getName() + " добавлен в базу данных");

        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}


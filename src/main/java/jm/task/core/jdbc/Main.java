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
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();

        User u1 = new User("Ivan", "Ivanov", (byte) 25);
        userService.saveUser(u1.getName(), u1.getLastName(), u1.getAge());
        User u2 = new User("Petr", "Petrov", (byte) 50);
        userService.saveUser(u2.getName(), u2.getLastName(), u2.getAge());
        User u3 = new User("Elena", "Serova", (byte) 20);
        userService.saveUser(u3.getName(), u3.getLastName(), u3.getAge());
        User u4 = new User("Rick", "Sanchez", (byte) 60);
        userService.saveUser(u4.getName(), u4.getLastName(), u4.getAge());

        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}


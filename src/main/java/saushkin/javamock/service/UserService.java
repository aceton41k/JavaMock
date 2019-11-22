package saushkin.javamock.service;

import saushkin.javamock.dao.UserDao;
import saushkin.javamock.model.User;

import java.util.List;

public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User getUser(int id) {
        return usersDao.getUser(id);
    }

    public int addUser(User user) {
        usersDao.addUser(user);
        return user.getId();
    }

    public boolean deleteUser(int id) {
        if (getUser(id) != null) {
            usersDao.delete(getUser(id));
            return true;
        } else
            return false;
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }
}

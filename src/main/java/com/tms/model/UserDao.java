package com.tms.model;

import com.tms.bean.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private List<User> users = UsersHolder.getList();

    public UserDao() {
    }
    @Override
    public Optional<User> get(String name){
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<User> get(String name, String password) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void delete(String name) {
    users.removeIf(s -> s.equals(name));
    }

    @Override
    public void edit(User userEdit) {
         User userOriginal = null;
        for (User user : users) {
            if (user.getName() == userEdit.getName()) {
                userOriginal = user;
                break;
            }
        }
        if (userOriginal != null) {
            User replacement = new User();
            replacement.setFirstName(userEdit.getFirstName());
            replacement.setAddress(userEdit.getAddress());
            replacement.setName(userEdit.getName());
            replacement.setIsAdmin(userEdit.getIsAdmin());
            replacement.setLastName(userEdit.getLastName());
            replacement.setPassword(userEdit.getPassword());
            replacement.setSex(userEdit.getSex());
            users.remove(userEdit);
            users.add(replacement);
        }
        }
    }

class UsersHolder {
    private static List<User> list;

    static List<User> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}
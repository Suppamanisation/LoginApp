package com.tms.controller;

import com.tms.bean.User;
import com.tms.model.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LogInAction {
    private static UserDao userDao = new UserDao();

    public static void logIn(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        req.getSession().setAttribute("user", user);

        List<User> userList;
        if (user.getIsAdmin()) {
            userList = userDao.getAll();
        } else {
            userList = Collections.singletonList(user);
        }
        req.setAttribute("userList", userList);

        servletContext.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
    }
}

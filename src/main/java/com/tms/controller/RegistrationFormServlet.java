package com.tms.controller;


import com.tms.bean.User;
import com.tms.model.Dao;
import com.tms.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;


@WebServlet("/registration")
public class RegistrationFormServlet extends HttpServlet {
    private Dao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");

        User newUser = createUserFromParameters(req);
        String name;
        name = newUser.getName();
        Optional optionalUser = userDao.get(name);
        if (optionalUser.isPresent()) {
            req.setAttribute("newError", "Данное имя пользователя уже занято");
            getServletContext().getRequestDispatcher("/registrationForm.jsp").forward(req, resp);
        } else {
            userDao.save(newUser);

            LogInAction.logIn(getServletContext(), req, resp, newUser);
        }
    }
    private void deleteUser(HttpServletRequest req) {
        if (req.getParameter("delete") != null) {
            String name;
            name = req.getParameter("name");
            userDao.delete(name);
        }
    }

    private User createUserFromParameters(HttpServletRequest req) {
        Enumeration<String> names = req.getParameterNames();

        User newUser = new User();
        String tempName, tempValue;

        while (names.hasMoreElements()) {
            tempName = names.nextElement();
            tempValue = req.getParameter(tempName);
            switch (tempName) {
                case ("name"):
                    newUser.setName(tempValue);
                    break;
                case ("password"):
                    newUser.setPassword(tempValue);
                    break;
                case ("firstName"):
                    newUser.setFirstName(tempValue);
                    break;
                case ("lastName"):
                    newUser.setLastName(tempValue);
                    break;
                case ("sex"):
                    newUser.setSex(tempValue);
                    break;
                case ("address"):
                    newUser.setAddress(tempValue);
                    break;
                case ("isAdmin"):
                    newUser.setIsAdmin(true);
                    break;
            }
        }
        return newUser;
    }

    @Override
    public void init() {
        userDao = new UserDao();
    }
}
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
import java.util.Optional;

@WebServlet("/login")
public class LogInFormServlet extends HttpServlet {
    private Dao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("registration") != null) {
            getServletContext().getRequestDispatcher("/registrationForm.jsp").forward(req, resp);
        } else if (req.getParameter("logOut") != null) {
            resp.sendRedirect("loginForm.jsp");
        } else if (req.getParameter("edit") != null) {
            resp.sendRedirect("editProfileForm.jsp");
        } else {
            String name, password;
            name = req.getParameter("userName");
            password = req.getParameter("userPassword");

            Optional optionalUser = userDao.get(name, password);
            if (optionalUser.isPresent()) {
                User tempUser = (User) optionalUser.get();
                LogInAction.logIn(getServletContext(), req, resp, tempUser);
            } else {
                req.setAttribute("error", " Указано неверное имя пользователя или пароль");
                getServletContext().getRequestDispatcher("/loginForm.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void init() {
        userDao = new UserDao();
    }
}

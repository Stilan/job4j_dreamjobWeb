package ru.job4j.dream.servlet;


import ru.job4j.dream.model.User;
import ru.job4j.dream.store.DbStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
                 if (DbStore.instOf().findByEmail(email) == null) {
                     User user = new User(0, name, email, password);
                     DbStore.instOf().saveUser(user);
                     HttpSession sc = req.getSession();
                     sc.setAttribute("user", user);
                     resp.sendRedirect(req.getContextPath() + "/posts.do");
                 } else {
                     req.setAttribute("error", "Пользавател уже существует");
                     req.getRequestDispatcher("reg.jsp").forward(req, resp);
                 }
            }
}

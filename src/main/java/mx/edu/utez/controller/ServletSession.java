package mx.edu.utez.controller;

import mx.edu.utez.model.category.DaoCategory;
import mx.edu.utez.model.user.BeanUser;
import mx.edu.utez.model.user.DaoUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletSession", value = "/ServletSession")
public class ServletSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obteniendo la sesión
        HttpSession session = request.getSession();
        //Matando la sesión
        session.setAttribute("session", null);
        session.invalidate();
        //Redirigiendo
        request.getRequestDispatcher("/").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        DaoUser daouser = new DaoUser();
        boolean res = daouser.createSession(request.getParameter("email"), request.getParameter("password"));
        BeanUser beanUser = new BeanUser();
        beanUser.setEmail(request.getParameter("email"));
        beanUser.setPassword(request.getParameter("password"));

        if(res) {
            session.setAttribute("session", beanUser);
            request.setAttribute("categoryList", new DaoCategory().findAllCategories());
            request.getRequestDispatcher("/views/game/games.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("/").forward(request,response);
        }
    }
}

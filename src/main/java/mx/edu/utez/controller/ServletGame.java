package mx.edu.utez.controller;

import com.google.gson.Gson;
import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.model.game.BeanGames;
import mx.edu.utez.model.game.DaoGames;
import mx.edu.utez.model.user.DaoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@MultipartConfig
@WebServlet(name = "ServletGame", urlPatterns = {"/readGames", "/createGames", "/updateGames", "/deleteGames"})
public class ServletGame extends HttpServlet {
    Map map = new HashMap();
    final private Logger CONSOLE = LoggerFactory.getLogger(ServletGame.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("ListGames", new DaoGames().findAll());
        //request.getRequestDispatcher("/views/mage/games.jsp").forward(request,response);
        map.put("listGames", new DaoGames().findAll());
        write(response, map);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println("post");
        BeanGames beanGames = new BeanGames();
        BeanCategory beanCategory = new BeanCategory();
        DaoGames daoGames = new DaoGames();
        switch (action) {

            case "create":
                Part part = request.getPart("image");
                InputStream image = part.getInputStream();
                System.out.println("imagen lista");
                beanCategory.setIdCategory(Integer.parseInt(request.getParameter("category")));
                beanGames.setName(request.getParameter("name"));
                beanGames.setDatePremiere(request.getParameter("date"));
                beanGames.setCategory_idCategory(beanCategory);
                System.out.println("parámetros listos");
                boolean flag = daoGames.create(beanGames, image);

                if (flag) {
                    map.put("message", "Se ha resgistrado correctamente");
                } else {
                    map.put("message", "No se registró correctamente");
                }
                write(response, map);
                break;
            case "update":
                beanCategory.setIdCategory(Integer.parseInt(request.getParameter("idCategory")));
                beanGames.setIdGames(Integer.parseInt(request.getParameter("idGames")));
                beanGames.setName(request.getParameter("name"));
                beanGames.setDatePremiere(request.getParameter("date"));
                beanGames.setCategory_idCategory(beanCategory);
                boolean flag1 = daoGames.update(beanGames);

                if (flag1) {
                    map.put("message", "Se ha actualizado correctamente");
                } else {
                    map.put("message", "No se actualizó correctamente");
                }
                break;
            case "delete":
                if (daoGames.delete(Integer.parseInt(request.getParameter("idGame")))) {
                    map.put("message", "Se ha eliminado correctamente");
                } else {
                    map.put("message", "No se eliminó correctamente");
                }
                break;
            default:
                request.getRequestDispatcher("/").forward(request, response);
        }
        response.sendRedirect(request.getContextPath()+"/readGames");
    }
    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException{
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(map));
    }
}
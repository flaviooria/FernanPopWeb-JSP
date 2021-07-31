package operaciones;

import com.google.gson.Gson;
import modelos.Gestion;
import modelos.Producto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ajaxBuscador", value = "/ajaxBuscador")
public class ajaxBuscador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("nameProduct");
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(gestion != null) {
            ArrayList<Producto> productosByTerm =  Gestion.obtenerProductosByTerm(product);
            String json = new Gson().toJson(productosByTerm);
            response.setContentType("aplication/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package operaciones;

import modelos.Gestion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "borrarProducto", value = "/borrarProducto")
public class borrarProducto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("id"));
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            if(Gestion.borrarProductoById(idProducto)) {
                request.getSession().setAttribute("exito","Tu producto se borro de forma correcta.");
                response.sendRedirect("./pages/exitosa.jsp");
            } else {
                request.getSession().setAttribute("errOperacion","No se pudo borrar tu producto, intentalo de nuevo.");
                response.sendRedirect("./pages/errorOperaciones.jsp");
            }
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

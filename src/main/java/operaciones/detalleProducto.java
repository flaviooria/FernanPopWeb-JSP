package operaciones;

import modelos.Gestion;
import modelos.Producto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "detalleProducto", value = "/detalleProducto")
public class detalleProducto extends HttpServlet {
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
            //Con el id obtengo los datos del producto y lo redirijo
            Producto producto = Gestion.obtenerProductoById(idProducto);
            request.getSession().setAttribute("producto",producto);
            response.sendRedirect("./pages/detalleProducto.jsp");
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package operaciones;

import modelos.Gestion;
import modelos.Producto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "mostrarProductos", value = "/mostrarProductos")
public class mostrarProductos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String term = request.getParameter("nameProduct");
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            //Obtengo todos los productos por el termino que ingreso el usuario
            ArrayList<Producto> productosObtenidos = Gestion.obtenerProductosByTerm(term);
            request.getSession().setAttribute("productosByTerm",productosObtenidos);
            response.sendRedirect("./pages/productos.jsp");
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }
}

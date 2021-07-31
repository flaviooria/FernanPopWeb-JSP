package operaciones;

import modelos.Gestion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "valorarProducto", value = "/valorarProducto")
public class valorarProducto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int idTrato = Integer.parseInt(request.getParameter("idTrato"));
        String comentario = request.getParameter("comentario");
        int puntuacion = 0;
        boolean todoOk = true;


        Gestion gestion = null;

        if (comentario.isEmpty()) {
            request.getSession().setAttribute("failed", "true");
            response.sendRedirect("./pages/valorarProducto.jsp?idTrato="+idTrato);
            todoOk = false;
        } else if (request.getParameter("puntuacion").isEmpty()) {
            request.getSession().setAttribute("failed", "true");
            response.sendRedirect("./pages/valorarProducto.jsp?idTrato="+idTrato);
            todoOk = false;
        } else {
            puntuacion = Integer.parseInt(request.getParameter("puntuacion"));
            if (puntuacion < 1 || puntuacion > 5) {
                request.getSession().setAttribute("failed", "true");
                response.sendRedirect("./pages/valorarProducto.jsp?idTrato="+idTrato);
                todoOk = false;
            }
        }


        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (todoOk) {
            if (gestion != null) {
                if (Gestion.valorarTratoCompra(idTrato,comentario,puntuacion)) {
                    if (Gestion.borrarValoracionPendiente(idTrato)) {
                        request.getSession().setAttribute("exito", "Valoración realizada con exito.");
                        response.sendRedirect("./pages/exitosa.jsp");
                    }
                } else {
                    request.getSession().setAttribute("failed", "true");
                    response.sendRedirect("./pages/valorarProducto.jsp");
                }
            } else {
                request.getSession().setAttribute("error", "No se pudo conectar a la base de datos.");
                response.sendRedirect("./pages/error.jsp");
            }
        }
    }
}

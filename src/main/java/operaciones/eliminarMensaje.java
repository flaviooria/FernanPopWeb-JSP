package operaciones;

import modelos.Gestion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "eliminarMensaje", value = "/eliminarMensaje")
public class eliminarMensaje extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idChat = Integer.parseInt(request.getParameter("idChat"));
        String nombreTabla = request.getParameter("nameTable");
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            if (Gestion.eliminarMensaje(idChat,nombreTabla)) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Mensaje Eliminado");
            } else {
                request.getSession().setAttribute("errOperacion","No se pudo borrar el mensaje");
                response.sendRedirect("./pages/errorOperaciones.jsp");
            }
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }
}

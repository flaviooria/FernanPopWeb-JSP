package operaciones;

import modelos.Gestion;
import modelos.Producto;
import modelos.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "enviarMensaje", value = "/enviarMensaje")
public class enviarMensaje extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gestion gestion = null;
        int idReceptor,idEmisor;
        request.setCharacterEncoding("UTF-8");
        Usuario vendedor = (Usuario) request.getSession().getAttribute("recuperarVendedor");
        request.getSession().removeAttribute("recuperarVendedor");
        String mensaje = request.getParameter("mensaje");
        String asunto = request.getParameter("asunto");

        if (request.getParameter("user") == null ||request.getParameter("user").isEmpty() ||
                mensaje == null ||  mensaje.isEmpty() || asunto == null || asunto.isEmpty()) {
            request.getSession().setAttribute("userVendedor",vendedor);
            request.getSession().setAttribute("failed","true");
            response.sendRedirect("./pages/nuevoMensaje.jsp");
        } else {
            try {
                gestion = new Gestion();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (gestion != null) {
                idEmisor = Integer.parseInt(request.getParameter("idEmisor"));
                idReceptor = Integer.parseInt(request.getParameter("user"));

                if (Gestion.enviarMensaje(mensaje,asunto,idEmisor, idReceptor)) {
                    request.getSession().setAttribute("exito","Mensaje enviado exitosamente.");
                    response.sendRedirect("./pages/exitosa.jsp");
                } else {
                    request.getSession().setAttribute("errOperacion","Mensaje no enviado.");
                    response.sendRedirect("./pages/errorOperaciones.jsp");
                }
            } else  {
                request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
                response.sendRedirect("./pages/error.jsp");
            }
        }
    }
}

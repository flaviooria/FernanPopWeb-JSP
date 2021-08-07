package operaciones;

import modelos.Gestion;
import modelos.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "authToken", value = "/authToken")
public class authToken extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        //Obtengo el token del usuario.
        String tokenUser = (String) request.getSession().getAttribute("token");
        //Obtengo el token del input
        String tokenInput = request.getParameter("token");
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            if (tokenInput.trim().equals(tokenUser)) {
                //autentifico el token
                Gestion.setAutentificado(user);
                //Borro el token de la sesión
                request.getSession().removeAttribute("token");
                //redirenciono a inicio
                request.getSession().setAttribute("user", user);
                response.sendRedirect("./pages/inicio.jsp");
            } else {
                request.getSession().setAttribute("failed", "true");
                response.sendRedirect("./pages/autentificarToken.jsp");
            }
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }
}

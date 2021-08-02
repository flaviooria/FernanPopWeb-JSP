package operaciones;

import modelos.Gestion;
import modelos.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "verificarLogin", value = "/verificarLogin")
public class verificarLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        System.out.println("email, " + email);
        System.out.println("pass, " + pass);

        Usuario user;
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ((email.isEmpty() || pass.isEmpty())) {
            request.getSession().setAttribute("failed","true");
            response.sendRedirect("../index.jsp");
        } else {
            if (gestion != null) {
                user = Gestion.login(email,pass);
                if (user != null) {
                    //Si no esta autentificado el usuario le hacemos el auth y lo rederijimos a la página de cuenta registrada
                    if(!Gestion.isAutentificado(user)) {
                        Gestion.setAutentificado(user);
                        String token = Gestion.obtenerTokenUsuario(user);
                        request.getSession().setAttribute("user", user);
                        request.getSession().setAttribute("token", token);
                        response.sendRedirect("./pages/cuentaRegistrada.jsp");
                    } else {
                        request.getSession().setAttribute("user", user);
                        response.sendRedirect("./pages/inicio.jsp");
                    }
                } else {
                    request.getSession().setAttribute("failed","true");
                    response.sendRedirect("../index.jsp");
                }
            } else {
                request.getSession().setAttribute("error","No se pudo conectar a la base de datoas.");
                response.sendRedirect("./error.jsp");
            }
        }
    }
}

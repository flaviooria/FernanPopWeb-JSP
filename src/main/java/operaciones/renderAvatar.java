package operaciones;

import modelos.Gestion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "renderAvatar", value = "/renderAvatar")
public class renderAvatar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Gestion gestion = null;
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            Gestion.renderAvatarUser(id,response);
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

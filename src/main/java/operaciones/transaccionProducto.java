package operaciones;

import modelos.Gestion;
import modelos.Producto;
import modelos.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "transaccionProducto", value = "/transaccionProducto")
public class transaccionProducto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Producto producto = (Producto) request.getSession().getAttribute("producto");
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        int idProducto = producto.getId();
        String accion = request.getParameter("accion");
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            switch (accion) {
                case "solicitud":
                    //Si la solicitud corresponde al mismo propietario del producto, no procede
                    if (Gestion.comprobarCorreoComprador(usuario,idProducto)) {
                        request.getSession().setAttribute("request","err1");
                        response.sendRedirect("./pages/detalleProducto.jsp");
                    } else {
                        // Aqui añado la solicitud
                        if (Gestion.aniadirProductSolicitado(producto, usuario)){
                            request.getSession().setAttribute("request","exito");
                            response.sendRedirect("./pages/detalleProducto.jsp");
                        } else { // Si surge un problema mando el error
                            request.getSession().setAttribute("request","err2");
                            response.sendRedirect("./pages/detalleProducto.jsp");
                        }
                    }
                    break;
                case "chat":
                    Usuario userVendedor = Gestion.obtenerUsuarioById(producto.getIdUsuarioPropietario());
                    if (userVendedor == null) {
                        request.getSession().setAttribute("request","err3");
                        response.sendRedirect("./pages/detalleProducto.jsp");
                    } else {
                        request.getSession().setAttribute("userVendedor",userVendedor);
                        response.sendRedirect("./pages/nuevoMensaje.jsp");
                    }
                    break;
                default:
                    request.getSession().setAttribute("request","err2");
                    response.sendRedirect("./pages/detalleProducto.jsp");
            }
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
    }
}

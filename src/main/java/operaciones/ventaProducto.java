package operaciones;

import comunicaciones.Email;
import modelos.Gestion;
import modelos.Producto;
import modelos.Trato;
import modelos.Usuario;
import plantillasHtml.NotificacionCompra;
import plantillasHtml.NotificacionPendiente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ventaProducto", value = "/ventaProducto")
public class ventaProducto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Gestion gestion = null;
        String accion = request.getParameter("accion");
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto productoVendido = Gestion.obtenerProductoById(idProducto);
        Usuario vendedor = (Usuario) request.getSession().getAttribute("user");

        ArrayList<String> requests = new ArrayList<>();
        requests.add(request.getParameter("correoComprador"));
        requests.add(request.getParameter("comentario"));
        requests.add(request.getParameter("precioFinal"));
        requests.add(request.getParameter("puntuacion"));
        boolean hayError = false;

        for (String s:requests) {
            if (s.isEmpty()) {
                hayError = true;
                break;
            }
        }

        if (hayError) {
            request.getSession().setAttribute("failed", "true");
            response.sendRedirect("./pages/ventaProducto.jsp");
        } else {
            String correoComprador = request.getParameter("correoComprador");
            Usuario comprador = Gestion.obtenerUsuarioByCorreo(correoComprador);
            String comentario = request.getParameter("comentario");
            float precioFinal = Float.parseFloat((request.getParameter("precioFinal")));
            int puntuacion = Integer.parseInt((request.getParameter("puntuacion")));

            try {
                gestion = new Gestion();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (gestion != null) {
                switch (accion) {
                    case "vender":
                        if (productoVendido == null) {
                            request.getSession().setAttribute("errOperacion", "Producto no existente.");
                            response.sendRedirect("./pages/errorOperaciones.jsp");
                        } else {
                            if ( precioFinal < 1 || (puntuacion < 1 || puntuacion > 5)) {
                                request.getSession().setAttribute("failed", "true");
                                response.sendRedirect("./pages/ventaProducto.jsp");
                            } else {
                                if (String.valueOf(precioFinal).isEmpty())
                                    precioFinal = productoVendido.getPrecio();

                                int solicitudPendiente = Gestion.obtenerIdSolicitud(vendedor, comprador, productoVendido);

                                if (solicitudPendiente != -1 && comprador != null) {
                                    //Genero venta del producto!!!
                                    int idVenta = Gestion.addTratoVenta(idProducto, correoComprador, precioFinal, vendedor.getId());
                                    if (idVenta != -1) {
                                        //Borro la solicitud pendiente
                                        Gestion.borrarSolicitudPendiente(solicitudPendiente);
                                        //genero compra despues
                                        Trato compra = new Trato("compra",
                                                vendedor.getCorreo(), productoVendido.getNombre(), precioFinal);
                                        compra.setComentario(comentario);
                                        compra.setPuntuacion(puntuacion);
                                        compra.setImgProduct(productoVendido.getImagenBlobData());
                                        //Luego genero el trato de compra  a la lista de tratos del comprador
                                        if (Gestion.addTratoCompra(compra, comprador.getId())) {
                                            //Genero la valoración
                                            Gestion.addValoracionPendiente(idVenta, correoComprador);
                                            //el comprador tendra una valaración pendiente  de la venta.
                                            String mensajeVenta = NotificacionCompra.obtenerHtml(comprador.getNombre(), productoVendido.getNombre(),
                                                    vendedor.getNombre(), precioFinal);
                                            String notificacionPendiente = NotificacionPendiente.obtenerHtml(comprador.getNombre(),
                                                    vendedor.getNombre(), productoVendido.getNombre());

                                            Email.seEnviaElEmail(correoComprador, mensajeVenta, "Compra satisfactoria.");
                                            Email.seEnviaElEmail(correoComprador, notificacionPendiente, "Notificación pendiente de compra.");

                                            request.getSession().setAttribute("exito", "Venta generada correctamente.");
                                            response.sendRedirect("./pages/exitosa.jsp");
                                        } else {
                                            request.getSession().setAttribute("errOperacion", "No se pudo generar la compra, intentalo de nuevo.");
                                            response.sendRedirect("./pages/errorOperaciones.jsp");
                                        }
                                    } else {
                                        request.getSession().setAttribute("errOperacion", "No se pudo generar la venta, intentalo de nuevo.");
                                        response.sendRedirect("./pages/errorOperaciones.jsp");
                                    }

                                } else {
                                    request.getSession().setAttribute("errOperacion", "Solicitud y/o comprador no encontrados.");
                                    response.sendRedirect("./pages/errorOperaciones.jsp");
                                }
                            }
                        }

                        break;
                    case "borrar":
                        int solicitudPendiente = Gestion.obtenerIdSolicitud(vendedor, comprador, productoVendido);
                        if (Gestion.borrarSolicitudPendiente(solicitudPendiente)) {
                            request.getSession().setAttribute("exito", "Solicitud borrada correctamente.");
                            response.sendRedirect("./pages/exitosa.jsp");
                        } else {
                            request.getSession().setAttribute("errOperacion", "No se pudo borrar la solicitud, intentalo de nuevo.");
                            response.sendRedirect("./pages/errorOperaciones.jsp");
                        }
                        break;
                }

            } else {
                request.getSession().setAttribute("error", "No se pudo conectar a la base de datos.");
                response.sendRedirect("./pages/error.jsp");
            }
        }


    }
}

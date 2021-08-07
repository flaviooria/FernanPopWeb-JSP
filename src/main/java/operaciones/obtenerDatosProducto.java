/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import modelos.Gestion;
import modelos.Producto;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "obtenerDatosProducto", urlPatterns = {"/obtenerDatosProducto"})
public class obtenerDatosProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String desc = request.getParameter("descrip");
        float price = Float.parseFloat(request.getParameter("price"));
        int idUser = Integer.parseInt(request.getParameter("id"));
        Part part = request.getPart("imgFile");
        InputStream inputStream = part.getInputStream();

        Producto producto;
        Gestion gestion = null;

        if (name.isEmpty() ||desc.isEmpty() || price < 0) {
            request.getSession().setAttribute("failed","true");
            response.sendRedirect("./pages/subirProducto.jsp");
        } else {
            try {
                gestion = new Gestion();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (gestion != null) {
                producto = new Producto(name.trim(),desc.trim(),price);
                producto.setImagenBlobData(inputStream);
                if (Gestion.insertarProducto(producto,idUser)) {
                    response.sendRedirect("./pages/inicio.jsp");
                }  else {
                    request.getSession().setAttribute("errOperacion","No se pudo insertar el producto.");
                    response.sendRedirect("./pages/errorOperaciones.jsp");
                }
            } else {
                request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
                response.sendRedirect("./pages/error.jsp");
            }
        }

    }
}

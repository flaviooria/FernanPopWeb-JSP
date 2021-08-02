/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import comunicaciones.Email;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Gestion;
import modelos.Usuario;
import plantillasHtml.NotificacionToken;

/**
 *
 * @author 2097k
 */
@WebServlet(name = "registerUser", value = {"/registerUser"})
public class registerUser extends HttpServlet {

 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String name,email,pass;
        name = request.getParameter("name");
        email = request.getParameter("email");
        pass = request.getParameter("pass");
        Usuario user = new Usuario(name,email,pass);
        Gestion gestion = null;
            
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (name.isEmpty() || !Usuario.validarCorreo(email) || !Usuario.validarContrasenia(pass)) {
            session.setAttribute("failed",true);
            response.sendRedirect("./pages/registro.jsp");
        } else {
            if (gestion != null) {
                if (Gestion.registrarUsuario(user)) {
                    try {
                        String asunto = "Validación de token para iniciar sesión en FernanPop";
                        Gestion.asignarTokenUsuario(Usuario.obtengoToken(),user);
                        String token = Gestion.obtenerTokenUsuario(user);
                        String mensajeUsuario = NotificacionToken.obtenerHtml(user.getNombre(),token);
                        if (Email.seEnviaElEmail(user.getCorreo(), mensajeUsuario, asunto)) {
                            response.sendRedirect("./index.jsp");
                        }
                    } catch (Exception e) {
                        System.out.println("Ha surgido un error no se pudo realizar el registro");
                    }

                } else {
                    session.setAttribute("failed",true);
                    response.sendRedirect("./pages/registro.jsp");
                }
            } else {
                session.setAttribute("error","No se pudo conectar a la base de datoas.");
                response.sendRedirect("./pages/error.jsp");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

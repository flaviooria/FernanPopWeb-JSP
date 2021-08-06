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
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Gestion;
import modelos.Usuario;
import plantillasHtml.NotificacionToken;

import static test.CifradoContrasenia.createSecretKey;
import static test.CifradoContrasenia.encrypt;

/**
 *
 * @author 2097k
 */
@WebServlet(name = "registerUser", value = {"/registerUser"})
public class registerUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String name,email,pass;
        name = request.getParameter("name");
        email = request.getParameter("email");
        pass = request.getParameter("pass");
        Gestion gestion = null;
            
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (name.isEmpty() || !Usuario.validarCorreo(email) || !Usuario.validarContrasenia(pass)) {
            session.setAttribute("failed","true");
            response.sendRedirect("./pages/registro.jsp");
        } else {
            if (gestion != null) {
                String base64Password = "";
                try {
                    //Genero la clave cifrada con la contraseña registrada del usuario.
                    byte[] salt = "12345678".getBytes();
                    int iterationCount = 40000;
                    int keyLength = 128;
                    SecretKeySpec key = createSecretKey(pass.toCharArray(), salt, iterationCount, keyLength);
                    base64Password = encrypt(pass,key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Usuario user = new Usuario(name,email,pass,base64Password);
                if (Gestion.registrarUsuario(user)) {
                    try {
                        Gestion.asignarTokenUsuario(Usuario.obtengoToken(),user);
                        System.out.println("Registrando al usuario: => " + user);
                        response.sendRedirect("./index.jsp");
                    } catch (Exception e) {
                        System.out.println("Ha surgido un error no se pudo realizar el registro");
                    }

                } else {
                    session.setAttribute("failed","true");
                    response.sendRedirect("./pages/registro.jsp");
                }
            } else {
                session.setAttribute("error","No se pudo conectar a la base de datos.");
                response.sendRedirect("./pages/error.jsp");
            }
        }
    }
}

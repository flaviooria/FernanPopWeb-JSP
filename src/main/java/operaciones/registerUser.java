/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import modelos.Gestion;
import modelos.Usuario;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static test.CifradoContrasenia.createSecretKey;
import static test.CifradoContrasenia.encrypt;

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
        name = StringEscapeUtils.escapeHtml4(request.getParameter("name"));
        email = StringEscapeUtils.escapeHtml4(request.getParameter("email"));
        pass = StringEscapeUtils.escapeHtml4(request.getParameter("pass"));
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
                    SecretKeySpec key = createSecretKey(pass.trim().toCharArray(), salt, iterationCount, keyLength);
                    base64Password = encrypt(pass.trim(),key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Usuario user = new Usuario(name.trim(),email.trim(),pass.trim(),base64Password);
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

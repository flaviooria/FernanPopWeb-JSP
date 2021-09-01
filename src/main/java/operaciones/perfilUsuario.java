package operaciones;

import modelos.Gestion;
import modelos.Usuario;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

import static test.CifradoContrasenia.createSecretKey;
import static test.CifradoContrasenia.encrypt;

@MultipartConfig
@WebServlet(name = "perfilUsuario", value = "/perfilUsuario")
public class perfilUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String accion = request.getParameter("accion");
        int id = user.getId();

        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            switch (accion) {
                //Aqui se borra al usuario
                case "borrar":
                    if (Gestion.borrarUsuarioById(id)) {
                        response.sendRedirect("./pages/cuentaBorrada.jsp");
                    } else {
                        request.getSession().setAttribute("errOperacion", "No se pudo borrar tu cuenta " +
                                "vuelve a intentarlo.");
                        request.getRequestDispatcher("./pages/errorOperaciones.jsp").forward(request, response);
                    }
                    break;
                case "guardar":
                    String correoUser = user.getCorreo();
                    String nombre, apellido, correo, contrasenia;
                    int movil, edad, cont = 0;

                    nombre = StringEscapeUtils.escapeHtml4(request.getParameter("name"));
                    apellido = StringEscapeUtils.escapeHtml4(request.getParameter("lastname"));
                    correo = StringEscapeUtils.escapeHtml4(request.getParameter("email"));
                    contrasenia = StringEscapeUtils.escapeHtml4(request.getParameter("pass"));
                    Part avatar = request.getPart("avatar");
                    InputStream avatarUser = avatar.getInputStream();

                    if (!nombre.isEmpty() && !nombre.equalsIgnoreCase(user.getNombre())) {
                        if (Gestion.actualizarDatos("nombre", nombre, correoUser)) {
                            user.setNombre(nombre);
                            request.getSession().setAttribute("editada", "true");
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        } else {
                            request.getSession().setAttribute("editada", "false");
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        }
                    } else cont++;
                    if (!apellido.isEmpty() && !apellido.equalsIgnoreCase(user.getApellido())) {
                        if (Gestion.actualizarDatos("apellido", apellido, correoUser)) {
                            user.setApellido(apellido);
                            request.getSession().setAttribute("editada", "true");
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        } else {
                            request.getSession().setAttribute("editada", "false");
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        }
                    } else cont++;
                    if (!correo.isEmpty() && !correo.equalsIgnoreCase(user.getCorreo()) &&
                            Usuario.validarCorreo(correo)) {
                            if (Gestion.actualizarDatos("correo", correo, correoUser)) {
                                user.setCorreo(correo);
                                request.getSession().setAttribute("editada", "true");
                            } else {
                                request.getSession().setAttribute("editada", "false");
                            }
                        response.sendRedirect("./pages/editarPerfil.jsp");
                    } else cont++;
                    if (!contrasenia.isEmpty() && !contrasenia.equalsIgnoreCase(user.getContrasenia())) {
                        String base64Password = "";
                        try {
                            //Genero la clave cifrada con la contraseña editada del usuario.
                            byte[] salt = "12345678".getBytes();
                            int iterationCount = 40000;
                            int keyLength = 128;
                            SecretKeySpec key = createSecretKey(contrasenia.toCharArray(), salt, iterationCount, keyLength);
                            //Clave encriptada
                            base64Password = encrypt(contrasenia,key);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (Gestion.actualizarContrasenia(contrasenia,base64Password,correoUser)) {
                            user.setContrasenia(contrasenia);
                            request.getSession().setAttribute("editada", "true");
                        } else {
                            request.getSession().setAttribute("editada", "false");
                        }
                        response.sendRedirect("./pages/editarPerfil.jsp");
                    } else cont++;


                    if (request.getParameter("phone").length() == 9 &&
                            !request.getParameter("phone").isEmpty()) {
                        movil = Integer.parseInt(StringEscapeUtils.escapeHtml4(request.getParameter("phone")));
                        if (movil != user.getMovil()) {
                            if (Gestion.actualizarDatos("movil", String.valueOf(movil),
                                    correoUser)) {
                                user.setMovil(movil);
                                request.getSession().setAttribute("editada", "true");
                            } else {
                                request.getSession().setAttribute("editada", "false");
                            }
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        }
                    } else cont++;

                    if (request.getParameter("age").length() == 2 &&
                            !request.getParameter("age").isEmpty()) {
                        edad = Integer.parseInt(StringEscapeUtils.escapeHtml4(request.getParameter("age")));
                        if (edad != user.getEdad()) {
                            if (Gestion.actualizarDatos("edad", String.valueOf(edad),
                                    correoUser)) {
                                user.setEdad(edad);
                                request.getSession().setAttribute("editada", "true");
                            } else {
                                request.getSession().setAttribute("editada", "false");
                            }
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        }
                    } else cont++;

                    //Aqui si el tamaño del avatar es mayor es porque se ha subido una foto
                    if (avatar.getSize() > 0) {
                        //Actualizo la foto si es null
                        if (user.getFotoPerfil() == null) {
                            if (Gestion.actualizarFotoUsuario(avatarUser, id)) {
                                user.setFotoPerfil(avatarUser);
                                request.getSession().setAttribute("editada", "true");
                            } else {
                                request.getSession().setAttribute("editada", "false");
                            }
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        } else {
                            //Si la foto es diferente, es decir si se ha subido una nueva, se guarda esta.
                            if (avatarUser.available() != user.getFotoPerfil().available()) {
                                if (Gestion.actualizarFotoUsuario(avatarUser, id)) {
                                    user.setFotoPerfil(avatarUser);
                                    request.getSession().setAttribute("editada", "true");
                                } else {
                                    request.getSession().setAttribute("editada", "false");
                                }
                                response.sendRedirect("./pages/editarPerfil.jsp");
                            }
                        }

                    } else cont++;

                    //Si no hay ningun dato para editar solo redireccina a la pagina de nuevo.
                    if (cont == 7) {
                        response.sendRedirect("./pages/editarPerfil.jsp");
                    }

                    break;
                default:
                    response.sendRedirect("./pages/inicio.jsp");
            }
        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }

    }
}

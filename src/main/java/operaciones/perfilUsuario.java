package operaciones;

import modelos.Gestion;
import modelos.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

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
        System.out.println("usuario para editar es: " + user);
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
                    int movil, edad;

                    nombre = request.getParameter("name");
                    apellido = request.getParameter("lastname");
                    correo = request.getParameter("email");
                    contrasenia = request.getParameter("pass");
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
                    }
                    if (!apellido.isEmpty() && !apellido.equalsIgnoreCase(user.getApellido())) {
                        if (Gestion.actualizarDatos("apellido", apellido, correoUser)) {
                            user.setApellido(apellido);
                            request.getSession().setAttribute("editada", "true");
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        } else {
                            request.getSession().setAttribute("editada", "false");
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        }
                    }
                    if (!correo.isEmpty() && !correo.equalsIgnoreCase(user.getCorreo()) &&
                            Usuario.validarCorreo(correo)) {
                            if (Gestion.actualizarDatos("correo", correo, correoUser)) {
                                user.setCorreo(correo);
                                request.getSession().setAttribute("editada", "true");
                            } else {
                                request.getSession().setAttribute("editada", "false");
                            }
                        response.sendRedirect("./pages/editarPerfil.jsp");
                    }
                    if (!contrasenia.isEmpty() && !contrasenia.equalsIgnoreCase(user.getContrasenia())) {
                        if (Gestion.actualizarDatos("contrasenia", contrasenia, correoUser)) {
                            user.setContrasenia(contrasenia);
                            request.getSession().setAttribute("editada", "true");
                        } else {
                            request.getSession().setAttribute("editada", "false");
                        }
                        response.sendRedirect("./pages/editarPerfil.jsp");
                    }


                    if (request.getParameter("phone").length() == 9 &&
                            !request.getParameter("phone").isEmpty()) {
                        movil = Integer.parseInt(request.getParameter("phone"));
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
                    }

                    if (request.getParameter("age").length() == 2 &&
                            !request.getParameter("age").isEmpty()) {
                        edad = Integer.parseInt(request.getParameter("age"));
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
                    }
                    if (avatar.getSize() > 0) {
                        if (user.getFotoPerfil() == null) {
                            if (Gestion.actualizarFotoUsuario(avatarUser, id)) {
                                user.setFotoPerfil(avatarUser);
                                request.getSession().setAttribute("editada", "true");
                            } else {
                                request.getSession().setAttribute("editada", "false");
                            }
                            response.sendRedirect("./pages/editarPerfil.jsp");
                        } else {
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

                    }
                    break;
                default:
                    response.sendRedirect("./pages/inicio.jsp");
            }
        }

    }
}

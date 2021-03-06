/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.68
 * Generated at: 2021-07-03 00:33:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import modelos.Usuario;

public final class inicio_jsp extends org.apache.jasper.runtime.HttpJspBase
        implements org.apache.jasper.runtime.JspSourceDependent,
        org.apache.jasper.runtime.JspSourceImports {

    private static final javax.servlet.jsp.JspFactory _jspxFactory =
            javax.servlet.jsp.JspFactory.getDefaultFactory();

    private static java.util.Map<java.lang.String, java.lang.Long> _jspx_dependants;

    private static final java.util.Set<java.lang.String> _jspx_imports_packages;

    private static final java.util.Set<java.lang.String> _jspx_imports_classes;

    static {
        _jspx_imports_packages = new java.util.HashSet<>();
        _jspx_imports_packages.add("javax.servlet");
        _jspx_imports_packages.add("javax.servlet.http");
        _jspx_imports_packages.add("javax.servlet.jsp");
        _jspx_imports_classes = new java.util.HashSet<>();
        _jspx_imports_classes.add("modelos.Usuario");
    }

    private volatile javax.el.ExpressionFactory _el_expressionfactory;
    private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

    public java.util.Map<java.lang.String, java.lang.Long> getDependants() {
        return _jspx_dependants;
    }

    public java.util.Set<java.lang.String> getPackageImports() {
        return _jspx_imports_packages;
    }

    public java.util.Set<java.lang.String> getClassImports() {
        return _jspx_imports_classes;
    }

    public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
        if (_el_expressionfactory == null) {
            synchronized (this) {
                if (_el_expressionfactory == null) {
                    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
                }
            }
        }
        return _el_expressionfactory;
    }

    public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
        if (_jsp_instancemanager == null) {
            synchronized (this) {
                if (_jsp_instancemanager == null) {
                    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
                }
            }
        }
        return _jsp_instancemanager;
    }

    public void _jspInit() {
    }

    public void _jspDestroy() {
    }

    public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
            throws java.io.IOException, javax.servlet.ServletException {

        final java.lang.String _jspx_method = request.getMethod();
        if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
            return;
        }

        final javax.servlet.jsp.PageContext pageContext;
        javax.servlet.http.HttpSession session = null;
        final javax.servlet.ServletContext application;
        final javax.servlet.ServletConfig config;
        javax.servlet.jsp.JspWriter out = null;
        final java.lang.Object page = this;
        javax.servlet.jsp.JspWriter _jspx_out = null;
        javax.servlet.jsp.PageContext _jspx_page_context = null;


        try {
            response.setContentType("text/html;charset=UTF-8");
            pageContext = _jspxFactory.getPageContext(this, request, response,
                    null, true, 8192, true);
            _jspx_page_context = pageContext;
            application = pageContext.getServletContext();
            config = pageContext.getServletConfig();
            session = pageContext.getSession();
            out = pageContext.getOut();
            _jspx_out = out;

            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("<!DOCTYPE html>\n");

            Usuario user = (Usuario) session.getAttribute("user");
            if (user == null) {
                session.setAttribute("error", "Debes de loguearte.");
                response.sendRedirect("pages/error.jsp");
            } else {

                out.write("\n");
                out.write("<html lang=\"en\">\n");
                out.write("\n");
                out.write("<head>\n");
                out.write("    <meta charset=\"UTF-8\">\n");
                out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
                out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
                out.write("    <title>FernanPop | Inicio</title>\n");
                out.write("    <!-- Styles  -->\n");
                out.write("    <link rel=\"stylesheet\" href=\"../css/home.css\">\n");
                out.write("    <!--fonts -->\n");
                out.write("    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\n");
                out.write("    <link\n");
                out.write("        href=\"https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap\"\n");
                out.write("        rel=\"stylesheet\">\n");
                out.write("    <!--fontawesome icons-->\n");
                out.write("    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\"\n");
                out.write("        integrity=\"sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp\" crossorigin=\"anonymous\" />\n");
                out.write("    <!-- Style of hamburger_menu -->\n");
                out.write("    <link href=\"../dist/hamburgers.css\" rel=\"stylesheet\">\n");
                out.write("</head>\n");
                out.write("\n");
                out.write("<body>\n");
                out.write("    <div class=\"container\">\n");
                out.write("        <!-- Header -->\n");
                out.write("        <header id=\"inicio\">\n");
                out.write("            <div class=\"hamburger hamburger--spin\">\n");
                out.write("                <div class=\"hamburger-box\">\n");
                out.write("                    <div class=\"hamburger-inner\">\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("            </div>\n");
                out.write("            <div class=\"logo\">\n");
                out.write("                <h1>Fernan<span class=\"orange\">Pop</span></h1>\n");
                out.write("            </div>\n");
                out.write("            <nav class=\"navbar-menu\">\n");
                out.write("                <ul>\n");
                out.write("                    <li><a class=\"link\" href=\"../pages/inicio.jsp\">Inicio</a></li>\n");
                out.write("                    <li><a class=\"link\" href=\"../pages/editarPerfil.html\">Editar Perf??l</a></li>\n");
                out.write("                    <li><a class=\"link\" href=\"../pages/subirProducto.html\">Subir Producto</a></li>\n");
                out.write("                    <li><a class=\"link\" href=\"\">Ver mis tratos</a></li>\n");
                out.write("                    <li><a class=\"link\" href=\"\">Cerrar Sesi??n</a></li>\n");
                out.write("                </ul>\n");
                out.write("            </nav>\n");
                out.write("        </header>\n");
                out.write("        <!-- Hero  -->\n");
                out.write("        <section class=\"hero\">\n");
                out.write("            <div class=\"hero-description\">\n");
                out.write("                <h3 class=\"hero-title\">Bienvenido a FernanPop ");
                out.print(user.getNombre());
                out.write("</h3>\n");
                out.write("                <p class=\"hero-subtitle\">Tus productos:</p>\n");
                out.write("                <!-- Notifications -->\n");
                out.write("                <div class=\"hero-notifications\">\n");
                out.write("                    <!-- Notifications valorates -->\n");
                out.write("                    <div class=\"notification-bell center\">\n");
                out.write("                        <input type=\"checkbox\" class=\"bell\" name=\"\" id=\"\">\n");
                out.write("                        <div class=\"num number-bell center\"></div>\n");
                out.write("                        <div class=\"box\">\n");
                out.write("                            <div class=\"heading center\">\n");
                out.write("                                <p><span class=\"number-bell\"></span>Notificaciones</p>\n");
                out.write("                            </div>\n");
                out.write("                            <div class=\"notification_box\">\n");
                out.write("                                <form action=\"\" class=\"form-notification num-bell\">\n");
                out.write("                                    <p class=\"subject\">Asunto: Valoraci??n Pendiente</p>\n");
                out.write("                                    <p class=\"product\">Producto: MacBook Pro 13</p>\n");
                out.write("                                    <p class=\"user\">Usuario: Flavio Oria</p>\n");
                out.write("                                    <button type=\"submit\" class=\"send-valorate\">Ir</button>\n");
                out.write("                                </form>\n");
                out.write("                                <form action=\"\" class=\"form-notification num-bell\">\n");
                out.write("                                    <p class=\"subject\">Asunto: Valoraci??n Pendiente</p>\n");
                out.write("                                    <p class=\"product\">Producto: MacBook Pro 13</p>\n");
                out.write("                                    <p class=\"user\">Usuario: Flavio Oria</p>\n");
                out.write("                                    <button type=\"submit\" class=\"send-valorate\">Ir</button>\n");
                out.write("                                </form>\n");
                out.write("                                <form action=\"\" class=\"form-notification num-bell\">\n");
                out.write("                                    <p class=\"subject\">Asunto: Valoraci??n Pendiente</p>\n");
                out.write("                                    <p class=\"product\">Producto: MacBook Pro 13</p>\n");
                out.write("                                    <p class=\"user\">Usuario: Flavio Oria</p>\n");
                out.write("                                    <button type=\"submit\" class=\"send-valorate\">Ir</button>\n");
                out.write("                                </form>\n");
                out.write("                            </div>\n");
                out.write("                        </div>\n");
                out.write("                    </div>\n");
                out.write("                    <!-- Notifications messages -->\n");
                out.write("                    <div class=\"notification-msg center\">\n");
                out.write("                        <input type=\"checkbox\" class=\"msg\" name=\"\" id=\"\">\n");
                out.write("                        <div class=\"num number-msg center\"></div>\n");
                out.write("                        <div class=\"box\">\n");
                out.write("                            <div class=\"heading center\">\n");
                out.write("                                <p><span class=\"number-msg\"></span>Notificaciones</p>\n");
                out.write("                            </div>\n");
                out.write("                            <div class=\"notification_box\">\n");
                out.write("                                <form action=\"\" class=\"form-notification num-msg\">\n");
                out.write("                                    <p class=\"subject\">Asunto: Valoraci??n Pendiente</p>\n");
                out.write("                                    <p class=\"product\">Producto: MacBook Pro 13</p>\n");
                out.write("                                    <p class=\"user\">Usuario: Flavio Oria</p>\n");
                out.write("                                    <button type=\"submit\" class=\"send-valorate\">Ir</button>\n");
                out.write("                                </form>\n");
                out.write("                                <form action=\"\" class=\"form-notification num-msg\">\n");
                out.write("                                    <p class=\"subject\">Asunto: Valoraci??n Pendiente</p>\n");
                out.write("                                    <p class=\"product\">Producto: MacBook Pro 13</p>\n");
                out.write("                                    <p class=\"user\">Usuario: Flavio Oria</p>\n");
                out.write("                                    <button type=\"submit\" class=\"send-valorate\">Ir</button>\n");
                out.write("                                </form>\n");
                out.write("                            </div>\n");
                out.write("                        </div>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <!-- Nav search -->\n");
                out.write("                <form action=\"\" class=\"form-search\">\n");
                out.write("                    <div class=\"header-search\">\n");
                out.write("                        <input type=\"text\" name=\"nameProduct\" id=\"nameProduct\" placeholder=\"Busca todo lo que quieras\">\n");
                out.write("                        <button type=\"submit\" class=\"btn-search\"><i class=\"fas fa-search\"></i></button>\n");
                out.write("                    </div>\n");
                out.write("                </form>\n");
                out.write("            </div>\n");
                out.write("            <!-- Section Products -->\n");
                out.write("            <div class=\"products\">\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("                <div class=\"card\">\n");
                out.write("                    <img class=\"card-image\" loading=\"lazy\" src=\"../assets/img/macbook.jpeg\" alt=\"imagen\">\n");
                out.write("                    <h3>Titulo</h3>\n");
                out.write("                    <div class=\"focus-content\">\n");
                out.write("                        <p class=\"focus-description\">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aut ipsum\n");
                out.write("                            voluptas molestias, quos similique necessitatibus.</p>\n");
                out.write("                        <p class=\"focus-price\">0.00 ???</p>\n");
                out.write("                        <a href=\"\" class=\"btn-delete\">Borrar Producto</a>\n");
                out.write("                    </div>\n");
                out.write("                </div>\n");
                out.write("            </div>\n");
                out.write("        </section>\n");
                out.write("        <!-- Footer -->\n");
                out.write("        <footer>\n");
                out.write("            <p class=\"footer-rigth\">?? Derechos Reservados</p>\n");
                out.write("            <a class=\"footer-designed\" href=\"\">Designed by @flaviooria</a>\n");
                out.write("            <div class=\"about-media\">\n");
                out.write("                <ul>\n");
                out.write("                    <li><a href=\"\" title=\"facebook/fernanpop.com\"><i class=\"fab fa-facebook-square\"></i></a></li>\n");
                out.write("                    <li><a href=\"\"><i class=\"fab fa-instagram-square\"></i></a></li>\n");
                out.write("                    <li><a href=\"\"><i class=\"fab fa-twitter-square\"></i></a></li>\n");
                out.write("                    <li><a class=\"inicio\" href=\"#inicio\" title=\"Inicio\"><i class=\"fas fa-arrow-circle-up\"></i></a></li>\n");
                out.write("                </ul>\n");
                out.write("            </div>\n");
                out.write("        </footer>\n");
                out.write("    </div>\n");
                out.write("    <script>\n");
                out.write("        let hamburger = document.querySelector(\".hamburger--spin\");\n");
                out.write("\n");
                out.write("        let menu = document.querySelector(\".navbar-menu\");\n");
                out.write("\n");
                out.write("\n");
                out.write("        console.log(hamburger)\n");
                out.write("        hamburger.addEventListener(\"click\", () => {\n");
                out.write("            hamburger.classList.toggle(\"is-active\");\n");
                out.write("            menu.classList.toggle(\"open\");\n");
                out.write("        })\n");
                out.write("\n");
                out.write("        const bell = document.querySelectorAll(\".notification_box  .num-bell\");\n");
                out.write("        console.log(bell)\n");
                out.write("        const numberBell = document.querySelectorAll(\".number-bell\");\n");
                out.write("        numberBell.forEach(item => item.innerText = bell.length);\n");
                out.write("\n");
                out.write("        const msg = document.querySelectorAll(\".notification_box  .num-msg\");\n");
                out.write("        console.log(msg)\n");
                out.write("        const numberMsg = document.querySelectorAll(\".number-msg\");\n");
                out.write("        numberMsg.forEach(item => item.innerText = msg.length);\n");
                out.write("    </script>\n");
                out.write("    ");
            }
            out.write("\n");
            out.write("</body>\n");
            out.write("\n");
            out.write("</html>\n");
        } catch (java.lang.Throwable t) {
            if (!(t instanceof javax.servlet.jsp.SkipPageException)) {
                out = _jspx_out;
                if (out != null && out.getBufferSize() != 0)
                    try {
                        if (response.isCommitted()) {
                            out.flush();
                        } else {
                            out.clearBuffer();
                        }
                    } catch (java.io.IOException e) {
                    }
                if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
                else throw new ServletException(t);
            }
        } finally {
            _jspxFactory.releasePageContext(_jspx_page_context);
        }
    }
}

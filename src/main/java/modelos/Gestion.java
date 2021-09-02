package modelos;

import dao.DAOManager;
import dao.DaoGestionSQL;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;

import static test.CifradoContrasenia.*;


public class Gestion {
    private static final DAOManager dao = DAOManager.getSingletonInstance();
    ;
    private static final DaoGestionSQL daoGestionSQL = new DaoGestionSQL();
    ;

    public Gestion() throws Exception {
        dao.open();
    }

    public static boolean registrarUsuario(Usuario user) {
        return daoGestionSQL.insert(user, dao);
    }

    public static String obtenerTokenUsuario(Usuario user) {
        return daoGestionSQL.obtenerToken(user.getCorreo(), dao);
    }

    public static void asignarTokenUsuario(String token, Usuario user) {
        daoGestionSQL.insertToken(token, user.getCorreo(), dao);
    }

    public static boolean isAutentificado(Usuario user) {
        return daoGestionSQL.obtenerAutenticacion(user.getCorreo(), dao);
    }

    public static void setAutentificado(Usuario user) {
        daoGestionSQL.insertAutentificacion(user.getCorreo(), dao);
    }

    public static Usuario login(String email, String pass) {
        //Obtengo la clave cifrada del usuario en la base de datos.
        String claveCifrada = daoGestionSQL.getClaveCifrada(email, dao);
        String base64Password = "";
        try {
            //Genero la key con la contraseña del usuario encontrado.
            byte[] salt = "12345678".getBytes();
            int iterationCount = 40000;
            int keyLength = 128;
            SecretKeySpec key = createSecretKey(pass.toCharArray(), salt, iterationCount, keyLength);
            //Ahora desencripto la clave!
            if (claveCifrada != null)
                base64Password = decrypt(claveCifrada, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Aquí compruebo que la claves coincidan.
        if (pass.equals(base64Password))
            return daoGestionSQL.obtenerUsuario(email, pass, dao);
        else return null;
    }

    public static ArrayList<Producto> obtenerProductosByUser(int idUser) {
        return daoGestionSQL.mostrarProductoUsuario(idUser, dao);
    }

    public static boolean insertarProducto(Producto p, int idUser) {
        return daoGestionSQL.insertProducto(p, idUser, dao);
    }

    public static void renderImagen(int id, HttpServletResponse response) {
        daoGestionSQL.renderImagenProductoById(id, response, dao);
    }

    public static void renderAvatarUser(int id, HttpServletResponse response) {
        daoGestionSQL.renderAvatarUsuarioById(id, response, dao);
    }

    public static boolean borrarProductoById(int id) {
        return daoGestionSQL.borrarProductoById(id, dao);
    }

    public static boolean borrarUsuarioById(int id) {
        return daoGestionSQL.borrarUsuario(id, dao);
    }

    public static boolean actualizarDatos(String tipoDato, String valor, String correoUsuario) {
        return daoGestionSQL.actualizarDatos(tipoDato, valor, correoUsuario, dao);
    }

    public static boolean actualizarContrasenia(String pass,String claveCifrada,String correoUser) {
        return daoGestionSQL.actulizarContraseniaCifrada(pass,claveCifrada,correoUser,dao);
    }

    public static boolean actualizarFotoUsuario(InputStream avatarUser, int idUser) {
        return daoGestionSQL.insertarAvatarUsuario(avatarUser, idUser, dao);
    }

    public static ArrayList<Producto> obtenerProductosByTerm(String term) {
        return daoGestionSQL.obtenerProductoByTerm(term, dao);
    }

    public static Producto obtenerProductoById(int id) {
        return daoGestionSQL.obtenerProductoById(id, dao);
    }

    public static boolean aniadirProductSolicitado(Producto product, Usuario user) {
        if (product == null || user == null) return false;
        //Itera en cada usuario obteniendo su producto y si ese
        // concide con uno se acepta la solicitud y se añade
        for (Usuario usuario : getAllUsuarios()) {
            for (Producto p : getProductoUsuario(usuario.getId())) {
                if (p.getId() == product.getId()) {
                    addProductSolicitado(product, user);
                    return true;
                }
            }
        }

        return false;
    }

    private static ArrayList<Usuario> getAllUsuarios() {
        return daoGestionSQL.obtenerAllUsuarios(dao);
    }

    public static ArrayList<Producto> getProductoUsuario(int id) {
        if (id < 1) return new ArrayList<>();
        return daoGestionSQL.mostrarProductoUsuario(id, dao);
    }

    public static void addProductSolicitado(Producto producto, Usuario user) {
        SolicitudProducto p = new SolicitudProducto(user.getCorreo(), producto, user.getNombre());
        daoGestionSQL.addProductoSolicitado(p, dao);
    }

    /**
     * @param user el usuario a comprobar para iterar en la lista de productos.
     * @param id   el id del producto a comparar.
     * @return si es verdadero es decir que el usuario no puede comprar el producto puesto que ya es suyo.
     */
    public static boolean comprobarCorreoComprador(Usuario user, int id) {
        if (user == null || id < 1) return false;
        for (Usuario u : getAllUsuarios()) {
            if (user.getCorreo().equalsIgnoreCase(u.getCorreo())) {
                for (Producto p : getProductoUsuario(u.getId())) {
                    if (id == p.getId()) return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<SolicitudProducto> mostrarSolicitudesByUsuario(Usuario usuario) {
        ArrayList<SolicitudProducto> solicitudes = new ArrayList<>();
        if (usuario != null) {
            for (SolicitudProducto sp : daoGestionSQL.mostrarSolicitudes(usuario, dao)) {
                if (sp.getProducto() != null) {
                    solicitudes.add(sp);
                }
            }
        }
        return solicitudes;
    }

    public static Usuario obtenerUsuarioByCorreo(String correo) {
        return daoGestionSQL.obtenerUsuarioByCorreo(correo, dao);
    }

    public static SolicitudProducto obtenerSolicitudById(int id) {
        return daoGestionSQL.obtenerSolicitudById(id, dao);
    }

    public static boolean borrarSolicitudPendiente(int idSolicitud) {
        return daoGestionSQL.borraSolicitudProducto(idSolicitud, dao);
    }

    public static int obtenerIdSolicitud(Usuario vendedor, Usuario comprador, Producto productoVendido) {
        for (SolicitudProducto sp : mostrarSolicitudesByUsuario(vendedor)) {
            if (productoVendido.getId() == sp.getProducto().getId() && comprador.getCorreo().equals(sp.getCorreoComprador())) {
                return sp.getId();
            }
        }

        return -1;
    }

    public static int comprobarQueNoSeRepitaSolicitud(int idProducto,String correoUser) {
        return daoGestionSQL.comprobarQueNoSeRepitaSolicitud(idProducto,correoUser,dao);
    }

    /**
     * @param idProducto    la id que nos servira para obtener el producto como resultado.
     * @param correoUsuario el correo del usuario que esta comprando el producto
     * @param precioFinal   el precio aumentado o disminuido por el vendedor
     * @param idUsuario     la id del vendedor servira para guardarla en la base de datos nos sirve para identificar que vendedor genero la venta
     * @return delvolvera la id del nuevo trato generado.
     */
    public static int addTratoVenta(int idProducto, String correoUsuario, double precioFinal, int idUsuario) {
        Producto p = obtenerProductoById(idProducto);
        if (p == null) return -1;
        Trato t = new Trato("venta", correoUsuario, p.getNombre(), precioFinal);
        if (daoGestionSQL.generarTratoVenta(t.getTipo(), t.getCorreoOtroUsuario(), t.getNombreProducto(), idUsuario, (float) t.getPrecio(), p.getImagenBlobData(), dao)) {
            return daoGestionSQL.obtenerIdTrato(t.getNombreProducto(), dao);
        }
        return -1;
    }

    public static boolean addTratoCompra(Trato compra, int idComprador) {
        return daoGestionSQL.generarTratoCompra(compra.getTipo(), compra.getCorreoOtroUsuario(), compra.getNombreProducto(), idComprador,
                (float) compra.getPrecio(), compra.getComentario(), compra.getPuntuacion(), compra.getImgProduct(), dao);
    }

    public static void addValoracionPendiente(int idTrato, String correoUsuario) {
        daoGestionSQL.addValoracionPendiente(idTrato, correoUsuario, dao);
    }

    public static ArrayList<Trato> getValoracionPendiente(Usuario usuario) {
        ArrayList<Trato> tratos = new ArrayList<>();
        for (Usuario u : getAllUsuarios()) {
            for (Trato t : daoGestionSQL.obtenerTratoVentaByUsuarioID(u.getId(), dao)) {
                for (int id : daoGestionSQL.obtenerValoracionesPendientesByUsuarioID(usuario.getCorreo(), dao)) {
                    if (id == t.getId()) {
                        Trato tratoTemp = new Trato(t);
                        tratoTemp.setCorreoOtroUsuario(u.getCorreo());
                        tratoTemp.setTipo("compra");
                        tratos.add(tratoTemp);
                    }
                }
            }
        }
        return tratos;
    }


    public static Trato obtenerTratoById(int idTrato) {
        return daoGestionSQL.obtenerTratoById(idTrato, dao);
    }

    public static boolean valorarTratoCompra(int idTrato, String comentario, int puntuacion) {
        return daoGestionSQL.actualizarTratoCompra(idTrato, comentario, puntuacion, dao);
    }

    public static Usuario obtenerUsuarioById(int idUser) {
        return daoGestionSQL.obtenerUserById(idUser, dao);
    }

    public static boolean borrarValoracionPendiente(int idTrato) {
        return daoGestionSQL.borrarValoracionPendiente(idTrato, dao);
    }

    public static ArrayList<Trato> obtenerTratosByUsuario(int idUsuario) {
        return daoGestionSQL.obtenerTratosByUsuario(idUsuario, dao);
    }

    public static void renderImgTrato(int idTrato, HttpServletResponse response) {
        daoGestionSQL.renderImageTrato(idTrato, response, dao);
    }

    public static ArrayList<Usuario> usersPosiblesCompradores(int idReceptor) {
        return daoGestionSQL.usersPosiblesCompradores(idReceptor, dao);
    }

    public static boolean enviarMensaje(String msg, String asunto, int idEmisor, int idReceptor) {
        return daoGestionSQL.almacenarEnvioBuzonEnviados(msg, asunto, idEmisor, idReceptor, dao) &&
        daoGestionSQL.almacenarEnvioBuzonRecibidos(msg, asunto, idEmisor, idReceptor, dao);
    }

    public static ArrayList<Mensaje> mensajesRecibidos(int idUsuario) {
        return daoGestionSQL.mensajesRecibidosOfUser(idUsuario, dao);
    }

    public static ArrayList<Mensaje> mensajesRecibidosSinLeer(int idUsuario) {
        return daoGestionSQL.mensajesRecibidosOfUserSinLeer(idUsuario, dao);
    }

    public static ArrayList<Mensaje> mensajesEnviados(int idUsuario) {
        return daoGestionSQL.mensajesEnviadosOfUser(idUsuario, dao);
    }

    public static Mensaje obtenerMensajeEnviado(int idMensaje) {
        return daoGestionSQL.obtenerMensajeDeBuzonEnviados(idMensaje, dao);
    }

    public static Mensaje obtenerMensajeRecibido(int idMensaje) {
        return daoGestionSQL.obtenerMensajeDeBuzonRecibidos(idMensaje, dao);
    }

    public static ArrayList<Producto> allProductos() {
        return daoGestionSQL.obtenerAllProductos(dao);
    }

    public static void setearMensajeComoLeido(int idChat,String nombreTabla) {
        daoGestionSQL.setearLecturaMensaje(idChat, nombreTabla , dao);
    }
}


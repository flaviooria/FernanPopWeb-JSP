package dao;

import modelos.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DaoGestionSQL implements DaoGestion, DaoUsuario, DaoProducto, DaoChat,DaoTrato {
    @Override
    public boolean insert(Usuario usuario, DAOManager dao) {
        final String SQL_INSERT = "insert into usuarios (nombre,correo,contrasenia,contraseniaCifrada) values (?,?,?,?)";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasenia());
            stmt.setString(4, usuario.getContraseniaCifrada());
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public String getClaveCifrada(String correoUser, DAOManager dao) {
        String  sql = "select contraseniaCifrada from usuarios where correo = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setString(1,correoUser);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("contraseniaCifrada");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario obtenerUsuario(String correo, String contrasenia, DAOManager dao) {
        Usuario user = null;
        final String SQL_SELECTUSUARIO = "SELECT *, DATE_FORMAT(fechaCreacion, \"%e/%m/%Y\") as fechaMiembro FROM `usuarios` " +
                "where correo = ? and contrasenia = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_SELECTUSUARIO);
            stmt.setString(1, correo);
            stmt.setString(2, contrasenia);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String correoU = rs.getString("correo");
                String apellido = rs.getString("apellido");
                String contraseniaU = rs.getString("contrasenia");
                int edad = rs.getInt("edad");
                int movil = rs.getInt("movil");
                InputStream avatarUser = rs.getBinaryStream("avatarUser");
                int id = rs.getInt("id");
                float notaMedia = rs.getFloat("notaMedia");
                String fechaCreacion = rs.getString("fechaMiembro");
                user = new Usuario(nombre, correoU, contraseniaU);
                user.setId(id);
                user.setFotoPerfil(avatarUser);
                user.setEdad(edad);
                user.setMovil(movil);
                user.setApellido(apellido);
                user.setNotaMedia(notaMedia);
                user.setFechaCreacion(fechaCreacion);
                user.setContraseniaCifrada(rs.getString("contraseniaCifrada"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public Usuario obtenerUserById(int idUser, DAOManager dao) {
        String sql = "select * from usuarios where id = ?";
        Usuario user = new Usuario();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setCorreo(rs.getString("correo"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setApellido(rs.getString("apellido"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public Usuario obtenerUsuarioByCorreo(String correo, DAOManager dao) {
        final String SQL_SELECTUSUARIO = "select id,nombre,correo,apellido,contrasenia,notaMedia from usuarios where correo = ?";
        Usuario user = null;
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_SELECTUSUARIO);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String correoU = rs.getString("correo");
                String contraseniaU = rs.getString("contrasenia");
                float notaMedia = rs.getFloat("notaMedia");
                int id = rs.getInt("id");
                user = new Usuario(nombre, correoU, contraseniaU);
                user.setNotaMedia(notaMedia);
                user.setApellido(rs.getString("apellido"));
                user.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<Usuario> obtenerAllUsuarios(DAOManager dao) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        final String SQLSELECTUSERS = "select * from usuarios";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTUSERS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String correoU = rs.getString("correo");
                String contraseniaU = rs.getString("contrasenia");
                Usuario user = new Usuario(nombre, correoU, contraseniaU);
                user.setId(rs.getInt("id"));
                listaUsuarios.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaUsuarios;
    }

    @Override
    public void addProductoSolicitado(SolicitudProducto sp, DAOManager dao) {
        final String SQLINSERTSOLIPROD = "insert into productosSolicitados (nombreComprador, correoComprador, idProductoSolicitado) values (?,?,?)";
        String nombre = sp.getNombreComprador();
        String correo = sp.getCorreoComprador();
        int idProducto = sp.getProducto().getId();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLINSERTSOLIPROD);
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setInt(3, idProducto);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<SolicitudProducto> mostrarSolicitudes(Usuario u, DAOManager dao) {
        final String SQLSELECTSPPRODUCTS = " select * from productosSolicitados where idProductoSolicitado in " +
                "( select id from productos where idUsuario = ?);";
        ArrayList<SolicitudProducto> solicitudProductos = new ArrayList<>();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTSPPRODUCTS);
            stmt.setInt(1, u.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idSolicitud = rs.getInt("id");
                String nombreComprador = rs.getString("nombreComprador");
                String correoComprador = rs.getString("correoComprador");
                int idProducto = rs.getInt("idProductoSolicitado");
                Producto p = obtenerProductoById(idProducto, dao);
                SolicitudProducto sp = new SolicitudProducto(correoComprador, p, nombreComprador);
                sp.setId(idSolicitud);
                solicitudProductos.add(sp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return solicitudProductos;
    }

    @Override
    public SolicitudProducto obtenerSolicitudById(int idSolicitud, DAOManager dao) {
        SolicitudProducto sp = null;
        String sql = "select *, DATE_FORMAT(fechaSolicitud, \"%e/%m/%Y\") as \"fechaFormat\" from productosSolicitados where id = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, idSolicitud);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombreComprador = rs.getString("nombreComprador");
                String correoComprador = rs.getString("correoComprador");
                int idProducto = rs.getInt("idProductoSolicitado");
                String fechaSolicitud = rs.getString("fechaFormat");
                Producto p = obtenerProductoById(idProducto, dao);
                sp = new SolicitudProducto(correoComprador, p, nombreComprador);
                sp.setFechaSolicitud(fechaSolicitud);
                sp.setId(rs.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sp;
    }

    @Override
    public boolean borraSolicitudProducto(int id, DAOManager dao) {
        final String SQLDELETESP = "delete from productosSolicitados where id = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLDELETESP);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public int comprobarQueNoSeRepitaSolicitud(int idProducto, String correoUser, DAOManager dao) {
        final String sql = "select count(*)  \"numSolicitudes\" from productosSolicitados where correoComprador = ? " +
                "and idProductoSolicitado = ?";
        int numVeces = -1;
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setString(1,correoUser);
            stmt.setInt(2,idProducto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 numVeces = rs.getInt("numSolicitudes");
            }
            return numVeces;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numVeces;
    }

    @Override
    public boolean generarTratoVenta(String tipo, String correoUsuario, String nombreProducto, int idUsuario,
                                     float precio, InputStream imgProducto, DAOManager dao) {
        final String SQLINSERTTRATOVENTA = "insert into tratos (tipo,correoUsuario,nombreProducto,idUsuario,precio,imgProducto) " +
                "values (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLINSERTTRATOVENTA);
            stmt.setString(1, tipo);
            stmt.setString(2, correoUsuario);
            stmt.setString(3, nombreProducto);
            stmt.setInt(4, idUsuario);
            stmt.setFloat(5, precio);
            stmt.setBlob(6, imgProducto);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean generarTratoCompra(String tipo, String correoUsuario, String nombreProducto, int idUsuario,
                                      float precio, String comentario, int puntuacion, InputStream imgProducto, DAOManager dao) {
        final String SQLINSERTTRATOCOMPRA = "insert into tratos (tipo,correoUsuario,nombreProducto,idUsuario,precio,comentario,puntuacion,imgProducto) " +
                "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLINSERTTRATOCOMPRA);
            stmt.setString(1, tipo);
            stmt.setString(2, correoUsuario);
            stmt.setString(3, nombreProducto);
            stmt.setInt(4, idUsuario);
            stmt.setFloat(5, precio);
            stmt.setString(6, comentario);
            stmt.setInt(7, puntuacion);
            stmt.setBlob(8, imgProducto);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void addValoracionPendiente(int idTrato, String correoUsuario, DAOManager dao) {
        final String SQLINSERTVALORPEND = "insert into valoracionesPendientes (correoUsuario,idTrato) values (?,?)";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLINSERTVALORPEND);
            stmt.setString(1, correoUsuario);
            stmt.setInt(2, idTrato);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<Trato> obtenerTratoVentaByUsuarioID(int id, DAOManager dao) {
        final String SQLSELECTTRATOVENTA = " select * from tratos where tipo=" + "'venta'" + "and idUsuario=?";
        ArrayList<Trato> listaTratoTipoVentas = new ArrayList<>();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTTRATOVENTA);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idTrato = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String correo = rs.getString("correoUsuario");
                String nombreProducto = rs.getString("nombreProducto");
                String comentario = rs.getString("comentario");
                int puntuacion = rs.getInt("puntuacion");
                float precio = rs.getFloat("precio");
                Trato t = new Trato(tipo, correo, nombreProducto, precio);
                t.setId(idTrato);
                t.setComentario(comentario);
                t.setPuntuacion(puntuacion);
                listaTratoTipoVentas.add(t);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaTratoTipoVentas;
    }

    @Override
    public ArrayList<Trato> obtenerTratosByUsuario(int idUsuario, DAOManager dao) {
        ArrayList<Trato> listaTratos = new ArrayList<>();
        String sql = "select *, DATE_FORMAT(fechaTrato, \"%e/%m/%Y\") as fecha from tratos where idUsuario = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Trato t = new Trato(rs.getString("tipo"), rs.getString("correoUsuario"), rs.getString("nombreProducto"),
                        rs.getFloat("precio"));
                t.setId(rs.getInt("id"));
                t.setImgProduct(rs.getBinaryStream("imgProducto"));
                t.setFechaTrato(rs.getString("fecha"));
                t.setComentario(rs.getString("comentario"));
                t.setPuntuacion(rs.getInt("puntuacion"));
                listaTratos.add(t);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listaTratos;
    }

    @Override
    public Trato obtenerTratoById(int idTrato, DAOManager dao) {
        String sql = "select *, DATE_FORMAT(fechaTrato, \"%e/%m/%Y\") as fecha from tratos where id= ?";
        Trato t;
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, idTrato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                t = new Trato(rs.getString("tipo"), rs.getString("correoUsuario"), rs.getString("nombreProducto"),
                        rs.getFloat("precio"));
                t.setId(rs.getInt("id"));
                t.setImgProduct(rs.getBinaryStream("imgProducto"));
                t.setFechaTrato(rs.getString("fecha"));
                t.setIdUsuarioVendedor(rs.getInt("idUsuario"));
                return t;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Integer> obtenerValoracionesPendientesByUsuarioID(String correoUsuario, DAOManager dao) {
        final String SQLSELECTVALORPEND = "select idTrato from valoracionesPendientes where correoUsuario=?";
        ArrayList<Integer> listaIdTratosPendientes = new ArrayList<>();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTVALORPEND);
            stmt.setString(1, correoUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaIdTratosPendientes.add(rs.getInt("idTrato"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaIdTratosPendientes;
    }

    @Override
    public boolean borrarValoracionPendiente(int id, DAOManager dao) {
        final String SQLDELETEVALORPEND = " delete from valoracionesPendientes where idTrato =?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLDELETEVALORPEND);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarTratoCompra(int id, String comentario, int puntuacion, DAOManager dao) {
        final String SQLUPDATETRATO = "update tratos set comentario = ? ,puntuacion = ? where id=?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLUPDATETRATO);
            stmt.setString(1, comentario);
            stmt.setInt(2, puntuacion);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public int obtenerIdTrato(String nombreProducto, DAOManager dao) {
        final String SQLSELECTIDTRATO = "select id from tratos where tipo=" + "'venta'" + " and " +
                "nombreProducto='" + nombreProducto + "'and comentario = 'Sin comentario.' and puntuacion=0";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTIDTRATO);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean borrarUsuario(int id, DAOManager dao) {
        final String SQLDELETEUSER = "delete from usuarios where id= ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLDELETEUSER);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertarAvatarUsuario(InputStream avatarUser, int idUser, DAOManager dao) {
        String sql = "update usuarios set avatarUser = ? where id = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setBlob(1, avatarUser);
            stmt.setInt(2, idUser);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void insertAutentificacion(String correo, DAOManager dao) {
        final String SQL_UPDATEAUTENTICAION = " update usuarios set autentificado =? where correo=?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_UPDATEAUTENTICAION);
            stmt.setBoolean(1, true);
            stmt.setString(2, correo);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insertToken(String token, String correo, DAOManager dao) {
        final String SQL_UPDATETOKEN = "update usuarios set tokenUsuario =? where correo= ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_UPDATETOKEN);
            stmt.setString(1, token);
            stmt.setString(2, correo);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String obtenerToken(String correo, DAOManager dao) {
        final String SQL_SELECTTOKEN = "select tokenUsuario from usuarios where correo = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_SELECTTOKEN);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("tokenUsuario");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean obtenerAutenticacion(String correo, DAOManager dao) {
        final String SQL_SELECTAUTH = "select autentificado from usuarios where correo = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_SELECTAUTH);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            int result = -1;
            if (rs.next()) {
                result = rs.getInt("autentificado");
            }
            if (result == 1) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarDatos(String tipoDato, String valor, String correoUsuario, DAOManager dao) {
        final String SQL_UPDATEDATO = "update usuarios set " + tipoDato + "= '" + valor + "' where correo = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQL_UPDATEDATO);
            stmt.setString(1, correoUsuario);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actulizarContraseniaCifrada(String contrasenia, String claveCifrada, String correoUser, DAOManager dao) {
        String sql = "update usuarios set contrasenia = ?, contraseniaCifrada = ? where correo = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setString(1,contrasenia);
            stmt.setString(2,claveCifrada);
            stmt.setString(3,correoUser);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertProducto(Producto producto, int id, DAOManager dao) {
        final String SQLINSERTPRODUCT = "insert into productos (nombre,descrip,precio,idUsuario,imagen) values (?,?,?,?,?) ";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLINSERTPRODUCT);
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setFloat(3, Float.parseFloat(String.valueOf(producto.getPrecio())));
            stmt.setInt(4, id);
            stmt.setBlob(5, producto.getImagenBlobData());
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Producto obtenerProductoById(int id, DAOManager dao) {
        final String SQLSELECTPRODUCT = "select * from productos where id = ?";
        Producto producto = null;
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTPRODUCT);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String desc = rs.getString("descrip");
                float preciof = rs.getFloat("precio");
                InputStream is = rs.getBinaryStream("imagen");
                producto = new Producto(nombre, desc, preciof);
                producto.setId(rs.getInt("id"));
                producto.setImagenBlobData(is);
                producto.setIdUsuarioPropietario(rs.getInt("idUsuario"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return producto;
    }

    @Override
    public void renderImagenProductoById(int id, HttpServletResponse response, DAOManager dao) {
        String sql = "select * from productos where id = ?";
        InputStream inputStream;
        byte[] b = new byte[0];
        response.setContentType("image/*");
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                b = rs.getBytes("imagen");
            }
            inputStream = new ByteArrayInputStream(b);

            int tamanioInput = inputStream.available();
            byte[] datosImagen = new byte[tamanioInput];
            inputStream.read(datosImagen, 0, tamanioInput);
            response.getOutputStream().write(datosImagen);
            inputStream.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void renderAvatarUsuarioById(int idUsuario, HttpServletResponse response, DAOManager dao) {
        String sql = "select avatarUser from usuarios where id = ?";
        InputStream inputStream;
        byte[] b = new byte[0];
        response.setContentType("image/*");
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                b = rs.getBytes("avatarUser");
            }
            inputStream = new ByteArrayInputStream(b);

            int tamanioInput = inputStream.available();
            byte[] datosImagen = new byte[tamanioInput];
            inputStream.read(datosImagen, 0, tamanioInput);
            response.getOutputStream().write(datosImagen);
            inputStream.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void renderImageTrato(int idTrato,HttpServletResponse response, DAOManager dao) {
        String sql = "select imgProducto from tratos where id = ?";
        byte[] b = new byte[0];
        response.setContentType("image/*");
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1,idTrato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                b = rs.getBytes("imgProducto");
            }
            try (InputStream inputStream = new ByteArrayInputStream(b)) {
                int tamanioInput = inputStream.available();
                byte[] datosImagen = new byte[tamanioInput];
                inputStream.read(datosImagen, 0, tamanioInput);
                response.getOutputStream().write(datosImagen);
            } catch (IOException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<Producto> obtenerAllProductos(DAOManager dao) {
        final String SQLSELECTPRODUCTS = "select * from productos order by nombre";
        return getProductos(dao, SQLSELECTPRODUCTS);
    }

    private ArrayList<Producto> getProductos(DAOManager dao, String SQLSELECTPRODUCTS) {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTPRODUCTS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String desc = rs.getString("descrip");
                float preciof = rs.getFloat("precio");
                Producto producto = new Producto(nombre, desc, preciof);
                producto.setId(rs.getInt("id"));
                listaProductos.add(producto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaProductos;
    }

    @Override
    public ArrayList<Producto> obtenerProductoByTerm(String term, DAOManager dao) {
        final String SQLSELECTBYTERM = "select * from productos where nombre like '%" + term + "%';";
        return getProductos(dao, SQLSELECTBYTERM);
    }

    @Override
    public ArrayList<Producto> mostrarProductoUsuario(int id, DAOManager dao) {
        final String SQLSELECTPRODUCTSUSER = "select * from productos where idUsuario = ?";
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLSELECTPRODUCTSUSER);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String desc = rs.getString("descrip");
                float preciof = rs.getFloat("precio");
                Producto producto = new Producto(nombre, desc, preciof);
                producto.setId(rs.getInt("id"));
                listaProductos.add(producto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaProductos;
    }

    @Override
    public boolean borrarProductoById(int id, DAOManager dao) {
        final String SQLDELETEPRODUCT = "delete from productos where id= ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(SQLDELETEPRODUCT);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean envioMensajePrivado(String contenido,String asunto, int idEmisor, int idReceptor, DAOManager dao) {
        String sql = "insert into mensajes (contenido,emisor,receptor,asunto) values (?,?,?,?)";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setString(1, contenido);
            stmt.setInt(2, idEmisor);
            stmt.setInt(3, idReceptor);
            stmt.setString(4,asunto);
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Mensaje> mensajesPrivadosOfUser(int idUser, DAOManager dao) {
        String sql = "select *, DATE_FORMAT(fechaEnvio, \"%e/%m/%Y\") as fechaE, " +
                "DATE_FORMAT(fechaLectura, \"%e/%m/%Y\") as fechaL from mensajes where emisor = ?";
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(rs.getInt("id"));
                mensaje.setContenido(rs.getString("contenido"));
                mensaje.setEmisor(obtenerUserById(rs.getInt("emisor"), dao));
                mensaje.setReceptor(obtenerUserById(rs.getInt("receptor"), dao));
                mensaje.setFechaEnvio(rs.getString("fechaE"));
                mensaje.setFechaLectura(rs.getString("fechaL"));
                mensaje.setEstaLeido(rs.getBoolean("estaLeido"));
                mensaje.setAsunto(rs.getString("asunto"));
                mensajes.add(mensaje);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return mensajes;
    }

    @Override
    public ArrayList<Mensaje> mensajesRecibidosOfUser(int idUser, DAOManager dao) {
        String sql = "select *, DATE_FORMAT(fechaEnvio, \"%e/%m/%Y\") as fechaE, " +
                "DATE_FORMAT(fechaLectura, \"%e/%m/%Y\") as fechaL from mensajes where receptor = ?" +
                " and estaLeido = 0";
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(rs.getInt("id"));
                mensaje.setContenido(rs.getString("contenido"));
                mensaje.setEmisor(obtenerUserById(rs.getInt("emisor"), dao));
                mensaje.setReceptor(obtenerUserById(rs.getInt("receptor"), dao));
                mensaje.setFechaEnvio(rs.getString("fechaE"));
                mensaje.setFechaLectura(rs.getString("fechaL"));
                mensaje.setEstaLeido(rs.getBoolean("estaLeido"));
                mensaje.setAsunto(rs.getString("asunto"));
                mensajes.add(mensaje);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return mensajes;
    }

    @Override
    public void setearLecturaMensaje(int idMensaje, DAOManager dao) {
        String sql = "update mensajes set estaLeido = ?,fechaLectura = ? where id = ?";
        String fecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, fecha);
            stmt.setInt(3, idMensaje);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Mensaje obtenerMensaje(int idMensaje, DAOManager dao) {
        String sql = "select *,DATE_FORMAT(fechaEnvio, \"%e/%m/%Y\") as fechaE," +
                "DATE_FORMAT(fechaLectura, \"%e/%m/%Y\") as fechaL from mensajes where id = ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1,idMensaje);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Mensaje(rs.getInt("id"),rs.getString("contenido"),rs.getBoolean("estaLeido")
                ,rs.getString("fechaE"),rs.getString("fechaL"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> usersPosiblesCompradores(int idReceptor,DAOManager dao) {
        ArrayList<Usuario> users = new ArrayList<>();
        String sql = "select emisor from mensajes where emisor != ?";
        try {
            PreparedStatement stmt = dao.getConn().prepareStatement(sql);
            stmt.setInt(1,idReceptor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(obtenerUserById(rs.getInt("emisor"),dao));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }
}

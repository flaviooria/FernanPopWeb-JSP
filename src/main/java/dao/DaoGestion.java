package dao;

import modelos.SolicitudProducto;
import modelos.Trato;
import modelos.Usuario;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;

public interface DaoGestion {
  public boolean insert(Usuario usuario,DAOManager dao);
  public Usuario obtenerUsuario(String correo, String contrasenia,DAOManager dao);
  public Usuario obtenerUsuarioByCorreo(String correo,DAOManager dao);
  public ArrayList<Usuario> obtenerAllUsuarios(DAOManager dao);
  public void addProductoSolicitado(SolicitudProducto solicitudProducto,DAOManager dao);
  public ArrayList<SolicitudProducto> mostrarSolicitudes(Usuario u,DAOManager dao);
  public SolicitudProducto obtenerSolicitudById(int idSolicitud, DAOManager dao);
  public boolean borraSolicitudProducto(int id,DAOManager dao);
  public int comprobarQueNoSeRepitaSolicitud(int idProducto,String correoUser,DAOManager dao);
  public ArrayList<Integer> obtenerValoracionesPendientesByUsuarioID(String correoUsuario,DAOManager dao);
  public boolean borrarValoracionPendiente(int id,DAOManager dao);
  public boolean borrarUsuario(int id,DAOManager dao);
  public Usuario obtenerUserById(int idUser,DAOManager dao);
}

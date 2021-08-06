package dao;

import modelos.Trato;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;

public interface DaoUsuario {
  public void insertAutentificacion(String correo,DAOManager dao);
  public void insertToken(String token,String correo,DAOManager dao);
  public String obtenerToken(String correo,DAOManager dao);
  public boolean obtenerAutenticacion(String correo,DAOManager dao);
  public boolean actualizarDatos(String tipoDato,String valor,String correoUsuario,DAOManager dao);
  public boolean actulizarContraseniaCifrada(String contrasenia,String claveCifrada,String correoUser,DAOManager dao);
  public boolean insertarAvatarUsuario(InputStream avatarUser,int idUser, DAOManager dao);
  public void renderAvatarUsuarioById(int idUsuario, HttpServletResponse response,DAOManager dao);
  public String getClaveCifrada(String correoUser,DAOManager dao);
}

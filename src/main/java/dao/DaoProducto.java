package dao;

import modelos.Producto;
import modelos.Usuario;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public interface DaoProducto {
  public boolean insertProducto(Producto producto, int idUsuario,DAOManager dao);
  public Producto obtenerProductoById(int id,DAOManager dao);
  public ArrayList<Producto> obtenerAllProductos(DAOManager dao);
  public ArrayList<Producto> obtenerProductoByTerm(String term,DAOManager dao);
  public ArrayList<Producto> mostrarProductoUsuario(int id,DAOManager dao);
  public boolean borrarProductoById(int id,DAOManager dao);
  public void renderImagenProductoById(int id, HttpServletResponse response,DAOManager dao);
}

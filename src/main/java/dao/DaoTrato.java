package dao;

import modelos.Trato;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;

public interface DaoTrato {
    public boolean generarTratoVenta(String tipo, String correoUsuario, String nombreProducto, int idUsuario, float precio,
                                     InputStream imgProducto, DAOManager dao);
    public boolean generarTratoCompra(String tipo, String correoUsuario, String nombreProducto, int idUsuario, float precio, String
            comentario, int puntuacion, InputStream imgProducto, DAOManager dao);
    public void addValoracionPendiente(int idTrato, String correoUsuario, DAOManager dao);
    public ArrayList<Trato> obtenerTratoVentaByUsuarioID(int id, DAOManager dao);
    public boolean actualizarTratoCompra(int id,String comentario,int puntuacion,DAOManager dao);
    public int obtenerIdTrato(String nombreProducto,DAOManager dao);
    public Trato obtenerTratoById(int idTrato, DAOManager dao);
    public ArrayList<Trato> obtenerTratosByUsuario(int idUsuario, DAOManager dao);
    public void renderImageTrato(int idTrato, HttpServletResponse response, DAOManager dao);
}

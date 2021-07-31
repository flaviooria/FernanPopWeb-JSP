package dao;

import modelos.Mensaje;
import modelos.Usuario;

import java.util.ArrayList;
import java.util.Calendar;

public interface DaoChat {
    public boolean envioMensajePrivado(String contenido,String ausnto, int idEmisor, int idReceptor,
                                       DAOManager dao);
    public ArrayList<Mensaje> mensajesPrivadosOfUser(int idUser,DAOManager dao);
    public ArrayList<Mensaje> mensajesRecibidosOfUser(int idUser,DAOManager dao);
    public ArrayList<Usuario> usersPosiblesCompradores(int idReceptor,DAOManager dao);
    public void setearLecturaMensaje(int idMensaje,DAOManager dao);
    public Mensaje obtenerMensaje(int idMensaje,DAOManager dao);
}

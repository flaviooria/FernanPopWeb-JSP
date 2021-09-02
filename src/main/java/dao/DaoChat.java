package dao;

import modelos.Mensaje;
import modelos.Usuario;

import java.util.ArrayList;

public interface DaoChat {
    public ArrayList<Mensaje> mensajesEnviadosOfUser(int idUser, DAOManager dao);
    public ArrayList<Mensaje> mensajesRecibidosOfUser(int idUser,DAOManager dao);
    public ArrayList<Mensaje> mensajesRecibidosOfUserSinLeer(int idUser,DAOManager dao);
    public ArrayList<Usuario> usersPosiblesCompradores(int idReceptor,DAOManager dao);
    public void setearLecturaMensaje(int idMensaje,String nombreTabla, DAOManager dao);
    public Mensaje obtenerMensajeDeBuzonEnviados(int idMensaje, DAOManager dao);
    public Mensaje obtenerMensajeDeBuzonRecibidos(int idMensaje,DAOManager dao);
    public boolean almacenarEnvioBuzonEnviados(String contenido, String asunto, int idEmisor, int idReceptor,
                                               DAOManager dao);
    public boolean almacenarEnvioBuzonRecibidos(String contenido, String asunto, int idEmisor, int idReceptor,
                                                DAOManager dao);
}

package modelos;

import utilidades.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Mensaje {
    private int id;
    private String contenido;
    private int longMax;
    private String fechaEnvio;
    private String fechaLectura;
    private boolean estaLeido;
    private Usuario emisor;
    private Usuario receptor;
    private String asunto;

    public Mensaje(String contenido, Usuario emisor, Usuario receptor) {
        this.contenido = contenido;
        this.emisor = emisor;
        this.receptor = receptor;
    }

    public Mensaje() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getLongMax() {
        return longMax;
    }

    public void setLongMax(int longMax) {
        this.longMax = longMax;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(String fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public boolean isEstaLeido() {
        return estaLeido;
    }

    public void setEstaLeido(boolean estaLeido) {
        this.estaLeido = estaLeido;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }


    @Override
    public String toString() {
        return "*** CHAT *** " +
                "\n Contenido: " + contenido +
                "\n Fecha Envio: " + fechaEnvio  +
                "\n Fecha Lectura: " + ((fechaLectura == null) ? "Sin Fecha" : fechaLectura) +
                "\n Estado: " + ((!estaLeido) ? "No leido" : "leido") +
                "\n Enviado por: " + emisor.getNombre() +
                "\n Recibido por: " + receptor.getNombre() +
                "*** **** **** *** ";
    }
}

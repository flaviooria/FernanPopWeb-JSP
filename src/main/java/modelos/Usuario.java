package modelos;

import comunicaciones.Email;
import plantillasHtml.NotificacionToken;

import java.io.IOException;
import java.io.InputStream;

public class Usuario {
  private int id;
  private String nombre;
  private String apellido;
  private String correo;
  private String contrasenia;
  private int edad;
  private int movil;
  private float notaMedia;
  private InputStream fotoPerfil;
  private String fechaCreacion;
  private String contraseniaCifrada;

  public Usuario(String nombre, String correo, String contrasenia,String contraseniaCifrada) {
    this.nombre = nombre;
    this.correo = correo;
    this.contrasenia = contrasenia;
    this.contraseniaCifrada = contraseniaCifrada;
  }

  public Usuario(String nombre, String correo, String contrasenia) {
    this.nombre = nombre;
    this.correo = correo;
    this.contrasenia = contrasenia;
  }

  public Usuario() {
  }

  public int getId() {
    return id;
  }

  public void setContraseniaCifrada(String contraseniaCifrada) {
    this.contraseniaCifrada = contraseniaCifrada;
  }

  public String getContraseniaCifrada() {
    return contraseniaCifrada;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }


  public boolean isAutentificado() {
    return false;
  }

  public void setAutentificado() {

  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public int getMovil() {
    return movil;
  }

  public void setMovil(int movil) {
    this.movil = movil;
  }

  public float getNotaMedia() {
    return notaMedia;
  }

  public void setNotaMedia(float notaMedia) {
    this.notaMedia = notaMedia;
  }

  public int numeroSolicitudesPendientes(int id) {
    return 0;
  }

  public int numeroValoracionesPendientes(String correo) {
    return 0;
  }

  public InputStream getFotoPerfil() {
    return fotoPerfil;
  }

  public void setFotoPerfil(InputStream fotoPerfil) {
    this.fotoPerfil = fotoPerfil;
  }

  public String getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(String fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public static String obtengoToken() {
    String tokenNumerio = "";
    for (int i = 0; i < 4; i++) {
      tokenNumerio += String.valueOf(generarTokenNumerico());
    }
    return tokenNumerio;
  }

  private static int generarTokenNumerico() {
    return (int) (Math.random() * 10);
  }

  public static boolean validarCorreo(String correoIntroducido) {
    String com = ".com";
    String despuesArroba = "";
    for (int i = 0; i < correoIntroducido.length(); i++) {

      if (correoIntroducido.charAt(i) == '@') {
        despuesArroba = correoIntroducido.substring(i);
      }

      for (int j = 0; j < despuesArroba.length(); j++) {
        if (despuesArroba.charAt(j) == '.') {
          if (despuesArroba.startsWith(com, j)) return true;
        }
      }

      if (i == correoIntroducido.length() - 1) return false;
    }

    return false;
  }

  //metodo para validar contraseña
  public static boolean validarContrasenia(String contrasenia) {
    return contrasenia.length() > 4 && contrasenia.length() <= 16;
  }

  @Override
  public String toString() {
    //notaMedia();
    String data = " ";
    try {
        data = "Datos personales: " +
              "\n  nombre: " + nombre +
              "\n  correo: " + correo +
              "\n  edad: " + ((edad == 0) ? "Campo no editado " : edad) +
              "\n  movil: " + ((movil == 0) ? "Campo no editado  " : movil) +
              "\n  nota media: " + ((notaMedia == -1) ? "Sin valoración" : notaMedia + "%") +
              "\n avatar: " + (getFotoPerfil() != null ? getFotoPerfil().available() : 0);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return  data;
  }
}

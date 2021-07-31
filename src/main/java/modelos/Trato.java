package modelos;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Trato {
  private int id;
  private String tipo;
  private String correoOtroUsuario;
  private String nombreProducto;
  private double precio;
  private String comentario;
  private int puntuacion;
  private final Calendar fecha;
  private static int increment = 1;
  private InputStream imgProduct;
  private String fechaTrato;
  private int idUsuarioVendedor;

  public Trato( String tipo, String correoOtroUsuario, String nombreProducto, double precio) {
    this.nombreProducto = nombreProducto;
    this.tipo = tipo;
    this.correoOtroUsuario = correoOtroUsuario;
    this.fecha = Calendar.getInstance();
    this.precio = precio;
    this.id = increment++;
  }

  public Trato() {
    this.fecha = Calendar.getInstance();
  }

  public Trato(Trato trato) {
    this.nombreProducto = trato.nombreProducto;
    this.tipo = trato.tipo;
    this.correoOtroUsuario = trato.correoOtroUsuario;
    this.fecha = Calendar.getInstance();
    this.precio = trato.precio;
    this.id = trato.id;
    this.comentario = trato.comentario;
    this.puntuacion = trato.puntuacion;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIdUsuarioVendedor() {
    return idUsuarioVendedor;
  }

  public void setIdUsuarioVendedor(int idUsuarioVendedor) {
    this.idUsuarioVendedor = idUsuarioVendedor;
  }

  public String getFechaTrato() {
    return fechaTrato;
  }

  public void setFechaTrato(String fechaTrato) {
    this.fechaTrato = fechaTrato;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getCorreoOtroUsuario() {
    return correoOtroUsuario;
  }

  public void setCorreoOtroUsuario(String correoOtroUsuario) {
    this.correoOtroUsuario = correoOtroUsuario;
  }

  public String getNombreProducto() {
    return nombreProducto;
  }

  public void setNombreProducto(String nombreProducto) {
    this.nombreProducto = nombreProducto;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public int getPuntuacion() {
    return puntuacion;
  }

  public void setPuntuacion(int puntuacion) {
    this.puntuacion = puntuacion;
  }

  public Calendar getFecha() {
    return fecha;
  }

  public InputStream getImgProduct() {
    return imgProduct;
  }

  public void setImgProduct(InputStream imgProduct) {
    this.imgProduct = imgProduct;
  }

  private String mostrarFecha(Calendar fecha) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
    return sdf.format(fecha.getTime());
  }

  @Override
  public String toString() {
    return " ID: " + id +
            "\n "+((tipo.equals("compra")) ? "Correo vendedor: "  + correoOtroUsuario : "Correo comprador: " + correoOtroUsuario) +
            "\n Producto: " + ( nombreProducto.isEmpty() ? "Ningún producto disponible" : nombreProducto) +
            "\n Fecha Producto: " + mostrarFecha(fecha) +
            "\n Precio: " + precio +
            "\n Comentario: " + ((comentario == null) ? "No valorado" : comentario) +
            "\n Puntuacion: " + ((puntuacion == 0) ? "No valorado" : puntuacion) +
            "\n ============================================";
  }
}

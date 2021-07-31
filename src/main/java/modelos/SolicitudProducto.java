package modelos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SolicitudProducto {
  private int id;
  private String nombreComprador;
  private String correoComprador;
  private Producto producto;
  private static int idIncrementado = 1;
  private final Calendar fecha;
  private String fechaSolicitud;

  public SolicitudProducto(String correoComprador, Producto producto, String nombreComprador) {
    this.id = idIncrementado++;
    this.correoComprador = correoComprador;
    this.producto = producto;
    this.nombreComprador = nombreComprador;
    fecha = Calendar.getInstance();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCorreoComprador() {
    return correoComprador;
  }

  public void setCorreoComprador(String correoComprador) {
    this.correoComprador = correoComprador;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public Calendar getFecha() {
    return fecha;
  }

  public String getNombreComprador() {
    return nombreComprador;
  }

  public void setNombreComprador(String nombreComprador) {
    this.nombreComprador = nombreComprador;
  }

  public String getFechaSolicitud() {
    return fechaSolicitud;
  }

  public void setFechaSolicitud(String fechaSolicitud) {
    this.fechaSolicitud = fechaSolicitud;
  }

  private String mostrarFecha(Calendar fecha) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
    return sdf.format(fecha.getTime());
  }

  @Override
  public String toString() {
    return  " Número solicitud: " + id +
            "\n - Nombre comprador: " + nombreComprador +
            "\n - Correo Comprador: " + correoComprador +
            "\n - Producto: " + producto +
            "\n - Fecha: " + mostrarFecha(fecha) +
            "\n ====================================";
  }
}

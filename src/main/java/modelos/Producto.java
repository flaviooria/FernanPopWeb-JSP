package modelos;

import java.io.InputStream;

public class Producto {
  private int id;
  private String nombre;
  private String descripcion;
  private double precio;
  private InputStream imagenBlobData;
  private int idUsuarioPropietario;

  public Producto(String nombre, String descripcion, double precio) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIdUsuarioPropietario() {
    return idUsuarioPropietario;
  }

  public void setIdUsuarioPropietario(int idUsuarioPropietario) {
    this.idUsuarioPropietario = idUsuarioPropietario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public InputStream getImagenBlobData() {
    return imagenBlobData;
  }

  public void setImagenBlobData(InputStream imagenBlobData) {
    this.imagenBlobData = imagenBlobData;
  }

  @Override
  public String toString() {
    return  nombre +
            "\n - Identidicador: " + id +
            "\n - Descripición: " + descripcion +
            "\n - Precio: " + precio + " €" +
            "\n ====================================";
  }
}

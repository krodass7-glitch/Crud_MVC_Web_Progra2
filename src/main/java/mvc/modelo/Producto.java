/**
 * @author: Keneth Andree Rodas Santos
 */
package mvc.modelo;

public class Producto {

    private int id_producto;
    private int id_marca;
    private String nombre;
    private float precio;
    private int stock;

    public Producto(int id_producto, int id_marca, String nombre, float precio, int stock) {
        this.id_producto = id_producto;
        this.id_marca = id_marca;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

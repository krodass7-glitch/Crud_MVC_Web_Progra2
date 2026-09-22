/**
 * @author: Keneth Andree Rodas Santos
 */
package modelo;

public class Cliente extends persona {

    private int id_cliente;
    private String nit;
    private String email;

    public Cliente() {
        super("", "");
    }

    public Cliente(int id_cliente, String nombre, String apellidos, String nit, String email) {
        super(nombre, apellidos);
        this.id_cliente = id_cliente;
        this.nit = nit;
        this.email = email;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

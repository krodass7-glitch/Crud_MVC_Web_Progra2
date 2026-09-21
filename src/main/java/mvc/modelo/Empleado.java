/**
 * @author: Keneth Andree Rodas Santos
 */
package mvc.modelo;

public class Empleado extends persona {

    private int id_empleado;
    private int id_puesto;
    private java.util.Date fecha_contratacion;

    public Empleado(String nombre, String apellidos, int id_empleado, int id_puesto, java.util.Date fecha_contratacion) {
        super(nombre, apellidos);
        this.id_empleado = id_empleado;
        this.id_puesto = id_puesto;
        this.fecha_contratacion = fecha_contratacion;
    }

    public int getid_empleado() {
        return id_empleado;
    }

    public void setid_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getid_puesto() {
        return id_puesto;
    }
 
    public void setid_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public java.util.Date getfecha_contratacion() {
        return fecha_contratacion;
    }

    public void setfecha_contratacion(java.util.Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

}

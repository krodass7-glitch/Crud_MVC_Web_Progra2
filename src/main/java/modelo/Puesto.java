/**
 * @author: Keneth Andree Rodas Santos
 */
package modelo;

public class Puesto {

    private int id_puesto;
    private String nombre;
    private float salario_base;

    public Puesto(int id_puesto, String nombre, float salario_base) {
        this.id_puesto = id_puesto;
        this.nombre = nombre;
        this.salario_base = salario_base;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(float salario_base) {
        this.salario_base = salario_base;
    }

}


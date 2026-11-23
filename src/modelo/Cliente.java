package modelo;

public class Cliente {
    private String ci;
    private String nombre;

    public Cliente(String ci, String nombre) {
        this.ci=ci;
        this.nombre=nombre;
    }
    public String getNombre() { return nombre; }
}
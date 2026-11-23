package modelo;

public class Laptop {

    public enum Estado{ DISPONIBLE, ALQUILADA}
    
    private String id;
    private String marca;
    private String modelo;
    private String caracteristica;
    private Estado estado; 

    public Laptop(String id, String marca, String modelo, String caracteristica) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.caracteristica = caracteristica;
        this.estado = Estado.DISPONIBLE;
    }

    public String getId() { return id; }
    public String getEstado() { return estado.toString(); }
    public void setEstado(String estado) {

        if(estado.equalsIgnoreCase("ALQUILADA")){
            this.estado=Estado.ALQUILADA;
        }
        else if(estado.equalsIgnoreCase("DISPONIBLE")){
            this.estado=Estado.DISPONIBLE;
        }
     }
    
    public String obtenerDetalles() {
        return "Laptop(id)["+id+"] Marca:"+marca+" Modelo:"+modelo+" Caract:("+caracteristica+ ") Estado:"+estado;
    }
}
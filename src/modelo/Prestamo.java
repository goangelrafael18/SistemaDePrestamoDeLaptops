package modelo;

public class Prestamo {
    private Laptop lap;
    private Cliente cliente;
    private double tafdia;    
    private int dia;      

    public Laptop getLap() { return lap; }

    public String prestamoInicio(Cliente cliente, Laptop lap, double tafdia, int dia) {
        this.cliente=cliente;
        this.lap=lap;
        this.tafdia=tafdia;
        this.dia=dia;
        
        this.lap.setEstado("ALQUILADA");
        
        return "\n--- Prestamo Iniciado ---\n" +
               "Cliente:"+cliente.getNombre()+" Alquiló la laptop "+lap.getId()+".\n" +
               "Dias acordados:" + dia + " | Tarifa por dia:"+tafdia+"$\n" +
               "Tarifa total estimada:"+(tafdia*dia)+"$\n";
    }

    public String prestamoFin(int cantreal) { 
        double tafbase=tafdia*dia;
        String resultado = "\n--- Procesando Devolución ---\n";
        
        if (cantreal > this.dia) {
            double sub_taf = tafdia * (cantreal - this.dia);
            double taftotal = tafbase + (sub_taf + sub_taf * 0.1);
            resultado+="PENALIZACION POR DIAS DE RETRASO\n"+
                        "Dias extra cobrados:"+(cantreal - this.dia)+"\n" +
                        "Su tarifa total nueva es de:"+taftotal+"$\n";
        } else { 
            resultado+="Devolucion a tiempo. Su tarifa es de:"+tafbase+"$\n";
        }

        this.lap.setEstado("DISPONIBLE");
        resultado+="El equipo:"+this.lap.getId()+" vuelve a estar "+this.lap.getEstado()+"\n";
        return resultado;
    }
}
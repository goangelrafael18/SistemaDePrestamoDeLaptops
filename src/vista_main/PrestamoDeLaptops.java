package vista_main;

import javax.swing.*;
import modelo.*;


public class PrestamoDeLaptops {
    
    public static void main(String[] args) {
        System.out.println("Iniciando el Sistema de Prestamo de Laptops...");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Advertencia: No se pudo cargar el tema nativo.");
        }
        final int cap_max = 10;
        final int pres_max = 100;
        
        Laptop[] inventario = new Laptop[cap_max]; 
        Prestamo[] hist_prestamo = new Prestamo[pres_max];
        
        int[] cantreg = {0}; 
        int[] cantprestamos = {0};

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana vista = new Ventana();
                    vista.setTitle("Sistema Prestamo de Laptops");
                    vista.setLocationRelativeTo(null);
        
                    vista.getBtnr().addActionListener(e -> {
                        vista.limpiarYMostrarInventario(inventario, cantreg);
                        if (cantreg[0] >= cap_max) {
                            JOptionPane.showMessageDialog(null, "El inventario esta lleno.");
                            return;
                        }
                        
                        String id = JOptionPane.showInputDialog("Ingrese ID del equipo:");
                        if (id == null) return;
                        
                        String marca = JOptionPane.showInputDialog("Ingrese Marca:");
                        String modelo = JOptionPane.showInputDialog("Ingrese Modelo:");
                        String caract = JOptionPane.showInputDialog("Características (ej:alm, ram, cpu):");

                        inventario[cantreg[0]] = new Laptop(id, marca, modelo, caract);
                        cantreg[0]++;
                        
                        vista.agregarTextoPantalla("\n-> Laptop registrada exitosamente: ID " + id + "\n");
        
                        vista.agregarTextoPantalla("\n--- INVENTARIO ACTUAL ---\n");
                        if (cantreg[0] == 0) {
                            vista.agregarTextoPantalla("El inventario esta vacio.\n");
                        } else {
                            for (int i = 0; i < cantreg[0]; i++) {
                                vista.agregarTextoPantalla(inventario[i].obtenerDetalles() + "\n");
                            }
                        }
                    });

                    vista.getBtnp().addActionListener(e -> {
                        vista.limpiarYMostrarInventario(inventario, cantreg);
                        if (cantreg[0] == 0) {
                            JOptionPane.showMessageDialog(null, "No hay laptops registradas.");
                            return;
                        }
                        
                        

                        String ci = JOptionPane.showInputDialog("Ingrese Cedula del Cliente:");
                        if (ci == null) return;
                        String nombre = JOptionPane.showInputDialog("Ingrese Nombre del Cliente:");
                        if (nombre == null) return; 

                        Cliente cliente = new Cliente(ci, nombre);

                        String idBuscado = JOptionPane.showInputDialog("Ingrese el ID de la laptop a llevarse:");
                        if (idBuscado == null) return;
                        
                        Laptop lapselec = null;
                        for (int i = 0; i < cantreg[0]; i++) {
                            if (inventario[i].getId().equals(idBuscado) && inventario[i].getEstado().equals("DISPONIBLE")) {
                                lapselec = inventario[i]; 
                                break;   
                            }
                        }

                        if (lapselec != null) {
                            try {
                                String dias = JOptionPane.showInputDialog("Cantidad de Dias de Prestamo:");
                                if (dias == null) return;
                                int cantdia = Integer.parseInt(dias);
                                double taf_fija = 20.0;

                                Prestamo nuevoPrestamo = new Prestamo();
                                String recibo = nuevoPrestamo.prestamoInicio(cliente, lapselec, taf_fija, cantdia);
                                
                                hist_prestamo[cantprestamos[0]] = nuevoPrestamo;
                                cantprestamos[0]++;

                                vista.agregarTextoPantalla(recibo);
                                
                                vista.agregarTextoPantalla("\n--- INVENTARIO ACTUAL ---\n");
                                for (int i = 0; i < cantreg[0]; i++) {
                                    vista.agregarTextoPantalla(inventario[i].obtenerDetalles() + "\n");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un numero valido de dias.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Laptop no encontrada o ya esta ALQUILADA.");
                        }
                    });

                    vista.getBtnd().addActionListener(e -> {
                        vista.limpiarYMostrarInventario(inventario, cantreg);
                        if (cantprestamos[0] == 0) {
                            JOptionPane.showMessageDialog(null, "No hay Prestamos Activos.");
                            return;
                        }
                        
                        String idBuscado = JOptionPane.showInputDialog("Ingrese el ID de la laptop que desea entregar:");
                        if (idBuscado == null) return;

                        Prestamo prestamo_act = null;
                        for (int i = 0; i < cantprestamos[0]; i++) {
                            if (hist_prestamo[i].getLap().getId().equals(idBuscado) && hist_prestamo[i].getLap().getEstado().equals("ALQUILADA")){
                                prestamo_act = hist_prestamo[i];
                                break;
                            }
                        }

                        if (prestamo_act != null) {
                            try {
                                String real = JOptionPane.showInputDialog("Ingrese cantidad de días reales que duró el préstamo:");
                                int cantreal = Integer.parseInt(real);
                                
                                String reciboDevolucion = prestamo_act.prestamoFin(cantreal);
                                vista.agregarTextoPantalla(reciboDevolucion);
                            }
                            catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No se encontro la laptop alquilada con ese ID.");
                        }
                    });

                    vista.getBtni().addActionListener(e -> {
                        vista.limpiarYMostrarInventario(inventario, cantreg);
                       
                    });

                    vista.setVisible(true);

                } catch (Exception e) {
                    System.err.println("Error al arrancar la interfaz grafica:");
                    e.printStackTrace();
                }
            }
        });
    }
}
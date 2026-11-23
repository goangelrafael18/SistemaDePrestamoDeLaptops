package vista_main;

import java.awt.*;
import javax.swing.*;
import modelo.Laptop;

public class Ventana extends JFrame {
    
    private JTextArea pantalla;
    private JButton btnr, btnp, btnd, btni;

    public Ventana() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pantalla = new JTextArea();
        pantalla.setEditable(false);
        pantalla.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(pantalla);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(Color.DARK_GRAY);

        btnr = new JButton("1. Registrar Laptop");
        btnp = new JButton("2. Prestar Laptop");
        btnd = new JButton("3. Devolver Laptop");
        btni = new JButton("4. Ver Inventario");

        panelBotones.add(btnr);
        panelBotones.add(btnp);
        panelBotones.add(btnd);
        panelBotones.add(btni);

        add(panelBotones, BorderLayout.NORTH);
        
        pantalla.setText("Bienvenido al Sistema de Gestion de Laptops.\nSeleccione una opcion de los botones superiores.\n");
    }

    public JButton getBtnr() { return btnr; }
    public JButton getBtnp() { return btnp; }
    public JButton getBtnd() { return btnd; }
    public JButton getBtni() { return btni; }

    public void agregarTextoPantalla(String texto) {
        pantalla.append(texto);
    }

    public void limpiarYMostrarInventario(Laptop[] inventario, int[] cantreg) {
        pantalla.setText("");

        agregarTextoPantalla("--- INVENTARIO ACTUALIZADO ---\n");
        if (cantreg[0] == 0) {
            agregarTextoPantalla("El inventario esta vacio.\n");
        } else {
            for (int i = 0; i < cantreg[0]; i++) {
                agregarTextoPantalla(inventario[i].obtenerDetalles() + "\n");
            }
        }
        agregarTextoPantalla("-------------------------------\n\n");
    }
}
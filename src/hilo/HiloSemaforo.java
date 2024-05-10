/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilo;

import java.awt.Color;

import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rafael
 */
public class HiloSemaforo implements Runnable {

    private JPanel rojo, verde, amarillo;
    private Color[] colores = {Color.RED, Color.YELLOW, Color.GREEN};
    private int indiceColorActual = 0;
    Thread t;

    public HiloSemaforo(JPanel rojo, JPanel verde, JPanel amarillo) {
        this.rojo = rojo;
        this.verde = verde;
        this.amarillo = amarillo;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                cambiarColorSemaforo(colores[indiceColorActual]);
                TimeUnit.SECONDS.sleep(3); // Espera 3 segundos
                indiceColorActual = (indiceColorActual + 1) % colores.length; // Avanza al siguiente color
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cambiarColorSemaforo(Color color) {
        SwingUtilities.invokeLater(() -> {
            rojo.setBackground(Color.GRAY); // Apaga el panel rojo
            verde.setBackground(Color.GRAY); // Apaga el panel verde
            amarillo.setBackground(Color.GRAY); // Apaga el panel amarillo
            
            // Enciende el panel correspondiente al color actual
            if (color.equals(Color.RED)) {
                rojo.setBackground(Color.RED);
            } else if (color.equals(Color.YELLOW)) {
                amarillo.setBackground(Color.YELLOW);
            } else if (color.equals(Color.GREEN)) {
                verde.setBackground(Color.GREEN);
            }
        });
    }
}
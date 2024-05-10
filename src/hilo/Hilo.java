/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilo;

import javax.swing.JLabel;

/**
 *
 * @author Rafael
 */
public class Hilo implements Runnable {

    Thread t;

    JLabel carrito;

    public Hilo(JLabel carrito) {

        this.carrito = carrito;

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        int retardo;
        try {

            retardo = 4;

            carrito.setVisible(true);

            int x = 50;
            int y = 50;
            int paso = 1;
            boolean semaforo = true;
            

            do {
                while (true) {
                    if (x < 500 && y == 50) {
                        x += paso;
                        carrito.setLocation(x, y);
                    } else if (x == 500 && y < 500) {
                        y += paso;
                        carrito.setLocation(x, y);
                    } else if (x > 50 && y == 500) {
                        x -= paso;
                        carrito.setLocation(x, y);
                    } else if (x == 50 && y > 50) {
                        y -= paso;
                        carrito.setLocation(x, y);
                    }
                    Thread.sleep(retardo);
                }
            } while (semaforo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

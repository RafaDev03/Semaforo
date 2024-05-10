/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilo;

import javax.swing.JLabel;

public class Hilo implements Runnable {
    Thread t;
    JLabel carrito;
    private volatile boolean puedeAvanzar = true;

    public Hilo(JLabel carrito) {
        this.carrito = carrito;
        t = new Thread(this);
        t.start();
    }

    public void setPuedeAvanzar(boolean estado) {
        puedeAvanzar = estado;
    }

    @Override
    public void run() {
        int retardo = 4;
        try {
            carrito.setVisible(true);
            int x = 50, y = 50;
            int paso = 1;

            while (true) {
                if (puedeAvanzar) {
                    if (x < 500 && y == 50) {
                        x += paso;
                    } else if (x == 500 && y < 500) {
                        y += paso;
                    } else if (x > 50 && y == 500) {
                        x -= paso;
                    } else if (x == 50 && y > 50) {
                        y -= paso;
                    }
                    carrito.setLocation(x, y);
                }
                Thread.sleep(retardo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
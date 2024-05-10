package hilo;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HiloSemaforo implements Runnable {
    private JPanel rojo1, verde1, amarillo1, rojo2, verde2, amarillo2, rojo3, verde3, amarillo3, rojo4, verde4, amarillo4;
    private Color[] colores = {Color.RED, Color.YELLOW, Color.GREEN};
    private int indiceColorActual = 0;
    Thread t;
    private Hilo controlCarrito;

    public HiloSemaforo(JPanel rojo1, JPanel verde1, JPanel amarillo1, JPanel rojo2, JPanel verde2, JPanel amarillo2,
                        JPanel rojo3, JPanel verde3, JPanel amarillo3, JPanel rojo4, JPanel verde4, JPanel amarillo4,
                        Hilo controlCarrito) {
        this.rojo1 = rojo1; this.verde1 = verde1; this.amarillo1 = amarillo1;
        this.rojo2 = rojo2; this.verde2 = verde2; this.amarillo2 = amarillo2;
        this.rojo3 = rojo3; this.verde3 = verde3; this.amarillo3 = amarillo3;
        this.rojo4 = rojo4; this.verde4 = verde4; this.amarillo4 = amarillo4;
        this.controlCarrito = controlCarrito;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Color colorActual = colores[indiceColorActual];
                cambiarColorSemaforo(colorActual);
                TimeUnit.SECONDS.sleep(3); // Espera 3 segundos
                indiceColorActual = (indiceColorActual + 1) % colores.length; // Avanza al siguiente color
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cambiarColorSemaforo(Color color) {
        SwingUtilities.invokeLater(() -> {
            // Apagar todos los semáforos
            apagarSemaforos();

            // Encender el semáforo correspondiente en todas las ubicaciones
            if (color.equals(Color.RED)) {
                rojo1.setBackground(Color.RED); rojo2.setBackground(Color.RED);
                rojo3.setBackground(Color.RED); rojo4.setBackground(Color.RED);
            } else if (color.equals(Color.YELLOW)) {
                amarillo1.setBackground(Color.YELLOW); amarillo2.setBackground(Color.YELLOW);
                amarillo3.setBackground(Color.YELLOW); amarillo4.setBackground(Color.YELLOW);
            } else if (color.equals(Color.GREEN)) {
                verde1.setBackground(Color.GREEN); verde2.setBackground(Color.GREEN);
                verde3.setBackground(Color.GREEN); verde4.setBackground(Color.GREEN);
            }

            // Determinar si el carro puede avanzar basado en el semáforo más cercano
            controlCarrito.setPuedeAvanzar(color != Color.RED);
        });
    }

    private void apagarSemaforos() {
        rojo1.setBackground(Color.GRAY); verde1.setBackground(Color.GRAY); amarillo1.setBackground(Color.GRAY);
        rojo2.setBackground(Color.GRAY); verde2.setBackground(Color.GRAY); amarillo2.setBackground(Color.GRAY);
        rojo3.setBackground(Color.GRAY); verde3.setBackground(Color.GRAY); amarillo3.setBackground(Color.GRAY);
        rojo4.setBackground(Color.GRAY); verde4.setBackground(Color.GRAY); amarillo4.setBackground(Color.GRAY);
    }
}
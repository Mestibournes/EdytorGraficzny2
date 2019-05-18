import java.awt.*;

/**
 * Glowna klasa programu
 * Program umozliwia rysowanie prostych figur na ekranie i ich edycje: zmienianie ich polozenia, wielkosci i koloru. Wyswietla parametry narysowanych figur.
 *
 * @author Janusz Twardak
 * @date May 2019
 * @location Piława Dolna
 */


public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            /**
             * Glowna klasa, tworzy okno programu.
             */

            @Override
            public void run() {
                new MyFrame();
            }
        });
    }
}


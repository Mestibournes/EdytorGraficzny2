import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadajaca za Przycisk ustawiajacy aktualny tryb programu na rysowanie wielokatow
 */


public class BtnPolygon extends JButton implements ActionListener {
    private int n;
    private MyFrame frame;


    /**
     * Konstruktor przycisku do rysowania wielokatow
     */


    BtnPolygon(MyFrame inframe) {
        super("Polygon");
        frame = inframe;
        addActionListener(this);
    }


    @Override
    /**
     * Ustala status rysowanej figury na wielokat, pobiera od uzytkownika liczbe wierzcholkow wielokata, przeksztalca ja na inta i podaje do funkcji setN
     */

    public void actionPerformed(ActionEvent e) {
        frame.getMyPanel().pointsClicked = 0;
        String points = JOptionPane.showInputDialog(null, "Podaj liczbe wierzcholkow:");
        try {
            n = Integer.parseInt(points);
            frame.getMyPanel().setN(n);
            frame.getMyPanel().setToDrawPolygon();
        } catch (NumberFormatException f) {
            System.out.println("ZLA LICZBA");
        }


    }
}

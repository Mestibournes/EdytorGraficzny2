import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Klasa odpowiadajaca za Przycisk ustawiajacy aktualny tryb programu na tryb zmiany koloru narysowanych figur
 */

public class BtnColorMode extends JButton implements ActionListener {
    private MyFrame frame;


    /**
     * Konstruktor przycisku do czyszczenia panelu
     */


    BtnColorMode(MyFrame inframe) {
        super("Color Mode");
        frame = inframe;
        addActionListener(this);
    }


    /**
     * Metoda zmienia tryb programu na zmiane koloru narysowanych figur
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getMyPanel().setToColorMode();
        frame.getMyPanel().repaint();
        frame.getMyPanel().setEditedFigureToNull();
    }
}

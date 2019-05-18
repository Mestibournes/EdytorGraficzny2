import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadajaca za Przycisk ustawiajacy aktualny tryb programu na tryb edycji
 */


public class BtnEdit extends JButton implements ActionListener {
    private MyFrame frame;

    /**
     * Konstruktor przycisku do edycji
     */

    BtnEdit(MyFrame inframe) {
        super("Edit Mode");
        frame = inframe;
        addActionListener(this);
    }


    /**
     * Metoda zmienia tryb programu na tryb edycji narysowanych figur
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getMyPanel().setToNull();
        frame.getMyPanel().repaint();
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadajaca za Przycisk uusuwajacy wszystkie narysowane figury
 */


public class BtnClear extends JButton implements ActionListener {
    private MyFrame frame;

    /**
     * Konstruktor przycisku do czyszczenia panelu
     */

    BtnClear(MyFrame inframe) {
        super("Clear");
        frame = inframe;
        addActionListener(this);
    }

    /**
     * Metoda zmienia aktualny tryb programu na tryb edycji, wywoluje metode clearAll i ustala status aktualnie edytowanej figury na NULL
     * @see MyPanel#clearAll();
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getMyPanel().setToNull();
        frame.getMyPanel().clearAll();
        frame.getMyPanel().setEditedFigureToNull();
    }
}
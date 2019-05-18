import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadajaca za Przycisk ustawiajacy aktualny tryb programu na rysowanie elips
 */


public class BtnCircle extends JButton implements ActionListener {
    private MyFrame frame;

    /**
     * Konstruktor przycisku do rysowania kol
     */
    BtnCircle(MyFrame inframe) {
        super("Ellipse");
        frame = inframe;
        addActionListener(this);
    }


    @Override
    /**
     * Ustala status rysowanej figury na wielokat, ustala status edytowanej figury na NULL
     */

    public void actionPerformed(ActionEvent e) {
        frame.getMyPanel().setToDrawCircle();
        frame.getMyPanel().setEditedFigureToNull();
    }
}

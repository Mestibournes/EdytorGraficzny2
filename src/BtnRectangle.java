import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadajaca za Przycisk ustawiajacy aktualny tryb programu na rysowanie prostokatow
 */


public class BtnRectangle extends JButton implements ActionListener {

    private MyFrame frame;


    /**
     * Konstruktor przycisku do rysowania prostokatow
     */


    BtnRectangle(MyFrame inframe) {
        super("Rectangle");
        frame = inframe;
        addActionListener(this);
    }


    @Override

    /**
     * Ustala status rysowanej figury na prostokat, ustala status edytowanej figury na NULL
     */

    public void actionPerformed(ActionEvent e) {
        frame.getMyPanel().setToDrawRectangle();
        frame.getMyPanel().setEditedFigureToNull();


    }
}

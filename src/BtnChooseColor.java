import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadajaca za Przycisk uwybierajacy aktywny kolor
 */


public class BtnChooseColor extends JButton implements ActionListener {
    private MyFrame frame;

    /**
     * Konstruktor przycisku do wyboru koloru
     */
    BtnChooseColor(MyFrame inframe) {
        super("Active color");
        frame = inframe;
        addActionListener(this);
    }


    /**
     * Metoda wywoluje okno zmiany aktywnego koloru
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getMyPanel().setColor();
    }
}

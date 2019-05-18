import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Przycisk zapisywania pliku
 */


public class BtnSave extends JButton implements ActionListener {
    private MyFrame frame;

    /**
     *
     * @param inframe glowna ramka programy
     */
    BtnSave (MyFrame inframe) {
        super("Save");
        frame = inframe;
        addActionListener(this);
    }

    /**
     * funkcja zapisujaca wszystkie narysowane figury w postaci tekstowej do pliku
     */

    public void save(String name) throws FileNotFoundException {
        PrintWriter saver = new PrintWriter(name);
        saver.println(frame.getMyPanel().paintedRectangles.size() + " " + frame.getMyPanel().paintedCircles.size() + " " + frame.getMyPanel().paintedPolygons.size());

        for (int i = 0; i < frame.getMyPanel().paintedRectangles.size(); i++) {
            Rectangle r = (Rectangle) frame.getMyPanel().paintedRectangles.get(i);
            saver.println(r.x + " " + r.y + " " + r.width + " " + r.height + " " + r.currentColor.getRGB());
        }

        for (int i = 0; i < frame.getMyPanel().paintedCircles.size(); i++) {
            Circle r = (Circle) frame.getMyPanel().paintedCircles.get(i);
            saver.println(r.x + " " + r.y + " " + r.width + " " + r.height + " " + r.currentColor.getRGB());
        }

        for (int i = 0; i < frame.getMyPanel().paintedPolygons.size(); i++) {
            MyPolygon r = (MyPolygon) frame.getMyPanel().paintedPolygons.get(i);
            saver.print(r.npoints);
            for (int j = 0; j < r.npoints; j++) {
                saver.print(" " + r.xpoints[j]);
            }
            for (int j = 0; j < r.npoints; j++) {
                saver.print(" " + r.ypoints[j]);
            }
            saver.print(" " + r.currentColor.getRGB());
            saver.println("");

        }

        saver.close();
    }

    /**
     * Po wcisnieciu przycisku ma wykonac sie zapisanie do pliku przedstawione wyzej
     * @param e wcisniecie przycisku
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = JOptionPane.showInputDialog(null, "Podaj nazwe pliku: ");
        if (name!=null){
            try {
                save(name);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
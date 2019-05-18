import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Przycisk otwierania pliku
 */
public class BtnOpen extends JButton implements ActionListener {
    private MyFrame frame;

    /**
     * Konstruktor przycisku
     * @param inframe Glowna ramka programu
     */

    BtnOpen(MyFrame inframe) {
        super("Open");
        frame = inframe;
        addActionListener(this);
    }

    /**
     * Metoda otwierajaca plik, sczytujaca wszystkie narysowane figury i zapisujaca je do aktualnych list obiektow
     */

    public void open(String name) throws FileNotFoundException {
        int nRectangles, nCircles, nPolygons;
        File file = new File(name);
        Scanner reader = new Scanner(file);
        char[] current_line;
        String text = "";

        frame.getMyPanel().clearAll();
        current_line = reader.nextLine().toCharArray();

        int i = 0;


        while (current_line[i] != ' ' && i < current_line.length) {
            text += current_line[i];
            i++;
        }

        nRectangles = Integer.parseInt(text);

        text = "";
        i++;


        while (current_line[i] != ' ' && i < current_line.length) {
            text += current_line[i];
            i++;
        }

        nCircles = Integer.parseInt(text);

        text = "";
        i++;


        while (i < current_line.length && current_line[i] != ' ') {

            text += current_line[i];
            i++;
        }


        nPolygons = Integer.parseInt(text);


        /** Petla rysuje prostokaty z txt */
        for (int j = 0; j < nRectangles; j++) {

            current_line = reader.nextLine().toCharArray();
            i = 0;
            List<Float> parameters = new ArrayList<>();

            for (int k = 0; k < 4; k++) {
                text = "";

                /** Pobiera x, y, width, height prostokata*/
                while (i < current_line.length && current_line[i] != ' ') {
                    text += current_line[i];
                    i++;
                }
                parameters.add(Float.parseFloat(text));
                i++;
                text = "";
            }

            /** Pobiera kolor*/
            while (i < current_line.length && current_line[i] != ' ') {
                text += current_line[i];
                i++;

            }

            frame.getMyPanel().paintedRectangles.add(new Rectangle(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), new Color(Integer.parseInt(text))));
        }


        /** Petla rysuje kola z txt*/
        for (int j = 0; j < nCircles; j++) {

            current_line = reader.nextLine().toCharArray();
            i = 0;
            List<Float> parameters = new ArrayList<>();

            for (int k = 0; k < 4; k++) {
                text = "";

                /** Pobiera x, y, width, height prostokata*/
                while (i < current_line.length && current_line[i] != ' ') {
                    text += current_line[i];
                    i++;
                }
                parameters.add(Float.parseFloat(text));
                i++;
                text = "";
            }

            /** Pobiera kolor*/
            while (i < current_line.length && current_line[i] != ' ') {
                text += current_line[i];
                i++;

            }

            frame.getMyPanel().paintedCircles.add(new Circle(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), new Color(Integer.parseInt(text))));
        }


        for (int j = 0; j < nPolygons; j++) {
            text = "";
            int npoints;
            List<Integer> parameters_polygon = new ArrayList();

            current_line = reader.nextLine().toCharArray();
            i = 0;
            List<Float> parameters = new ArrayList<>();

            while (i < current_line.length && current_line[i] != ' ') {
                text += current_line[i];
                i++;
            }
            i++;
            npoints = Integer.parseInt(text);


            for (int k = 0; k < 2 * npoints; k++) {

                text = "";


                /** Pobiera parametry wielokata */
                while (i < current_line.length && current_line[i] != ' ') {

                    text += current_line[i];
                    i++;
                }
                parameters_polygon.add(Integer.parseInt(text));

                i++;
                text = "";
            }

            /** Pobiera kolor*/
            while (i < current_line.length && current_line[i] != ' ') {
                text += current_line[i];
                i++;

            }
            List<Integer> xpoints = new ArrayList<>();
            List<Integer> ypoints = new ArrayList<>();

            for (int k = 0; k < npoints; k++) {
                xpoints.add((parameters_polygon.get(k)));
                ypoints.add(parameters_polygon.get(npoints + k));
            }


            frame.getMyPanel().paintedPolygons.add(new MyPolygon(npoints, xpoints, ypoints, new Color(Integer.parseInt(text))));
        }


    }

    /**
     * Akcja wykonywana po wcisnieciu przycisku
     * @param e Wcisniecie przycisku
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog(null, "Podaj nazwe pliku: ");
        if (name != null) {
            try {
                open(name);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }

    }
}
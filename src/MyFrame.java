import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiadajaca za stworzenie okna z jego komponentami
 */

public class MyFrame extends JFrame {

    private MyPanel panel;

    /**
     * Konstruktor klasy MyFrame, odpowiada za ustawienia ramki
     */

    public MyFrame() {


        var content = getContentPane();
        content.setLayout(new BorderLayout());


        // - - - - - - - - - - CENTER - - - - - - - - -

        panel = new MyPanel(this);
        content.add(panel, BorderLayout.CENTER);


        // - - - - - - - - - - GORA - - - - - - - - -
        /**
         * Funkcje okna zwarte w BorderLayout.NORTH. Stworzenie przyciskow, dodanie ich do jednego JPanela i dodanie ich do standardowej zawartosci okna
         */
        JPanel controls = new JPanel();

        BtnEdit btnEdit = new BtnEdit(this);
        BtnColorMode btnColorMode = new BtnColorMode(this);
        BtnChooseColor btnChooseColor = new BtnChooseColor(this);
        BtnCircle btnCircle = new BtnCircle(this);
        BtnRectangle btnRectangle = new BtnRectangle(this);
        BtnPolygon btnPolygon = new BtnPolygon(this);
        BtnClear btnClear = new BtnClear(this);

        controls.add(btnEdit);
        controls.add(btnColorMode);
        controls.add(btnChooseColor);
        controls.add(btnCircle);
        controls.add(btnRectangle);
        controls.add(btnPolygon);
        controls.add(btnClear);

        content.add(controls, BorderLayout.NORTH);

        // - - - - - - - - - - - PRAWO - - - - - -  - - - - - -

        JPanel fileOptions = new JPanel(new GridLayout(20,1));
        BtnSave btnSave = new BtnSave(this);
        BtnOpen btnOpen = new BtnOpen(this);

        fileOptions.add(btnSave);
        fileOptions.add(btnOpen);

        content.add(fileOptions, BorderLayout.EAST);


        /**
         * Ustawienia odpowiedzialne za menubar, przyciski w menubarze i ich funkcje.
         */

        var menubar = new JMenuBar();
        var fileMenu = new JMenu("File");

        var menuInfo = new JMenuItem("Info");
        menuInfo.setToolTipText("Informations about program");
        menuInfo.addActionListener((event) -> JOptionPane.showMessageDialog(this,
                "2D Graphics Editor \nAuthor: Janusz Twardak\n\nProgram for drawing and editing simple 2D figures"));


        var menuExit = new JMenuItem("Exit");
        menuExit.setToolTipText("Exits application");
        menuExit.addActionListener((event) -> System.exit(0));

        fileMenu.add(menuInfo);
        fileMenu.add(menuExit);
        menubar.add(fileMenu);
        setJMenuBar(menubar);


        /**
         * Ustawienia okna - tytuł, rozmiar, ikona, polozenie, przycisk wyjscia, rozszerzanie.
         */


        setTitle("2D Graphics Editor");
        var appIcon = new ImageIcon("src/resources/appIcon.png");
        setIconImage(appIcon.getImage());
        setSize(1000, 1000);
        setResizable(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Funkcja zwracajaca panel klasy MyFrame
     * @return panel klasy MyFrame
     */

    public MyPanel getMyPanel() {
        return panel;
    }

}
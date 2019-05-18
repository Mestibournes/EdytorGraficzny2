import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa definiujaca Panel sluzacy do rysowania
 */


public class MyPanel extends JPanel {
    public int npoints, pointsClicked, ID;
    boolean finishDrawing = false;
    /**
     * @param hitPolygon zmienna przechowujaca aktualnie edytowany wielokat
     * @param hitCircle zmienna przechowujaca aktualnie edytowana elipse
     * @param chosenColor zmienna przechowujaca aktualnie wybrany kolor
     * @param Modes enum zawierajacy zbior mozliwych statusow programu (odpowiednio rysowanie elipsy, prostokata, wielokata, edycja i zmiana koloru)
     * @param Figures enum zawierajacy zbior mozliwych do narysowania figur i dodatkowa, bazowa
     * @param currentMode status aktualnie wybranej funkcji programu
     * @param figuresColor kolor aktualnie edytowanej figury
     * @param EditedFigure rodzaj aktualnie edytowanej figury
     */

    List<Integer> xpoints = new ArrayList<>();
    List<Integer> ypoints = new ArrayList<>();
    Rectangle hitRectangle;
    MyPolygon hitPolygon;
    Circle hitCircle;
    /**
     * @param x zmienna przechowujaca wspolrzedna x wcisniecia przycisku myszy
     * @param y zmienna przechowujaca wspolrzedna y wcisniecia przycisku myszy
     * @param x2 zmienna przechowujaca wspolrzedna x puszczenia przycisku myszy
     * @param y2 zmienna przechowujaca wspolrzedna y puszczenia przycisku myszy
     * @param currentX zmienna przechowujaca wspolrzedna x aktualnie edytowanej figury
     * @param currentY zmienna przechowujaca wspolrzedna y aktualnie edytowanej figury
     * @param paintedRectangles lista obiektow przechowujaca narysowane prostokaty
     * @param paintedCircles lista obiektow przechowujaca narysowane elipsy
     * @param paintedPolygons lista obiektow przechowujaca narysowane wielokaty
     * @param finishDrawing zmienna przechowujaca status rysowania figury. Jesli jest ustawiona na true figura zostala narysowana i moze zostac dodana do listy narysowanych figur
     * @param npoints zmienna przechowujaca liczbe wierzcholkow rysowanego wielokata
     * @param pointsClicked zmienna przechowujaca liczbe wybranych wierzcholkow rysowanego wielokata
     */

    private float x, y, x2, y2, currentX, currentY;
    public List<Object> paintedRectangles = new ArrayList<Object>();
    public List<Object> paintedCircles = new ArrayList<Object>();
    public List<Object> paintedPolygons = new ArrayList<Object>();
    private MyFrame frame;
    private Color chosenColor = Color.BLACK;
    private Modes currentMode = Modes.NULL;
    private Color figuresColor;
    private Figures EditedFigure = Figures.NULL;

    /**
     * Konstruktor klasy MyPanel. Ustala podstawowe funkcje panelu, dodaje sluchaczy myszy.
     */
    public MyPanel(MyFrame inframe) {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        MyMouseListener mouseListener = new MyMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addMouseWheelListener(new ScaleHandler());
        frame = inframe;
    }

    /**
     * Ustala liczbe wierzcholkow rysowanego wielokata.
     *
     * @input Liczba wierzcholkow wielokata podana przez uzytkownika w klasie BtnPolygon
     * @see BtnPolygon
     */

    public void setN(int input) {
        npoints = input;
    }

    /**
     * Zapisuje do odpowiednich zmiennych parametry aktualnie edytowanego prostokata i ustala status aktualnie edytowanej figury na prostokat
     *
     * @param i numer indeksu aktualnie edytowanego prostokata w liscie narysowanych prostokatow.
     */

    public void setRectangle(int i) {

        hitRectangle = (Rectangle) paintedRectangles.get(i);
        currentX = hitRectangle.x;
        currentY = hitRectangle.y;
        figuresColor = hitRectangle.currentColor;
        EditedFigure = Figures.RECTANGLE;
    }

    /**
     * Zapisuje do odpowiednich zmiennych parametry aktualnie edytowanej elipsy i ustala status aktualnie edytowanej figury na elipse
     *
     * @param i numer indeksu aktualnie edytowanej elipsy w liscie narysowanych elips.
     */

    public void setCircle(int i) {

        hitCircle = (Circle) paintedCircles.get(i);
        currentX = hitCircle.x;
        currentY = hitCircle.y;
        figuresColor = hitCircle.currentColor;
        EditedFigure = Figures.CIRCLE;
    }

    /**
     * Zapisuje do odpowiednich zmiennych parametry aktualnie edytowanego wielokata i ustala status aktualnie edytowanej figury na wielokat
     *
     * @param i numer indeksu aktualnie edytowanego wielokata w liscie narysowanych wielokatow.
     */

    public void setPolygon(int i) {

        hitPolygon = (MyPolygon) paintedPolygons.get(i);
        currentX = hitPolygon.xpoints[0];
        currentY = hitPolygon.ypoints[0];
        figuresColor = hitPolygon.currentColor;
        EditedFigure = Figures.POLYGON;
        ID = i;
    }

    /**
     * Zmienia status aktualnie edytowanej figury na NULL
     */

    public void setEditedFigureToNull() {
        EditedFigure = Figures.NULL;
        //repaint();
    }

    /**
     * Zmienia status aktualnej funkcji programu na rysowane prostokata
     */

    public void setToDrawRectangle() {

        currentMode = Modes.RECTANGLE;
    }

    /**
     * Zmienia status aktualnej funkcji programu na rysowane elipsy
     */

    public void setToDrawCircle() {

        currentMode = Modes.CIRCLE;
    }

    /**
     * Zmienia status aktualnej funkcji programu na rysowane wielokata
     */

    public void setToDrawPolygon() {
        pointsClicked = 0;
        currentMode = Modes.POLYGON;
    }

    /**
     * Zmienia status aktualnej funkcji programu na tryb edycji narysowanych figur
     */

    public void setToNull() {
        currentMode = Modes.NULL;
    }

    /**
     * Zmienia status aktualnej funkcji programu na tryb zmiany koloru narysowanych figur
     */

    public void setToColorMode() {
        currentMode = Modes.COLORMODE;
    }

    /**
     * Zmienia i zapisuje do zmiennej aktualnie wybrany kolor na wybrany przez uzytkownika za pomoca metody JColorChooser
     */

    public void setColor() {
        chosenColor = JColorChooser.showDialog(null, "Wybierz", Color.black);
    }

    /**
     * Ustala wspolrzedne x, y wcisniecia przycisku myszy
     *
     * @param inx wspolrzedna x wcisniecia przycisku myszy
     * @param iny wspolrzedne y wcisniecia przycisku myszy
     */

    public void setStartPoints(int inx, int iny) {
        x = inx;
        y = iny;
    }

    /**
     * Ustala wspolrzedne x, y polozenia myszy podczas przeciagania i puszczenia przycisku
     *
     * @param inx wspolrzedna x polozenia myszy podczas przeciagania i puszczenia przycisku
     * @param iny wspolrzedne y polozenia myszy podczas przeciagania i puszczenia przycisku.
     */

    public void setEndPoints(int inx, int iny) {
        x2 = inx;
        y2 = iny;
    }

    /**
     * Podstawowe funkcje rysowania na Panelu.
     *
     * @see MyPanel#drawCurrent(Graphics) Umozliwia plynne przedstawianie aktualnie rysowanych figur
     * @see MyPanel#drawFigure(Graphics) Dodaje rysowane figury do odpowiednich list narysowanych figur
     * @see MyPanel#redraw(Graphics) Rysuje wszystkie narysowane obiekty zapisane w listach
     * @see MyPanel#drawText(Graphics) Rysuje na ekranie tekst o informacjach dotyczacych edytowanej figury (jesli w trybie edycji)
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawCurrent(g);
        drawFigure(g);
        redraw(g);
        g2d.setPaint(Color.BLACK);
        drawText(g);
        finishDrawing = false;
    }

    /**
     * Rysuje na ekranie tekst o informacjach dotyczacych edytowanej figury (jesli w trybie edycji)
     */

    public void drawText(Graphics g) {
        Graphics g2d = g;


        if (currentMode.equals(Modes.COLORMODE)) {
            g.drawString("In color mode", 5, 10);
            g.drawString("Click a figure to change it's", 5, 25);
            g.drawString("color to active one", 5, 38);
        } else if (EditedFigure.equals(Figures.RECTANGLE)) {
            g.drawString("In editing mode", 5, 10);
            g.drawString("x: " + currentX + " y: " + currentY, 5, 25);
            g.drawString("Rectangle's color: " + figuresColor.getRed() + " G:" + figuresColor.getGreen() + " B:" + figuresColor.getBlue(), 5, 40);
        } else if (EditedFigure.equals(Figures.CIRCLE)) {
            g.drawString("In editing mode", 5, 10);
            g.drawString("x: " + currentX + " y: " + currentY, 5, 25);
            g.drawString("Circle's color: R:" + figuresColor.getRed() + " G:" + figuresColor.getGreen() + " B:" + figuresColor.getBlue(), 5, 40);
        } else if (EditedFigure.equals(Figures.POLYGON)) {
            g.drawString("In editing mode", 5, 10);
            g.drawString("x: " + currentX + " y: " + currentY, 5, 25);
            g.drawString("Polygon's color: R:" + figuresColor.getRed() + " G:" + figuresColor.getGreen() + " B:" + figuresColor.getBlue(), 5, 40);
        }
    }

    /**
     * Dodaje rysowane na biezaco figury do odpowiednich list narysowanych figur
     */

    public void drawFigure(Graphics g) {

        if (currentMode.equals(Modes.CIRCLE) && (finishDrawing == true)) {
            paintedCircles.add(new Circle(Math.min(x, x2), Math.min(y, y2), Math.abs(x - x2), Math.abs(y - y2), chosenColor));
        } else if (currentMode.equals(Modes.RECTANGLE) && (finishDrawing == true)) {
            paintedRectangles.add(new Rectangle(Math.min(x, x2), Math.min(y, y2), Math.abs(x - x2), Math.abs(y - y2), chosenColor));
        } else if (currentMode.equals(Modes.POLYGON)) {

            if (pointsClicked >= npoints) {
                paintedPolygons.add(new MyPolygon(npoints, xpoints, ypoints, chosenColor));
                xpoints = new ArrayList<>();
                ypoints = new ArrayList<>();
                npoints = 0;
                repaint();
                setToNull();
            }
        }


    }

    /**
     * Umozliwia plynne przedstawianie aktualnie rysowanych figur
     */

    public void drawCurrent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        if (currentMode.equals(Modes.CIRCLE)) {
            Circle current = new Circle(Math.min(x, x2), Math.min(y, y2), Math.abs(x - x2), Math.abs(y - y2), chosenColor);
            g2d.setPaint(current.currentColor);
            g2d.fill(current);

        } else if (currentMode.equals(Modes.RECTANGLE)) {
            Rectangle current = new Rectangle(Math.min(x, x2), Math.min(y, y2), Math.abs(x - x2), Math.abs(y - y2), chosenColor);
            g2d.setPaint(current.currentColor);
            g2d.fill(current);

        }
    }

    /**
     * Rysuje wszystkie narysowane obiekty zapisane w listach
     */

    public void redraw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < paintedCircles.size(); i++) {
            Circle t = (Circle) paintedCircles.get(i);
            g2d.setPaint(t.currentColor);
            g2d.fill(t);

        }

        for (int i = 0; i < paintedRectangles.size(); i++) {
            Rectangle r = (Rectangle) paintedRectangles.get(i);
            g2d.setPaint(r.currentColor);
            g2d.fill(r);
        }

        for (int i = 0; i < paintedPolygons.size(); i++) {
            MyPolygon r = (MyPolygon) paintedPolygons.get(i);
            g2d.setPaint(r.currentColor);
            g2d.fill(r);
        }
    }

    /**
     * Metoda czyszczaca wszystkie narysowane figury z panelu (czysci wszystkie listy narysowanych figur i odswieza widok narysowanych figur)
     *
     * @see BtnClear
     */

    public void clearAll() {
        paintedRectangles = new ArrayList<>();
        paintedCircles = new ArrayList<>();
        paintedPolygons = new ArrayList<>();
        repaint();
        finishDrawing = false;
    }

    public enum Modes {CIRCLE, RECTANGLE, POLYGON, NULL, COLORMODE}

    public enum Figures {CIRCLE, RECTANGLE, POLYGON, NULL}

    /**
     * Klasa odpowiadajaca za funkcje myszy - nacisniecie, przytrzymanie, puszczenie, klikniecie
     */

    public class MyMouseListener extends MouseAdapter {

        /**
         * Metoda klikniecia myszy. Wykonuje swoje funkcje tylko jesli aktualnie program jest w trybie edycji. Umozliwia wtedy zmiane koloru kliknietej figury na aktywny. Funkcja isHit zwraca true jezeli kliknieto w obszar figury.
         *
         * @see Rectangle#isHit(float, float)
         * @see Circle#isHit(float, float)
         * @see MyPolygon#isHit(float, float)
         */
        public void mouseClicked(MouseEvent e) {

            if (currentMode.equals(Modes.COLORMODE)) {

                for (int i = 0; i < paintedRectangles.size(); i++) {
                    Rectangle p = (Rectangle) paintedRectangles.get(i);

                    if (p.isHit(x, y)) {

                        p.currentColor = chosenColor;
                        paintedRectangles.set(i, p);
                    }
                }


                for (int i = 0; i < paintedCircles.size(); i++) {
                    Circle p = (Circle) paintedCircles.get(i);

                    if (p.isHit(x, y)) {

                        p.currentColor = chosenColor;
                        paintedCircles.set(i, p);
                    }

                }

                for (int i = 0; i < paintedPolygons.size(); i++) {
                    MyPolygon p = (MyPolygon) paintedPolygons.get(i);

                    if (p.isHit(x, y)) {

                        p.currentColor = chosenColor;
                        paintedPolygons.set(i, p);
                    }
                }
                repaint();
            }
        }

        /**
         * Metoda wcisniecia przycisku myszy. Zapisuje punkty wcisniecia przycisku,
         * Jezeli w trybie edycji, wcisniecie konkretnej figury ustawia ja na aktualna aby pozniej wyswietlic jej parametry na ekranie.
         * Jezeli w trybie rysowania wielokatow, wcisniecie oznacza ustalenie wierzcholku rysowanego wielokata i dodanie jego wspolrzednych do odpowiednich list.
         * Zwieksza sie tez licznik aktualnie narysowanych wierzcholkow o jeden.
         */

        public void mousePressed(MouseEvent e) {
            setStartPoints(e.getX(), e.getY());
            finishDrawing = false;
            int licznik_pomoc = 0;

            if (currentMode.equals(Modes.NULL)) {
                for (int i = 0; i < paintedRectangles.size(); i++) {
                    Rectangle p = (Rectangle) paintedRectangles.get(i);

                    if (p.isHit(x, y)) {
                        setRectangle(i);
                    }
                }

                for (int i = 0; i < paintedCircles.size(); i++) {
                    Circle p = (Circle) paintedCircles.get(i);

                    if (p.isHit(x, y)) {
                        setCircle(i);
                    }
                }
                for (int i = 0; i < paintedPolygons.size(); i++) {
                    MyPolygon p = (MyPolygon) paintedPolygons.get(i);


                    if (p.isHit(e.getX(), e.getY())) {
                        licznik_pomoc++;
                        setPolygon(i);
                    }
                }
            } else if (currentMode.equals(Modes.POLYGON)) {


                if (pointsClicked < npoints) {

                    xpoints.add(e.getX());
                    ypoints.add(e.getY());
                    pointsClicked++;
                }


            }

        }

        /**
         * Metoda przeciagania wcisnietego przycisku myszy. Jezeli wcisnieto na jakiejs figurze, jest ona przesuwana po panelu za pomoca funkcji doMove(e).
         * Wywoluje funkcje setEndPoints ustalajac aktualne polozenie kursora.
         *
         * @see MyMouseListener#doMove2(MouseEvent)
         */

        public void mouseDragged(MouseEvent e) {
            finishDrawing = false;
            setEndPoints(e.getX(), e.getY());
            if (currentMode.equals(Modes.NULL)) {
                doMove2(e);
            }

            repaint();
        }


        public void mouseReleased(MouseEvent e) {
            finishDrawing = true;
            setEndPoints(e.getX(), e.getY());

            repaint();
        }

        /**
         * Jezeli w trybie edycji, wcisniecie na figure umozliwia jej przesuwanie podczas przytrzymania przycisku myszy az do jej puszczenia.
         */


        private void doMove2(MouseEvent e) {
            float dx = e.getX() - x;
            float dy = e.getY() - y;

            if (currentMode.equals(Modes.NULL)) {

                if (EditedFigure == Figures.RECTANGLE) {
                    hitRectangle.addX(dx);
                    hitRectangle.addY(dy);
                    repaint();
                    x += dx;
                    y += dy;
                }
                else if (EditedFigure == Figures.CIRCLE) {
                    hitCircle.addX(dx);
                    hitCircle.addY(dy);
                    repaint();
                    x += dx;
                    y += dy;

                }

               else if (EditedFigure == Figures.POLYGON) {
                    hitPolygon.addX(dx);
                    hitPolygon.addY(dy);
                    paintedPolygons.set(ID, hitPolygon);

                    x += dx;
                    y += dy;
                    repaint();

                }
            }
        }

    }

    /**
     * Klasa odpowiadajaca za funkcje rolki myszy
     */

    class ScaleHandler implements MouseWheelListener {

        @Override
        /**
         * Metoda uruchamiajaca sie w momencie przesuniecia rolka myszy
         */
        public void mouseWheelMoved(MouseWheelEvent e) {
            doScale(e);
        }

        /**
         * Metoda powiekszajaca wielokat na ktorym aktualnie znajduje sie kursor myszy za pomoca rolki myszy
         */
        private void doScale(MouseWheelEvent e) {

           if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                if (currentMode.equals(Modes.NULL)) {

                    if (EditedFigure == Figures.RECTANGLE) {
                        float amount = e.getWheelRotation() * 5f;
                        hitRectangle.addWidth(amount);
                        hitRectangle.addHeight(amount);
                        repaint();
                    }
                    else if (EditedFigure == Figures.CIRCLE) {
                        float amount = e.getWheelRotation() * 5f;
                        hitCircle.addWidth(amount);
                        hitCircle.addHeight(amount);
                        repaint();

                    }

                    else if (EditedFigure == Figures.POLYGON) {
                        float amount = e.getWheelRotation() * 5f;
                        hitPolygon.resize((int) amount);
                        repaint();

                    }
                }


            }
        }
    }
}







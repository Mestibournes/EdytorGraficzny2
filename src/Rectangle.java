import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Klasa zawierajaca wlasnosci i metody dotyczace rysowania prostokatow
 */

public class Rectangle extends Rectangle2D.Float {
    /**
     * @param currentColor zmienna zawierajaca kolor prostokata
     */
    public Color currentColor;

    /**
     * Konstruktor klasy, ustala kolor prostokata na kolor otrzymany w argumencie (zapisuje go) oraz tworzy prostokat.
     * @param x polozenie na osi x prostokata
     * @param y polozenie na osi y prostokata
     * @param width szerokosc prostokata
     * @param height wysokosc prostokata
     * @param incolor aktualnie wybrany kolor
     */
    public Rectangle(float x, float y, float width, float height, Color incolor) {
        currentColor = incolor;
        setRect(x, y, width, height);
    }

    /**
     *
     * @param x pozycja na osi x kursora w momencie wcisniecia przycisku myszy
     * @param y pozycja na osi y kursora w momencie wcisniecia przycisku myszy
     * @return zwraca true jesli punkt o wspolrzednych (x, y) znajduje sie w prostokacie
     */
    public boolean isHit(float x, float y) {

        return getBounds2D().contains(x, y);
    }

    /**
     * Zmienia polozenie na osi x figury
     * @param x zmiana polozenia kursora myszy na osi x od momentu wcisniecia przycisku myszy do aktualnego polozenia kursora
     */

    public void addX(float x) {

        this.x += x;
    }

    /**
     * Zmienia polozenie na osi y figury
     * @param y zmiana polozenia kursora myszy na osi y od momentu wcisniecia przycisku myszy do aktualnego polozenia kursora
     */

    public void addY(float y) {

        this.y += y;
    }

    /**
     * Zmienia szerokosc figury
     * @param w wartosc o ktora ma sie zwiekszyc szerokosc figury
     */

    public void addWidth(float w) {

        this.width += w;
    }

    /**
     * Zmienia wysokosc figury
     * @param h wartosc o ktora ma sie zwiekszyc wysokosc figury
     */

    public void addHeight(float h) {

        this.height += h * height/width;
    }
}

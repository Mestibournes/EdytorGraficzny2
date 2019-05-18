
import java.awt.*;
import java.util.List;

/**
 * Klasa zawierajaca wlasnosci i metody dotyczace rysowania wielokatow
 */



public class MyPolygon extends Polygon {
    /**
     * @param currentColor zmienna zawierajaca kolor wielokata
     * @param npoints  zmienna przechowujaca liczbe wierzcholkow wielokata
     */
    public Color currentColor;
    public int npoints;

    /**
     * Konstruktor zapisujacy aktualny kolor jako kolor wielokata oraz otrzymana liczbe jako liczbe wierzcholkow wielokata.
     * Ustala wszystkie wierzcholki wielokata.
     *
     * @param npoints liczba wierzcholkow wielokata
     * @param xpoints lista zawierajaca wszystkie wspolrzedne x wierzcholkow wielokata
     * @param ypoints lista zawierajaca wszystkie wspolrzedne y wieczcholkow wielokata
     * @param incolor zmienna zawierajaca aktywny kolor
     */


    MyPolygon(int npoints, List<Integer> xpoints, List<Integer> ypoints, Color incolor) {
        currentColor = incolor;
        this.npoints = npoints;
        for (int i = 0; i < npoints; i++) {
            addPoint(xpoints.get(i), ypoints.get(i));
        }

    }

    /**
     * Funkcja sprawdzajaca czy punkt (x,y) znajduje sie w wielokacie
     *
     * @param x wspolrzedne x wcisnietego punktu
     * @param y wspolrzedne y wcisnietego punktu
     * @return zwraca true jesli punkt (x,y) znajduje sie w obrzarze wielokata
     */

    public boolean isHit(float x, float y) {
        return this.contains(x, y);
    }

    /**
     * Funkcja przesuwajaca wszystkie wierzcholki wielokata na osi x o wartosc otrzymana jako argument
     *
     * @param x wartosc przesuniecia wierzcholkow na osi x
     */

    public void addX(float x) {
        for (int i = 0; i < this.npoints; i++) {

            this.xpoints[i] += x;
        }

    }

    /**
     * Funkcja przesuwajaca wszystkie wierzcholki wielokata na osi y o wartosc otrzymana jako argument
     *
     * @param y wartosc przesuniecia wierzcholkow na osi y
     */

    public void addY(float y) {

        for (int i = 0; i < this.npoints; i++) {

            this.ypoints[i] += y;
        }
    }

    /**
     * Metoda powiekszajaca wielokat

     */

    public void resize(int d) {

        float x_current, width_current, x_mid = 0, width_start, x_max = xpoints[0], x_min = xpoints[0];
        float y_current, height_current, y_mid = 0, height_start, y_max = ypoints[0], y_min = ypoints[0];
        float x_add, y_add;

        for (int i = 0; i < npoints; i++)
        {
            x_mid += xpoints[i];
            y_mid += ypoints[i];

            if (xpoints[i] > x_max){
                x_max = xpoints[i];
            }
            if (xpoints[i] < x_min) {
                x_min = xpoints[i];
            }
            if (ypoints[i] > y_max){
                y_max = ypoints[i];
            }
            if (ypoints[i] < y_min) {
                y_min = ypoints[i];
            }
        }
        x_mid = x_mid / npoints;
        y_mid = y_mid / npoints;


        width_start = Math.abs(x_max - x_min);
        height_start = Math.abs(y_max - y_min);


        x_add = width_start / height_start * d;
        y_add = d;
        width_current = width_start + x_add;
        height_current = height_start + y_add;




        for (int i=0; i<npoints; i++) {

            x_current = width_current / width_start * (xpoints[i] - x_mid) + x_mid;
            xpoints[i] = (int) x_current;
            y_current = height_current / height_start * (ypoints[i] - y_mid) + y_mid;
            ypoints[i] = (int) y_current;
        }


        }

    }




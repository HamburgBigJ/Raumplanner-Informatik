package cho.info.planner;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
/**
* Eine Schrankwand, die ...
*/
public class Schrankwand extends Moebel {
   
    private ArrayList<Schrank> schraenke;
    private int anzahl;
    /**
    * Erzeuge eine neue Schrankwand mit einer Standardfarbe und Standardgroesse
    * an einer Standardposition.
    */
    public Schrankwand(int a) {
        schraenke = new ArrayList<>();
        setXPosition(40);
        setYPosition(80);
        setFarbe("blau");
        setOrientierung(0);
        setSichtbarkeit(false);
        setBreite(300);
        setTiefe(37);
        anzahl = a;
        for(int i=0; i<anzahl; i++) {
          schraenke.add(new Schrank(i*getBreite()/anzahl, 0, getFarbe(), 0, getBreite()/anzahl, getTiefe()));
        }
    }

    /**
    * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
    */
    protected Shape gibAktuelleFigur() {
        GeneralPath schrankwand = new GeneralPath();
        for(Schrank s : schraenke) {
            schrankwand.append(s.gibAktuelleFigur(), false);
        }
       
        return transformiere(schrankwand);
    }
}
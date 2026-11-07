package cho.info.planner;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;


/**
 * Ein Stuhl, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 */
public class Stuhl extends Moebel
{
    /**
     * Erzeuge einen neuen Stuhl mit einer Standardfarbe und Standardgroesse
     * an einer Standardposition. (Standardkonstruktor)
     */
    public Stuhl(int initX, int initY, String initFarbe) {
        setXPosition(initX);
        setYPosition(initY);
        setFarbe(initFarbe);
        setOrientierung(0);
        setSichtbarkeit(false);
        setBreite(40);
        setTiefe(40);
    }
    
    public Stuhl() {
        setXPosition(10);
        setYPosition(10);
        setFarbe("rot");
        setOrientierung(0);
        setSichtbarkeit(false);
        setBreite(40);
        setTiefe(40);
        zeige();
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte �ber Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    protected Shape gibAktuelleFigur() {
        // einen GeneralPath definieren
        GeneralPath stuhl = new GeneralPath();
        stuhl.moveTo(0 , 0);
        stuhl.lineTo(getBreite(), 0);
        stuhl.lineTo(getBreite()+(getBreite()/20+1), getTiefe());
        stuhl.lineTo(-(getBreite()/20+1), getTiefe());
        stuhl.lineTo(0 , 0);
        // Das ist die Umrandung. Das Stuhl bekommt noch eine Lehne:
        stuhl.moveTo(0 , (getBreite()/10+1));
        stuhl.lineTo(getBreite(), (getBreite()/10+1));    

        return transformiere(stuhl);

    }
}

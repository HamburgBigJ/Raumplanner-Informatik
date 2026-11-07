package cho.info.planner;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;


/**
 * Ein Schrank, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 */
public class Schrank extends Moebel
{
    /**
     * Erzeuge einen neuen Schrank mit einer Standardfarbe und Standardgroesse
     * an einer Standardposition. (Standardkonstruktor)
     */
    public Schrank() {
        setXPosition(200);
        setYPosition(80);
        setFarbe("rot");
        setOrientierung(0);
        setSichtbarkeit(false);
        setBreite(110);
        setTiefe(60);
    }
    
    public Schrank(int x, int y, String f, int o, int b, int t) {
        setXPosition(x);
        setYPosition(y);
        setFarbe(f);
        setOrientierung(o);
        setSichtbarkeit(false);
        setBreite(b);
        setTiefe(t);
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte �ber Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    protected Shape gibAktuelleFigur() {
        // einen GeneralPath definieren
        GeneralPath schrank = new GeneralPath();
        schrank.moveTo(0 , 0);
        schrank.lineTo(getBreite(),0);
        schrank.lineTo(getBreite(),getTiefe());
        schrank.lineTo(0 , getTiefe());
        schrank.lineTo(0 , 0);
        schrank.lineTo(getBreite() , getTiefe());
        schrank.moveTo(0 , getTiefe());
        schrank.lineTo(getBreite() , 0);
           
        // transformieren
        AffineTransform t = new AffineTransform();
        t.translate(getXPosition(), getYPosition());
        Rectangle2D umriss = schrank.getBounds2D();
        t.rotate(Math.toRadians(getOrientierung()),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(schrank);
    }
}

package cho.info.planner;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

/**
 * Ein Tisch, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 */
public class Tisch extends Moebel
{
    /**
     * Erzeuge einen neuen Stuhl mit einer Standardfarbe und Standardgroesse
     * an einer Standardposition. (Standardkonstruktor)
     */
    public Tisch()  {
        setXPosition(150);
        setYPosition(120);
        setFarbe("rot");
        setOrientierung(0);
        setSichtbarkeit(false);
        setBreite(120);
        setTiefe(100);
        zeige();
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte �ber Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    protected Shape gibAktuelleFigur()
    {
        // definieren
        Shape tisch = new Ellipse2D.Double(0 , 0, getBreite(), getTiefe());
        
        // transformieren
        AffineTransform t = new AffineTransform();
        t.translate(getXPosition(), getYPosition());
        Rectangle2D umriss = tisch.getBounds2D();
        t.rotate(Math.toRadians(getOrientierung()),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(tisch);
    }

}

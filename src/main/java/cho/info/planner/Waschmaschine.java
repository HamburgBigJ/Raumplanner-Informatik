package cho.info.planner;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

/**
 * Ein Waschmaschine, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 */
public class Waschmaschine extends Moebel
{  
    /**
     * Erzeuge einen neuen Stuhl mit einer Standardfarbe und Standardgroesse
     * an einer Standardposition. (Standardkonstruktor)
     */
    public Waschmaschine()  {
        setXPosition(150);
        setYPosition(120);
        setFarbe("rot");
        setOrientierung(0);
        setSichtbarkeit(false);
        setBreite(60);
        setTiefe(60);
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
        GeneralPath waschmaschine = new GeneralPath();
        Rectangle2D rahmen = new Rectangle2D.Double(0, 0, getBreite(), getTiefe());
        Shape waschtrommel = new Ellipse2D.Double(getBreite()*0.1 , getTiefe()*0.1, getBreite()*0.8, getTiefe()*0.8);
        Shape waesche = new Ellipse2D.Double(getBreite()*0.4 , getTiefe()*0.4, getBreite()*0.2, getTiefe()*0.2);
        waschmaschine.append(rahmen, false);
        waschmaschine.append(waschtrommel, false);
        waschmaschine.append(waesche, false);
        
        // transformieren
        AffineTransform t = new AffineTransform();
        t.translate(getXPosition(), getYPosition());
        Rectangle2D umriss = waschmaschine.getBounds2D();
        t.rotate(Math.toRadians(getOrientierung()),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(waschmaschine);
    }
}

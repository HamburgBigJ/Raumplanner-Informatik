package cho.info.planner;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;


/**
 * Ein Klavier, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author Claus Albowski
 * @version 2.2  (aug 07)
 */
public class Klavier extends Moebel
{
    /**
     * Erzeuge einen neuen Klavier mit einer Standardfarbe und Standardgroesse
     * an einer Standardposition. (Standardkonstruktor)
     */
    public Klavier() {
        setXPosition(200);
        setYPosition(80);
        setFarbe("rot");
        setOrientierung(0);
        setSichtbarkeit(false);
        setBreite(140);
        setTiefe(60);
        zeige();
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte �ber Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    protected Shape gibAktuelleFigur() {
        // einen GeneralPath definieren
        GeneralPath klavier = new GeneralPath();
        
        Rectangle2D klaviaturkasten = new Rectangle2D.Double(0, 0, getBreite(), getTiefe());
        klavier.append(klaviaturkasten, false);
        
        Rectangle2D tastenkasten = new Rectangle2D.Double(0.1*getBreite(), 0.8*getTiefe(), 0.8*getBreite(), 0.2*getTiefe());
        klavier.append(tastenkasten, false);
        
        
        for (int i=0; i<12; i++){
            Line2D taste = new Line2D.Double(((0.8*getBreite())/12)*i+0.1*getBreite(), 0.8*getTiefe(), ((0.8*getBreite())/12)*i+0.1*getBreite(), getTiefe());
            klavier.append(taste, false);
        }
        
           
        // transformieren
        AffineTransform t = new AffineTransform();
        t.translate(getXPosition(), getYPosition());
        Rectangle2D umriss = klavier.getBounds2D();
        t.rotate(Math.toRadians(getOrientierung()),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(klavier);
    }
}

package cho.info.planner;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
/**
 * Write a description of class Moebel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Moebel
{
    private int xPosition;
    private int yPosition;
    private int orientierung;
    private String farbe;
    private boolean istSichtbar;
    private int breite;
    private int tiefe;
    
    public void setXPosition(int x){
        xPosition = x;
    }
    
    public int getXPosition() {
        return xPosition;
    }
    
    public void setYPosition(int y){
        yPosition = y;
    }
    
    public int getYPosition() {
        return yPosition;
    }
    
    public void setOrientierung(int o){
        orientierung = o;
    }
    
    public int getOrientierung() {
        return orientierung;
    }
    
    public void setFarbe(String f){
        farbe = f;
    }
    
    public String getFarbe() {
        return farbe;
    }    
    
    public void setSichtbarkeit(boolean s){
        istSichtbar = s;
    }
    
    public boolean getSichtbarkeit() {
        return istSichtbar;
    }
    
    public void setBreite(int b){
        breite = b;
    }
    
    public int getBreite() {
        return breite;
    }
    
    public void setTiefe(int t){
        tiefe = t;
    }
    
    public int getTiefe() {
        return tiefe;
    }
    
    
    protected abstract Shape gibAktuelleFigur();
    
    protected Shape transformiere(Shape figur) {
        // transformieren
        AffineTransform t = new AffineTransform();
        t.translate(getXPosition(), getYPosition());
        Rectangle2D umriss = figur.getBounds2D();
        t.rotate(Math.toRadians(getOrientierung()),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(figur);
    }
    
    /**
     * Mache dieses Objekt sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
    public void zeige() {
        if (!istSichtbar) {
            istSichtbar = true;
            zeichne();
        }
    }
    
    public void demo() {
        for(int i=0; i<10; i++) {
            if (i==5) {
                //mache etwas
                break;
            }
        }
    }
    
    /**
     * Mache dieses Objekt unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
     */
    public void verberge() {
        loesche(); // "tue nichts" wird in loesche() abgefangen.
        istSichtbar = false;
    }
    
    /**
     * Drehe auf den angegebenen Winkel
     */
    public void dreheAuf(int neuerWinkel) {
        loesche();
        orientierung = neuerWinkel;
        zeichne();
    }
    
    public void umWinkelDrehen(int winkel) {
        loesche();
        orientierung += winkel;
        zeichne();
    }
    
    public void dreheUm(int winkel) {
        dreheAuf(winkel + orientierung);
    }
    
    public int gibBreite() {
        return breite;
    }
    
    public void rotieren(){
        for(int i=0; i<360; i++) {
            dreheAuf(i);
        }
    }
    
    public void setPos(int x, int y){
        xPosition = x;
        yPosition = y;
        loesche();
        zeichne();
    }
    
    public void aendereGroe�eUm(double faktor) {
        loesche();
        breite *= faktor;
        tiefe *= faktor;
        zeichne();
    }
    
    public void skalieren(float faktor) {
        loesche();
        breite = (int) Math.round(breite * faktor);
        tiefe *= faktor;
        zeichne();
    }
    
    public void orientierungEinrasten () {
        if (orientierung < 45) {
            orientierung = 0;
        }
        else if (orientierung < 135) {
            orientierung = 90;
        }
        else if (orientierung < 225) {
            orientierung = 180;
        }
        else if (orientierung < 315) {
            orientierung = 270;
        }
        else {
            orientierung = 0;
        }
        loesche();
        zeichne();
    }
    
   
    public void orientierungEinrasten3 () {
        for(int i=45; orientierung<i; i+=90){
            orientierung -= 45;
            loesche();
            zeichne();
        }
    }

   
    public void orientierungEinrasten2 () {
        for(int i=0; i<5; i++){
            if (orientierung < i*90+45) {
                orientierung = i*90;
                loesche();
                zeichne();
                break;
            }         
        }
    }
    
    
    
    public void xPosEinrasten () {
        if (xPosition < 25) {
            xPosition = 0;
        }
        else if (xPosition < 75) {
            xPosition = 50;
        }
        else if (xPosition < 125) {
            xPosition = 100;
        }
        else if (xPosition < 175) {
            xPosition = 150;
        }
        else if (xPosition < 225) {
            xPosition = 200;
        }
        else if (xPosition < 275) {
            xPosition = 250;
        }
        else if (xPosition < 325) {
            xPosition = 300;
        }
        else if (xPosition < 375) {
            xPosition = 350;
        }
        else {
            xPosition = 400;
        }
        loesche();
        zeichne();
    }
    
    public void xPosEinrasten2 () {
        float valueX = ((float) xPosition)/50;
        if(valueX-(int) valueX>0.5) {
            valueX += 1;
        }
        xPosition = ((int) valueX)*50;
        loesche();
        zeichne();
    }
    
    
    
    
    /**
     * Bewege dieses Objekt horizontal um 'entfernung' Bildschirmpunkte.
     */
    public void bewegeHorizontal(int entfernung) {
        loesche();
        xPosition += entfernung;
        zeichne();
    }
    
    public void bewege50nachRechts() {
        loesche();
        xPosition += 50;
        zeichne(); 
    }
    
    
    
    /**
     * Bewege dieses objekt vertikal um 'entfernung' Bildschirmpunkte.
     */
    public void bewegeVertikal(int entfernung) {
        loesche();
        yPosition += entfernung;
        zeichne();
    }
    
    
    /**
     * Aendere die Farbe dieses Objektes in 'neueFarbe'.
     * Gueltige Angaben sind "rot", "gelb", "blau", "gruen",
     * "lila" und "schwarz".
     */
    public void aendereFarbe(String neueFarbe) {
        loesche();
        farbe = neueFarbe;
        zeichne();
    }
    
    /**
     * Zeichne dieses Objekt mit seinen aktuellen Werten auf den Bildschirm.
     */
    private void zeichne() {
        if (istSichtbar) {
            Shape figur = gibAktuelleFigur();
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.zeichne (
              this,           // leinwand kennt das Objekt
              farbe,          // definiert seine Zeichenfarbe
              figur);         // definiert seinen grafischen Aspekt
            leinwand.warte(10);
        }
    }
    
    /**
     * Loesche dieses Objekt vom Bildschirm.
     */
    private void loesche() {
        if (istSichtbar){
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.entferne(this);
        }
    }

}
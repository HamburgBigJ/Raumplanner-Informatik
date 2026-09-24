package cho.info.Furniture;

import cho.info.Leinwand;
import cho.info.register.ObjectRegister;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Furniture {

    public int scaleL;
    public int scaleH;
    public GeneralPath gPath;
    public int xPos;
    public int yPos;
    public int ori;
    public String color;
    public boolean isHidden;
    public int _height;
    public int _length;
    public int height;
    public int length;

    private List<Shape> shapes; // init add shapes

    public Furniture() {
        this.scaleL = 1;
        this.scaleH = 1;
        this.gPath = new GeneralPath();
        this.xPos = 100;
        this.yPos = 100;
        this.ori = 0;
        this.color = "blue";
        this.isHidden = false;
        this._height = 50;
        this._length = 50;
        this.shapes = new ArrayList<>();
    }

    public void Init() {
        updateScale();
        ListShape();
        updateDraw();
    }

    private Shape getFigure() {
        shapes.forEach(shape -> {
            gPath.append(shape, false);
        });

        AffineTransform t = new AffineTransform();
        t.translate(xPos, yPos);
        Rectangle2D umriss = gPath.getBounds2D();
        t.rotate(Math.toRadians(ori),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(gPath);
    }

    public abstract void ListShape();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void zeichne() {
        if (!isHidden) {
            Shape figur = getFigure();
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.zeichne (
                    this,           // leinwand kennt das Objekt
                    color,          // definiert seine Zeichenfarbe
                    figur);         // definiert seinen grafischen Aspekt
            leinwand.warte(10);
        }
    }

    public void remove() {
        if (!isHidden) {
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.entferne(this);
        }
    }

    public void changeColor(String color) {
        remove();
        color = color;
        zeichne();
    }

    public void moveVertical(int dictance) {
        remove();
        xPos += dictance;
        zeichne();
    }

    public void verberge() {
        remove(); // "tue nichts" wird in loesche() abgefangen.
        isHidden = true;
    }

    public void show() {
        if (isHidden) {
            isHidden = false;
            zeichne();
        }
    }

    public void updateScale(int scale) {
        this.scaleH = scale;
        this.scaleL = scale;
    }

    public void updateScale(int sh, int sl) {
        this.scaleH = sh;
        this.scaleL = sl;
    }
    public void updateScale() {
        this.height = _height*scaleH;
        this.length = _length*scaleL;
    }
    public void updateDraw() {
        updateScale();
        remove();
        zeichne();
    }

    public void bewege50NachRechts() {
        xPos+=50;
        updateDraw();
    }

    public void rotate(int winkel) {
        ori+=winkel;
        updateDraw();
    }

    public int gibBreite() {
        return length;
    }

    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
        updateDraw();
    }


    public class DrawUtil {
        private DrawUtil() {

        }
    }

    public void snaptogrid() {
        xPos = Math.round((float) xPos/50)*50;
        yPos = Math.round((float) yPos/50)*50;
        updateDraw();
    }


    public void registerObject() {
        try {
            ObjectRegister.addObject(UUID.randomUUID(), this.getClass());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerObjectUUID(UUID uuid) {
        try {
            ObjectRegister.addObject(uuid, this.getClass());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
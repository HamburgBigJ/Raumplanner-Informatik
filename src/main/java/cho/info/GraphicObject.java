package cho.info;

import cho.info.rendering.Window;
import lombok.Data;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@Data
public abstract class GraphicObject {

    private Window window;
    private Color color = Color.BLUE;
    private int xPos;
    private int yPos;
    private int rotation;
    private boolean isVisible = true;


    public GraphicObject(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    protected abstract Shape createShape();

    public final Shape getShape() {
        Shape shape = createShape();
        Rectangle2D bounds = shape.getBounds2D();
        AffineTransform transform = new AffineTransform();
        transform.translate(xPos, yPos);
        transform.rotate(Math.toRadians(rotation), bounds.getCenterX(), bounds.getCenterY());
        return transform.createTransformedShape(shape);
    }

    public void draw() {
        if (isVisible) {
            if (window == null) throw new NullPointerException();
            Window canvas = window;
            canvas.canvasDraw(this, color, getShape());
            canvas.pause(10);
        }
    }

    public void erase() {
        if (isVisible) {
            if (window == null) throw new NullPointerException();
            window.canvasRemove(this);
        }
    }

    public void show() {
        setVisible(true);
        draw();
    }

    public void hide() {
        erase();
        setVisible(false);
    }

    public void reDraw() {
        erase();
        draw();
    }

    public void setPos(int xPos, int yPos) {
        setXPos(xPos);
        setYPos(yPos);
    }

}

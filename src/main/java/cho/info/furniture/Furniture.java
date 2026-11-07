package cho.info.furniture;

import cho.info.rendering.RoomCanvas;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/** Shared position, dimensions, visibility, and drawing behavior for furniture. */
public abstract class Furniture {
    private int xPosition;
    private int yPosition;
    private int rotation;
    private String color;
    private boolean visible;
    private int width;
    private int depth;

    protected Furniture(int xPosition, int yPosition, String color, int width, int depth) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        this.width = width;
        this.depth = depth;
    }

    // Property setters update the model; movement and appearance operations also redraw it.
    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if (visible) {
            show();
        } else {
            hide();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    /** Creates the furniture outline in local coordinates. */
    protected abstract Shape createShape();

    /** Applies position and rotation around the outline's center. */
    protected final Shape getShape() {
        Shape shape = createShape();
        Rectangle2D bounds = shape.getBounds2D();
        AffineTransform transform = new AffineTransform();
        transform.translate(xPosition, yPosition);
        transform.rotate(Math.toRadians(rotation), bounds.getCenterX(), bounds.getCenterY());
        return transform.createTransformedShape(shape);
    }

    public void show() {
        if (!visible) {
            visible = true;
            draw();
        }
    }

    public void hide() {
        erase();
        visible = false;
    }

    public void rotateTo(int angle) {
        erase();
        rotation = angle;
        draw();
    }

    public void rotateBy(int angle) {
        rotateTo(rotation + angle);
    }

    public void rotateFullCircle() {
        for (int angle = 0; angle < 360; angle++) {
            rotateTo(angle);
        }
    }

    public void setPosition(int xPosition, int yPosition) {
        erase();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        draw();
    }

    /** Scales both dimensions, truncating fractional pixels. */
    public void scaleBy(double factor) {
        erase();
        width = (int) (width * factor);
        depth = (int) (depth * factor);
        draw();
    }

    /** Preserves the original alternative scaling exercise's width rounding. */
    public void scaleWithRoundedWidth(float factor) {
        erase();
        width = Math.round(width * factor);
        depth = (int) (depth * factor);
        draw();
    }

    /** Snaps rotation to a cardinal direction using the original angle thresholds. */
    public void snapRotation() {
        int snappedRotation;
        if (rotation < 45) {
            snappedRotation = 0;
        } else if (rotation < 135) {
            snappedRotation = 90;
        } else if (rotation < 225) {
            snappedRotation = 180;
        } else if (rotation < 315) {
            snappedRotation = 270;
        } else {
            snappedRotation = 0;
        }
        rotateTo(snappedRotation);
    }

    /** Alternative snapping exercise; the upper threshold can snap to 360 degrees. */
    public void snapRotationUsingLoop() {
        for (int direction = 0; direction < 5; direction++) {
            if (rotation < direction * 90 + 45) {
                rotateTo(direction * 90);
                break;
            }
        }
    }

    /** Snaps the horizontal position to the original 50-pixel grid between 0 and 400. */
    public void snapXPosition() {
        int snappedPosition = 400;
        for (int gridPosition = 0; gridPosition < 400; gridPosition += 50) {
            if (xPosition < gridPosition + 25) {
                snappedPosition = gridPosition;
                break;
            }
        }
        setPosition(snappedPosition, yPosition);
    }

    /** Alternative grid exercise; halfway values round down and positions are not clamped. */
    public void snapXPositionWithoutBounds() {
        float gridPosition = (float) xPosition / 50;
        if (gridPosition - (int) gridPosition > 0.5) {
            gridPosition += 1;
        }
        setPosition((int) gridPosition * 50, yPosition);
    }

    public void moveHorizontally(int distance) {
        setPosition(xPosition + distance, yPosition);
    }

    public void moveRightBy50() {
        moveHorizontally(50);
    }

    public void moveVertically(int distance) {
        setPosition(xPosition, yPosition + distance);
    }

    public void changeColor(String color) {
        erase();
        this.color = color;
        draw();
    }

    private void draw() {
        if (visible) {
            RoomCanvas canvas = RoomCanvas.getInstance();
            canvas.draw(this, color, getShape());
            canvas.pause(10);
        }
    }

    private void erase() {
        if (visible) {
            RoomCanvas.getInstance().remove(this);
        }
    }
}

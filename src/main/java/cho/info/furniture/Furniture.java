package cho.info.furniture;

import cho.info.GraphicObject;
import cho.info.Main;
import cho.info.rendering.Window;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Furniture extends GraphicObject {
    private Color color;
    private boolean visible;
    private int width;
    private int depth;

    protected Furniture(int xPosition, int yPosition, Color color, int width, int depth) {
        super(xPosition, yPosition);
        this.color = color;
        this.width = width;
        this.depth = depth;
    }

    public void rotateTo(int angle) {
        erase();
        setRotation(angle);
        draw();
    }

    public void rotateBy(int angle) {
        rotateTo(getRotation() + angle);
    }

    public void rotateFullCircle() {
        for (int angle = 0; angle < 360; angle++) {
            rotateTo(angle);
        }
    }

    public void scaleBy(double factor) {
        erase();
        width = (int) (width * factor);
        depth = (int) (depth * factor);
        draw();
    }

    public void scaleWithRoundedWidth(float factor) {
        erase();
        width = Math.round(width * factor);
        depth = (int) (depth * factor);
        draw();
    }

    public void snapRotationUsingLoop() {
        for (int direction = 0; direction < 5; direction++) {
            if (getRotation() < direction * 90 + 45) {
                rotateTo(direction * 90);
                break;
            }
        }
    }

    public void snapXPosition() {
        int snappedPosition = 400;
        for (int gridPosition = 0; gridPosition < 400; gridPosition += 50) {
            if (getXPos() < gridPosition + 25) {
                snappedPosition = gridPosition;
                break;
            }
        }
        setPos(snappedPosition, getYPos());
    }

    public void snapXPositionWithoutBounds() {
        float gridPosition = (float) getXPos() / 50;
        if (gridPosition - (int) gridPosition > 0.5) {
            gridPosition += 1;
        }
        setPos((int) gridPosition * 50, getYPos());
    }

    public void moveHorizontally(int distance) {
        setPos(getXPos() + distance, getYPos());
    }

    public void moveRightBy50() {
        moveHorizontally(50);
    }

    public void moveVertically(int distance) {
        setPos(getXPos(), getYPos() + distance);
    }

    public void changeColor(Color color) {
        erase();
        this.color = color;
        draw();
    }

}

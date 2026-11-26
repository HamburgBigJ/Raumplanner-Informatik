package cho.info.rendering;

import cho.info.GraphicObject;
import cho.info.backrounds.BackTypeToObj;
import cho.info.backrounds.BackgroundType;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.*;

public class Window {
    //private static Window instance;

    private final JFrame window;
    private final DrawingPanel drawingPanel;
    private final Color backgroundColor;
    private final BufferedImage canvasImage;
    private final Graphics2D graphics;
    private BackgroundType bType;
    private final Map<Object, ColoredShape> shapes = new LinkedHashMap<>();
    private Map<String, GraphicObject> graphicObject = new HashMap<>();

    public Window(String title, int width, int height, Color backgroundColor, BackgroundType bType) {
        this.backgroundColor = backgroundColor;
        canvasImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = canvasImage.createGraphics();
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(width, height));
        window = new JFrame(title);
        window.setContentPane(drawingPanel);
        window.pack();
        this.bType = bType;
        clear();
    }

    public void append(String key, GraphicObject object) {
        graphicObject.put(key, object);
    }

    public void remove(String key) {
        graphicObject.remove(key);
    }

    public void draw() {
        BackTypeToObj.getObject(bType).setWindow(this);
        BackTypeToObj.getObject(bType).draw();
        graphicObject.forEach((key, graphicObject) -> {
            graphicObject.setWindow(this);
            graphicObject.show();
        });

    }

    public void reDraw() {
        BackTypeToObj.getObject(bType).setWindow(this);
        BackTypeToObj.getObject(bType).reDraw();
        graphicObject.forEach((key, graphicObject) -> {
            graphicObject.reDraw();
        });
    }


    public void setVisible(boolean visible) {
        window.setVisible(visible);
    }


    public void canvasDraw(Object owner, Color color, Shape shape) {
        // Redrawn objects move to the front of the drawing order.

        shapes.remove(owner);
        shapes.put(owner, new ColoredShape(shape, color));
        redraw();
    }

    public void canvasRemove(Object owner) {
        shapes.remove(owner);
        redraw();
    }

    public void setDrawingColor(Color color) {
        graphics.setColor(color);
    }

    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private void redraw() {
        clear();
        for (ColoredShape coloredShape : shapes.values()) {
            setDrawingColor(coloredShape.color());
            graphics.draw(coloredShape.shape());
        }
        drawingPanel.repaint();
    }

    private void clear() {
        Color previousColor = graphics.getColor();
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
        graphics.setColor(previousColor);
    }

    private class DrawingPanel extends JPanel {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            graphics.drawImage(canvasImage, 0, 0, null);
        }
    }

    private record ColoredShape(Shape shape, Color color) {
    }
}

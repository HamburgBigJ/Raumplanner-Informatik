package cho.info.rendering;

import cho.info.furniture.Furniture;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Window {
    //private static Window instance;

    private final JFrame window;
    private final DrawingPanel drawingPanel;
    private final Color backgroundColor;
    private final BufferedImage canvasImage;
    private final Graphics2D graphics;
    private final Map<Object, ColoredShape> shapes = new LinkedHashMap<>();
    private final ArrayList<Furniture> furnitures = new ArrayList<>();

    public Window(String title, int width, int height, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        canvasImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = canvasImage.createGraphics();
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(width, height));
        window = new JFrame(title);
        window.setContentPane(drawingPanel);
        window.pack();
        clear();
    }

    /*public static Window getInstance() {
        if (instance == null) {
            instance = new Window("Room Planner", 400, 400, Color.WHITE);
        }
        instance.setVisible(true);
        return instance;
    }*/

    public void append(Furniture furniture) {
        furnitures.add(furniture);
    }

    public void remFurni(Furniture furniture) {
        furnitures.remove(furniture);
    }

    public void setVisible(boolean visible) {
        window.setVisible(visible);
    }

    public void draw() {
        for (Furniture furniture : furnitures) {
            draw(this, furniture.getColor(), furniture.get);
            pause(10);
        }
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

    /** English color names are preferred; original German names remain supported. */
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

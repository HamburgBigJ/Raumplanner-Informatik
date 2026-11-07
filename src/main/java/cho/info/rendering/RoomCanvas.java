package cho.info.rendering;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A shared Swing canvas that draws furniture outlines in insertion order.
 *
 * Adapted from the educational canvas by Bruce Quig, Michael Kölling,
 * Axel Schmolitzky, the Java-MS Groupies, and Uwe Debacher.
 */
public class RoomCanvas {
    private static RoomCanvas instance;

    private final JFrame window;
    private final DrawingPanel drawingPanel;
    private final Color backgroundColor;
    private final BufferedImage canvasImage;
    private final Graphics2D graphics;
    private final Map<Object, ColoredShape> shapes = new LinkedHashMap<>();

    private RoomCanvas(String title, int width, int height, Color backgroundColor) {
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

    public static RoomCanvas getInstance() {
        if (instance == null) {
            instance = new RoomCanvas("Room Planner", 400, 400, Color.WHITE);
        }
        instance.setVisible(true);
        return instance;
    }

    public void setVisible(boolean visible) {
        window.setVisible(visible);
    }

    public void draw(Object owner, String color, Shape shape) {
        // Redrawn objects move to the front of the drawing order.
        shapes.remove(owner);
        shapes.put(owner, new ColoredShape(shape, color));
        redraw();
    }

    public void remove(Object owner) {
        shapes.remove(owner);
        redraw();
    }

    /** English color names are preferred; original German names remain supported. */
    public void setDrawingColor(String colorName) {
        Color color = switch (colorName) {
            case "red", "rot" -> Color.RED;
            case "blue", "blau" -> Color.BLUE;
            case "yellow", "gelb" -> Color.YELLOW;
            case "green", "gruen" -> Color.GREEN;
            case "purple", "lila" -> Color.MAGENTA;
            case "white", "weiss" -> Color.WHITE;
            default -> Color.BLACK;
        };
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

    private record ColoredShape(Shape shape, String color) {
    }
}

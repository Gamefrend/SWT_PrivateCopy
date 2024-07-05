package de.hsrm.mi.swt.view.uikomponente;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class KartonView {
    private int width;
    private int height;
    private Color color;
    private int maxBelastung;
    private int xPosition;
    private Rectangle rectangle;  // Rechteck, das den Karton visualisiert


    public KartonView(int width, int height, Color color, int maxBelastung, int xPosition) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.maxBelastung = maxBelastung;
        this.xPosition = xPosition;
        createRectangle();  // Rechteck erstellen und initialisieren
    }

    /**
     * Methode zum Erstellen eines Rechtecks mit Eigenschaften.
     */
    private void createRectangle() {
        rectangle = new Rectangle(width, height);
        rectangle.setFill(color);  // Setzen der Füllfarbe des Rechtecks
        rectangle.setX(xPosition); // Setzen der X-Position des Rechtecks
    }

    /**
     * Getter-Methode zum Abrufen des Rechtecks.
     *
     * @return Rechteck, das den Karton darstellt
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    // Getter- und Setter-Methoden für die Breite, Höhe, Farbe, max. Belastung und X-Position des Kartons

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        rectangle.setWidth(width);  // Aktualisieren der Breite des Rechtecks
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        rectangle.setHeight(height);  // Aktualisieren der Höhe des Rechtecks
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        rectangle.setFill(color);  // Aktualisieren der Farbe des Rechtecks
    }

    public int getMaxBelastung() {
        return maxBelastung;
    }

    public void setMaxBelastung(int maxBelastung) {
        this.maxBelastung = maxBelastung;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
        rectangle.setX(xPosition);  // Aktualisieren der X-Position des Rechtecks
    }
}


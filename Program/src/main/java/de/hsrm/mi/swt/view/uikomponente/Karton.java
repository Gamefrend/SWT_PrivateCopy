package de.hsrm.mi.swt.view.uikomponente;

import de.hsrm.mi.swt.model.storage.Ware;
import javafx.beans.property.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Karton implements Serializable {
    private transient IntegerProperty width;
    private transient IntegerProperty height;
    private transient ObjectProperty<Color> color;
    private transient IntegerProperty maxBelastung;
    private transient IntegerProperty xPosition;
    private transient ObjectProperty<Ware> ware;
    private transient Rectangle rectangle;  // Rechteck, das den Karton visualisiert

    public Karton(int width, int height, Color color, int maxBelastung, int xPosition, Ware ware) {
        this.width = new SimpleIntegerProperty(width);
        this.height = new SimpleIntegerProperty(height);
        this.color = new SimpleObjectProperty<>(color);
        this.maxBelastung = new SimpleIntegerProperty(maxBelastung);
        this.xPosition = new SimpleIntegerProperty(xPosition);
        this.ware = new SimpleObjectProperty<>(ware);
        createRectangle();  // Rechteck erstellen und initialisieren

        // Bind Rectangle properties to KartonView properties
        this.width.addListener((obs, oldVal, newVal) -> rectangle.setWidth(newVal.doubleValue()));
        this.height.addListener((obs, oldVal, newVal) -> rectangle.setHeight(newVal.doubleValue()));
        this.color.addListener((obs, oldVal, newVal) -> rectangle.setFill(newVal));
        this.xPosition.addListener((obs, oldVal, newVal) -> rectangle.setX(newVal.doubleValue()));
    }

    /**
     * Methode zum Erstellen eines Rechtecks mit Eigenschaften.
     */
    private void createRectangle() {
        rectangle = new Rectangle();
        rectangle.setWidth(getWidth());
        rectangle.setHeight(getHeight());
        rectangle.setFill(getColor());
        rectangle.setX(getXPosition());
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

    public int getWidth() {
        return width.get();
    }

    public void setWidth(int width) {
        this.width.set(width);
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public int getHeight() {
        return height.get();
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public Color getColor() {
        return color.get();
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public int getMaxBelastung() {
        return maxBelastung.get();
    }

    public void setMaxBelastung(int maxBelastung) {
        this.maxBelastung.set(maxBelastung);
    }

    public IntegerProperty maxBelastungProperty() {
        return maxBelastung;
    }

    public int getXPosition() {
        return xPosition.get();
    }

    public void setXPosition(int xPosition) {
        this.xPosition.set(xPosition);
    }

    public IntegerProperty xPositionProperty() {
        return xPosition;
    }

    public Ware getWare() {
        return ware.get();
    }

    public void setWare(Ware ware) {
        this.ware.set(ware);
    }

    public ObjectProperty<Ware> wareProperty() {
        return ware;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(getWidth());
        out.writeInt(getHeight());
        out.writeObject(getColor());
        out.writeInt(getMaxBelastung());
        out.writeInt(getXPosition());
        out.writeObject(getWare());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int width = in.readInt();
        int height = in.readInt();
        Color color = (Color) in.readObject();
        int maxBelastung = in.readInt();
        int xPosition = in.readInt();
        Ware ware = (Ware) in.readObject();

        this.width = new SimpleIntegerProperty(width);
        this.height = new SimpleIntegerProperty(height);
        this.color = new SimpleObjectProperty<>(color);
        this.maxBelastung = new SimpleIntegerProperty(maxBelastung);
        this.xPosition = new SimpleIntegerProperty(xPosition);
        this.ware = new SimpleObjectProperty<>(ware);

        createRectangle();

        // Restore listeners
        this.width.addListener((obs, oldVal, newVal) -> rectangle.setWidth(newVal.doubleValue()));
        this.height.addListener((obs, oldVal, newVal) -> rectangle.setHeight(newVal.doubleValue()));
        this.color.addListener((obs, oldVal, newVal) -> rectangle.setFill(newVal));
        this.xPosition.addListener((obs, oldVal, newVal) -> rectangle.setX(newVal.doubleValue()));
    }
}

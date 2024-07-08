package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.app.StorageShelvesApplication;
import de.hsrm.mi.swt.model.storage.Karton;
import de.hsrm.mi.swt.model.storage.Typ;
import de.hsrm.mi.swt.model.storage.Ware;
import de.hsrm.mi.swt.view.lager.KartonErstellenView;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class KartonErstellenController {
    private KartonErstellenView view;
    private Popup popup;

    StorageShelvesApplication application;

    public KartonErstellenController(StorageShelvesApplication application) {
        this.application = application;
        this.view = new KartonErstellenView();
        this.popup = new Popup();
        this.popup.getContent().add(view);

        initialize();
    }

    private void initialize() {
        view.getCloseButton().setOnAction(e -> {
            clearFields();
            hidePopup();
        });
        view.getCreateButton().setOnAction(e -> createKarton());
    }

    public void showPopup(Window owner) {
        if (!popup.isShowing()) {
            popup.show(owner);
        }
    }

    public void hidePopup() {
        popup.hide();
    }

    private void createKarton() {
        boolean isValid = true;

        resetFieldStyles();

        try {
            String name = validateTextField(view.getNameField(), "Name");
            int anzahl = validateIntegerField(view.getAnzahlField(), "Anzahl");
            int breite = validateIntegerField(view.getBreiteField(), "Breite");
            int hoehe = validateIntegerField(view.getHoeheField(), "H\u00F6he");
            int gewicht = validateIntegerField(view.getGewichtField(), "Gewicht");
            LocalDate mhd = validateDateField(view.getMhdField(), "MHD");

            Typ typ = new Typ(
                    view.getLebensmittelCheck().isSelected(),
                    view.getGiftigCheck().isSelected(),
                    view.getGekuehltCheck().isSelected()
            );

            if (isValid) {
                Ware ware = new Ware(name, hoehe, gewicht, 0, mhd, typ);
                Karton karton = new Karton(gewicht, breite, hoehe, 1000, 0, ware);
                application.getAktuellerRaum().getRegal().getUebrigesInventar().addKarton(karton);
                // Here you would add the karton to your inventory or storage system
                System.out.println("Karton created: " + karton);

                clearFields();
                hidePopup();
            }
        } catch (IllegalArgumentException e) {
            isValid = false;
            System.err.println(e.getMessage());
        }

        if (!isValid) {
            System.err.println("Fehlerhafter input");
        }
    }

    private String validateTextField(TextField field, String fieldName) throws IllegalArgumentException {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            field.getStyleClass().add("invalid-input");
            throw new IllegalArgumentException(fieldName + " darf nicht leer sein");
        }
        return value;
    }

    private int validateIntegerField(TextField field, String fieldName) throws IllegalArgumentException {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            field.getStyleClass().add("invalid-input");
            throw new IllegalArgumentException(fieldName + " muss eine ganze Zahl sein");
        }
    }

    private LocalDate validateDateField(TextField field, String fieldName) throws IllegalArgumentException {
        try {
            return LocalDate.parse(field.getText().trim());
        } catch (DateTimeParseException e) {
            field.getStyleClass().add("invalid-input");
            throw new IllegalArgumentException(fieldName + " muss im Format YYYY-MM-DD sein");
        }
    }

    private void resetFieldStyles() {
        view.getNameField().getStyleClass().remove("invalid-input");
        view.getAnzahlField().getStyleClass().remove("invalid-input");
        view.getBreiteField().getStyleClass().remove("invalid-input");
        view.getHoeheField().getStyleClass().remove("invalid-input");
        view.getGewichtField().getStyleClass().remove("invalid-input");
        view.getMhdField().getStyleClass().remove("invalid-input");
    }

    private void clearFields() {
        view.getNameField().clear();
        view.getAnzahlField().clear();
        view.getBreiteField().clear();
        view.getHoeheField().clear();
        view.getGewichtField().clear();
        view.getMhdField().clear();
        view.getLebensmittelCheck().setSelected(false);
        view.getGiftigCheck().setSelected(false);
        view.getGekuehltCheck().setSelected(false);
        resetFieldStyles();
    }
}

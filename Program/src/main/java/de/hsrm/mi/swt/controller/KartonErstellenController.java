package de.hsrm.mi.swt.controller;

import de.hsrm.mi.swt.model.storage.Karton;
import de.hsrm.mi.swt.model.storage.Typ;
import de.hsrm.mi.swt.model.storage.Ware;
import de.hsrm.mi.swt.view.lager.KartonErstellenView;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.time.LocalDate;

public class KartonErstellenController {
    private KartonErstellenView view;
    private Popup popup;

    public KartonErstellenController() {
        this.view = new KartonErstellenView();
        this.popup = new Popup();
        this.popup.getContent().add(view);

        initialize();
    }

    private void initialize() {
        view.getCloseButton().setOnAction(e -> hidePopup());
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
        try {
            String name = view.getNameField().getText();
            int anzahl = Integer.parseInt(view.getAnzahlField().getText());
            int breite = Integer.parseInt(view.getBreiteField().getText());
            int hoehe = Integer.parseInt(view.getHoeheField().getText());
            int gewicht = Integer.parseInt(view.getGewichtField().getText());
            LocalDate mhd = LocalDate.parse(view.getMhdField().getText());

            Typ typ = new Typ(
                    view.getLebensmittelCheck().isSelected(),
                    view.getGiftigCheck().isSelected(),
                    view.getGekuehltCheck().isSelected()
            );

            Ware ware = new Ware(name, hoehe, gewicht, 0, mhd, typ);
            Karton karton = new Karton(gewicht, breite, hoehe, 1000, 0, ware);

            // Here you would add the karton to your inventory or storage system
            System.out.println("Karton created: " + karton);

            hidePopup();
        } catch (NumberFormatException | NullPointerException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }
}

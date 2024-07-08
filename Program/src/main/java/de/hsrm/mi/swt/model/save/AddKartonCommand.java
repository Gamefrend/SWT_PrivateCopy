package de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Karton;
import de.hsrm.mi.swt.model.storage.RegalBrett;
import de.hsrm.mi.swt.view.lager.KartonErstellenView;
import javafx.scene.layout.Pane;

public class AddKartonCommand implements Command {
    private RegalBrett regalBrett;
    private Karton karton;
    private Pane centerArea;

    public AddKartonCommand(RegalBrett regalBrett, Karton karton) {
        this.regalBrett = regalBrett;
        this.karton = karton;
        this.centerArea = centerArea;
    }

    @Override
    public void redo() {
        regalBrett.addKarton(karton);
    }

    @Override
    public void undo() {
        regalBrett.removeKarton(karton);
    }
}

package de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.model.storage.Saeule;

public class AddSaeuleCommand implements Command {
    private Raum raum;
    private Saeule saeule;

    public AddSaeuleCommand(Raum raum, Saeule saeule) {
        this.raum = raum;
        this.saeule = saeule;
    }

    @Override
    public void redo() {
        raum.getRegal().addSaeule(saeule);
    }

    @Override
    public void undo() {
        raum.getRegal().getSaeulen().remove(saeule);
    }
}

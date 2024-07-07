package de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Regal;
import de.hsrm.mi.swt.model.storage.RegalBrett;

public class AddBrettCommand implements Command {
    private Regal regal;
    private RegalBrett regalBrett;

    public AddBrettCommand(Regal regal, RegalBrett regalBrett) {
        this.regal = regal;
        this.regalBrett = regalBrett;
    }

    @Override
    public void redo() {
        regal.getRegalBretter().add(regalBrett);
    }

    @Override
    public void undo() {
        regal.getRegalBretter().remove(regalBrett);
    }
}

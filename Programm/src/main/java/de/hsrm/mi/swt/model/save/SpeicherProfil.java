package main.java.de.hsrm.mi.swt.model.save;

import java.io.Serializable;

public class SpeicherProfil implements Serializable {
    String xmlSaveFile;

    public String getSaveFile() {
        return xmlSaveFile;
    }
}

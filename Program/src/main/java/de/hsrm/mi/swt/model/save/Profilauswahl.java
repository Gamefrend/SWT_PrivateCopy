package main.java.de.hsrm.mi.swt.model.save;

import java.util.List;

public class Profilauswahl {
    List<SpeicherProfil> speicherProfile;

    public List<SpeicherProfil> getSpeicherProfile() {
        return this.speicherProfile;
    }

    public void addProfile(SpeicherProfil speicherProfil) {
        speicherProfile.add(speicherProfil);

    }

    public void delProfile(SpeicherProfil speicherProfil) {
        speicherProfile.remove(speicherProfil);
    }
}

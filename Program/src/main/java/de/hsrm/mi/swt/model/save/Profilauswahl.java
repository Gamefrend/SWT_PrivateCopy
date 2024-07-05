package de.hsrm.mi.swt.model.save;

import java.io.File;
import java.util.*;

public class Profilauswahl {
    List<SpeicherProfil> speicherProfile;

    public Profilauswahl() {
        speicherProfile = new ArrayList<>();
        System.out.println(getClass().getResource("/saves/").getPath());
        File directory = new File(getClass().getResource("/saves/").getFile());
        if (directory.exists() && directory.isDirectory()) {
            for (File speicheDatei : directory.listFiles()) {
                if (speicheDatei.getName().endsWith(".StorageShelves")) {
                    try {
                        speicherProfile.add(new SpeicherProfil(speicheDatei.getName().split("\\.StorageShelves")[0]));
                    } catch (Exception e) {
                        System.err.println("Die Datei " + speicheDatei.getName() + " ist kein gültiges Speicherprofil");
                    }
                }
            }
        }
    }

    public List<SpeicherProfil> getSpeicherProfile() {
        return this.speicherProfile;
    }

    public void addProfile(SpeicherProfil speicherProfil) {
        speicherProfile.add(speicherProfil);
    }

    public SpeicherProfil getNeustesProfil() {
        return Collections.max(speicherProfile, Comparator.comparing(SpeicherProfil::getDatum));
    }

    public void delProfile(SpeicherProfil speicherProfil) {
        speicherProfile.remove(speicherProfil);
    }

    public void renameProfile(SpeicherProfil profile, String newName) {
        if (profile != null && newName != null && !newName.isEmpty()) {
            String oldName = profile.getSaveName();
            profile.renameFile(newName);

            // Profil updaten in Profilliste
            int index = speicherProfile.indexOf(profile);
            if (index != -1) {
                speicherProfile.set(index, profile);
            }

            System.out.println("Profil umbenannt von " + oldName + " zu " + newName);
        } else {
            System.out.println("Kein neuer name");
        }
    }
}

package de.hsrm.mi.swt.model.save;

import java.io.File;
import java.net.URISyntaxException;
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

            // Pfad zur Datei im src-Verzeichnis
            File srcFile = new File("src/main/resources/saves/" + oldName + ".StorageShelves");

            // Pfad zur Datei im build-Verzeichnis
            File buildFile = new File("build/resources/main/saves/" + oldName + ".StorageShelves");

            try {
                // Datei im src-Verzeichnis umbenennen
                if (srcFile.exists() && srcFile.renameTo(new File(srcFile.getParent(), newName + ".StorageShelves"))) {
                    System.out.println("Profil im src-Verzeichnis umbenannt von " + oldName + " zu " + newName);
                } else {
                    System.out.println("Umbenennung im src-Verzeichnis ist gescheitert oder Datei existiert nicht");
                }

                // Datei im build-Verzeichnis umbenennen
                if (buildFile.exists() && buildFile.renameTo(new File(buildFile.getParent(), newName + ".StorageShelves"))) {
                    System.out.println("Profil im build-Verzeichnis umbenannt von " + oldName + " zu " + newName);
                } else {
                    System.out.println("Umbenennung im build-Verzeichnis ist gescheitert oder Datei existiert nicht");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

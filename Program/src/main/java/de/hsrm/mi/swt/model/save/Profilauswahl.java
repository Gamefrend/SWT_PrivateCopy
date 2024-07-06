package de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Raum;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Profilauswahl {
    private List<SpeicherProfil> speicherProfile;

    public Profilauswahl() {
        speicherProfile = new ArrayList<>();
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
        try {
            // Pfad zur Datei im src-Verzeichnis
            Path srcPath = Paths.get("src/main/resources/saves/" + speicherProfil.getSaveName() + ".StorageShelves");
            Files.deleteIfExists(srcPath);

            // Pfad zur Datei im build-Verzeichnis
            Path buildPath = Paths.get("build/resources/main/saves/" + speicherProfil.getSaveName() + ".StorageShelves");
            Files.deleteIfExists(buildPath);

            // Remove the profile from the list
            speicherProfile.remove(speicherProfil);

            System.out.println("Profile files erfolgreich gelöscht");
        } catch (IOException e) {
            System.err.println("Fehler beim löschen: " + e.getMessage());
            e.printStackTrace();
        }
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
    public void ceateTestProfile(){
        Raum r1 = new Raum(2000, 5000);
        Raum r2 = new Raum(4000, 300);
        SpeicherProfil sp1 = new SpeicherProfil("Test1");
        SpeicherProfil sp2 = new SpeicherProfil("Test2");
        sp1.save(r1);
        sp2.save(r2);
    }
}

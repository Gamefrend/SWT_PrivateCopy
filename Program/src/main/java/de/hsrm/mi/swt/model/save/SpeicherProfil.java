package de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Raum;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpeicherProfil {
    private static final long serialVersionUID = 1L;
    private String saveName;
    private LocalDateTime datum;
    private static final String SAVES_DIR = "src/main/resources/saves";

    public SpeicherProfil(String name) {
        this.saveName = name;
        this.datum = LocalDateTime.now();
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public void save(Raum speicherRaum) {
        Path savesDir = Paths.get(SAVES_DIR);
        try {
            Files.createDirectories(savesDir);
            Path filePath = savesDir.resolve(saveName + ".StorageShelves");
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filePath))) {
                if (this.datum == null) {
                    this.datum = LocalDateTime.now();
                }
                oos.writeObject(this);
                oos.writeObject(speicherRaum);
            }
            System.out.println("Profil erfolgreich gespeichert: " + filePath.toAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Error beim Speichern des Profils: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Raum load() {
        Path filePath = Paths.get(SAVES_DIR, saveName + ".StorageShelves");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath))) {
            SpeicherProfil loadedProfile = (SpeicherProfil) ois.readObject();
            this.datum = loadedProfile.getDatum();
            Object obj = ois.readObject();
            if (obj instanceof Raum) {
                return (Raum) obj;
            } else {
                System.err.println("geladenes Objekt ist kein Raum");
                return null;
            }
        } catch (InvalidClassException e) {
            System.err.println(e.getMessage());
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Fehler beim Laden des Profils: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public String getFormattedDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return datum.format(formatter);
    }

    public static void main(String[] args) {
        SpeicherProfil sp1 = new SpeicherProfil("1");
        SpeicherProfil sp2 = new SpeicherProfil("2");
        Raum r1 = new Raum(2, 1);
        Raum r2 = new Raum(10, 20);
        sp1.setSaveName("1");
        sp2.setSaveName("2");
    }
}

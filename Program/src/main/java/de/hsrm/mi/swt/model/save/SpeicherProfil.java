package de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Raum;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpeicherProfil {
    private String saveName;
    private LocalDateTime datum;
    private static final String SAVES_DIR = "saves";

    private File gespeicherteProfile;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputstream;
    private Raum raum;

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

    public Raum getSaveFile() {
        return raum;
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
                oos.writeObject(speicherRaum);
            }
            System.out.println("Profile saved successfully: " + filePath.toAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Error saving profile: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Raum load() {
        Path filePath = Paths.get(SAVES_DIR, saveName + ".StorageShelves");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath))) {
            return (Raum) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error loading profile: " + ex.getMessage());
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

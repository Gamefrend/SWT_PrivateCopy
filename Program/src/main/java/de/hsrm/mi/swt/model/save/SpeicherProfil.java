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

    public void save(Raum speichrRaum) {
        try {
            gespeicherteProfile = new File(getClass().getResource("/saves/" + saveName + ".StorageShelves").toURI());
            fileOutputStream = new FileOutputStream(gespeicherteProfile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(speichrRaum);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Raum load() {
        try {
            gespeicherteProfile = new File(getClass().getResource("/saves/" + saveName + ".StorageShelves").toURI());
            System.out.println(gespeicherteProfile.getAbsolutePath());
            fileInputStream = new FileInputStream(gespeicherteProfile);
            System.out.println("Geht 1");
            objectInputstream = new ObjectInputStream(fileInputStream);
            System.out.println("Geht 2");
            raum = (Raum) objectInputstream.readObject();
            objectInputstream.close();
            fileInputStream.close();
        } catch (FileNotFoundException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return raum;
    }

    public String getFormattedDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return datum.format(formatter);
    }

    public void renameFile(String newName) {
        try {
            Path oldPath = Paths.get(getClass().getResource("/saves/" + saveName + ".StorageShelves").toURI());
            Path newPath = oldPath.resolveSibling(newName + ".StorageShelves");

            Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
            this.saveName = newName;
            System.out.println("File erfolgreich umbenannt");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler beim umbenennen", e);
        }
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

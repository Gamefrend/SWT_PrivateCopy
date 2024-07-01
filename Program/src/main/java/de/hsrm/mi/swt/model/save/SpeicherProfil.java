package main.java.de.hsrm.mi.swt.model.save;

import main.java.de.hsrm.mi.swt.model.storage.Raum;

import java.io.*;
import java.time.LocalDateTime;

public class SpeicherProfil {
    private String saveName;
    private LocalDateTime datum;

    private File gespeicherteProfile;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    private FileInputStream fileInputStream;

    private ObjectInputStream objectInputstream;
    private Raum raum;

    public SpeicherProfil(String name ){
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
<<<<<<< HEAD
            gespeicherteProfile = new File("Program/src/main/resources/saves/" + saveName + ".StorageShelves");
=======
            // mit 2024swto4 starten bei Dominik
            gespeicherteProfile = new File("Program/src/main/resources/saves/"+saveName+".StorageShelves");
>>>>>>> 5c713f8ee1c67c4fa298da2815a6d07c880b83e9
            System.out.println(gespeicherteProfile.getAbsolutePath());
            fileOutputStream = new FileOutputStream(gespeicherteProfile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(speichrRaum);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Raum load() {
        try {
            gespeicherteProfile = new File("Program/src/main/resources/saves/" + saveName + ".StorageShelves");
            fileInputStream = new FileInputStream(gespeicherteProfile);
            objectInputstream = new ObjectInputStream(fileInputStream);
            raum = (Raum) objectInputstream.readObject();
            objectInputstream.close();
            fileInputStream.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return raum;
    }

<<<<<<< HEAD
    public static void main(String[] args) {
        SpeicherProfil sp1 = new SpeicherProfil();
        SpeicherProfil sp2 = new SpeicherProfil();
        Raum r1 = new Raum(2, 1);
        Raum r2 = new Raum(10, 20);
        sp1.setSaveName("1");
        sp2.setSaveName("2");
=======
    public static void main(String []args){
        SpeicherProfil sp1 = new SpeicherProfil("1");
        SpeicherProfil sp2 = new SpeicherProfil("2");
        Raum r1 = new Raum(2,1);
        Raum r2 = new Raum(10,20);
>>>>>>> 5c713f8ee1c67c4fa298da2815a6d07c880b83e9
        sp1.save(r1);
        sp2.save(r2);
        Raum r3 = sp1.load();
        System.out.println(r3.getHoehe() + " | " + r3.getBreite());
    }
}

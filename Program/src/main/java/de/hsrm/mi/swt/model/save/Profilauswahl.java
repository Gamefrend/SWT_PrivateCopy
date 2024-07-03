package main.java.de.hsrm.mi.swt.model.save;

import main.java.de.hsrm.mi.swt.model.storage.Raum;

import java.io.File;
import java.util.*;

public class Profilauswahl {
    List<SpeicherProfil> speicherProfile;

    public Profilauswahl() {
        //Hier können Testspeicherprofile erstellt werden
        /*
        SpeicherProfil sp1 = new SpeicherProfil("Test_1");
        SpeicherProfil sp2 = new SpeicherProfil("Test_2");
        Raum r1 = new Raum(1,2);
        Raum r2 = new Raum(1341234,42134234);
        sp1.save(r1);
        sp2.save(r2);
         */

        speicherProfile = new ArrayList<>();
        File directory = new File("Program/src/main/resources/saves/");
        if (directory.exists() && directory.isDirectory()) {
            for (File speicheDatei : directory.listFiles()) {
                if(speicheDatei.getName().endsWith(".StorageShelves")){
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

    public SpeicherProfil getNeustesProfil(){
        return Collections.max(speicherProfile, Comparator.comparing(SpeicherProfil::getDatum));
    }

    public void delProfile(SpeicherProfil speicherProfil) {
        speicherProfile.remove(speicherProfil);
    }
}

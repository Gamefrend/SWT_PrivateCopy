package test.java.de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Raum;
import de.hsrm.mi.swt.model.storage.Karton;
import de.hsrm.mi.swt.model.storage.Typ;
import de.hsrm.mi.swt.model.storage.Ware;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import de.hsrm.mi.swt.model.save.SpeicherProfil;
import de.hsrm.mi.swt.model.save.Profilauswahl;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;


class ProfilAuswahlTest{
    @Test
    public void profileAdd() {
        SpeicherProfil sp1 = new SpeicherProfil("Test");
        SpeicherProfil sp2 = new SpeicherProfil("Test2");
        Profilauswahl pa1 = new Profilauswahl();

        pa1.addProfile(sp1);
        pa1.addProfile(sp2);
        Assertions.assertEquals(true, pa1.getSpeicherProfile().contains(sp1));
        Assertions.assertEquals(true, pa1.getSpeicherProfile().contains(sp2));
    }

    @Test
    public void getNewest(){
        Profilauswahl pa1 = new Profilauswahl();
        SpeicherProfil sp1 = new SpeicherProfil("Test");
        SpeicherProfil sp2 = new SpeicherProfil("Test2");
        sp2.setDatum(LocalDateTime.of(2001,9,3,5,0));

        pa1.addProfile(sp1);
        pa1.addProfile(sp2);
        Assertions.assertSame(sp1, pa1.getNeustesProfil());
    }

    @Test
    public void loadFromResources(){
        Profilauswahl pa1 = new Profilauswahl();
        int i = pa1.getSpeicherProfile().size();
        File directory = new File("src/main/resources/saves/");
        File file = new File(directory,"JUnitTest.StorageShelves");
        pa1.reloadSpeicherProfile();
        Assertions.assertEquals(i+1, pa1.getSpeicherProfile().size());
        file.delete();
    }

    @Test
    public void loadFromResourcesWrongFileExtension(){
        Profilauswahl pa1 = new Profilauswahl();
        int i = pa1.getSpeicherProfile().size();
        File directory = new File("src/main/resources/saves/");
        File file = new File(directory,"JUnitTest.txt");
        pa1.reloadSpeicherProfile();
        Assertions.assertEquals(i, pa1.getSpeicherProfile().size());
        file.delete();
    }
}
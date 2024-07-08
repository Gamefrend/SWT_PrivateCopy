package test.java.de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Raum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import de.hsrm.mi.swt.model.save.SpeicherProfil;


class SpeicherProfilTest {
    @Test
    public void speicherProfilnameWirdRichtigGesetzt() {

        SpeicherProfil sp1 = new SpeicherProfil("Test");
        Assertions.assertEquals("Test", sp1.getSaveName());
    }

    @Test
    public void raumSaveAndLoadIsEqual(){
        Raum raum = new Raum(1,1);
        SpeicherProfil sp1 = new SpeicherProfil("Raumtest");
        sp1.save(raum);
        Raum raum2 = sp1.load();
        Assertions.assertEquals(raum, raum2);
    }
}
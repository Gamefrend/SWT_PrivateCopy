package de.hsrm.mi.swt.model.save;

import de.hsrm.mi.swt.model.storage.Karton;
import de.hsrm.mi.swt.model.storage.Typ;
import de.hsrm.mi.swt.model.storage.Ware;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import de.hsrm.mi.swt.model.save.SpeicherProfil;

import java.time.LocalDate;


class SpeichProfilTest{
    @Test
    public void neuerKartonTest(){

        SpeicherProfil sp1 = new SpeicherProfil("Test");
        Assertions.assertEquals("Test",sp1.getSaveName());
        System.out.println("Idk wie das funktioniert :c");
    }
}
package test.java.de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.model.storage.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RegalTest {

    @Test
    public void testAddSaeule() {
        Regal regal = new Regal(100);
        Saeule saeule = new Saeule(50);
        regal.addSaeule(saeule);
        Assertions.assertTrue(regal.getSaeulen().contains(saeule));
    }

    @Test
    public void testAddBrett() {
        Regal regal = new Regal(100);
        RegalBrett brett = new RegalBrett(50, 10, 100, 0);
        regal.addBrett(brett);
        Assertions.assertTrue(regal.getRegalBretter().contains(brett));
    }

    @Test
    public void testVerschiebeSaeule() {
        Regal regal = new Regal(100);
        Saeule saeule = new Saeule(50);
        regal.addSaeule(saeule);
        regal.verschiebeSaeule(saeule, 75);
        Assertions.assertEquals(75, saeule.getPositionX());
    }
}
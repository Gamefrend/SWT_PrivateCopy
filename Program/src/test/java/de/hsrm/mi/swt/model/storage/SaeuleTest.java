package test.java.de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.model.storage.Saeule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SaeuleTest {

    @Test
    public void testSaeulePosition() {
        Saeule saeule = new Saeule(100);
        Assertions.assertEquals(100, saeule.getPositionX());
    }

    @Test
    public void testSaeulePositionBoundary() {
        Saeule saeule = new Saeule(1500); // Should be capped at 1300
        Assertions.assertEquals(1300, saeule.getPositionX());
    }

    @Test
    public void testSetPositionX() {
        Saeule saeule = new Saeule(100);
        saeule.setPositionX(200);
        Assertions.assertEquals(200, saeule.getPositionX());
    }
}
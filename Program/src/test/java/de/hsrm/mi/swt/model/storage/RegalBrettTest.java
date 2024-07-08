package test.java.de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.model.storage.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class RegalBrettTest {

    @Test
    public void testAddKarton() {
        RegalBrett brett = new RegalBrett(50, 10, 100, 0);
        Karton karton = new Karton(1, 1, 1, 1, 1, new Ware("TestWare", 1, 1, 1, LocalDate.now(), new Typ(false, true, false)));
        brett.addKarton(karton);
        Assertions.assertTrue(brett.getKartons().contains(karton));
    }

    @Test
    public void testRemoveKarton() {
        RegalBrett brett = new RegalBrett(50, 10, 100, 0);
        Karton karton = new Karton(1, 1, 1, 1, 1, new Ware("TestWare", 1, 1, 1, LocalDate.now(), new Typ(false, true, false)));
        brett.addKarton(karton);
        brett.removeKarton(karton);
        Assertions.assertFalse(brett.getKartons().contains(karton));
    }

    @Test
    public void testSetHoehe() {
        RegalBrett brett = new RegalBrett(50, 10, 100, 0);
        brett.setHoehe(75);
        Assertions.assertEquals(75, brett.getHoehe());
    }
}
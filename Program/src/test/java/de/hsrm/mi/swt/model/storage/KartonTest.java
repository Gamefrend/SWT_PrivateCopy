package test.java.de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.model.storage.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class KartonTest {

    @Test
    public void testKartonProperties() {
        Ware ware = new Ware("TestWare", 1, 1, 1, LocalDate.now(), new Typ(false, true, false));
        Karton karton = new Karton(10, 20, 30, 100, 5, ware);

        Assertions.assertEquals(10, karton.getGewicht());
        Assertions.assertEquals(20, karton.getBreite());
        Assertions.assertEquals(30, karton.getHoehe());
        Assertions.assertEquals(100, karton.getMaxBelastung());
        Assertions.assertEquals(5, karton.getXPosition());
        Assertions.assertEquals(ware, karton.getWaren());
    }

    @Test
    public void testSetXPosition() {
        Ware ware = new Ware("TestWare", 1, 1, 1, LocalDate.now(), new Typ(false, true, false));
        Karton karton = new Karton(10, 20, 30, 100, 5, ware);
        karton.setXPosition(15);
        Assertions.assertEquals(15, karton.getXPosition());
    }
}
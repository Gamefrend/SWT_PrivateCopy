package test.java.de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.model.storage.Regal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import de.hsrm.mi.swt.model.storage.Raum;
public class RaumTest {

    @Test
    public void raumHoeheBreite(){
        Raum r1 = new Raum(10,20);
        Assertions.assertEquals(10, r1.getHoehe());
        Assertions.assertEquals(20, r1.getBreite());
    }
    @Test
    public void addRegal(){
        Raum r1 = new Raum(10,20);
        r1.setRegal(new Regal(r1.getHoehe()));
        Assertions.assertEquals(true, r1.getRegal() != null);
    }
}

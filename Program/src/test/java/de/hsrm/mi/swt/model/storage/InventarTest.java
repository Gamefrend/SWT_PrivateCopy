package test.java.de.hsrm.mi.swt.model.storage;

import de.hsrm.mi.swt.model.storage.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class InventarTest {

    @Test
    public void addBoxes(){
        Inventar inventar = new Inventar();
        Karton karton = new Karton(1,1,1,1,1,
                new Ware("TestWare",1,1,1, LocalDate.now(),new Typ(false,true,false)));
        inventar.addKarton(karton);
        Assertions.assertEquals(true, inventar.getKartons().contains(karton));
    }

    @Test
    public void removeBoxes(){
        Inventar inventar = new Inventar();
        Karton karton = new Karton(1,1,1,1,1,
                new Ware("TestWare",1,1,1, LocalDate.now(),new Typ(false,true,false)));
        inventar.addKarton(karton);
        Assertions.assertEquals(true, inventar.getKartons().contains(karton));
        inventar.removeKarton(karton);
        Assertions.assertEquals(false, inventar.getKartons().contains(karton));
    }
}

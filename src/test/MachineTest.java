package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import machine.*;

public class MachineTest {

    private Machine machine;

    @Before
    public void initialize () {

        machine = new Machine();
    }

    @Test
    public void testMachine() {
        Machine machine = new Machine();
        assertFalse(machine.isBroken());
    }

    @Test
    public void testSetBrokenMachine() {
        Machine machine = new Machine();
        machine.setBroken();
        assertTrue(machine.isBroken());
    }

    @Test
    public void testRepairMachine() {
        Machine machine = new Machine();
        machine.setBroken();
        machine.repair();
        assertFalse(machine.isBroken());
    }
    
}

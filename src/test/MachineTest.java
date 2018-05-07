package test;

import static org.junit.Assert.*;
import org.junit.Test;
import machine.*;

public class MachineTest {
    @Test
    public void testBrokenMachine() {
        Machine machine = new Machine();
        machine.setBroken();
        assertTrue(machine.isBroken());
    }
    @Test
    public void testNoBrokenMachine() {
        Machine machine = new Machine();
        assertFalse(machine.isBroken());
    }
    @Test
    public void testMachineRepair() {
        Machine machine = new Machine();
        machine.setBroken();
        machine.repair();
        assertFalse(machine.isBroken());
    }
}

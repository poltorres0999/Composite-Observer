package test;

import machine.Machine;
import machine.MachineComposite;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class MachineCompositeTest {

    private Machine machine;
    private Machine machine1;
    private Machine machine2;
    private MachineComposite machineComposite;
    private MachineComposite machineComposite1;

    @Before
    public void initialize () {

        machine = new Machine();
        machine1 = new Machine();
        machine2 = new Machine();
        machineComposite = new MachineComposite();
        machineComposite1 = new MachineComposite();
    }

    @Test
    public void testAddCorrectComponents() {

        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machineComposite.addComponent(machine2);
        machineComposite.addComponent(machineComposite1);
        assertFalse(machineComposite.isBroken());
        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(4, machineComposite.getComponents().size());
    }

    @Test
    public void testSetBrokenMachineComposite() {
        machineComposite.setBroken();
        assertTrue(machineComposite.isBroken());
    }

    @Test
    public void testBrokenComponentAfterUpdate() {

        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machineComposite.addComponent(machine2);
        machineComposite.addComponent(machineComposite1);
        machine.setBroken();
        machineComposite1.setBroken();

        assertTrue(machineComposite.isBroken());
        assertEquals(2, machineComposite.getTrackBrokenComponents().size());
        assertEquals(4, machineComposite.getComponents().size());
    }

    @Test
    public void testAddBrokenComponent() {

        machine.setBroken();
        machineComposite1.setBroken();
        machineComposite.addComponent(machine);
        machineComposite.addComponent(machineComposite1);
        machineComposite.addComponent(machine1);
        machineComposite.addComponent(machine2);

        assertTrue(machineComposite.isBroken());
        assertEquals(2, machineComposite.getTrackBrokenComponents().size());
        assertEquals(4, machineComposite.getComponents().size());
    }

    @Test
    public void testRepairBrokenMachineCompositeWithoutComponents() {

        machineComposite.setBroken();
        machineComposite.repair();
        assertFalse(machineComposite.isBroken());
        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(0, machineComposite.getComponents().size());
    }

    @Test
    public void testRepairBrokenMachineCompositeWithBrokenComponents() {
        MachineComposite machineComposite = new MachineComposite();
        machineComposite.setBroken();
        Machine machine = new Machine();
        Machine machine1 = new Machine();
        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machineComposite.repair();
        assertFalse(machineComposite.isBroken());
        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(2, machineComposite.getComponents().size());
    }
}

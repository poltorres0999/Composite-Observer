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
    public void testMachineComposite () {

        assertFalse(machineComposite.isBroken());
        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(0, machineComposite.getComponents().size());
    }

    @Test
    public void testAddNoBrokenComponents() {

        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machineComposite.addComponent(machine2);
        machineComposite.addComponent(machineComposite1);

        assertFalse(machineComposite.isBroken());

        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(4, machineComposite.getComponents().size());

    }

    @Test
    public void testAddBrokenComponents() {

        machineComposite.addComponent(machine);
        machine.setBroken();
        machineComposite.addComponent(machine1);
        machine1.setBroken();
        machineComposite.addComponent(machine2);
        machineComposite.addComponent(machineComposite1);

        assertTrue(machineComposite.isBroken());

        assertEquals(2, machineComposite.getTrackBrokenComponents().size());
        assertEquals(4, machineComposite.getComponents().size());

    }

    @Test
    public void testSetBrokenInNoBrokenMachineComposite() {

        machineComposite.setBroken();
        assertTrue(machineComposite.isBroken());

    }

    @Test
    public void testSetBrokenInAlreadyBrokenMachine(){

        machineComposite.setBroken();
        machineComposite.setBroken();
        assertTrue(machineComposite.isBroken());

    }

    @Test
    public void testRepairWithNoBrokenComponents() {

        machineComposite.addComponent(machine);
        machineComposite.addComponent(machineComposite1);
        machineComposite.setBroken();
        machineComposite.repair();

        assertFalse(machineComposite.isBroken());

    }
    @Test
    public void testRepairWIthBrokenComponents() {

        machineComposite.addComponent(machine);
        machine.setBroken();
        machineComposite.addComponent(machineComposite1);
        machineComposite.setBroken();
        machineComposite.repair();

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

        assertEquals(2, machineComposite.getTrackBrokenComponents().size());
        assertEquals(4, machineComposite.getComponents().size());
    }

    @Test
    public void testRepairedComponentAfterUpdate() {

        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machineComposite.addComponent(machine2);
        machineComposite.addComponent(machineComposite1);
        machine.setBroken();
        machineComposite1.setBroken();
        machine.repair();
        machineComposite1.repair();

        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(4, machineComposite.getComponents().size());
    }

}

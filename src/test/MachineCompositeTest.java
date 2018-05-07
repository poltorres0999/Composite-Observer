package test;

import machine.Machine;
import machine.MachineComposite;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class MachineCompositeTest {
    @Test
    public void testAddCorrectComponents() {
        Machine machine = new Machine();
        Machine machine1 = new Machine();
        Machine machine2 = new Machine();
        MachineComposite machineComposite = new MachineComposite();
        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machineComposite.addComponent(machine2);
        assertFalse(machineComposite.isBroken());
        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(3, machineComposite.getComponents().size());
    }
    @Test
    public void testBrokenMachineComposite() {
        MachineComposite machineComposite = new MachineComposite();
        machineComposite.setBroken();
        assertTrue(machineComposite.isBroken());
        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(0, machineComposite.getComponents().size());
    }
    @Test
    public void testBrokenComponentAfterUpdate() {
        Machine machine = new Machine();
        Machine machine1 = new Machine();
        MachineComposite machineComposite = new MachineComposite();
        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machine.setBroken();
        assertTrue(machineComposite.isBroken());
        assertEquals(1, machineComposite.getTrackBrokenComponents().size());
        assertEquals(2, machineComposite.getComponents().size());
    }
    @Test
    public void testAddBrokenComponent() {
        Machine machine = new Machine();
        machine.setBroken();
        MachineComposite machineComposite = new MachineComposite();
        machineComposite.addComponent(machine);
        assertTrue(machineComposite.isBroken());
        assertEquals(1, machineComposite.getTrackBrokenComponents().size());
        assertEquals(1, machineComposite.getComponents().size());
    }
    @Test
    public void testAddBrokenComponentAndRepair() {
        Machine machine = new Machine();
        Machine machine1 = new Machine();
        machine.setBroken();
        MachineComposite machineComposite = new MachineComposite();
        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machine.repair();
        assertFalse(machineComposite.isBroken());
        assertEquals(0, machineComposite.getTrackBrokenComponents().size());
        assertEquals(2, machineComposite.getComponents().size());
    }
    @Test
    public void testRepairBrokenMachineCompositeWithoutComponents() {
        MachineComposite machineComposite = new MachineComposite();
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

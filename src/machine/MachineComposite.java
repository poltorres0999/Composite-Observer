package machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends MachineComponent implements Observer {

    private final List<MachineComponent> components = new ArrayList<>();
    private final List<MachineComponent> trackBrokenComponents = new ArrayList<>();

    public void addComponent(final MachineComponent mc) {

        mc.addObserver(this);

        this.components.add(mc);
        if (mc.isBroken() && !this.isBroken()) {
            this.setBroken();
            this.trackBrokenComponents.add(mc);
            this.notifyChanges();
        }
    }

    @Override
    public void setBroken() {
        if (!this.isBroken()) {
            this.broken = true;
            this.notifyChanges();
        }
    }

    @Override
    public void repair() {
        if (this.isBroken()) {
            this.broken = false;
            this.notifyChanges();
        }
    }

    @Override
    public boolean isBroken() {
        return broken || !this.trackBrokenComponents.isEmpty();

    }

    @Override
    public void update(Observable observable, Object arg) {
        final MachineComponent machineComponent=(MachineComponent) observable;
        if(machineComponent.isBroken()){
            this.trackBrokenComponents.add(machineComponent);
            if (!this.isBroken()) {
                this.notifyChanges();
            }
        }

        else {
            this.trackBrokenComponents.remove(machineComponent);
            if (!this.isBroken()) {
                this.notifyChanges();
            }
        }
    }

    private void notifyChanges() {
        setChanged();
        notifyObservers();
    }

    public List<MachineComponent> getComponents() {
        return components;
    }
    public List<MachineComponent> getTrackBrokenComponents() {
        return trackBrokenComponents;
    }
}

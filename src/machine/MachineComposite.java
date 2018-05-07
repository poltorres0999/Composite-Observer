package machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends MachineComponent implements Observer {

    private final List<MachineComponent> components = new ArrayList<>();
    private final List<MachineComponent> trackBrokenComponents = new ArrayList<>();

    public void addComponent(final MachineComponent mc) {
        if(this == mc){
            throw new RuntimeException("A machine can not be added to it's component List");
        }
        mc.addObserver(this);
        this.components.add(mc);
        if (mc.isBroken()) {
            this.setBroken();
            this.trackBrokenComponents.add(mc);
            this.notifyObservers();
        }
    }

    @Override
    public void setBroken() {
        if (!this.isBroken()) {
            this.broken = true;
            this.setChanged();
            this.notifyObservers();
        }
    }

    @Override
    public void repair() {
        if (this.isBroken()) {
            for (final MachineComponent mc : this.trackBrokenComponents) {
                mc.repair();
            }
            this.trackBrokenComponents.clear();
            this.broken = false;
            this.hasChanged();
            this.notifyObservers();
        }
    }

    @Override
    public boolean isBroken() {
        return this.broken;
    }

    @Override
    public void update(Observable observable, Object arg) {
        final MachineComponent machineComponent=(MachineComponent) observable;
        if(machineComponent.isBroken()){
            this.trackBrokenComponents.add(machineComponent);
            this.setBroken();
        }
        else if(!machineComponent.isBroken()){
            this.trackBrokenComponents.remove(machineComponent);
            if(this.trackBrokenComponents.isEmpty()){
                this.broken=false;
            }
        }
    }

    public List<MachineComponent> getComponents() {
        return components;
    }
    public List<MachineComponent> getTrackBrokenComponents() {
        return trackBrokenComponents;
    }
}

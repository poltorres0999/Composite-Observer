import java.util.ArrayList;
import java.util.List;

public class MachineComposite extends MachineComponent{
    private List<MachineComponent> components = new ArrayList<>();
    private List<MachineComponent> trackBrokenComponents = new ArrayList<>();

    public void addComponent(MachineComponent mc) {
        this.components.add(mc);
    }

    @Override
    public void setBroken() {
        if (!isBroken()) {
            this.broken = true;
            notifyObservers();
        }
    }

    @Override
    public void repair() {
        if (isBroken()) {
            for (MachineComponent mc: trackBrokenComponents) {
                mc.repair();
            }
            trackBrokenComponents.clear();
            this.broken = false;
            notifyObservers();
        }
    }

    @Override
    public boolean isBroken() {
        return this.broken && this.trackBrokenComponents.isEmpty();
    }
}

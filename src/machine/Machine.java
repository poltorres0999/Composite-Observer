package machine;

public class Machine extends MachineComponent {

    public void setBroken () {
        if (!this.isBroken()) {
            this.broken = true;
            this.notifyChanges();
        }
    }

    public void repair() {
        if (this.isBroken()) {
            this.broken = false;
            this.notifyChanges();
        }
    }

    @Override
    public boolean isBroken() {
        return this.broken;
    }

    private void notifyChanges() {
        setChanged();
        notifyObservers();
    }

}

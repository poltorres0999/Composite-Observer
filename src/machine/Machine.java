package machine;

public class Machine extends MachineComponent {

    public void setBroken () {
        if (!this.isBroken()) {
            this.broken = true;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void repair() {
        if (this.isBroken()) {
            this.broken = false;
            this.setChanged();
            this.notifyObservers();
        }
    }

    @Override
    public boolean isBroken() {
        return this.broken;
    }

}

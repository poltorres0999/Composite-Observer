public class Machine extends MachineComponent {

    public void setBroken () {
        if (!isBroken()) {
            this.broken = true;
            setChanged();
            notifyObservers();
        }
    }

    public void repair() {
        if (isBroken()) {
            this.broken = false;
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public boolean isBroken() {
        return this.broken;
    }
}

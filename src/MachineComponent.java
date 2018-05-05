public abstract class MachineComponent {
    protected boolean broken = false;

    public void setBroken () {
        this.broken = true;
    }

    public void repair() {
        broken = false;
    }

    public abstract boolean isBroken();
}

package pl.sggw.stosprzepelniony.data.event;

public class NavigationDrawerItemSelectedEvent {

    private int navigationItem;

    public NavigationDrawerItemSelectedEvent(int navigationItem) {
        this.navigationItem = navigationItem;
    }

    public int getNavigationItem() {
        return navigationItem;
    }
}

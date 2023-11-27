package use_case.switchView;

public class SwitchViewInputData {
    /**
     * An object representing the name of the view to switch to.
     */
    private final String viewName;

    /**
     * Creates a SwitchViewInputData object.
     * @param viewName, the name of the view to switch to.
     */
    public SwitchViewInputData(String viewName) {this.viewName = viewName;}

    /**
     * Gets the viewName.
     * @return the name of the view to switch to.
     */
    public String getViewName() {return this.viewName;}
}

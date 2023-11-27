package use_case.switchView;

public class SwitchViewOutputData {
    /**
     * An object representing the name of the view to switch to.
     */
    private final String viewName;

    /**
     * Creates a SwitchViewOutputData object.
     * @param viewName, the name of the view to switch to.
     */
    public SwitchViewOutputData(String viewName) {this.viewName = viewName;}

    /**
     * Gets the viewName.
     * @return the name of the view to switch to.
     */
    public String getViewName() {return viewName;}
}

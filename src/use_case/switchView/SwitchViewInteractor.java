package use_case.switchView;

public class SwitchViewInteractor implements SwitchViewInputBoundary{
    /**
     * An input boundary to handle input data relating to the SwitchViewUseCase
     */
    private final SwitchViewOutputBoundary switchViewPresenter;

    /**
     * Creates a SwitchViewInteractor.
     * @param switchViewPresenter, the output boundary responsible for updating the current view.
     */
    public SwitchViewInteractor(SwitchViewOutputBoundary switchViewPresenter) {this.switchViewPresenter = switchViewPresenter;}

    /**
     * Calls an output boundary to switch the current view to the view name in the input data.
     * @param switchViewInputData, the name of the view to switch to.
     */
    @Override
    public void execute(SwitchViewInputData switchViewInputData) {
        SwitchViewOutputData switchViewOutputData = new SwitchViewOutputData(switchViewInputData.getViewName());
        switchViewPresenter.switchViewTo(switchViewOutputData);
    }
}

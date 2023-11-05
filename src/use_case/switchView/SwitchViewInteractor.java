package use_case.switchView;

public class SwitchViewInteractor implements SwitchViewInputBoundary{
    private final SwitchViewOutputBoundary switchViewPresenter;
    public SwitchViewInteractor(SwitchViewOutputBoundary switchViewPresenter) {this.switchViewPresenter = switchViewPresenter;}
    @Override
    public void execute(SwitchViewInputData switchViewInputData) {
        SwitchViewOutputData switchViewOutputData = new SwitchViewOutputData(switchViewInputData.getViewName());
        switchViewPresenter.switchViewTo(switchViewOutputData);
    }
}

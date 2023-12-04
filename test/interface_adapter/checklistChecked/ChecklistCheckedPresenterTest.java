package interface_adapter.checklistChecked;


import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import org.junit.jupiter.api.Test;
import use_case.checklistChecked.ChecklistOutputData;

public class ChecklistCheckedPresenterTest {
    @Test
    void execute() {
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();
        TableViewModel tableViewModel = new TableViewModel();

        TableState tableState = new TableState();
        tableState.addData(new String[]{"advil", "2 mg", "20 mg", "2", "2", "2", "2", "2", "2", "2", ""});
        tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();
        ChecklistPresenter checklistPresenter = new ChecklistPresenter(checklistViewModel,tableViewModel);
        ChecklistOutputData checklistOutputData = new ChecklistOutputData("advil","2 pill","0");

        checklistPresenter.take(checklistOutputData);
        checklistOutputData = new ChecklistOutputData("advil","2 pill","2");
        checklistPresenter.untake(checklistOutputData);
    }
}

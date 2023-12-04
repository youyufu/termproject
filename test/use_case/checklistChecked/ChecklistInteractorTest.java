package use_case.checklistChecked;

import data_access.MedicineDAO;
import data_access.MedicineDataAccessInterface;
import entity.Dose;
import entity.Medicine;
import interface_adapter.checklistChecked.ChecklistPresenter;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.table.TableState;
import interface_adapter.table.TableViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChecklistInteractorTest {
    @Test
    void execute() {
        ChecklistViewModel checklistViewModel = new ChecklistViewModel();
        TableViewModel tableViewModel = new TableViewModel();
        MedicineDataAccessInterface medicineDAO = MedicineDAO.getMedicineDAO(LocalDate.of(2023, 12, 1), "./test.json");
        Medicine medicine = new Medicine("advil", new Dose(2,2,"pill"), new Integer[]{1,1,1,1,1,1,1} , "advil", "123");
        medicineDAO.saveMedicine(medicine);
        TableState tableState = new TableState();
        tableState.addData(new String[]{"advil", "2 mg", "20 mg", "2", "2", "2", "2", "2", "2", "2", ""});
        tableViewModel.setState(tableState);
        tableViewModel.firePropertyChanged();
        ChecklistPresenter checklistPresenter = new ChecklistPresenter(checklistViewModel,tableViewModel);

        ChecklistInteractor checklistInteractor = new ChecklistInteractor(checklistPresenter,medicineDAO);
        ChecklistInputData checklistInputData = new ChecklistInputData("advil",1);
        checklistInteractor.execute(checklistInputData);
        ChecklistInputData checklistInputData2 = new ChecklistInputData("advil",-1);
        checklistInteractor.execute(checklistInputData);
    }
}

package app;

import data_access.MedicineDataAccessInterface;
import interface_adapter.table.TableViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.deleteMedicine.DeleteController;
import interface_adapter.deleteMedicine.DeletePresenter;
import interface_adapter.deleteMedicine.DeleteViewModel;
import interface_adapter.switchView.SwitchViewController;
import use_case.deleteMedicine.DeleteInputBoundary;
import use_case.deleteMedicine.DeleteInteractor;
import use_case.deleteMedicine.DeleteOutputBoundary;
import view.DeleteView;

public class DeleteUseCaseFactory {
    private DeleteUseCaseFactory() {}
    public static DeleteView create(SwitchViewController switchViewController, DeleteViewModel deleteViewModel,
                                    ChecklistViewModel checklistViewModel, TableViewModel tableViewModel, ViewManagerModel viewManagerModel,
                                    MedicineDataAccessInterface medicineDAO) {
        DeleteController deleteController = createDeleteUseCase(deleteViewModel, checklistViewModel, tableViewModel,
                viewManagerModel, medicineDAO);
        return new DeleteView(switchViewController, deleteController, deleteViewModel);
    }
    public static DeleteController createDeleteUseCase(DeleteViewModel deleteViewModel,
                                                       ChecklistViewModel checklistViewModel,
                                                       TableViewModel tableViewModel,
                                                       ViewManagerModel viewManagerModel,
                                                       MedicineDataAccessInterface medicineDAO) {
        DeleteOutputBoundary deletePresenter = new DeletePresenter(deleteViewModel, checklistViewModel,
                tableViewModel, viewManagerModel);
        DeleteInputBoundary deleteInteractor = new DeleteInteractor(medicineDAO, deletePresenter);
        return new DeleteController(deleteInteractor);

    }
}

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
    /**
     * DeleteUseCaseFactory is a factory class dedicated to creating and configuring
     * components for the delete use case in the application.
     */

    private DeleteUseCaseFactory() {}

    /**
     * Creates and returns a DeleteView for the delete use case.
     *
     * @param switchViewController The controller for switching views.
     * @param deleteViewModel The view model for delete.
     * @param checklistViewModel The view model for checklist.
     * @param tableViewModel The view model for table view.
     * @param viewManagerModel The model managing different views.
     * @param medicineDAO The DAO for medicine data.
     * @return DeleteView The configured view for delete.
     */
    public static DeleteView create(SwitchViewController switchViewController, DeleteViewModel deleteViewModel,
                                    ChecklistViewModel checklistViewModel, TableViewModel tableViewModel, ViewManagerModel viewManagerModel,
                                    MedicineDataAccessInterface medicineDAO) {
        DeleteController deleteController = createDeleteUseCase(deleteViewModel, checklistViewModel, tableViewModel,
                viewManagerModel, medicineDAO);
        return new DeleteView(switchViewController, deleteController, deleteViewModel);
    }

    /**
     * Creates and returns a DeleteController for the delete use case.
     *
     * @param deleteViewModel The view model for delete.
     * @param checklistViewModel The view model for checklist.
     * @param tableViewModel The view model for table view.
     * @param viewManagerModel The model managing different views.
     * @param medicineDAO The DAO for interacting with medicine data.
     * @return DeleteController The configured controller for the delete use case.
     */
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

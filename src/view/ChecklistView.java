package view;

import app.CrossUseCaseFactory;
import entity.Medicine;
import interface_adapter.checklistChecked.ChecklistController;
import interface_adapter.checklistChecked.ChecklistState;
import interface_adapter.checklistChecked.ChecklistViewModel;
import interface_adapter.switchView.SwitchViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ChecklistView extends JPanel implements ActionListener, PropertyChangeListener, ItemListener {
    public final String viewName = "checklist";
    private final ChecklistViewModel checklistViewModel;
    private final SwitchViewController switchViewController;
    private final ChecklistController checklistController;
    private final JButton back;
    private JPanel checklist = new JPanel();
    private JPanel lowStock = new JPanel();
    private HashMap<String, ArrayList<JCheckBox>> checklistMap = new HashMap<>();
    private HashMap<String, JLabel> lowMap = new HashMap<>();
    public ChecklistView(SwitchViewController switchViewController1, ChecklistController checklistController1, ChecklistViewModel checklistViewModel1) {
        this.checklistController = checklistController1;
        this.checklistViewModel = checklistViewModel1;
        this.switchViewController = switchViewController1;
        checklistViewModel.addPropertyChangeListener(this);

        back = new JButton(ChecklistViewModel.BACK_BUTTON_LABEL);
        JLabel header1 = new JLabel(ChecklistViewModel.HEADER_LABEL);
        header1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel header2 = new JLabel(ChecklistViewModel.LOW_STOCK_LABEL);
        header2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.add(back);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            switchViewController.execute("main");
                        }
                    }
                }
        );
        ArrayList<String[]> takeToday = this.checklistViewModel.getState().getTakeToday();
        HashMap<String, Integer> low = this.checklistViewModel.getState().getLow();
        for (String[] medication: takeToday) {
            System.out.println(medication[1]);
            JCheckBox checkBox = new JCheckBox(("Take " + medication[1] + " of " + medication[0]));
            if (!checklistMap.containsKey(medication[0])) {
                checklistMap.put(medication[0], new ArrayList<>());
            }
            ArrayList<JCheckBox> checkBoxList = checklistMap.get(medication[0]);
            checkBoxList.add(checkBox);
            checklistMap.put(medication[0], checkBoxList);
            checkBox.addItemListener(this);
            checklist.add(checkBox);
        } for (String medication: low.keySet()) {
            JLabel lowMed = new JLabel(medication + " (" + low.get(medication) + " doses remaining)");
            lowStock.add(lowMed);
            lowMap.put(medication, lowMed);
        }
        checklist.setLayout(new BoxLayout(checklist, BoxLayout.Y_AXIS));
        checklist.setAlignmentX(Component.CENTER_ALIGNMENT);
        lowStock.setLayout(new BoxLayout(lowStock, BoxLayout.Y_AXIS));
        lowStock.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(header1);
        this.add(checklist);
        this.add(header2);
        this.add(lowStock);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("addTake")) {
            String[] medication = (String[]) evt.getNewValue();
            JCheckBox checkBox = new JCheckBox(("Take " + medication[1] + " of " + medication[0]));
            if (!checklistMap.containsKey(medication[0])) {
                checklistMap.put(medication[0], new ArrayList<>());
            }
            ArrayList<JCheckBox> checkBoxList = checklistMap.get(medication[0]);
            checkBoxList.add(checkBox);
            checklistMap.put(medication[0], checkBoxList);
            checkBox.addItemListener(this);
            checklist.add(checkBox);
        } else if (evt.getPropertyName().equals("removeMed")) {
            String medication = (String) evt.getNewValue();
            ArrayList<JCheckBox> checkBoxList = checklistMap.get(medication);
            for (JCheckBox checkBox: checkBoxList) {
                checklist.remove(checkBox);
            }
            checklistMap.remove(medication);
            lowStock.remove(lowMap.get(medication));
            lowMap.remove(medication);
        } else if (evt.getPropertyName().equals("addLow")) {
            String[] medication = (String[]) evt.getNewValue();
            System.out.println("----------"+medication[0] );
            HashMap<String, Medicine> userMedicines = CrossUseCaseFactory.medicineDAO.getUserMedicines();
            if(null == CrossUseCaseFactory.map.get(medication[0])){
                for (Medicine medicine:userMedicines.values()) {
                    if(medicine.getName().equals(medication[0])){
                        CrossUseCaseFactory.map.put(medication[0],Integer.parseInt(medicine.getInventoryString().split(" ")[0]));
                    }
                }

            }
            JLabel lowMed = new JLabel(medication[0] + " (" + CrossUseCaseFactory.map.get(medication[0]) + " doses remaining)");
            lowStock.add(lowMed);
            lowMap.put(medication[0], lowMed);
        } else if (evt.getPropertyName().equals("showRestock")) {
            ChecklistState checklistState = (ChecklistState) evt.getNewValue();
            ArrayList<String> restock = checklistState.getRestock();
            if (!restock.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Restock:\n");
                for (String medicine:restock) {
                    stringBuilder.append(medicine).append("\n");
                } String s = stringBuilder.toString();
                JOptionPane.showMessageDialog(this, s);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ItemSelectable source = e.getItemSelectable();
        String text = ((JCheckBox)e.getSource()).getText();
        for (String medicine: checklistMap.keySet()) {

            ArrayList<JCheckBox> checkBoxList = checklistMap.get(medicine);
            for (JCheckBox checkBox: checkBoxList) {
                if (checkBox == source && text.indexOf(medicine) != -1){
                    int num = Integer.parseInt(text.split(" ")[1]);
                    System.out.println("(((((:"+medicine);
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        checklistController.cacelExecute(medicine,num,lowMap,lowStock);
                        break;
                    } else {

                        checklistController.execute(medicine,num,lowMap,lowStock);
                        break;
                    }
                }

            }
        }
    }
}


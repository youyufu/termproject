package view;

import interface_adapter.checklistChecked.ChecklistController;
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

public class ChecklistView extends JPanel implements PropertyChangeListener, ItemListener {
    public static final String viewName = "checklist";
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
                            switchViewController.execute(MainView.viewName);
                        }
                    }
                }
        );
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
            checklistViewModel.removeRestock(medication);
            if (checklistMap.containsKey(medication)) {
                ArrayList<JCheckBox> checkBoxList = checklistMap.get(medication);
                if (!checkBoxList.isEmpty()) {
                    for (JCheckBox checkBox: checkBoxList) {
                        checklist.remove(checkBox);
                    }
                }
            }
            checklistMap.remove(medication);
            if (lowMap.containsKey(medication)) {
                lowStock.remove(lowMap.get(medication));
                lowMap.remove(medication);
            }
        } else if (evt.getPropertyName().equals("addLow")) {
            String[] medication = (String[]) evt.getNewValue();
            if (!lowMap.containsKey(medication[0])) {
                JLabel lowMed = new JLabel(medication[0] + " (" + medication[1] + " doses remaining)");
                lowStock.add(lowMed);
                lowMap.put(medication[0], lowMed);
            }
        } else if (evt.getPropertyName().equals("removeLow")) {
            String medication = (String) evt.getNewValue();
            if (lowMap.containsKey(medication)) {
                lowStock.remove(lowMap.get(medication));
                lowMap.remove(medication);
            }
        } else if (evt.getPropertyName().equals("updateLow")) {
            String[] medication = (String[]) evt.getNewValue();
            if (lowMap.containsKey(medication[0])) {
                JLabel label = lowMap.get(medication[0]);
                label.setText(medication[0] + " (" + medication[1] + " doses remaining)");
            }
        }
        else if (evt.getPropertyName().equals("showRestock")) {
            ArrayList<String> restock = (ArrayList<String>) evt.getNewValue();
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
        for (String medicine: checklistMap.keySet()) {
            ArrayList<JCheckBox> checkBoxList = checklistMap.get(medicine);
            for (JCheckBox checkBox: checkBoxList) {
                if (checkBox == source)
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        checklistController.execute(medicine, ChecklistViewModel.UNTAKE);
                        break;
                    } else {
                        checklistController.execute(medicine, ChecklistViewModel.TAKE);
                        break;
                    }
            }
        }
    }
}


package interface_adapter.checklistChecked;

import app.CrossUseCaseFactory;
import entity.Medicine;
import interface_adapter.TableState;
import view.TableView;

import javax.swing.*;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ChecklistController {
    TableState tableState = null;
    public void execute(String m, int num, HashMap<String, JLabel> lowMap, JPanel lowStock) {
        HashMap<String, Medicine> userMedicines = CrossUseCaseFactory.medicineDAO.getUserMedicines();
        TableState tableState = new TableState();
        for (Medicine medicine:userMedicines.values()) {
            Integer[] weeklySched = medicine.getWeeklySchedule();
            String infact = medicine.getInventoryString();
            if(m.equals(medicine.getName())){
                infact = (CrossUseCaseFactory.map.get(medicine.getName()) - num) +" "+ medicine.getInventoryString().split(" ")[1];
                CrossUseCaseFactory.map.put(m, CrossUseCaseFactory.map.get(m)-num);
                if(CrossUseCaseFactory.map.get(m) <= 0){
                    ExecutorService executor = Executors.newFixedThreadPool(1);
                    Future<String> future = executor.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            Object[] fruits = {"ok"};
                            JOptionPane.showOptionDialog(null, "Please note: " + m +
                                    " inventory is depleted. Urgent restocking required","Attention",JOptionPane.YES_NO_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE,null, fruits, fruits[0]);
                            return "this is future execute final result!!!";
                        }
                    });


                    change(tableState,m,num);
                }
            }
            System.out.println(infact.split(" ")[0] + "--"+ m);

            String[] tableData = new String[]{medicine.getName(), medicine.getDoseString(),
                    CrossUseCaseFactory.map.get(medicine.getName())+" "+infact.split(" ")[1], String.valueOf(weeklySched[0]), String.valueOf(weeklySched[1]),
                    String.valueOf(weeklySched[2]), String.valueOf(weeklySched[3]), String.valueOf(weeklySched[4]),
                    String.valueOf(weeklySched[5]), String.valueOf(weeklySched[6]), medicine.getDescription()};
            tableState.addData(tableData);
        }
        this.tableState = tableState;
        System.out.println(CrossUseCaseFactory.map.get(m)-num);

        JLabel lowMed = new JLabel(m + " (" + (CrossUseCaseFactory.map.get(m))  + " doses remaining)");
        lowStock.remove(lowMap.get(m));
        lowMap.remove(m);
        lowStock.add(lowMed);
        lowMap.put(m, lowMed);
        CrossUseCaseFactory.tableViewModel.setState(tableState);
        TableView tableView = new TableView(CrossUseCaseFactory.switchViewController, CrossUseCaseFactory.tableViewModel);
        CrossUseCaseFactory.tableViewModel.firePropertyChanged();
    }

    public void change(TableState tableState,String m,int num){
        for(String[] s : tableState.getData() ){
            if(s[0].equals(m)){
                HashMap<String, Medicine> userMedicines = CrossUseCaseFactory.medicineDAO.getUserMedicines();
                for (Medicine medicine:userMedicines.values()) {
                    if(m.equals(medicine.getName())){
                        Integer[] weeklySched = medicine.getWeeklySchedule();
                        String infact = s[2];
                        if(m.equals(medicine.getName())){
                            infact = CrossUseCaseFactory.map.get(m) +" "+ medicine.getInventoryString().split(" ")[1];

                            s[2] = infact;
                        }

                    }
                }
            }
        }
    }

    public void changeRestory(TableState tableState,String m,int num){
        for(String[] s : tableState.getData() ){
            if(s[0].equals(m)){
                HashMap<String, Medicine> userMedicines = CrossUseCaseFactory.medicineDAO.getUserMedicines();
                for (Medicine medicine:userMedicines.values()) {
                    if(m.equals(medicine.getName())){
                        Integer[] weeklySched = medicine.getWeeklySchedule();
                        String infact = s[2];
                        if(m.equals(medicine.getName())){
                            infact = CrossUseCaseFactory.map.get(m) +" "+ medicine.getInventoryString().split(" ")[1];
                            s[2] = infact;
                        }

                    }
                }
            }
        }
    }
    public void cacelExecute(String m, int num, HashMap<String, JLabel> lowMap, JPanel lowStock) {
        HashMap<String, Medicine> userMedicines = CrossUseCaseFactory.medicineDAO.getUserMedicines();
        TableState tableState = new TableState();
        for (Medicine medicine:userMedicines.values()) {
            Integer[] weeklySched = medicine.getWeeklySchedule();
            String infact = medicine.getInventoryString();
            if(m.equals(medicine.getName())){

                CrossUseCaseFactory.map.put(m, CrossUseCaseFactory.map.get(m)+num);
                infact = CrossUseCaseFactory.map.get(m) +" "+ medicine.getInventoryString().split(" ")[1];
                changeRestory(tableState,m,num);
            }

            String[] tableData = new String[]{medicine.getName(), medicine.getDoseString(),
                    CrossUseCaseFactory.map.get(medicine.getName())+" "+infact.split(" ")[1], String.valueOf(weeklySched[0]), String.valueOf(weeklySched[1]),
                    String.valueOf(weeklySched[2]), String.valueOf(weeklySched[3]), String.valueOf(weeklySched[4]),
                    String.valueOf(weeklySched[5]), String.valueOf(weeklySched[6]), medicine.getDescription()};
            tableState.addData(tableData);
        }
        this.tableState = tableState;
        JLabel lowMed = new JLabel(m + " (" + (CrossUseCaseFactory.map.get(m)) + " doses remaining)");
        lowStock.remove(lowMap.get(m));
        lowMap.remove(m);
        lowStock.add(lowMed);
        lowMap.put(m, lowMed);
        CrossUseCaseFactory.tableViewModel.setState(tableState);
        TableView tableView = new TableView(CrossUseCaseFactory.switchViewController, CrossUseCaseFactory.tableViewModel);
        CrossUseCaseFactory.tableViewModel.firePropertyChanged();
    }

}

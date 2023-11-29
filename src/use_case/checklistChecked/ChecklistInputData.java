package use_case.checklistChecked;

public class ChecklistInputData {
    private String name;
    private Integer change;
    public ChecklistInputData(String name, Integer change) {
        this.name = name;
        this.change = change;
    }
    public String getName() {return this.name;}
    public Integer getChange() {return this.change;}
}

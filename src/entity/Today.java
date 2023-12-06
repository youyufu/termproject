package entity;

import java.util.HashMap;

public class Today {
    /**
     * The entity Today responsible for the daily information regarding all medicine stored for the user.
     */

    private HashMap<String, Integer> todayChecklist = new HashMap<>();
    private Integer day;

    /**
     * Constructor for the Today entity.
     * @param day the day Today is pertaining to.
     */
    public Today(Integer day) {this.day = day;}

    /**
     * Adds to the daily checklist the medicine, and the status of if its dosage has been taken or not.
     * @param medicine the medicine.
     * @param taken the status of if its dosage has been taken or not.
     */
    public void add(String medicine, Integer taken) {todayChecklist.put(medicine, taken);}

    /**
     * Logs the taking of the medicine dosage pertaining to the daily checklist.
     * @param medicine the medicine that is being taken.
     */
    public void take(String medicine){todayChecklist.put(medicine, todayChecklist.get(medicine) + 1);}
    /**
     * reverses the log of taking of the medicine dosage pertaining to the daily checklist.
     * @param medicine the medicine which the status of taken is being reversed.
     */
    public void untake(String medicine) {todayChecklist.put(medicine, todayChecklist.get(medicine) - 1);}

    /**
     * Removes the medicine from the daily checklist.
     * @param medicine The medicine being removed from the daily checklist.
     */
    public void remove(String medicine){todayChecklist.remove(medicine);}

    /**
     * A getter for the day that the Today entity is pertaining to.
     * @return The day the daily information is based off of.
     */
    public Integer getDay() {return this.day;}

    /**
     * A getter for the daily checklist pertaining to the day.
     * @return the daily checklist pertaining to the day.
     */
    public HashMap<String, Integer> getTodayChecklist() {return todayChecklist;}
}

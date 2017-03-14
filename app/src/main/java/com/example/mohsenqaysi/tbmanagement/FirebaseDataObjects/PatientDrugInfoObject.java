package com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohsenqaysi on 3/14/17.
 */

public class PatientDrugInfoObject {

    private String drugName;
    private String startDate;
    private String schedule;
    private String endDate;


    public PatientDrugInfoObject(String drugName, String startDate, String schedule, String endDate) {
        this.drugName = drugName;
        this.startDate = startDate;
        this.schedule = schedule;
        this.endDate = endDate;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Map<String, Object> toMapObject(){

        HashMap<String, Object> result = new HashMap<>();
        result.put("drugName", drugName);
        result.put("startDate", startDate);
        result.put("schedule", schedule);
        result.put("endDate", endDate);
        return  result;
    }
}

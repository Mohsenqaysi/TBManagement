package com.example.mohsenqaysi.tbmanagement.CustomCardView;

/**
 * Created by mohsenqaysi on 3/6/17.
 */

public class DrugsInfo {

    private String drugname;
    private String sartdate;
    private String schedule;
    private String enddate;

    public  DrugsInfo() {

    }
    public DrugsInfo(String drugname, String sartdate, String schedule, String enddate) {
        this.drugname = drugname;
        this.sartdate = sartdate;
        this.schedule = schedule;
        this.enddate = enddate;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getSartdate() {
        return sartdate;
    }

    public void setSartdate(String sartdate) {
        this.sartdate = sartdate;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}

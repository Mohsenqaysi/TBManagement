package com.example.mohsenqaysi.tbmanagement.CustomCardView;

/**
 * Created by mohsenqaysi on 2/13/17.
 */

public class Patients {
    private String image;
    private String fullName;
    private String stageDiagnosis;

    public Patients() {

    }
    public Patients(String image, String fullName, String stageDiagnosis) {
        this.image = image;
        this.fullName = fullName;
        this.stageDiagnosis = stageDiagnosis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStageDiagnosis() {
        return stageDiagnosis;
    }

    public void setStageDiagnosis(String stageDiagnosis) {
        this.stageDiagnosis = stageDiagnosis;
    }
}

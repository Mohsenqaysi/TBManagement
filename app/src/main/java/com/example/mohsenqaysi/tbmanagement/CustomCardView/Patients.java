package com.example.mohsenqaysi.tbmanagement.CustomCardView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohsenqaysi on 2/13/17.
 */

public class Patients {

    public String image;
    public String fullName;
    public String dateOfBirth;
    public String gender;
    public String phoneNumber;
    public String stageDiagnosis;
    public String flatNumber;
    public String address;
    public String city;
    public String area;
    public String postalCode;

    public Patients() {

    }

    public Patients(String image, String fullName, String dateOfBirth, String gender, String phoneNumber, String stageDiagnosis, String flatNumber, String address, String city, String area, String postalCode) {
        this.image = image;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.stageDiagnosis = stageDiagnosis;
        this.flatNumber = flatNumber;
        this.address = address;
        this.city = city;
        this.area = area;
        this.postalCode = postalCode;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStageDiagnosis() {
        return stageDiagnosis;
    }

    public void setStageDiagnosis(String stageDiagnosis) {
        this.stageDiagnosis = stageDiagnosis;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Map<String, Object> toMapObject(){
        String fullAddress = "";
        HashMap<String, Object> result = new HashMap<>();

        result.put("image", image);
        result.put("fullName", fullName);
        result.put("dateOfBirth", dateOfBirth);
        result.put("gender", gender);
        result.put("phoneNumber", phoneNumber);
        result.put("stageDiagnosis", stageDiagnosis);
        result.put("address",fullAddress);
        result.put("flatNumber", flatNumber);
        result.put("address", address);
        result.put("city", city);
        result.put("area", area);
        result.put("postalCode", postalCode);
        return  result;
    }
}

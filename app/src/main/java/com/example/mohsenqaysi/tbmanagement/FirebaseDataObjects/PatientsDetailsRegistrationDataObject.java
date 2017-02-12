package com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohsenqaysi on 2/9/17.
 */

public class PatientsDetailsRegistrationDataObject {

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

    //TODO: Add area variable and Image URL from Firebase Storge

    public Map<String, Object> UserDetails = new HashMap<>();

    public PatientsDetailsRegistrationDataObject() {
    }

    public PatientsDetailsRegistrationDataObject(String fullName, String gender, String phoneNumber, String stageDiagnosis, String flatNumber, String address, String city, String area, String postalCode) {
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

    public Map <String, Object> toMapObject(){
        String fullAddress = "";
        HashMap<String, Object> result = new HashMap<>();

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

    // TODO: concatenate the address into one line
    public String fullAdress(){
       return  "Flat NO. "+flatNumber +"-" + address + " " + city + " "+ area + " "+ postalCode;
    }



}

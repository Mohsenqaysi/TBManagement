package com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohsenqaysi on 2/9/17.
 */

public class PatientsDetailsRegistrationDataObject {

    private String image;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String stageDiagnosis;
    private String flatNumber;
    private String address;
    private String city;
    private String area;
    private String postalCode;


    public PatientsDetailsRegistrationDataObject() {
    }

    public PatientsDetailsRegistrationDataObject(String image, String fullName,String dateOfBirth, String gender, String phoneNumber, String stageDiagnosis, String flatNumber, String address, String city, String area, String postalCode) {
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

    public Map <String, Object> toMapObject(){
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

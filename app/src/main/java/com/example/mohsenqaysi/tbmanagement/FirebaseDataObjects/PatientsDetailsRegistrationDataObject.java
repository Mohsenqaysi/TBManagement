package com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohsenqaysi on 2/9/17.
 */

public class PatientsDetailsRegistrationDataObject {

    public String fullName;
    public int age;
    public String gender;
    public String phoneNumber;
    public int stageDiagnosis;
    public String flatNumber;
    public String address;
    public String city;
    public String postalCode;

    public Map<String, Object> UserDetails = new HashMap<>();

    public PatientsDetailsRegistrationDataObject(String fullName, int age, String gender, String phoneNumber, int stageDiagnosis, String flatNumber, String address, String city, String postalCode) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.stageDiagnosis = stageDiagnosis;
        this.flatNumber = flatNumber;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }


    public Map <String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("fullName", fullName);
        result.put("age", age);
        result.put("gender", gender);
        result.put("phoneNumber", phoneNumber);
        result.put("stageDiagnosis", stageDiagnosis);
        result.put("flatNumber", flatNumber);
        result.put("address", address);
        result.put("city", city);
        result.put("postalCode", postalCode);
        return  result;
    }

}

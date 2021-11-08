package org.tensorflow.lite.examples.detection.models;

public class TrustedPerson {
    private String personName;
    private String phoneNumber;

    public TrustedPerson() {
    }

    public TrustedPerson(String personName, String phoneNumber) {
        this.personName = personName;
        this.phoneNumber = phoneNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

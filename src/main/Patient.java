package main;

import java.time.LocalDate;

public class Patient {
    // personal information
    private String fullName;
    private double weight; // in pounds
    private double height; // in inches
    private LocalDate dateOfBirth; // import time package
    private String address;
    private String phoneNumber;
    private String rxNumber;

    //prescriber information
    private String prescriberName;
    private String prescriberPhoneNumber;

    //pharmacy information
    private String pharmacyName;
    private String pharmacyAddress;

    public Patient(String fullName, double weight, double height, LocalDate dateOfBirth,
                   String address, String phoneNumber, String rxNumber, String prescriberName, String prescriberPhoneNumber,
                   String pharmacyName, String pharmacyAddress) {
        this.fullName = fullName;
        this.weight = weight;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rxNumber = rxNumber;
        this.prescriberName = prescriberName;
        this.prescriberPhoneNumber = prescriberPhoneNumber;
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
    }

    public void printPatientInfo() {
        System.out.println("Patient Information:");
        System.out.println("Full Name: " + fullName);
        System.out.println("Weight: " + weight + " pounds");
        System.out.println("Height: " + height + " inches");
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Rx Number: " + rxNumber);
        System.out.println();
        System.out.println("Prescriber Name: " + prescriberName);
        System.out.println("Prescriber Phone Number: " + prescriberPhoneNumber);
        System.out.println();
        System.out.println("Pharmacy Name: " + pharmacyName);
        System.out.println("Pharmacy Address: " + pharmacyAddress);
        System.out.println();
    }


}

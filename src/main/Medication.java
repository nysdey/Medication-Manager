package main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Medication {
    private String brandName;
    private String genericName;
    private int prescriptionAmount; // in mg
    private int daySupply; // 90 day amount
    private double cost;
    private LocalDate startDate;

    public Medication(String brandName, String genericName, int prescriptionAmount, int daySupply,
                      double cost, LocalDate startDate) {
        this.brandName = brandName;
        this.genericName = genericName;
        this.prescriptionAmount = prescriptionAmount;
        this.daySupply= daySupply;
        this.cost = cost;
        this.startDate = startDate;
    }

    public void printMedicationInfo() {
        System.out.println("Medication Information:");
        System.out.println("Brand Name: " + brandName);
        System.out.println("Generic Name: " + genericName);
        System.out.println("Prescription Amount: " + prescriptionAmount);
        System.out.println("Day Supply: " + daySupply);
        System.out.println("Cost: " + "$" + cost);
        System.out.println("Start Date: " + startDate);
    }

    public int daysLeft() {
        // calculate how many days are left in the current supply
        long daysPassed = ChronoUnit.DAYS.between(startDate, LocalDate.now()); // import class
        return daySupply - (int) daysPassed;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDaySupply() {
        return daySupply;
    }
}

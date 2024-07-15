package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        // create and display the UI on the event dispatch thread
        SwingUtilities.invokeLater(() -> new MedicationApp().createAndShowGUI());
    }
}
class MedicationApp {
    private JFrame frame;
    private JTextField fullNameField, weightField, heightField, dateOfBirthField, addressField,
            phoneNumberField, rxNumberField, prescriberNameField, prescriberPhoneNumberField, pharmacyNameField, pharmacyAddressField;
    private JTextField brandNameField, genericNameField, prescriptionAmountField, daySupplyField, costField, startDateField;
    private JTextArea outputArea;

    public void createAndShowGUI() {
        // initialize the main frame
        frame = new JFrame("Medication Manager");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a container to hold the components
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(0, 2));

        // add patient input fields and labels
        container.add(new JLabel("Full Name: "));
        fullNameField = new JTextField();
        container.add(fullNameField);

        container.add(new JLabel("Weight: "));
        weightField = new JTextField();
        container.add(weightField);

        container.add(new JLabel("Height: "));
        heightField = new JTextField();
        container.add(heightField);

        container.add(new JLabel("Date of Birth (YYYY-MM-DD): "));
        dateOfBirthField = new JTextField();
        container.add(dateOfBirthField);

        container.add(new JLabel("Address: "));
        addressField = new JTextField();
        container.add(addressField);

        container.add(new JLabel("Phone Number: "));
        phoneNumberField = new JTextField();
        container.add(phoneNumberField);

        container.add(new JLabel("Rx Number: "));
        rxNumberField = new JTextField();
        container.add(rxNumberField);

        container.add(new JLabel("Prescriber Name: "));
        prescriberNameField = new JTextField();
        container.add(prescriberNameField);

        container.add(new JLabel("Prescriber Phone Number: "));
        prescriberPhoneNumberField = new JTextField();
        container.add(prescriberPhoneNumberField);

        container.add(new JLabel("Pharmacy Name: "));
        pharmacyNameField = new JTextField();
        container.add(pharmacyNameField);

        container.add(new JLabel("Pharmacy Address: "));
        pharmacyAddressField = new JTextField();
        container.add(pharmacyAddressField);

        // add medication info
        container.add(new JLabel("Brand Name: "));
        brandNameField = new JTextField();
        container.add(brandNameField);

        container.add(new JLabel("Generic Name: "));
        genericNameField = new JTextField();
        container.add(genericNameField);

        container.add(new JLabel("Prescription Amount: "));
        prescriptionAmountField = new JTextField();
        container.add(prescriptionAmountField);

        container.add(new JLabel("Day Supply: "));
        daySupplyField = new JTextField();
        container.add(daySupplyField);

        container.add(new JLabel("Cost: "));
        costField = new JTextField();
        container.add(costField);

        container.add(new JLabel("Start Date: (YYYY-MM-DD): "));
        startDateField = new JTextField();
        container.add(startDateField);

        // add output area for displaying patient and medication info
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        container.add(new JScrollPane(outputArea));

        // add a submit button and its action listener
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        container.add(submitButton);

        // make the frame visible
        frame.setVisible(true);
    }

    private void handleSubmit() {
        // collect patient data from the input fields
        try {
            String fullName = fullNameField.getText();
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthField.getText());
            String address = addressField.getText();
            String phoneNumber = phoneNumberField.getText();
            String rxNumber = rxNumberField.getText();
            String prescriberName = prescriberNameField.getText();
            String prescriberPhoneNumber = prescriberPhoneNumberField.getText();
            String pharmacyName = pharmacyNameField.getText();
            String pharmacyAddress = pharmacyAddressField.getText();

            // create a patient object with the collected data
            Patient patient = new Patient(fullName, weight, height, dateOfBirth, address, phoneNumber,
                    rxNumber, prescriberName, prescriberPhoneNumber, pharmacyName, pharmacyAddress);

            // collect medication information from input fields
            String brandName = brandNameField.getText();
            String genericName = genericNameField.getText();
            int prescriptionAmount = Integer.parseInt(prescriptionAmountField.getText());
            int daySupply = Integer.parseInt(daySupplyField.getText());
            double cost = Double.parseDouble(costField.getText());
            LocalDate startDate = LocalDate.parse(startDateField.getText());

            // create a medication object with the collected data
            Medication medication = new Medication(brandName, genericName, prescriptionAmount, daySupply,
                    cost, startDate);

            // display the collected information in the output area
            outputArea.setText("");
            outputArea.append("Patient Information: \n");
            outputArea.append("Full Name: " + fullName + "\n");
            outputArea.append("Weight: " + weight + "\n");
            outputArea.append("Height: " + height + "\n");
            outputArea.append("Date of Birth: " + dateOfBirth + "\n");
            outputArea.append("Address: " + address + "\n");
            outputArea.append("Phone Number: " + phoneNumber + "\n");
            outputArea.append("Rx Number: " + rxNumber + "\n");
            outputArea.append("Height: " + height + "\n");
            outputArea.append("Prescriber Name: " + prescriberName + "\n");
            outputArea.append("Prescriber Phone Number: " + prescriberPhoneNumber + "\n");
            outputArea.append("Pharmacy Name: " + pharmacyName + "\n");
            outputArea.append("Pharmacy Address: " + pharmacyAddress + "\n");
            outputArea.append("\n");
            outputArea.append("Medication Information: \n");
            outputArea.append("Brand Name: " + brandName + "\n");
            outputArea.append("Generic Name: " + genericName + "\n");
            outputArea.append("Prescription Amount: " + prescriptionAmount + "mg \n");
            outputArea.append("Day Supply: " + daySupply + "\n");
            outputArea.append("Cost: $" + cost + "\n");
            outputArea.append("Start Date: " + startDate + "\n");

            // add a refill reminder
            RefillReminder reminder = new RefillReminder(medication);
            reminder.scheduleReminder();
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Invalid number format. Please check weight, height, day supply, and cost.");
        } catch (DateTimeParseException e) {
            outputArea.setText("Error: Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }

    }
}



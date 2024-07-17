package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Timer;
import java.util.TimerTask;

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
    private Medication medication;
    private Timer countdown;

    public void createAndShowGUI() {
        // initialize the main frame
        frame = new JFrame("Medication Manager");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add patient input fields and labels
        inputPanel.add(new JLabel("Full Name: "));
        fullNameField = new JTextField();
        inputPanel.add(fullNameField);

        inputPanel.add(new JLabel("Weight: "));
        weightField = new JTextField();
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Height: "));
        heightField = new JTextField();
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Date of Birth (YYYY-MM-DD): "));
        dateOfBirthField = new JTextField();
        inputPanel.add(dateOfBirthField);

        inputPanel.add(new JLabel("Address: "));
        addressField = new JTextField();
        inputPanel.add(addressField);

        inputPanel.add(new JLabel("Phone Number: "));
        phoneNumberField = new JTextField();
        inputPanel.add(phoneNumberField);

        inputPanel.add(new JLabel("Rx Number: "));
        rxNumberField = new JTextField();
        inputPanel.add(rxNumberField);

        inputPanel.add(new JLabel("Prescriber Name: "));
        prescriberNameField = new JTextField();
        inputPanel.add(prescriberNameField);

        inputPanel.add(new JLabel("Prescriber Phone Number: "));
        prescriberPhoneNumberField = new JTextField();
        inputPanel.add(prescriberPhoneNumberField);

        inputPanel.add(new JLabel("Pharmacy Name: "));
        pharmacyNameField = new JTextField();
        inputPanel.add(pharmacyNameField);

        inputPanel.add(new JLabel("Pharmacy Address: "));
        pharmacyAddressField = new JTextField();
        inputPanel.add(pharmacyAddressField);

        // add medication info
        inputPanel.add(new JLabel("Brand Name: "));
        brandNameField = new JTextField();
        inputPanel.add(brandNameField);

        inputPanel.add(new JLabel("Generic Name: "));
        genericNameField = new JTextField();
        inputPanel.add(genericNameField);

        inputPanel.add(new JLabel("Prescription Amount: "));
        prescriptionAmountField = new JTextField();
        inputPanel.add(prescriptionAmountField);

        inputPanel.add(new JLabel("Day Supply: "));
        daySupplyField = new JTextField();
        inputPanel.add(daySupplyField);

        inputPanel.add(new JLabel("Cost: "));
        costField = new JTextField();
        inputPanel.add(costField);

        inputPanel.add(new JLabel("Start Date: (YYYY-MM-DD): "));
        startDateField = new JTextField();
        inputPanel.add(startDateField);

        // add output area for displaying patient and medication info
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        inputPanel.add(new JScrollPane(outputArea));

        // add a submit button and its action listener
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        inputPanel.add(submitButton);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(submitButton, BorderLayout.SOUTH);
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

            frame.getContentPane().removeAll();
            displayNonEditableInfo(patient, medication);
            frame.revalidate();
            frame.repaint();
            startCountdown();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Error: Invalid number format. Please check weight, " +
                    "height, day supply, and cost.");
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(frame, "Error: Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }

    }

    private void displayNonEditableInfo(Patient patient, Medication medication) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(new JLabel("Patient Information: \n"));
        infoPanel.add(new JLabel("Full Name: " + patient.getFullName() + "\n"));
        infoPanel.add(new JLabel("Weight: " + patient.getWeight() + "\n"));
        infoPanel.add(new JLabel("Height: " + patient.getHeight() + "\n"));
        infoPanel.add(new JLabel("Date of Birth: " + patient.getDateOfBirth() + "\n"));
        infoPanel.add(new JLabel("Address: " + patient.getAddress() + "\n"));
        infoPanel.add(new JLabel("Phone Number: " + patient.getPhoneNumber() + "\n"));
        infoPanel.add(new JLabel("Rx Number: " + patient.getRxNumber() + "\n"));
        infoPanel.add(new JLabel("Height: " + patient.getHeight() + "\n"));
        infoPanel.add(new JLabel("Prescriber Name: " + patient.getPrescriberName() + "\n"));
        infoPanel.add(new JLabel("Prescriber Phone Number: " + patient.getPrescriberPhoneNumber() + "\n"));
        infoPanel.add(new JLabel("Pharmacy Name: " + patient.getPharmacyName() + "\n"));
        infoPanel.add(new JLabel("Pharmacy Address: " + patient.getAddress() + "\n"));
        infoPanel.add(new JLabel("\n"));
        infoPanel.add(new JLabel("Medication Information: \n"));
        infoPanel.add(new JLabel("Brand Name: " + medication.getBrandName() + "\n"));
        infoPanel.add(new JLabel("Generic Name: " + medication.getGenericName() + "\n"));
        infoPanel.add(new JLabel("Prescription Amount: " + medication.getPrescriptionAmount() + "mg \n"));
        infoPanel.add(new JLabel("Day Supply: " + medication.getDaySupply() + "\n"));
        infoPanel.add(new JLabel("Cost: $" + medication.getCost() + "\n"));
        infoPanel.add(new JLabel("Start Date: " + medication.getStartDate() + "\n"));
    }

    private void startCountdown() {
        Timer countdownTimer = new Timer(true);
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updateDaysLeft());
            }
        }, 0, 1000 * 60 * 60 * 24);
    }

    private void updateDaysLeft() {
        outputArea.setText("Days Left: " + medication.daysLeft());
    }

        // add a refill reminder
        RefillReminder reminder = new RefillReminder(medication);
        RefillReminder.scheduleReminder();

    }
}

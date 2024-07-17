package main;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class RefillReminder {
    private Medication medication; // medication class, called medicine LOL

    public RefillReminder(Medication medication) {
        this.medication = medication;
    }

    public void scheduleReminder() {
        // calculate the date for a two-week warning
        LocalDate warningDate = medication.getStartDate().plusDays(medication.getDaySupply() - 14);
        LocalDate reminderDate = endDate.minusWeeks(2);

        if (LocalDate.now().isAfter(reminderDate) || LocalDate.now().isEqual(reminderDate)) {
            System.out.println("Reminder: You need to refill your prescription in two weeks.");

        // schedule a task to notify the user for two weeks before they run out of meds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Reminder: Your medication will run out in two weeks. Refill your prescription soon.");
            }
        }, java.sql.Date.valueOf(warningDate)); // what does this mean??
    }
}

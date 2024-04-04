import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class LA4 {

    public String getPlan(LocalDate date, String weather, LocalTime firstAppointment, LocalTime lastAppointment) {
        if (isWeekend(date)) {
            if (isGoodWeather(weather) && canTakeTrainOnWeekend()) {
                return "Please take the train to go to the city, and train to get back home on " + date.toString() + ".";
            } else {
                return "Please drive on " + date.toString() + ", and leave the house at least an hour before your first appointment.";
            }
        } else {
            if (isGoodWeather(weather) && canTakeTrainOnWeekday(firstAppointment, lastAppointment)) {
                return "Please take the train to go to the city, and train to get back home on " + date.toString() + ".";
            } else {
                return "Please drive on " + date.toString() + ", and leave the house at least an hour before your first appointment.";
            }
        }
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6; // Saturday or Sunday
    }

    private boolean isGoodWeather(String weather) {
        return !weather.equalsIgnoreCase("Rainy") && !weather.equalsIgnoreCase("Snowy");
    }

    private boolean canTakeTrainOnWeekday(LocalTime firstAppointment, LocalTime lastAppointment) {
        LocalTime firstTrain = LocalTime.of(6, 0);
        LocalTime lastTrain = LocalTime.of(23, 0);
        return firstAppointment.isAfter(firstTrain) && lastAppointment.isBefore(lastTrain);
    }

    private boolean canTakeTrainOnWeekend() {
        // Assume trains are available on weekends from 10 AM to 10 PM
        LocalTime firstTrain = LocalTime.of(10, 0);
        LocalTime lastTrain = LocalTime.of(22, 0);
        return LocalTime.now().isAfter(firstTrain) && LocalTime.now().isBefore(lastTrain);
    }

    public static void main(String[] args) {
        LA4 planner = new LA4();
        Scanner input = new Scanner(System.in);

        LocalDate date;
        String weather;
        LocalTime firstAppointment;
        LocalTime lastAppointment;

        // Input validation for date
        do {
            System.out.println("Enter the date (YYYY-MM-DD): ");
            try {
                date = LocalDate.parse(input.nextLine());
                break; // Break the loop if date is successfully parsed
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
            }
        } while (true);

        // Input validation for weather
        do {
            System.out.println("Enter the weather prediction (Rainy/Snowy/Cloudy/Sunny): ");
            weather = input.nextLine();
            if (!weather.equalsIgnoreCase("Rainy") && !weather.equalsIgnoreCase("Snowy") &&
                !weather.equalsIgnoreCase("Cloudy") && !weather.equalsIgnoreCase("Sunny")) {
                System.out.println("Invalid weather prediction. Please enter Rainy, Snowy, Cloudy, or Sunny.");
            } else {
                break; // Break the loop if weather prediction is valid
            }
        } while (true);

        // Input validation for first appointment time
        do {
            System.out.println("Enter the time of the first appointment (HH:MM): ");
            try {
                firstAppointment = LocalTime.parse(input.nextLine());
                break; // Break the loop if successful
            } catch (Exception e) {
                System.out.println("Invalid time format. Please enter in HH:MM format.");
            }
        } while (true);

        // Input validation for last appointment time
        do {
            System.out.println("Enter the time of the last appointment (HH:MM): ");
            try {
                lastAppointment = LocalTime.parse(input.nextLine());
                break; // Break the loop if time is successfully parsed
            } catch (Exception e) {
                System.out.println("Invalid time format. Please enter in HH:MM format.");
            }
        } while (true);

        // Check if last appointment is after first appointment
        if (lastAppointment.isBefore(firstAppointment)) {
            System.out.println("Last appointment time should be after first appointment time.");
            return;
        }

        String plan = planner.getPlan(date, weather, firstAppointment, lastAppointment);
        System.out.println(plan);

    }
}

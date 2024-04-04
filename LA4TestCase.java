import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class LA4TestCase {

    @Test
    void testGetPlan_Weekend_GoodWeather() {
        LA4 planner = new LA4();
        LocalDate date = LocalDate.of(2024, 4, 6); // Saturday
        String weather = "Sunny";
        LocalTime firstAppointment = LocalTime.of(9, 0);
        LocalTime lastAppointment = LocalTime.of(17, 0);
        String expected = "Please take the train to go to the city, and train to get back home on 2024-04-06.";
        String actual = planner.getPlan(date, weather, firstAppointment, lastAppointment);
        assertEquals(expected, actual);
    }
}
s
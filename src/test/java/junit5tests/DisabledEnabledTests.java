package junit5tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DisabledEnabledTests {

    @Test
    //standard disabling of test, no conditions needed
    @Disabled(value = "Disabled for demo of @Disabled")
    void firstTest() {
        System.out.println("This is the first test method");
    }

    @Test
    //disable test for certain operating system
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled for demo of " + "@DisabledOnOs")
    void secondTest() {
        System.out.println("This is the second test method");
    }

    @Test
    //disable for a property of the system - says it in the name
    @DisabledIfSystemProperty(named = "env", matches = "staging", disabledReason = "Disabled for demo of @DisabledIfSystemProperty")
    void thirdTest() {
        System.out.println("This is the third test method");
    }

    @Test
    //disable test for boolean condition
    @DisabledIf(value = "provider", disabledReason = "Disabled for demo of @DisabledIf")
    void fourthTest() {
        System.out.println("This is the fourth test method");
    }

    @Test
    void fifthTest() {
        System.out.println("This is the fifth test method");
    }

    boolean provider() {
        return LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.THURSDAY);
    }
}

package junit5tests;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.condition.DisabledIf;

public class RepeatedTests {

    //a test we would like to repeat several times
    @RepeatedTest(5)
    void firstRepeatedMethod() {
        System.out.println("We are repeating this test");
    }

    @RepeatedTest(value = 3, name = "Running repetition: {currentRepetition}." + " Total is: {totalRepetitions}")
    @DisplayName("This is a repeated test method")
    void secondRepeatedMethod() {
        System.out.println("We are repeating a new test");
    }

    @RepeatedTest(3)
    //RepetitionInfo comes automatically from JUnit with the attribute
    void thirdRepeatedMethod(RepetitionInfo repetitionInfo) {
        System.out.println("This code will run at each repetition");
        Assumptions.assumingThat(repetitionInfo.getCurrentRepetition() == 3, () -> System.out.println("This code only runs for repetition " + "3"));
    }

}

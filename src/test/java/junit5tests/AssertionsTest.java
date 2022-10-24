package junit5tests;

import listeners.Listener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.jupiter.api.Assertions.*;

//added to run with the listener class
@ExtendWith(Listener.class)
public class AssertionsTest {

    @Test
    void assertEqualsTest() {
        //first parameter is always the expected value, second parameter is always the actual value
        //a third parameter sends a message to the console for a test fail
        //test is expected to fail
        assertEquals("firstString", "secondString", "The String " + "values were not equal");
    }

    @Test
    void assertEqualsListTest() {
        List<String> expectedValues = asList("firstString", "secondString", "thirdString");
        List<String> actualValues = asList("firstString", "secondString", "thirdString");
        assertEquals(expectedValues, actualValues);
    }

    @Test
    void assertArraysEqualsTest() {
        int[] expectedValues = {1, 2, 3};
        int[] actualValues = {1, 2, 3};
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    void assertTrueTest() {
        assertFalse(false);
        //following test will fail
        assertTrue(false, "This boolean condition did not evaluate to true");
    }

    @Test
    void assertThrowsTest() {
        assertThrows(NullPointerException.class, null);
    }

    @Test
        //will see two failures and one pass (whole test will fail, details of failures will be in the console
    void assertAllTest() {
        assertAll(
                () -> assertEquals("firstString", "secondString", "The String " + "values were not equal"),
                () -> assertThrows(NullPointerException.class, null),
                () -> assertTrue(false, "This boolean condition did not evaluate to true")
        );
    }

    @Test
    void assertForMapTest() {
        Map<String, Integer> theMap = new HashMap<>();
        theMap.put("firstKey", 1);
        theMap.put("secondKey", 2);
        theMap.put("thirdKey", 3);

        assertThat(theMap, hasValue(2));
        //expect the following to fail
        assertThat(theMap, hasKey("secondKey1"));
    }

    @Test
    void assertForList() {
        List<String> theList = asList("firstString", "secondString", "thirdString");

        assertThat(theList, hasItem("thirdString"));
    }

    @Test
    void assertForAnyOf() {
        List<String> theList = asList("firstString", "secondString", "thirdString");

        //anyOf passes if any of the conditions pass
        //allOf needs all conditions to pass
        assertThat(theList, anyOf(hasItem("thirdString"), hasItem("noString")));
    }

    @Test
    void assertForContainsAnyOrder() {
        List<String> theList = asList("firstString", "secondString", "thirdString");

        assertThat(theList, containsInAnyOrder("firstString", "secondString", "thirdString"));
    }
}

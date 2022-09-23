package junit5tests;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterisedTests {

    @ParameterizedTest(name = "Run: {index} - value: {arguments}")
    @ValueSource(ints = {1, 5, 6})
    void intValues(int theParam) {
        System.out.println("theParam = " + theParam);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"firstString", "secondString"})
    void stringValues(String theParam) {
        System.out.println("theParam = " + theParam);
    }

    @ParameterizedTest
    //using csv source annotation when creating parameters, so strings are separated with a comma
    @CsvSource(value = {"steve,rogers", "captain,marvel", "bucky,barnes"})
    //parameters should have relevant names for the method
    void csvSource_StringString(String param1, String param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    @CsvSource(value = {"steve,32,true", "captain,21,false", "bucky,5,true"})
    //the underscore shows the reader of the code that these are the input types
    void csvSource_StringIntBoolean(String param1, int param2, boolean param3) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2 + ", param3 = " + param3);
    }

    @ParameterizedTest
    //the single quote allows a comma to be printed from the csv format
    @CsvSource(value = {"captain america,'steve,rogers'", "winter soldier,'bucky,barnes'"})
        void csvSource_StringWithComma(String param1, String param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    //adding delimiter after the curly braces (and with a comma) let's us specify what delimiter we would like
    @CsvSource(value = {"steve?rogers", "bucky?barnes"}, delimiter = '?')
    void csvSource_StringWithDifferentDelimiter(String param1, String param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    @CsvFileSource(files = {"src/test/resources/params/shoppinglist.csv", "src/test/resources/params/shoppinglist2.csv"}, numLinesToSkip = 1)
    void csvFileSource_StringDoubleIntStringString(String name, double price, int quantity, String unitOfMeasure, String provider) {
        System.out.println("name = " + name + ", price = " + price + ", quantity = " + quantity + ", unitOfMeasure = " + unitOfMeasure + ", provider = " + provider);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/params/shoppinglist3.csv", numLinesToSkip = 1, delimiterString = "___")
    void csvFileSource_StringDoubleIntStringStringSpecifiedDelimiter(String name, double price, int quantity, String unitOfMeasure, String provider) {
        System.out.println("name = " + name + ", price = " + price + ", quantity = " + quantity + ", unitOfMeasure = " + unitOfMeasure + ", provider = " + provider);
    }

    @ParameterizedTest
    @MethodSource(value = "sourceString")
    void methodSource_String(String param1) {
        System.out.println("param1 = " + param1);
    }

    @ParameterizedTest
    @MethodSource(value = "sourceStringAsStream")
    void methodSource_StringStream(String param1) {
        System.out.println("param1 = " + param1);
    }

    @ParameterizedTest
    @MethodSource(value = "sourceList_StringDouble")
    void methodSource_StringDoubleList(String param1, double param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    @ParameterizedTest
    @MethodSource(value = "junit5tests.ParamProvider#sourceStream_StreamDouble")
    void methodSource_StringDoubleStream(String param1, double param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
    }

    List<String> sourceString() {
        //processing done here
    return Arrays.asList("tomato", "carrot", "cabbage");
    }

    Stream<String> sourceStringAsStream() {
        //processing
        return Stream.of("beetroot", "apple", "pear");
    }

    List<Arguments> sourceList_StringDouble() {
        //processing
        return Arrays.asList(Arguments.arguments("tomato", 2.0), Arguments.arguments("carrot", 4.5), Arguments.arguments("cabbage", 7.8));
    }
}

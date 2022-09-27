package junit5tests;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderedTestClass1 {

    @BeforeAll
    public void beforeAll() {
        System.out.println("--This is the BeforeAll method");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("---This is the BeforeEach method");
    }

    @AfterAll
    public void afterAll() {
        System.out.println("--This is the AfterAll method");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("---This is the AfterEach method");
    }

    @Test
    public void firstMethod() {
        System.out.println("This is the first test method");
    }

    @Test
    @DisplayName("US1234 - TC12 - this method is the second one")
    public void secondMethod() {
        System.out.println("This is the second test method");
    }

    @Test
    @DisplayName("A display name")
    void thirdTest() {
        System.out.println("Ths is the third method");
    }
}

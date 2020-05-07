package helper;

import org.junit.jupiter.api.*;

public class BeforeAfterTest {

    @BeforeAll
    public static void beforeClass() {
        System.out.println("Before class");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Before each test");
    }

    @Test
    public void test1() {
        System.out.println("First test");
    }

    @Test
    public void test2() {
        System.out.println("Second test");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("After every test");
    }

    @AfterAll
    public static void afterClass() {
        System.out.println("After class");
    }
}

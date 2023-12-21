package org.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void mainTest1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SampleClass test = (new Injector()).inject(new SampleClass(), "resource1");
        test.func();

        String expectedOut =
                """
                Executing procedure, from Sample1Impl
                Executing procedure, from Sample2Impl
                """;
        String actualOut = outContent.toString();
        assertEquals(expectedOut, actualOut);
    }

    @org.junit.jupiter.api.Test
    void mainTest2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SampleClass test = (new Injector()).inject(new SampleClass(), "resource2");
        test.func();

        String expectedOut =
                """
                Executing procedure, from Sample1Impl2
                Executing procedure, from Sample2Impl2
                """;
        String actualOut = outContent.toString();
        assertEquals(expectedOut, actualOut);
    }
}
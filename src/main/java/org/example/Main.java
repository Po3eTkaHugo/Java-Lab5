package org.example;

public class Main {
    public static void main(String[] args) {
        SampleClass test = (new Injector()).inject(new SampleClass(), "resource1");
        test.func();
    }
}

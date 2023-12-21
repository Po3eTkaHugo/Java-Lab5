package org.example;

/***
 * A simple class using the @AutoIjectable annotation.
 */
public class SampleClass {
    /***
     * First class field.
     */
    @AutoInjectable
    private SampleInterface1 field1;
    /***
     * Second class field.
     */
    @AutoInjectable
    private SampleInterface2 field2;

    /***
     * Function calling interface procedures.
     */
    public void func() {
        field1.procedure();
        field2.procedure();
    }

}

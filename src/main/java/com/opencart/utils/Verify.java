package com.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Verify {
    private static final List<AssertionError> errors = new ArrayList<>();
    public static void verify(Runnable assertion){

        try {
            assertion.run();
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());
            errors.add(e);
        }
    }

    public static void verifyAll() {
        if (!errors.isEmpty()) {
          AssertionError combinedError = new AssertionError("Se encontraron los siguientes errores:");
          errors.forEach(combinedError::addSuppressed);
          errors.clear();
          throw combinedError;
        }
    }
}

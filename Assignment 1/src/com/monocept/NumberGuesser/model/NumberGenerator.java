package com.monocept.NumberGuesser.model;

import java.util.Random;

public class NumberGenerator {

    private Random random;

    public NumberGenerator() {
        random = new Random();
    }

    public int generateNumber() {

        return random.nextInt(100) + 1;

    }
}

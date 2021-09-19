package com.example.notes.utility;

import java.util.Random;

public class Utils {
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max) + min;
        return randomNumber;
    }

}

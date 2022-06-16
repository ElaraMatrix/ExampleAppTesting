package utils;

import logger.Log;

import java.util.Random;

public final class Randomizer {

    private Randomizer() {}

    public static String getRandomWord() {
        Log.info("Get random word");
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char tmp = (char) ('a' + random.nextInt('z' - 'a'));
            sb.append(tmp);
        }
        return sb.toString();
    }
}
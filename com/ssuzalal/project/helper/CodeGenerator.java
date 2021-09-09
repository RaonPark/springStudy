package com.ssuzalal.project.helper;

import java.util.concurrent.ThreadLocalRandom;

public class CodeGenerator {
    private final static String[] alphabet = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static StringBuilder generate() {
        StringBuilder code = new StringBuilder();

        for(int i=0; i<6; i++) {
            boolean mode = ThreadLocalRandom.current().nextBoolean();
            if(mode) {
                String x = alphabet[ThreadLocalRandom.current().nextInt(0, 26)];
                code.append(x);
            } else {
                int y = ThreadLocalRandom.current().nextInt(0, 10);
                code.append(y);
            }
        }

        return code;
    }
}

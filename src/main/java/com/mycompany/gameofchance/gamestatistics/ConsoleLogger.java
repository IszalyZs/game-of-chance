package com.mycompany.gameofchance.gamestatistics;

import com.mycompany.gameofchance.logger.Logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public ConsoleLogger() {
    }

    @Override
    public void info(String message) {
        System.out.println(ANSI_BLUE + "INFO " + LocalDateTime.now() + ": " + message + ANSI_RESET);
    }

    @Override
    public void error(String message) {
        System.out.println(ANSI_RED + "ERROR " + LocalDateTime.now() + ": " + message + ANSI_RESET);
    }
}

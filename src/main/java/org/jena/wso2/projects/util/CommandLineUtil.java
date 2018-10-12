package org.jena.wso2.projects.util;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.util.HashMap;
import java.util.Scanner;

public class CommandLineUtil {
    private static final CommandLineUtil COMMAND_LINE_UTIL = new CommandLineUtil();

    private final Scanner scanner;

    private ColoredPrinter inputPrinter;
    private ColoredPrinter infoPrinter;
    private ColoredPrinter errorPrinter;

    private HashMap<Ansi.FColor, ColoredPrinter> colorPrinterMap = new HashMap<>();

    private CommandLineUtil() {
        this.scanner = new Scanner(System.in);
        this.inputPrinter = new ColoredPrinter
                .Builder(0, false)
                .foreground(Ansi.FColor.WHITE)
                .build();
        this.infoPrinter = new ColoredPrinter
                .Builder(0, false)
                .foreground(Ansi.FColor.GREEN)
                .build();
        this.errorPrinter = new ColoredPrinter
                .Builder(0, false)
                .foreground(Ansi.FColor.RED)
                .build();

        this.colorPrinterMap.put(Ansi.FColor.WHITE, inputPrinter);
        this.colorPrinterMap.put(Ansi.FColor.GREEN, infoPrinter);
        this.colorPrinterMap.put(Ansi.FColor.RED, errorPrinter);
    }

    public static final CommandLineUtil getInstance() {
        return COMMAND_LINE_UTIL;
    }

    public TerminalScanner newTerminalScan() {
        return new TerminalScanner(scanner, inputPrinter, infoPrinter, errorPrinter);
    }

    public void println(Ansi.FColor color, String message) {
        ColoredPrinter coloredPrinter = getColoredPrinter(color);
        coloredPrinter.println(message);
    }

    public void printline(Ansi.FColor color) {
        ColoredPrinter coloredPrinter = getColoredPrinter(color);
        coloredPrinter.println("--------------------------------------------------------");
    }

    private ColoredPrinter getColoredPrinter(Ansi.FColor color) {
        ColoredPrinter coloredPrinter = colorPrinterMap.get(color);
        if (coloredPrinter == null) {
            coloredPrinter = new ColoredPrinter
                    .Builder(0, false)
                    .foreground(color)
                    .build();
            colorPrinterMap.put(color, coloredPrinter);
        }
        return coloredPrinter;
    }

    public class TerminalScanner {
        private final Scanner scanner;
        private final ColoredPrinter inputPrinter;
        private final ColoredPrinter infoPrinter;
        private final ColoredPrinter errorPrinter;

        private String message;
        private String pattern;
        private String defaultValue;
        private String errorMessage;

        public TerminalScanner(Scanner scanner, ColoredPrinter inputPrinter, ColoredPrinter infoPrinter, ColoredPrinter errorPrinter) {
            this.scanner = scanner;
            this.inputPrinter = inputPrinter;
            this.infoPrinter = infoPrinter;
            this.errorPrinter = errorPrinter;
        }

        public TerminalScanner withMessage(String message) {
            this.message = message;
            return this;
        }

        public TerminalScanner withPattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public TerminalScanner withDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public TerminalScanner withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public String scan() {
            this.infoPrinter.print(message);

            String input = null;
            boolean valid = false;
            do {
                this.inputPrinter.print("");
                input = this.scanner.nextLine();
                if (input.isEmpty() && this.defaultValue != null) {
                    input = this.defaultValue;
                    break;
                } else {
                    valid = input.matches(pattern) && !input.isEmpty();
                    if (!valid) {
                        this.errorPrinter.println(errorMessage);
                        this.infoPrinter.print(message);
                    }
                }
            } while (!valid);

            return input;
        }
    }
}

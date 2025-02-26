/*
 * @fileoverview    {Baloto}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.selenium.gana.struct;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Description of {@code Baloto}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Baloto {

    public static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
    private Date date;
    private String day;
    private int numberOne;
    private int numberTwo;
    private int numberThree;
    private int numberFour;
    private int numberFive;
    private int numberSix;
    private boolean revancha;

    /**
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
     */
    @Override
    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream printer = new PrintStream(os);
        String output;
        printer.printf("%s        %9s        %02d   %02d   %02d   %02d   %02d   %02d",
                OUTPUT_DATE_FORMAT.format(date), day,
                numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix);

        if (revancha)
            printer.printf("     Revancha");
        output = os.toString();

        return output;
    }

}

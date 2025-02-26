/*
 * @fileoverview    {Lottery}
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
import java.util.Comparator;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Description of {@code Lottery}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Lottery implements Comparable<Lottery> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private String name;
    private Date date;
    private String number;
    private String sign;
    private String serie;
    private int index;

    /**
     * TODO: Description of {@code getIntNumber}.
     *
     * @return
     */
    public int getIntNumber() {
        return Integer.parseInt(number);
    }

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
        //printer.printf("%20s   '%s'   '%4s'   '%s'", name, OUTPUT_DATE_FORMAT.format(date), number, favourite);
        printer.printf("%20s   '%s'   '%4s'", name, DATE_FORMAT.format(date), number);
        output = os.toString();

        return output;
    }

    /**
     * TODO: Description of {@code compareTo}.
     *
     */
    @Override
    public int compareTo(Lottery o) {
        return Comparator.comparing(Lottery::getDate)
                .thenComparing(Lottery::getName)
                .compare(this, o);
    }

}

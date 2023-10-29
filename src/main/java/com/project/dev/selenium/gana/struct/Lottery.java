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
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code Lottery}.
 *
 * @author Dyson Parra
 * @since 11
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Lottery implements Comparable<Lottery> {

    public static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
    private String name;
    private Date date;
    private String number;
    private String favourite;
    private String sign;
    private String favouriteSign;
    private String serie;
    private String giftPlan;
    private int index;

    /**
     * TODO: Definición de {@code getIntNumber}.
     *
     * @return
     */
    public int getIntNumber() {
        return Integer.parseInt(number);
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream printer = new PrintStream(os);
        String output;
        //printer.printf("%20s   '%s'   '%4s'   '%s'", name, OUTPUT_DATE_FORMAT.format(date), number, favourite);
        printer.printf("%20s   '%s'   '%4s'", name, OUTPUT_DATE_FORMAT.format(date), number);
        output = os.toString();

        return output;
    }

    /**
     * TODO: Definición de {@code compareTo}.
     *
     */
    @Override
    public int compareTo(Lottery o) {
        return Comparator.comparing(Lottery::getDate)
                .thenComparing(Lottery::getName)
                .compare(this, o);
    }

}

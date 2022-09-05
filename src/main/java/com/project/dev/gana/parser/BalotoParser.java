/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.gana.parser;

import com.project.dev.gana.struct.Baloto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * TODO: Definición de {@code BalotoParser}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class BalotoParser {

    // https://www.loterias.com/baloto/resultados/2022
    private static final String USER_DIRECTORY = System.getProperty("user.dir").concat("/csv/");
    private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy", new Locale("es", "ES"));
    private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
    private static final int ONE_DAY = 86400000;
    private static final String START_PANDEMIC = "2020-03-27";
    private static final String END_PANDEMIC = "2020-04-27";

    /**
     * TODO: Definición de {@code parseFromFile}.
     *
     * @param filename
     * @return
     */
    public static List<Baloto> parseFromFile(String filename) {
        List<Baloto> results = new ArrayList<>();
        try {
            BufferedReader scriptsRd = new BufferedReader(new FileReader(USER_DIRECTORY + filename));
            String line;
            String day = null;
            String strDate = null;
            String numberOne = null;
            String numberTwo = null;
            String numberThree = null;
            String numberFour = null;
            String numberFive = null;
            String numberSix = null;
            Date inputDate = null;
            String outputDate = null;
            boolean revancha = false;
            int i = 0;
            while (i++ != -1) {
                line = scriptsRd.readLine();
                if (line == null)
                    break;
                //System.out.println("'" + line + "'");

                if (line.equals("*miércoles*") || line.equals("*sábado*") || line.contains("Revancha")) {
                    if (!line.contains("Revancha")) {
                        revancha = false;
                        day = line.replaceAll("\\*", "");
                        strDate = scriptsRd.readLine().split(" <")[0];
                        scriptsRd.readLine();
                        scriptsRd.readLine();
                    } else {
                        revancha = true;
                    }

                    numberOne = scriptsRd.readLine().replaceAll("  \\* ", "");
                    numberTwo = scriptsRd.readLine().replaceAll("  \\* ", "");
                    numberThree = scriptsRd.readLine().replaceAll("  \\* ", "");
                    numberFour = scriptsRd.readLine().replaceAll("  \\* ", "");
                    numberFive = scriptsRd.readLine().replaceAll("  \\* ", "");
                    numberSix = scriptsRd.readLine().replaceAll("  \\* ", "");
                    inputDate = INPUT_DATE_FORMAT.parse(strDate);

                    Baloto nueva = Baloto.builder()
                            .numberOne(Integer.parseInt(numberOne))
                            .numberTwo(Integer.parseInt(numberTwo))
                            .numberThree(Integer.parseInt(numberThree))
                            .numberFour(Integer.parseInt(numberFour))
                            .numberFive(Integer.parseInt(numberFive))
                            .numberSix(Integer.parseInt(numberSix))
                            .day(day)
                            .date(inputDate)
                            .revancha(revancha)
                            .build();
                    results.add(nueva);
                }

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        Collections.sort(results, (Baloto p1, Baloto p2) -> p1.getDate().compareTo(p2.getDate()));

        //for (Baloto aux : results)
        //    System.out.println(aux);
        return results;
    }

    /**
     * TODO: Definición de {@code writeToFiles}.
     *
     * @param results
     */
    public static void writeToFiles(List<Baloto> results) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(USER_DIRECTORY + "baloto/" + "baloto" + ".csv"));
            for (Baloto aux : results) {
                writer.write(aux + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
}

/*
 * @fileoverview    {LotteryParser}
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
package com.project.dev.selenium.gana.parser;

import com.project.dev.selenium.gana.struct.Lottery;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * TODO: Description of {@code LotteryParser}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class LotteryParser {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
    private static final int ONE_DAY = 86400000;
    private static String START_PANDEMIC = "2020-03-27";
    private static String END_PANDEMIC = "2020-04-27";

    /**
     * TODO: Description of {@code parseFromFile}.resultsFilePath
     *
     * @param resultsFilePath
     * @return
     */
    public static Map<String, List<Lottery>> parseFromFile(String resultsFilePath) {
        Map<String, List<Lottery>> results = new HashMap<>();
        String listNameToInsert = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(resultsFilePath));
            String line;
            String[] lineItems;
            String sign = null;
            String serie = null;
            Date inputDate = null;
            int i = 0;
            while (i++ != -1) {
                line = reader.readLine();
                if (line == null)
                    break;
                //System.out.println("'" + line + "'");
                lineItems = line.split(";");

                if (lineItems.length < 3 || lineItems.length > 5) {
                    System.out.println("Error in line " + i);
                    break;
                } else if (lineItems.length == 4) {
                    sign = lineItems[3];
                } else if (lineItems.length == 5 && lineItems[3].equals("SERIE")) {
                    serie = lineItems[4];
                } else {
                    sign = null;
                    serie = null;
                }

                inputDate = DATE_FORMAT.parse(lineItems[0]);

                Lottery nueva = Lottery.builder()
                        .name(lineItems[1])
                        .date(inputDate)
                        .number(lineItems[2])
                        .sign(sign)
                        .serie(serie)
                        .build();

                switch (lineItems[1]) {
                    case "Paisita1":
                    case "Paisita 1":
                    case "Paisita 1 F":
                    case "Pais1f":
                    case "Paisita1f":
                        listNameToInsert = "Paisita 1";
                        break;

                    case "Paisita2":
                    case "Paisita 2":
                    case "Paisita 2 F":
                    case "Pais2f":
                    case "Paisita2f":
                        listNameToInsert = "Paisita 2";
                        break;

                    case "Paisita3":
                    case "Paisita 3":
                    case "Paisita 3 F":
                    case "Pais3f":
                    case "Paisita3f":
                        listNameToInsert = "Paisita 3";
                        break;

                    case "Cafetarde":
                    case "Cafeteritotarde":
                    case "Cafeterito Tarde":
                        listNameToInsert = "Cafeterito Tarde";
                        break;

                    case "Cafenoche":
                    case "Cafeteritonoche":
                    case "Cafeterito Noche":
                        listNameToInsert = "Cafeterito Noche";
                        break;

                    case "Caribdia":
                    case "Caribeña Dia":
                    case "Caribeñadia":
                        listNameToInsert = "Caribeña Dia";
                        break;

                    case "Caribnoche":
                    case "Caribeña Noche":
                    case "Caribeñanoche":
                        listNameToInsert = "Caribeña Noche";
                        break;

                    case "Culona Dia":
                    case "Culonadia":
                        listNameToInsert = "Culona Dia";
                        break;

                    case "Culonanoche":
                    case "Culona Noche":
                        listNameToInsert = "Culona Noche";
                        break;

                    case "Dordia":
                    case "Doradodia":
                    case "Dorado dia":
                    case "Dorado Dia":
                        listNameToInsert = "Dorado Dia";
                        break;

                    case "Dortarde":
                    case "Doradotarde":
                    case "Dorado tarde":
                    case "Dorado Tarde":
                        listNameToInsert = "Dorado Tarde";
                        break;

                    case "Dornoche":
                    case "Doradonoche":
                        listNameToInsert = "Dorado Noche";
                        break;

                    case "Antioquenita 1":
                    case "Antioqueñita 1":
                    case "Antioqueñita1":
                    case "Antioq1":
                        listNameToInsert = "Antioquenita 1";
                        break;

                    case "Antioquenita 2":
                    case "Antioqueñita 2":
                    case "Antioqueñita2":
                    case "Antioq2":
                        listNameToInsert = "Antioquenita 2";
                        break;

                    case "Lotería De Santander":
                    case "Lot De Santander":
                    case "Lot Santander":
                        listNameToInsert = "Lotería De Santander";
                        break;

                    case "Astro Luna":
                    case "Astroluna":
                        listNameToInsert = "Astro Luna";
                        break;

                    case "Astro Sol":
                    case "Astrosol":
                        listNameToInsert = "Astro Sol";
                        break;

                    case "Chontico Millonario":
                        listNameToInsert = "Chontico Día";
                        break;

                    case "Chonticonoche":
                    case "Chonnoche":
                    case "Chontico Noche":
                        listNameToInsert = "Chontico Noche";
                        break;

                    case "Pic3dia":
                    case "Pic3día":
                    case "Pick3dia":
                    case "Pick3día":
                    case "Pick 3 Dia":
                    case "Pick 3 Día":
                        listNameToInsert = "Pick 3 Dia";
                        break;

                    case "Pic4dia":
                    case "Pic4día":
                    case "Pick4dia":
                    case "Pick4día":
                    case "Pick 4 Dia":
                    case "Pick 4 Día":
                        listNameToInsert = "Pick 4 Dia";
                        break;

                    case "Pic3noche":
                    case "Pick3noche":
                    case "Pick 3 Noche":
                        listNameToInsert = "Pick 3 Noche";
                        break;

                    case "Pic4noche":
                    case "Pick4noche":
                    case "Pick 4 Noche":
                        listNameToInsert = "Pick 4 Noche";
                        break;

                    case "Fantastica Dia":
                    case "Fantasticadia":
                    case "Fantdia":
                        listNameToInsert = "Fantastica Dia";
                        break;

                    case "Fantastica Noche":
                    case "Fantasticanoche":
                    case "Fantnoche":
                        listNameToInsert = "Fantastica Noche";
                        break;

                    case "Motilon Noche":
                    case "Motilonnoche":
                    case "Motnoche":
                        listNameToInsert = "Motilon Noche";
                        break;

                    case "Sinuano Dia":
                    case "Sinuanodia":
                    case "Sinudia":
                        listNameToInsert = "Sinuano Dia";
                        break;

                    case "Sinuano Noche":
                    case "Sinuanonoche":
                    case "Sinunoche":
                        listNameToInsert = "Sinuano Noche";
                        break;

                    case "Cruzroja":
                    case "Cruz Roja":
                        listNameToInsert = "Cruz Roja";
                        break;

                    case "a":
                    case "b":
                    case "c":
                        listNameToInsert = "a";
                        break;

                    default:
                        listNameToInsert = lineItems[1];
                        break;
                }

                nueva.setName(listNameToInsert);

                if (!results.containsKey(listNameToInsert))
                    results.put(listNameToInsert, new ArrayList<>());
                results.get(listNameToInsert).add(nueva);

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        results.entrySet().forEach(entry -> Collections.sort(entry.getValue()));
        return results;
    }

    /**
     * TODO: Description of {@code writeToFiles}.
     *
     * @param results
     * @param outputPath
     */
    public static void writeToFiles(Map<String, List<Lottery>> results, String outputPath) {
        BufferedWriter writer;
        long startPandemic = 0;
        long endPandemic = 0;
        try {
            startPandemic = DATE_FORMAT.parse(START_PANDEMIC).getTime();
            endPandemic = DATE_FORMAT.parse(END_PANDEMIC).getTime();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        for (Map.Entry<String, List<Lottery>> entry : results.entrySet()) {
            //System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
            switch (entry.getKey()) {
                // Too few or discontinued.
                case "Cuyabrito":
                case "Cash Dia":
                case "Cash Noche":
                case "Play Four Dia":
                case "Play Four Noche":
                case "Cruz Roja Band":
                case "Ex Colombia":
                case "Ex Nav Boy":
                case "Ex Nav Mede":
                case "Ex Nav Medellin":
                case "Ex Navidad Boy":
                case "Excolombia":
                case "Extra Bogota":
                case "Extra Cauca":
                case "Extra Croja":
                case "Extra Navidad Bogota":
                case "S Extra Navideño Med Fisi":
                case "S Extra Navideño Med Loti":
                case "Sorteo Extraordinario De Navidad":
                case "Super Extra Navideño Medellin":
                case "Ex. Manizales":
                case "Sorteo Extra. Dorado":
                case "Ext.santander":
                case "-cruz Roja Band":
                case "Ext Boyaca":
                case "Ext Tolima":
                case "Extboy":
                case "Extra Colombia":
                case "Extracauca":
                case "Extracroja":
                case "Exttolima":
                case "Sexdor":
                case "Sexnavmedfisi":
                case "Sortextradorado":
                case "Suertudo":
                case "Exnavmed":
                case "Boyaca Fisica":
                case "Exhuicol":
                case "Exmetcol":
                    break;

                // Two days.
                case "Dorado Noche":
                    break;

                //Each eight days.
                case "Lotería De Santander":
                case "Bogotá":
                case "Boyaca":
                case "Cauca":
                case "Cruz Roja":
                case "Cundinam":
                case "Huila":
                case "Manizales":
                case "Medellin":
                case "Meta":
                case "Paisa Lotto":
                case "Paisita 3":
                case "Quindio":
                case "Risaralda":
                case "Santander":
                case "Tolima":
                case "Valle":

           try {
                    START_PANDEMIC = "2020-03-17";
                    END_PANDEMIC = "2020-05-15";
                    try {
                        startPandemic = DATE_FORMAT.parse(START_PANDEMIC).getTime();
                        endPandemic = DATE_FORMAT.parse(END_PANDEMIC).getTime();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    writer = new BufferedWriter(new FileWriter(outputPath + "one_day/" + entry.getKey() + ".csv"));
                    List<Lottery> result = entry.getValue();
                    Lottery aux;
                    for (int i = 0; i < result.size(); i++) {
                        aux = result.get(i);
                        //if (i != 0 && aux.getDate().equals(result.get(i - 1).getDate()))
                        //writer.write("              AGAIN!" + "\n");

                        if (i != result.size() - 1) {
                            if (!aux.getDate().equals(result.get(i + 1).getDate())) {
                                writer.write(aux + "\n");
                                long oldTime = aux.getDate().getTime();
                                long newTime = result.get(i + 1).getDate().getTime();
                                if (oldTime + ONE_DAY * 7 != newTime
                                        && oldTime + ONE_DAY * 6 != newTime
                                        && oldTime + ONE_DAY * 8 != newTime
                                        && oldTime + ONE_DAY * 5 != newTime
                                        && oldTime + ONE_DAY * 9 != newTime
                                        && oldTime + ONE_DAY * 4 != newTime
                                        && oldTime + ONE_DAY * 10 != newTime) {
                                    do {
                                        oldTime += ONE_DAY;
                                        if (oldTime >= startPandemic && oldTime <= endPandemic)
                                            writer.write("       PANDEMIC FOR:   '" + DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                        else
                                            writer.write("            NOT FOR:   '" + DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                    } while (oldTime + ONE_DAY != newTime);
                                }
                            }
                        } else
                            writer.write(aux + "\n");
                    }
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }

                break;

                // All days but one.
                case "Astro Sol":
                case "Cafeterito Tarde":
                case "Chontico Día":
                case "Dorado Dia":
                case "Dorado Tarde":
                case "Fantastica Dia":

            try {
                    START_PANDEMIC = "2020-03-22";
                    END_PANDEMIC = "2020-05-03";
                    try {
                        startPandemic = DATE_FORMAT.parse(START_PANDEMIC).getTime();
                        endPandemic = DATE_FORMAT.parse(END_PANDEMIC).getTime();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    writer = new BufferedWriter(new FileWriter(outputPath + "all_but_one/" + entry.getKey() + ".csv"));
                    List<Lottery> result = entry.getValue();
                    Lottery aux;
                    for (int i = 0; i < result.size(); i++) {
                        aux = result.get(i);
                        //if (i != 0 && aux.getDate().equals(result.get(i - 1).getDate()))
                        //writer.write("              AGAIN!" + "\n");

                        if (i != result.size() - 1) {
                            if (!aux.getDate().equals(result.get(i + 1).getDate())) {
                                writer.write(aux + "\n");
                                long oldTime = aux.getDate().getTime();
                                long newTime = result.get(i + 1).getDate().getTime();
                                if (oldTime + ONE_DAY != newTime
                                        && oldTime + ONE_DAY * 2 != newTime) {
                                    do {
                                        oldTime += ONE_DAY;
                                        if (oldTime >= startPandemic && oldTime <= endPandemic)
                                            writer.write("       PANDEMIC FOR:   '" + DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                        else
                                            writer.write("            NOT FOR:   '" + DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                    } while (oldTime + ONE_DAY != newTime);
                                }
                            }
                        } else
                            writer.write(aux + "\n");
                    }
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
                break;

                // All days
                default:

            try {
                    START_PANDEMIC = "2020-03-24";
                    END_PANDEMIC = "2020-05-03";
                    try {
                        startPandemic = DATE_FORMAT.parse(START_PANDEMIC).getTime();
                        endPandemic = DATE_FORMAT.parse(END_PANDEMIC).getTime();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    writer = new BufferedWriter(new FileWriter(outputPath + "all_days/" + entry.getKey() + ".csv"));
                    List<Lottery> result = entry.getValue();
                    Lottery aux;
                    for (int i = 0; i < result.size(); i++) {
                        aux = result.get(i);
                        //if (i != 0 && aux.getDate().equals(result.get(i - 1).getDate()))
                        //writer.write("              AGAIN!" + "\n");

                        if (i != result.size() - 1) {
                            if (!aux.getDate().equals(result.get(i + 1).getDate())) {
                                //if (aux.compareTo(result.get(i + 1)) != 0) {
                                writer.write(aux + "\n");
                                long oldTime = aux.getDate().getTime();
                                long newTime = result.get(i + 1).getDate().getTime();
                                if (oldTime + ONE_DAY != newTime) {
                                    do {
                                        oldTime += ONE_DAY;
                                        if (oldTime >= startPandemic && oldTime <= endPandemic)
                                            writer.write("       PANDEMIC FOR:   '" + DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                        else
                                            writer.write("            NOT FOR:   '" + DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                    } while (oldTime + ONE_DAY != newTime);
                                }
                            }
                        } else
                            writer.write(aux + "\n");
                    }
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }

                break;
            }

        }
    }

    /**
     * TODO: Description of {@code parseFromResultFile}.
     *
     * @param resultsFilePath
     * @return
     */
    public static List<Lottery> parseFromResultFile(String resultsFilePath) {
        List<Lottery> results = new ArrayList<>();
        try {
            String line;
            String[] lineItems;
            Date inputDate;
            BufferedReader reader = new BufferedReader(new FileReader(resultsFilePath));
            int i = 0;
            while (i++ != -1) {
                line = reader.readLine();
                if (line == null)
                    break;
                //System.out.println("'" + line + "'");
                lineItems = line.split("'");
                inputDate = DATE_FORMAT.parse(lineItems[1]);
                if (lineItems.length % 2 != 0 || lineItems.length < 4)
                    continue;

                Lottery nueva = Lottery.builder()
                        .name(lineItems[0].trim())
                        .date(inputDate)
                        .number(lineItems[3])
                        .sign(null)
                        .serie(null)
                        .build();
                results.add(nueva);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return results;
    }

}

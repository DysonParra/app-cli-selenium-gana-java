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

    private static final String USER_DIRECTORY = System.getProperty("user.dir").concat("/csv/");
    private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("MMMMM dd, yyyy", new Locale("es", "ES"));
    private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
    private static final int ONE_DAY = 86400000;
    private static String START_PANDEMIC = "2020-03-27";
    private static String END_PANDEMIC = "2020-04-27";

    /**
     * TODO: Description of {@code parseFromFile}.
     *
     * @param filename
     * @return
     */
    public static Map<String, List<Lottery>> parseFromFile(String filename) {
        Map<String, List<Lottery>> results = new HashMap<>();
        String listNameToInsert = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(USER_DIRECTORY + filename));
            String line;
            String[] lineItems;
            String sign = null;
            String favouriteSign = null;
            String serie = null;
            String giftPlan = null;
            Date inputDate = null;
            String outputDate = null;
            int i = 0;
            while (i++ != -1) {
                line = reader.readLine();
                if (line == null)
                    break;
                //System.out.println("'" + line + "'");
                lineItems = line.split(";");

                if (lineItems.length % 2 != 0 || (lineItems.length != 6 && lineItems.length != 10)
                        || !(lineItems[2].equals("Número") && lineItems[4].equals("Favorito"))) {
                    System.out.println("Error in line " + i);
                    break;
                }

                if (lineItems.length == 10) {
                    if (lineItems[6].equals("Signo") && lineItems[8].equals("Signo Favorito")) {
                        sign = lineItems[6];
                        favouriteSign = lineItems[8];
                    } else if (lineItems[6].equals("Serie") && lineItems[8].equals("Plan de premios")) {
                        serie = lineItems[6];
                        giftPlan = lineItems[8];
                    } else {
                        System.out.println("Undefined case in line " + i);
                        break;
                    }
                } else {
                    sign = null;
                    favouriteSign = null;
                    serie = null;
                    giftPlan = null;
                }

                inputDate = INPUT_DATE_FORMAT.parse(lineItems[1]);

                Lottery nueva = Lottery.builder()
                        .name(lineItems[0])
                        .date(inputDate)
                        .number(lineItems[3])
                        .favourite(lineItems[5])
                        .sign(sign)
                        .favouriteSign(favouriteSign)
                        .serie(serie)
                        .giftPlan(giftPlan)
                        .build();

                switch (lineItems[0]) {
                    case "Paisita 2":
                    case "Paisita 2 F":
                        listNameToInsert = "Paisita 2";
                        break;

                    case "Antioquenita 1":
                    case "Antioqueñita 1":
                        listNameToInsert = "Antioquenita 1";
                        break;

                    case "Antioquenita 2":
                    case "Antioqueñita 2":
                        listNameToInsert = "Antioquenita 2";
                        break;
                    case "Pick 4 Dia":
                    case "Pick 4 Día":
                        listNameToInsert = "Pick 4 Dia";
                        break;

                    case "Lotería De Santander":
                    case "Lot De Santander":
                    case "Lot Santander":
                        listNameToInsert = "Lotería De Santander";
                        break;

                    default:
                        listNameToInsert = lineItems[0];
                        break;
                }

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
     */
    public static void writeToFiles(Map<String, List<Lottery>> results) {
        BufferedWriter writer;
        long startPandemic = 0;
        long endPandemic = 0;
        try {
            startPandemic = OUTPUT_DATE_FORMAT.parse(START_PANDEMIC).getTime();
            endPandemic = OUTPUT_DATE_FORMAT.parse(END_PANDEMIC).getTime();
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
                    break;

                // Two days.
                case "Dorado Noche":
                    break;

                //Each eight days.
                case "Lot De Santander":
                case "Lot Santander":
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
                        startPandemic = OUTPUT_DATE_FORMAT.parse(START_PANDEMIC).getTime();
                        endPandemic = OUTPUT_DATE_FORMAT.parse(END_PANDEMIC).getTime();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    writer = new BufferedWriter(new FileWriter(USER_DIRECTORY + "one_day/" + entry.getKey() + ".csv"));
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
                                            writer.write("       PANDEMIC FOR:   '" + Lottery.OUTPUT_DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                        else
                                            writer.write("            NOT FOR:   '" + Lottery.OUTPUT_DATE_FORMAT.format(new Date(oldTime)) + "'\n");
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

            try {
                    START_PANDEMIC = "2020-03-22";
                    END_PANDEMIC = "2020-05-03";
                    try {
                        startPandemic = OUTPUT_DATE_FORMAT.parse(START_PANDEMIC).getTime();
                        endPandemic = OUTPUT_DATE_FORMAT.parse(END_PANDEMIC).getTime();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    writer = new BufferedWriter(new FileWriter(USER_DIRECTORY + "all_but_one/" + entry.getKey() + ".csv"));
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
                                            writer.write("       PANDEMIC FOR:   '" + Lottery.OUTPUT_DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                        else
                                            writer.write("            NOT FOR:   '" + Lottery.OUTPUT_DATE_FORMAT.format(new Date(oldTime)) + "'\n");
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

                default:

            try {
                    START_PANDEMIC = "2020-03-24";
                    END_PANDEMIC = "2020-05-03";
                    try {
                        startPandemic = OUTPUT_DATE_FORMAT.parse(START_PANDEMIC).getTime();
                        endPandemic = OUTPUT_DATE_FORMAT.parse(END_PANDEMIC).getTime();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    writer = new BufferedWriter(new FileWriter(USER_DIRECTORY + "all_days/" + entry.getKey() + ".csv"));
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
                                            writer.write("       PANDEMIC FOR:   '" + Lottery.OUTPUT_DATE_FORMAT.format(new Date(oldTime)) + "'\n");
                                        else
                                            writer.write("            NOT FOR:   '" + Lottery.OUTPUT_DATE_FORMAT.format(new Date(oldTime)) + "'\n");
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
     * @param filename
     * @return
     */
    public static List<Lottery> parseFromResultFile(String filename) {
        List<Lottery> results = new ArrayList<>();
        try {
            String line;
            String[] lineItems;
            Date inputDate;
            BufferedReader reader = new BufferedReader(new FileReader(USER_DIRECTORY + filename));
            int i = 0;
            while (i++ != -1) {
                line = reader.readLine();
                if (line == null)
                    break;
                //System.out.println("'" + line + "'");
                lineItems = line.split("'");
                inputDate = OUTPUT_DATE_FORMAT.parse(lineItems[1]);
                /*
                 * -
                 * for (int j = 0; j < lineItems.length; j++) { String aux = lineItems[j];
                 * System.out.println(j + "(" + aux + ")"); }
                 */
                //if (lineItems.length % 2 != 0 || lineItems.length < 6)
                if (lineItems.length % 2 != 0 || lineItems.length < 4)
                    continue;

                Lottery nueva = Lottery.builder()
                        .name(lineItems[0].trim())
                        .date(inputDate)
                        .number(lineItems[3])
                        //.favourite(lineItems[5])
                        .favourite(null)
                        .sign(null)
                        .favouriteSign(null)
                        .serie(null)
                        .giftPlan(null)
                        .build();
                results.add(nueva);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return results;
    }

    /**
     * TODO: Description of {@code parseFromPaisitaResultFile}.
     *
     * @return
     */
    public static List<Lottery> parseFromPaisitaResultFile() {
        List<Lottery> paisita = parseFromResultFile("all_days/Paisita 1.csv");
        List<Lottery> paisita2 = parseFromResultFile("all_days/Paisita 2.csv");
        List<Lottery> paisita3 = parseFromResultFile("one_day/Paisita 3.csv");
        paisita.addAll(paisita2);
        paisita2.clear();
        paisita.addAll(paisita3);
        paisita3.clear();
        Collections.sort(paisita);
        //for (Lottery aux : paisita)
        //  System.out.println(aux);
        return paisita;
    }

    /**
     * TODO: Description of {@code getRepeatedNumbers}.
     *
     * @param results
     */
    public static void getRepeatedNumbers(List<Lottery> results) {
        Map<String, List<Lottery>> repeatedNumbers = new HashMap<>();
        String actualNumber;
        List<Lottery> actualList;
        Lottery aux1, aux2;
        for (int i = 0; i < results.size(); i++) {
            //System.out.println(aux);
            aux1 = results.get(i);
            actualNumber = aux1.getNumber();
            aux1.setIndex(i + 1);
            for (int j = i + 1; j < results.size(); j++) {
                aux2 = results.get(j);
                if (aux2.getNumber().equals(actualNumber)) {
                    if (!repeatedNumbers.containsKey(actualNumber)) {
                        repeatedNumbers.put(actualNumber, new ArrayList<>());
                    }
                    actualList = repeatedNumbers.get(actualNumber);
                    if (!actualList.contains(aux1))
                        actualList.add(aux1);
                    if (!actualList.contains(aux2))
                        actualList.add(aux2);
                }
            }
        }

        long actualDays;
        long minimunDays = 10000;
        long actualGames;
        long minimunGames = 10000;
        int repetitionsQuantiy = 0;
        for (Map.Entry<String, List<Lottery>> entry : repeatedNumbers.entrySet()) {
            repetitionsQuantiy += entry.getValue().size() - 1;
            System.out.println("Number: " + entry.getKey());
            Lottery actual, before;
            for (int i = 0; i < entry.getValue().size(); i++) {
                actual = entry.getValue().get(i);
                System.out.print(Lottery.OUTPUT_DATE_FORMAT.format(actual.getDate()));
                if (i != 0) {
                    before = entry.getValue().get(i - 1);
                    actualDays = (actual.getDate().getTime() - before.getDate().getTime()) / ONE_DAY;
                    actualGames = actual.getIndex() - before.getIndex();
                    System.out.print("   " + actualDays + " days");
                    System.out.print("   " + actualGames + " games");
                    minimunDays = actualDays < minimunDays ? actualDays : minimunDays;
                    minimunGames = actualGames < minimunGames ? actualGames : minimunGames;
                }
                System.out.println("");

            }
            System.out.println("");
        }
        float percentRepetitions = (float) (repetitionsQuantiy * 100.0 / results.size());
        System.out.println("Total Quantity:           " + results.size());
        System.out.println("Repetitions:              " + repetitionsQuantiy);
        System.out.println("Differents:               " + (results.size() - repetitionsQuantiy));
        System.out.println("Minimum days to repeat:   " + minimunDays);
        System.out.println("Minimum games to repeat:  " + minimunGames);
        System.out.println("Percent Repetitions:      " + percentRepetitions);
        System.out.println("Percent differents:       " + (100.0 - percentRepetitions));
        System.out.println("");

    }

    /**
     * TODO: Description of {@code getVariationPercents}.
     *
     * @param results
     */
    public static void getVariationPercents(List<Lottery> results) {
        int thousandStart = 0;
        int thousandEnd = 999;
        int hundredStart = 0;
        int hundredEnd = 99;
        int tenStart = 0;
        int tenEnd = 9;
        int thousandUp = 0;
        int thousandDown = 0;
        int hundredUp = 0;
        int hundredDown = 0;
        int tenUp = 0;
        int tenDown = 0;

        int maxTenUp = 0;
        int maxHundredUp = 0;
        int maxThousandUp = 0;
        int maxTenThousandUp = 0;
        int maxTenDown = 0;
        int maxHundredDown = 0;
        int maxThousandDown = 0;
        int maxTenThousandDown = 0;

        List<String[]> transitions = new ArrayList<>();

        for (int i = 0; i < results.size(); i++)
            if (i != results.size() - 1)
                transitions.add(new String[]{results.get(i).getNumber(), results.get(i + 1).getNumber()});

        Collections.sort(transitions, (String[] p1, String[] p2) -> p1[0].compareTo(p2[0]));

        int actual;
        int next;
        for (int i = 0; i < transitions.size(); i++) {
            actual = Integer.parseInt(transitions.get(i)[0]);
            next = Integer.parseInt(transitions.get(i)[1]);

            if (i == transitions.size() || !(tenStart <= actual && actual <= tenEnd)) {
                System.out.printf("  --> (%04d, %04d):   %3d ↑   %3d ↓   %6.1f %% ↑   %6.2f %% ↓\n", tenStart, tenEnd, tenUp, tenDown, (tenUp * 100.0 / (tenUp + tenDown)), (tenDown * 100.0 / (tenUp + tenDown)));
                tenStart += 10;
                tenEnd += 10;
                tenUp = tenDown = 0;
            }
            if (!(hundredStart <= actual && actual <= hundredEnd)) {
                System.out.printf(" ---> (%04d, %04d):   %3d ↑   %3d ↓   %6.1f %% ↑   %6.2f %% ↓\n", hundredStart, hundredEnd, hundredUp, hundredDown, (hundredUp * 100.0 / (hundredUp + hundredDown)), (hundredDown * 100.0 / (hundredUp + hundredDown)));
                hundredStart += 100;
                hundredEnd += 100;
                hundredUp = hundredDown = 0;
            }
            if (!(thousandStart <= actual && actual <= thousandEnd)) {
                System.out.printf("----> (%04d, %04d):   %3d ↑   %3d ↓   %6.1f %% ↑   %6.2f %% ↓\n\n", thousandStart, thousandEnd, thousandUp, thousandDown, (thousandUp * 100.0 / (thousandUp + thousandDown)), (thousandDown * 100.0 / (thousandUp + thousandDown)));
                thousandStart += 1000;
                thousandEnd += 1000;
                thousandUp = thousandDown = 0;
            }
            //System.out.println(Arrays.toString(transitions.get(i)));
            if (actual < next) {
                tenUp++;
                hundredUp++;
                thousandUp++;
                if (next - actual > 1000)
                    maxTenThousandUp++;
                else if (next - actual > 100)
                    maxThousandUp++;
                else if (next - actual > 10)
                    maxHundredUp++;
                else
                    maxTenUp++;
            } else if (actual > next) {
                tenDown++;
                hundredDown++;
                thousandDown++;
                if (actual - next > 1000)
                    maxTenThousandDown++;
                else if (actual - next > 100)
                    maxThousandDown++;
                else if (actual - next > 10)
                    maxHundredDown++;
                else
                    maxTenDown++;
            }
        }
        System.out.printf("  --> (%04d, %04d):   %3d ↑   %3d ↓   %6.2f %% ↑   %6.1f %% ↓\n", tenStart, tenEnd, tenUp, tenDown, (tenUp * 100.0 / (tenUp + tenDown)), (tenDown * 100.0 / (tenUp + tenDown)));
        System.out.printf(" ---> (%04d, %04d):   %3d ↑   %3d ↓   %6.2f %% ↑   %6.1f %% ↓\n", hundredStart, hundredEnd, hundredUp, hundredDown, (hundredUp * 100.0 / (hundredUp + hundredDown)), (hundredDown * 100.0 / (hundredUp + hundredDown)));
        System.out.printf("----> (%04d, %04d):   %3d ↑   %3d ↓   %6.2f %% ↑   %6.1f %% ↓\n\n", thousandStart, thousandEnd, thousandUp, thousandDown, (thousandUp * 100.0 / (thousandUp + thousandDown)), (thousandDown * 100.0 / (thousandUp + thousandDown)));

        System.out.println("Quantity ↓ MAX    10: " + maxTenDown);
        System.out.println("Quantity ↓ MAX   100: " + maxHundredDown);
        System.out.println("Quantity ↓ MAX  1000: " + maxThousandDown);
        System.out.println("Quantity ↓ MAX  9999: " + maxTenThousandDown);
        System.out.println("Quantity ↑ MAX    10: " + maxTenUp);
        System.out.println("Quantity ↑ MAX   100: " + maxHundredUp);
        System.out.println("Quantity ↑ MAX  1000: " + maxThousandUp);
        System.out.println("Quantity ↑ MAX  9999: " + maxTenThousandUp);
        System.out.println("");

    }

    /**
     * TODO: Description of {@code getMinimunAndMaximum}.
     *
     * @param results
     * @param max
     * @param min
     * @return
     */
    public static Integer[] getMinimunAndMaximum(List<Lottery> results, int min, int max) {
        int minNegative = -10000;
        int maxNegative = 0;
        int minPositive = 10000;
        int maxPositive = 0;
        int tmp = 0;
        for (int i = 0; i < results.size(); i++) {
            if (i != 0) {
                if (results.get(i - 1).getIntNumber() >= min && results.get(i - 1).getIntNumber() <= max) {
                    tmp = results.get(i).getIntNumber() - results.get(i - 1).getIntNumber();
                    //System.out.printf("%s --> %s  =  %5d\n", results.get(i - 1).getNumber(), results.get(i).getNumber(), tmp);
                    if (tmp < 0) {
                        maxNegative = tmp < maxNegative ? tmp : maxNegative;
                        minNegative = tmp > minNegative ? tmp : minNegative;
                    } else if (tmp > 0) {
                        minPositive = tmp < minPositive ? tmp : minPositive;
                        maxPositive = tmp > maxPositive ? tmp : maxPositive;
                    }
                }
            }
        }
        System.out.printf("Number              (%5d, %5d)\n", min, max);
        System.out.printf("Transition UP   --> (%5d, %5d)\n", minPositive, maxPositive);
        System.out.printf("Transition DOWN --> (%5d, %5d)\n\n", maxNegative, minNegative);
        return new Integer[]{min, max, minPositive, maxPositive, maxNegative, minNegative};

    }

    /**
     * TODO: Description of {@code getMinimunAndMaximum}.
     *
     * @param results
     * @param thousand
     * @param number
     * @return
     */
    public static Integer[] getMinimunAndMaximum(List<Lottery> results, int number, boolean thousand) {
        int min = 0;
        int max = 0;
        if (thousand) {
            min = number - (number % 1000);
            max = min + 999;
        } else {
            min = number - (number % 100);
            max = min + 99;
        }
        return getMinimunAndMaximum(results, min, max);
    }

    /**
     * TODO: Description of {@code getMinimunAndMaximum}.
     *
     * @param results
     * @return
     */
    public static Integer[] getMinimunAndMaximum(List<Lottery> results) {
        return getMinimunAndMaximum(results, 0, 9999);
    }

    /**
     * TODO: Description of {@code getFaultNumbers}.
     *
     * @param results
     * @return
     */
    public static List<Integer> getFaultNumbers(List<Lottery> results) {
        List<Integer> fault = new ArrayList<>();
        boolean[] numbers = new boolean[10000];
        results.forEach(aux -> numbers[aux.getIntNumber()] = true);

        //System.out.println("\n\nFault numbers:");
        int faultQuantity = 0;
        for (int i = 0; i < numbers.length; i++)
            if (!numbers[i]) {
                faultQuantity++;
                //System.out.printf("%04d\n", i);
                fault.add(i);
            }
        //System.out.println("Fault: " + faultQuantity);
        return fault;
    }

    /**
     * TODO: Description of {@code selectFaultNumbers}.
     *
     * @param fault
     * @param quantity
     * @param number
     * @param up
     * @param maxAndMin
     */
    public static void selectFaultNumbers(List<Integer> fault, int number, Integer[] maxAndMin, boolean up, int quantity) {

        int min = number;
        int max = number;
        min += up ? maxAndMin[2] : maxAndMin[4];
        max += up ? maxAndMin[3] : maxAndMin[5];
        int start = -1;
        int end = 10000;

        for (int i = 0; i < fault.size(); i++) {
            //System.out.println(fault.get(i));
            if (fault.get(i) >= min) {
                start = i;
                break;
            }
        }

        for (int i = fault.size() - 1; i >= 0; i--) {
            //System.out.println(fault.get(i));
            if (fault.get(i) <= max) {
                end = i;
                break;
            }
        }

        System.out.println("Number:   " + number);
        System.out.println("Min:      " + min);
        System.out.println("Max:      " + max);
        System.out.println("Start:    " + start);
        System.out.println("End:      " + end);
        System.out.println("Quantity: " + (end - start));

        List<Integer> aleatories = new ArrayList<>();

        for (int i = 0; i < quantity; i++)
            while (true) {
                int aleat = (int) ((Math.random() * ((end - start) + 1)) + start);
                if (!aleatories.contains(aleat)) {
                    aleatories.add(aleat);
                    break;
                }
            }
        System.out.println("Numbers:");
        aleatories.forEach(aleat -> System.out.println(aleat));
        System.out.println("");

    }

    /**
     * TODO: Description of {@code analizeResultsList}.
     *
     * @param results
     * @param thousand
     * @param quantity
     */
    public static void analizeResultsList(List<Lottery> results, boolean thousand, int quantity) {

        //getMinimunAndMaximum(results);
        //getRepeatedNumbers(results);
        getVariationPercents(results);
        Integer[] maxAndMin = getMinimunAndMaximum(results, results.get(results.size() - 1).getIntNumber(), thousand);
        List<Integer> fault = getFaultNumbers(results);
        selectFaultNumbers(fault, results.get(results.size() - 1).getIntNumber(), maxAndMin, true, quantity);
        selectFaultNumbers(fault, results.get(results.size() - 1).getIntNumber(), maxAndMin, false, quantity);
    }

}

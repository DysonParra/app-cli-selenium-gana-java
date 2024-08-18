/*
 * @fileoverview    {LotteryAnalyzer}
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
package com.project.dev.selenium.gana.analyzer;

import com.project.dev.selenium.gana.struct.Lottery;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Description of {@code LotteryAnalyzer}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class LotteryAnalyzer {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final int ONE_DAY = 86400000;

    /**
     * TODO: Description of {@code getRepeatedNumbers}.
     *
     * @param results
     */
    public static void getRepeatedNumbers(List<Lottery> results) {
        Map<String, List<Lottery>> repeatedNumbers = new HashMap<>();
        String currentNumber;
        List<Lottery> currentList;
        Lottery aux1, aux2;
        for (int i = 0; i < results.size(); i++) {
            //System.out.println(aux);
            aux1 = results.get(i);
            currentNumber = aux1.getNumber();
            aux1.setIndex(i + 1);
            for (int j = i + 1; j < results.size(); j++) {
                aux2 = results.get(j);
                if (aux2.getNumber().equals(currentNumber)) {
                    if (!repeatedNumbers.containsKey(currentNumber)) {
                        repeatedNumbers.put(currentNumber, new ArrayList<>());
                    }
                    currentList = repeatedNumbers.get(currentNumber);
                    if (!currentList.contains(aux1))
                        currentList.add(aux1);
                    if (!currentList.contains(aux2))
                        currentList.add(aux2);
                }
            }
        }

        long currentDays;
        long minimunDays = 10000;
        long currentGames;
        long minimunGames = 10000;
        int repetitionsQuantiy = 0;
        for (Map.Entry<String, List<Lottery>> entry : repeatedNumbers.entrySet()) {
            repetitionsQuantiy += entry.getValue().size() - 1;
            System.out.println("Number: " + entry.getKey());
            Lottery current, before;
            for (int i = 0; i < entry.getValue().size(); i++) {
                current = entry.getValue().get(i);
                System.out.print(DATE_FORMAT.format(current.getDate()));
                if (i != 0) {
                    before = entry.getValue().get(i - 1);
                    currentDays = (current.getDate().getTime() - before.getDate().getTime()) / ONE_DAY;
                    currentGames = current.getIndex() - before.getIndex();
                    System.out.print("   " + currentDays + " days");
                    System.out.print("   " + currentGames + " games");
                    minimunDays = currentDays < minimunDays ? currentDays : minimunDays;
                    minimunGames = currentGames < minimunGames ? currentGames : minimunGames;
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

        int current;
        int next;
        for (int i = 0; i < transitions.size(); i++) {
            current = Integer.parseInt(transitions.get(i)[0]);
            next = Integer.parseInt(transitions.get(i)[1]);

            if (i == transitions.size() || !(tenStart <= current && current <= tenEnd)) {
                System.out.printf("  --> (%04d, %04d):   %3d ↑   %3d ↓   %6.1f %% ↑   %6.2f %% ↓\n", tenStart, tenEnd, tenUp, tenDown, (tenUp * 100.0 / (tenUp + tenDown)), (tenDown * 100.0 / (tenUp + tenDown)));
                tenStart += 10;
                tenEnd += 10;
                tenUp = tenDown = 0;
            }
            if (!(hundredStart <= current && current <= hundredEnd)) {
                System.out.printf(" ---> (%04d, %04d):   %3d ↑   %3d ↓   %6.1f %% ↑   %6.2f %% ↓\n", hundredStart, hundredEnd, hundredUp, hundredDown, (hundredUp * 100.0 / (hundredUp + hundredDown)), (hundredDown * 100.0 / (hundredUp + hundredDown)));
                hundredStart += 100;
                hundredEnd += 100;
                hundredUp = hundredDown = 0;
            }
            if (!(thousandStart <= current && current <= thousandEnd)) {
                System.out.printf("----> (%04d, %04d):   %3d ↑   %3d ↓   %6.1f %% ↑   %6.2f %% ↓\n\n", thousandStart, thousandEnd, thousandUp, thousandDown, (thousandUp * 100.0 / (thousandUp + thousandDown)), (thousandDown * 100.0 / (thousandUp + thousandDown)));
                thousandStart += 1000;
                thousandEnd += 1000;
                thousandUp = thousandDown = 0;
            }
            //System.out.println(Arrays.toString(transitions.get(i)));
            if (current < next) {
                tenUp++;
                hundredUp++;
                thousandUp++;
                if (next - current > 1000)
                    maxTenThousandUp++;
                else if (next - current > 100)
                    maxThousandUp++;
                else if (next - current > 10)
                    maxHundredUp++;
                else
                    maxTenUp++;
            } else if (current > next) {
                tenDown++;
                hundredDown++;
                thousandDown++;
                if (current - next > 1000)
                    maxTenThousandDown++;
                else if (current - next > 100)
                    maxThousandDown++;
                else if (current - next > 10)
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

/*
 * @fileoverview    {Application}
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
package com.project.dev;

import com.project.dev.selenium.gana.parser.LotteryParser;
import com.project.dev.selenium.gana.processor.SeleniumProcessor;

/**
 * TODO: Definición de {@code Application}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class Application {

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //https://www.gana.com.co/resultados
        // Pandemia 27-03-2020 --> 27-04-2020.
        //BalotoParser.writeToFiles(BalotoParser.parseFromFile("baloto.html"));
        // One day.
        //SeleniumProcessor.getGanaData(args, "result.csv", "20-03-2013", "05-04-2013", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "09-04-2014", "27-04-2014", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "22-12-2014", "10-01-2015", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "22-12-2014", "10-01-2015", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "25-03-2015", "10-04-2015", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "10-12-2015", "10-01-2016", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "16-03-2016", "03-04-2016", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "16-12-2016", "31-12-2016", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "05-04-2017", "23-04-2017", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "23-03-2018", "08-04-2018", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "01-12-2018", "29-12-2018", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "10-04-2019", "28-04-2019", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "13-12-2019", "01-03-2020", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "13-12-2019", "01-03-2020", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "24-03-2021", "11-04-2021", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "06-04-2022", "24-04-2022", false);
        // All day.
        //SeleniumProcessor.getGanaData(args, "result.csv", "28-03-2013", "30-03-2013", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "17-04-2014", "19-04-2014", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "02-04-2015", "04-04-2015", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "24-03-2016", "26-03-2016", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "31-12-2013", "02-01-2014", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "31-12-2014", "02-01-2015", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "31-12-2015", "02-01-2016", false);
        //SeleniumProcessor.getGanaData(args, "result.csv", "31-12-2017", "02-01-2018", false);

        SeleniumProcessor.getGanaData(args, "result.csv", "11-11-2022", "11-11-2022", false);
        LotteryParser.writeToFiles(LotteryParser.parseFromFile("result.csv"));
        String[] paths = {
            //"one_day/Medellin.csv",
            //"all_days/Antioquenita 1.csv",
            "all_days/Antioquenita 2.csv",
            //"all_days/Astro Luna.csv",
            //"all_days/Cafeterito Noche.csv",
            //"all_days/Chontico Millonario.csv",
            //"all_days/Chontico Noche.csv",
            //"all_days/Paisita 1.csv",
            //"all_days/Paisita 2.csv",
            //"all_days/Pick 3 Dia.csv",
            //"all_days/Pick 3 Noche.csv",
            //"all_days/Pick 4 Dia.csv",
            //"all_days/Pick 4 Noche.csv",
            //"all_days/Pijao.csv"
        };
        //for (String lottery : paths)
        //    LotteryParser.analizeResultsList(LotteryParser.parseFromResultFile(lottery), true, 5);
        //LotteryParser.analizeResultsList(LotteryParser.parseFromPaisitaResultFile(), true, 5);
    }

}

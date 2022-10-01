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
package com.project.dev;

import com.project.dev.selenium.gana.parser.LotteryParser;
import com.project.dev.selenium.gana.processor.SeleniumProcessor;

/**
 * TODO: Definición de {@code Application}.
 *
 * @author Dyson Parra
 * @since 1.8
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

        SeleniumProcessor.getGanaData(args, "result.csv", "25-09-2022", "27-09-2022", false);
        LotteryParser.writeToFiles(LotteryParser.parseFromFile("result.csv"));
        //LotteryParser.analizeResultsList(LotteryParser.parseFromResultFile("one_day/Medellin.csv"), true);
        //LotteryParser.analizeResultsList(LotteryParser.parseFromResultFile("all_days/Paisita 2.csv"), true);
        //LotteryParser.analizeResultsList(LotteryParser.parseFromPaisitaResultFile(), true);
    }

}

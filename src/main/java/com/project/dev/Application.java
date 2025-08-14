/*
 * @overview        {Application}
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

import com.project.dev.selenium.gana.analyzer.LotteryAnalyzer;
import com.project.dev.selenium.gana.parser.LotteryParser;
import com.project.dev.selenium.generic.processor.SeleniumProcessor;

/**
 * TODO: Description of {@code Application}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public class Application {

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos pasados por consola.
     */
    public static void main(String[] args) {
        // Pandemia 27-03-2020 --> 27-04-2020.
        //BalotoParser.writeToFiles(BalotoParser.parseFromFile("baloto.html"));
        //SeleniumProcessor.run(args);
        LotteryParser.writeToFiles(LotteryParser.parseFromFile("res\\output\\result.csv"), "res\\output\\");
        String[] paths = {
            //"res\\output\\one_day\\Medellin.csv",
            //"res\\output\\all_days\\Antioquenita 1.csv",
            "res\\output\\all_days\\Antioquenita 2.csv",
            //"res\\output\\all_days\\Astro Luna.csv",
            //"res\\output\\all_days\\Cafeterito Noche.csv",
            //"res\\output\\all_days\\Caribeña Dia.csv",
            //"res\\output\\all_days\\Caribeña Noche.csv",
            //"res\\output\\all_days\\Chontico Noche.csv",
            //"res\\output\\all_days\\Culona Dia.csv",
            //"res\\output\\all_days\\Culona Noche.csv",
            //"res\\output\\all_days\\Fantastica Noche.csv",
            //"res\\output\\all_days\\Motilon Noche.csv",
            //"res\\output\\all_days\\Motilon.csv",
            //"res\\output\\all_days\\Paisita 1.csv",
            //"res\\output\\all_days\\Paisita 2.csv",
            //"res\\output\\all_days\\Pick 3 Dia.csv",
            //"res\\output\\all_days\\Pick 3 Noche.csv",
            //"res\\output\\all_days\\Pick 4 Dia.csv",
            //"res\\output\\all_days\\Pick 4 Noche.csv",
            //"res\\output\\all_days\\Pijao.csv",
            //"res\\output\\all_days\\Saman.csv",
            //"res\\output\\all_days\\Sinuano Dia.csv",
            //"res\\output\\all_days\\Sinuano Noche.csv",
        };
        for (String lottery : paths)
            LotteryAnalyzer.analizeResultsList(LotteryParser.parseFromResultFile(lottery), true, 5);
    }

}

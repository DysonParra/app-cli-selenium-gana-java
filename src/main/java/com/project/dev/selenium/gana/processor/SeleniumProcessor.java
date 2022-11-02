/*
 * @fileoverview {SeleniumProcessor} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {SeleniumProcessor} fue realizada el 31/07/2022.
 * @Dev - La primera version de {SeleniumProcessor} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.selenium.gana.processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * TODO: Definición de {@code SeleniumProcessor}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class SeleniumProcessor {

    private static final String USER_DIRECTORY = System.getProperty("user.dir").concat("/csv");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat DAY_MONTH_FORMAT = new SimpleDateFormat("dd-MM");
    private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");

    /**
     * TODO: Definición de {@code getGanaData}.
     *
     * @param args
     * @param replace
     * @param outputFileName
     * @param endDate
     * @param startDate
     * @throws java.lang.InterruptedException
     */
    public static void getGanaData(String[] args, String outputFileName, String startDate, String endDate, boolean replace) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(USER_DIRECTORY.concat("/").concat(outputFileName), !replace));
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

            int tries;
            tries = 0;
            // Navigate to Url.
            while (tries != 3) {
                try {
                    driver.get("https://www.gana.com.co/resultados");
                    //driver.get("https://www.farmaciaspasteur.com.co/" + strExpresion);
                    break;
                } catch (Exception e) {
                    tries++;
                }
            }

            try {
                Thread.sleep(10000);
            } catch (Exception e) {
            }

            //Date start = DATE_FORMAT.parse("07-02-2013");
            Date start = DATE_FORMAT.parse(startDate);
            Date end = DATE_FORMAT.parse(endDate);

            tries = 0;
            while (tries != 3) {
                try {
                    WebElement close = driver.findElement(By.className("swal2-close"));
                    close.click();
                    break;
                } catch (Exception e) {
                    //e.printStackTrace(System.out);
                    System.out.println("Not clic on 'exit ");
                    Thread.sleep(2000);
                    tries++;
                }
            }

            if (start.compareTo(end) <= 0) {
                Date actualDate = start;
                tries = 0;
                while (actualDate.compareTo(end) <= 0) {
                    WebElement calendar = driver.findElement(By.className("ant-calendar-picker-input"));
                    try {
                        calendar.click();
                    } catch (Exception e) {
                        //e.printStackTrace(System.out);
                        System.out.println("Not clic on 'calendar'");
                    }

                    try {
                        sendNewDateToCalendar(driver, actualDate);
                        tries++;
                        //Thread.sleep(1000);
                        //System.out.println(actualDate);
                    } catch (Exception e) {
                    }

                    try {
                        WebElement root = driver.findElement(By.id("root"));
                        root.click();
                    } catch (Exception e) {
                        //e.printStackTrace(System.out);
                        System.out.println("Not clic on 'root'");
                    }
                    Thread.sleep(2000);

                    try {
                        if (getResults(driver, writer) || tries >= 5) {
                            actualDate.setTime(actualDate.getTime() + 86400000);
                            tries = 0;
                        }
                    } catch (Exception e) {
                    }
                }
            }
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        } finally {
            driver.quit();
        }
    }

    /**
     * TODO: Definición de {@code getGanaData}.
     *
     * @param args
     * @param endDate
     * @param outputFileName
     * @param startDate
     * @throws java.lang.InterruptedException
     */
    public static void getGanaData(String[] args, String outputFileName, String startDate, String endDate) throws InterruptedException {
        getGanaData(args, outputFileName, startDate, endDate, true);
    }

    /**
     * TODO: Definición de {@code sendNewDateToCalendar}.
     *
     * @param driver
     * @param newDate
     * @throws java.text.ParseException
     */
    public static void sendNewDateToCalendar(WebDriver driver, Date newDate) throws ParseException {
        WebElement calendarContainer = driver.findElement(By.className("ant-calendar-input"));

        String newDateStr = DATE_FORMAT.format(newDate);
        String oldDateStr = calendarContainer.getAttribute("value");
        Date oldDate = DATE_FORMAT.parse(oldDateStr);

        System.out.println("");
        System.out.println("Old date:" + oldDateStr);
        System.out.println("New date:" + newDateStr);

        String oldYear = YEAR_FORMAT.format(oldDate);
        String newYear = YEAR_FORMAT.format(newDate);

        if (!oldYear.equals(newYear)) {
            for (int i = 0; i < 4; i++)
                calendarContainer.sendKeys(Keys.BACK_SPACE);
            calendarContainer.sendKeys(newYear);
        }

        String oldDayMonth = DAY_MONTH_FORMAT.format(oldDate);
        String newDayMonth = DAY_MONTH_FORMAT.format(newDate);

        if (!oldDayMonth.equals(newDayMonth)) {
            calendarContainer.sendKeys(Keys.HOME);
            for (int i = 0; i < 5; i++)
                calendarContainer.sendKeys(Keys.DELETE);
            calendarContainer.sendKeys(newDayMonth);
        }
        //System.out.println(calendarContainer.getAttribute("value"));
    }

    /**
     * TODO: Definición de {@code getResults}.
     *
     * @param driver
     * @param writer
     * @return
     * @throws java.text.ParseException
     */
    public static boolean getResults(WebDriver driver, BufferedWriter writer) throws ParseException {
        List<WebElement> results = driver.findElements(By.className("resultado-loteria"));
        System.out.println("Found elements are: " + results.size());
        results.forEach(result -> {
            //System.out.println(result);
            String text = result.getText().replace("\n", ";");
            System.out.println(text);
            try {
                writer.write(text + "\n");
                writer.flush();
            } catch (Exception e) {
            }
        });

        return !results.isEmpty();
    }
}

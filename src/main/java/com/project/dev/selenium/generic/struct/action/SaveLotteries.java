/*
 * @overview        {SaveLotteries}
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
package com.project.dev.selenium.generic.struct.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.dev.selenium.generic.struct.Action;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * TODO: Description of {@code SaveLotteries}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveLotteries extends Action {

    @JsonProperty(value = "output-path")
    protected String outputPath;
    @JsonProperty(value = "output-file-name")
    protected String outputFileName;
    @JsonProperty(value = "dom-elements-xpath")
    protected String domElementsXpath;
    @JsonProperty(value = "current-date")
    protected String currentDate;

    /**
     * Ejecuta una acción en el elemento de la página actual.
     *
     * @param driver   es el driver del navegador.
     * @param element  es el {@code WebElement} que se le va a ejecutar dicha acción.
     * @param flagsMap contiene las {@code Flag} pasadas por consola.
     * @return {@code true} si se ejecuta la acción correctamente.
     * @throws Exception si ocurre algún error ejecutando la acción indicada.
     */
    @Override
    public boolean executeAction(@NonNull WebDriver driver, @NonNull WebElement element, Map<String, String> flagsMap) throws Exception {
        System.out.println("        Current page: " + driver.getCurrentUrl());
        new File(outputPath).mkdirs();
        try (FileOutputStream fos = new FileOutputStream(outputPath + "\\" + outputFileName, true);
                OutputStreamWriter osr = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(osr);) {
            getResults(driver, writer);
            //writer.write(driver.getCurrentUrl() + "\n");
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    /**
     * TODO: Description of method {@code getResults}.
     *
     * @param driver
     * @param writer
     * @return
     * @throws java.text.ParseException
     */
    public boolean getResults(WebDriver driver, BufferedWriter writer) throws ParseException {
        List<WebElement> results = driver.findElements(By.xpath(domElementsXpath));
        System.out.println(String.format("        Found elements are: %s (%s)",
                results.size(), currentDate));
        results.forEach(result -> {
            //System.out.println(result);
            String text = result.getText().replace("\n", ";");
            System.out.println("        " + text);
            try {
                writer.write(currentDate + ";" + text + "\n");
                writer.flush();
            } catch (Exception e) {
            }
        });

        return !results.isEmpty();
    }

}

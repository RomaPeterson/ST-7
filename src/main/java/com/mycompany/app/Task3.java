package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.time.Duration;

public class Task3 {
    private static final String URL =
        "https://api.open-meteo.com/v1/forecast"
        + "?latitude=56&longitude=44"
        + "&hourly=temperature_2m,rain"
        + "&current=cloud_cover"
        + "&timezone=Europe%2FMoscow"
        + "&forecast_days=1"
        + "&wind_speed_unit=ms";

    public static void execute() {
        System.out.println("=== Задание 3 ===");
        WebDriver driver = App.createDriver();
        try {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement pre = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("pre")));

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(pre.getText().trim());
            JSONObject hourly = (JSONObject) root.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-4s\t%-20s\t%-14s\t%s%n",
                "№", "Дата/время", "Температура", "Осадки (мм)"));

            for (int i = 0; i < times.size(); i++) {
                sb.append(String.format("%-4d\t%-20s\t%.1f°C\t\t%.2f%n",
                    i + 1,
                    times.get(i),
                    ((Number) temps.get(i)).doubleValue(),
                    ((Number) rains.get(i)).doubleValue()));
            }

            System.out.println(sb);

            File dir = new File("result");
            if (!dir.exists()) dir.mkdirs();
            try (FileWriter fw = new FileWriter("result/forecast.txt", false)) {
                fw.write(sb.toString());
                System.out.println("Сохранено в result/forecast.txt");
            }
        } catch (Exception e) {
            System.out.println("Ошибка в задании 3: " + e);
        } finally {
            driver.quit();
        }
    }
}

package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Task2 {
    public static void execute() {
        System.out.println("=== Задание 2 ===");
        WebDriver driver = App.createDriver();
        try {
            driver.get("https://api.ipify.org/?format=json");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement pre = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("pre")));
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(pre.getText().trim());
            System.out.println("Ваш IP-адрес: " + obj.get("ip"));
        } catch (Exception e) {
            System.out.println("Ошибка в задании 2: " + e);
        } finally {
            driver.quit();
        }
    }
}

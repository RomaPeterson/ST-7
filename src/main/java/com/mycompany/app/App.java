package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App {

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }

    public static void main(String[] args) {
        System.out.println("=== Задание 1 ===");
        WebDriver driver = createDriver();
        try {
            driver.get("https://www.calculator.net/password-generator.html");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement passwordElem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".verybigtext > b")));
            System.out.println("Сгенерированный пароль: " + passwordElem.getText().trim());
        } catch (Exception e) {
            System.out.println("Ошибка в задании 1: " + e);
        } finally {
            driver.quit();
        }

        System.out.println();
        Task2.execute();
        System.out.println();
        Task3.execute();
    }
}

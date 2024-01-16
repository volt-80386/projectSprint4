package project.sprint4.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class Main {
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    private final WebDriver webDriver;
    public Main(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Открыть главную страницу
    public Main openPage() {
        webDriver.get(URL);
        return this;
    }

    // Прокрутить до "Вопросов о главном"
    public Main goToQuestions() {
        WebElement element = webDriver.findElement(By.className("Home_FAQ__3uVm4"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    // Нажать на вопрос
    public void clickOnAccordionItem(int i) {
        webDriver.findElement(By.id(String.format("accordion__heading-%d", i))).click();
    }

    // Запросить состояние атрибута aria-expanded - индикатора раскрытия/не раскрытия
    public String checkExpandedAccordionItem(int i) {
        return webDriver.findElement(By.id(String.format("accordion__heading-%d", i))).getAttribute("aria-expanded");
    }

    // Запросить содержимое ответа на вопрос
    public String checkTextAnswer(int i) {
        return webDriver.findElement(By.xpath(String.format(".//div[@id='accordion__panel-%d']/p", i))).getText();
    }
}

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

    // Нажать на Заказать
    public void clickOnOrder() {
        webDriver.findElement(By.className("Button_Button__ra12g")).click();
    }

    // Заполнить поля, кроме Станция метро
    public void fillData(String name, String surname, String address, String telephone) {
        webDriver.findElement(By.xpath(String.format(".//input[@placeholder='* Имя']"))).sendKeys(name);
        webDriver.findElement(By.xpath(String.format(".//input[@placeholder='* Фамилия']"))).sendKeys(surname);
        webDriver.findElement(By.xpath(String.format(".//input[@placeholder='* Адрес: куда привезти заказ']"))).sendKeys(address);
        webDriver.findElement(By.xpath(String.format(".//input[@placeholder='* Телефон: на него позвонит курьер']"))).sendKeys(telephone);
    }

    // Заполнить поле Станция метро
    public void fillStation(String station) {
        webDriver.findElement(By.xpath(String.format(".//input[@placeholder='* Станция метро']"))).sendKeys(station);
        webDriver.findElement(By.className("select-search__select")).click();
    }

    // Нажать на Далее
    public void clickOnNext() {
        webDriver.findElement(By.xpath(String.format(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"))).click();
    }

    // Выбрать дату доставки
    public void selectDate(int i) {
        webDriver.findElement(By.xpath(String.format(".//input[@placeholder='* Когда привезти самокат']"))).click();
        webDriver.findElement(By.xpath(String.format(".//div[text() = '"+ i +"']"))).click();
    }

    // Выбрать срок аренды
    public void selectPeriod(String i) {
        webDriver.findElement(By.xpath(String.format(".//div[text() = '* Срок аренды']"))).click();
        webDriver.findElement(By.xpath(String.format(".//div[text() = '"+ i +"']"))).click();
    }

    // Заказать
    public void selectConfirm() {
        webDriver.findElement(By.xpath(String.format(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"))).click();
        webDriver.findElement(By.xpath(String.format(".//button[text() = 'Да']"))).click();
    }


}

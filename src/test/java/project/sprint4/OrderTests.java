package project.sprint4;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import project.sprint4.pageobject.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.time.LocalDate;

@RunWith(Parameterized.class)
public class OrderTests {
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String telephone;
    private final int day;
    private final String period;

    static LocalDate localDate = LocalDate.now();
    static int today = localDate.getDayOfMonth();

    public OrderTests(String name, String surname, String address, String station, String telephone, int day, String period) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.telephone = telephone;
        this.day = day;
        this.period = period;
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // Данные для теста заказа
    @Parameters
    public static Object[][] setUserInfo() {
        return new Object[][]{
                {"Василий", "Васильев", "1-я Тверская-Ямская 27", "Белорусская", "44993331111", today + 1, "двое суток"},
                {"Александр", "Александров", "Рогожский Вал 3", "Площадь Ильича", "44958884433", today + 3, "пятеро суток"}
        };
    }

    private static WebDriver webDriver;

    @Test
    public void makeOrder() {
        Main orderPage = new Main(webDriver);
        // Откроем главную страницу
        orderPage.openPage();
        // Нажмем на Заказать
        orderPage.clickOnOrder();
        // Заполним поля Имя, Фамилия, Адрес и Номер телефона
        orderPage.fillData(name, surname, address, telephone);
        // Заполним поле Станция метро (отдельный метод с выбором из ниспадающего списка)
        orderPage.fillStation(station);
        // Нажмем Далее
        orderPage.clickOnNext();
        // Выберем дату
        orderPage.selectDate(day);
        // Выберем срок аренды
        orderPage.selectPeriod(period);
        // Нажмем Заказать и подтвердим
        orderPage.selectConfirm();
    }

    @After
    public void close() {
        webDriver.quit();
    }

}

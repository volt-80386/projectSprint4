package project.sprint4;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import project.sprint4.pageobject.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

// Класc для проверки "Вопросов о важном"
@RunWith(Parameterized.class)
public class QATests {
      private final int questionNumber;
      private final String textAnswer;
      public QATests(int questionNumber, String textAnswer) {
          this.questionNumber = questionNumber;
          this.textAnswer = textAnswer;
      }

      @Before
      public void setUp() {
          ChromeOptions options = new ChromeOptions();
          webDriver = new ChromeDriver(options);
          webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
      }

      // Данные для теста (номера вопросов и ответы на них)
      @Parameters
      public static Object[][] getQuestions() {
          return new Object[][]{
                  {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                  {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."}
          };
      }

      private static WebDriver webDriver;

      @Test
      public void clickAccordionItems() {
          Main mainPage = new Main(webDriver);
          mainPage
                  // Откроем главную страницу
                  .openPage()
                  // Прокрутим страницу до вопросов
                  .goToQuestions()
                  // Нажмем на вопрос
                  .clickOnAccordionItem(questionNumber);
          // Проверим, что, при нажатии на вопрос, раскрылся ответ (атрибут aria-expanded="true")
          MatcherAssert.assertThat("Ответ на вопрос не показан", mainPage.checkExpandedAccordionItem(questionNumber), is("true"));
          // Проверим, что, при раскрытии, отобразился именно нужный ответ (сравним возвращенное значение с заданным параметром)
          MatcherAssert.assertThat("Ожидаемый ответ на вопрос не показан", mainPage.checkTextAnswer(questionNumber), is(textAnswer));
      }

      @After
      public void close() {
           webDriver.quit();
      }
}


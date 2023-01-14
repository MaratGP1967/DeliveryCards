package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardsTest {
    private LocalDate date = LocalDate.now();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestDeliveryCard() {
        LocalDate dateNew = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDateTime = dateNew.format(formatter);

        $x("//*[@data-test-id='city']//input").setValue("Краснодар");
        $x("//*[@data-test-id='date']//input").sendKeys(Keys.CONTROL + "a");
        $x("//*[@data-test-id='date']//input").sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").sendKeys(formattedDateTime);
        $x("//*[@data-test-id='name']//input").setValue("Александр Бесстужев-Никольский");
        $x("//*[@data-test-id='phone']//input").setValue("+79624569137");
        $x("//*[@data-test-id='agreement']").click();
        $x("//div[contains (@class, 'grid-row')]//button").click();
        $x("//div[contains (text(), 'Успешно!')]").shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @Test
    void shouldTestComplexDeliveryCard() {
        int month = date.getMonthValue();
        LocalDate dateNew = date.plusDays(7);
        long milliseconds = dateNew.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
        String strMilliseconds = Long.toString(milliseconds);

        $x("//*[@data-test-id='city']//input").setValue("См");
        $x("//*[contains(text(),'Смоленск')]").click();
        $x("//*[@data-test-id='date']//button").click();
        if (month != dateNew.getMonthValue()) {
            $x("//*[(@data-step='1')]").click();
        }
        $x("//*[@data-day='" + strMilliseconds + "']").click();
        $x("//*[@data-test-id='name']//input").setValue("Иван Петров-Разумовский");
        $x("//*[@data-test-id='phone']//input").setValue("+79628429586");
        $x("//*[@data-test-id='agreement']").click();
        $x("//div[contains (@class, 'grid-row')]//button").click();
        $x("//div[contains (text(), 'Успешно!')]").shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

}

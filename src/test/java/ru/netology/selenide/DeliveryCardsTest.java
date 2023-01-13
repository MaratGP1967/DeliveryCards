package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardsTest {

    @Test
    void shouldTestDeliveryCard() {
        //Configuration.holdBrowserOpen = true;
        LocalDate date = LocalDate.now();
        date = date.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDateTime = date.format(formatter);

        open("http://localhost:9999");
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
}

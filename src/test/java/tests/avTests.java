package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class avTests {
    @Test
    void avSearchTest()
    {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(byXpath("//div[@class='b-region-modal__button b-button b-button_green']")).click();

        //кликнуть на иконку поиска
        $(byXpath("//button[@class='b-button b-header-search__btn js-quicksearch']")).click();

        //Вводим в строке поиска текст "молоко"
        $(byName("text")).setValue("молоко").pressEnter();

        //Проверяем, что результат поиска перекинул в раздел каталога "Молоко"
        $(byXpath("//h1[@class='b-catalog-page__title']")).shouldHave(text("Молоко"));
   }

    @Test
    void avChangeRegionTest()
    {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(byXpath("//div[@class='b-region-modal__button b-button b-button_green']")).click();

        //в шапке нажать кнопку смены региона
        $(byXpath("//div[@class='b-header-dropdown b-header-dropdown_top']")).click();
        //выбрать Санкт-Петербург
        $(byXpath("//span[@class='b-header-dropdown__link b-header-dropdown__link_top js-region-change']")).click();

        //Проверить, что выбран Санкт-Петербург
        $(byXpath("//span[@class='b-header-dropdown__title']")).shouldHave(text("Санкт-Петербург"));

    }

    @Test
    void avTimeslotSelectionTest() throws InterruptedException {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(byXpath("//div[@class='b-region-modal__button b-button b-button_green']")).click();

        //кнопка выбора таймслота
        $(byXpath("//span[@class='b-header-nav__timeslot-choose']")).click();

        //Ввод адреса
        $(byName("address")).setValue("Россия, Москва, улица Юных Ленинцев, 3").pressEnter();

        //Ввод номера телефона
        $(byName("phone")).setValue("9999999999").pressEnter();

        //ввод подъезда
        $(byName("porch")).setValue("1");

        //Перейти на шаг выбора времени доставки
        $(byXpath("//button[contains(@class,'b-button b-button_green js-save-address')]")).click();

        Thread.sleep(2000);

        //Выбор слота доставки
        $(byXpath("//button[contains(@class,'b-button b-button_slot b-button_reactive')]")).click();

        Thread.sleep(1000);

        //Проверить, что отображается правильный таймслот
        $(byXpath("//span[@class='b-header-nav__timeslot-date']")).shouldHave(text("За 60 минут"));
    }

    @Test
    void avAddToCart() throws InterruptedException {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(byXpath("//div[@class='b-region-modal__button b-button b-button_green']")).click();

        //Развернуь каталог
        $(byXpath("//span[@class='b-header-dropdown__title b-header-dropdown__title_catalog']")).click();

        //Выбираем раздел каталога Бакалея
        $(byLinkText("Бакалея")).click();

        //проверяем, что открылся нужный раздел каталога
        $(byXpath("//h1[@class='b-catalog-page__title']")).shouldHave(text("Бакалея"));

        // Макароны, крупы
        $(byLinkText("Макароны, крупы")).click();

        // Макароны
        $(byLinkText("Макароны")).click();

        //Кликаем на иконку корзины у первого товара в категории
        $(byClassName("b-grid__item")).$(byTitle("В корзину")).click();

        //Ввод адреса
        $(byName("address")).setValue("Россия, Москва, улица Юных Ленинцев, 3").pressEnter();

        //Ввод номера телефона
        $(byName("phone")).setValue("9999999999").pressEnter();

        //ввод подъезда
        $(byName("porch")).setValue("1");

        //Перейти на шаг выбора времени доставки
        $(byXpath("//button[contains(@class,'b-button b-button_green js-save-address')]")).click();
        Thread.sleep(5000);

        //Выбор слота доставки
        $(byXpath("//button[contains(@class,'b-button b-button_slot b-button_reactive')]")).click();
        Thread.sleep(2000);

        //Кликаем на иконку корзины у первого товара в категории
        $(byClassName("b-grid__item")).$(byTitle("В корзину")).click();

        //Провряем, что в корзине 1 товар.
        $(byXpath("//div[@class='b-header-nav__cart-count js-new-header-total']")).shouldHave(text("1"));
    }
}

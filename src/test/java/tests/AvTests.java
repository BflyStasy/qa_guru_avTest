package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class AvTests {
    @Test
    void avSearchTest()
    {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(".b-region-modal__button").click();

        //кликнуть на иконку поиска
        $(".b-header-search__btn").click();

        //Вводим в строке поиска текст "молоко"
        $(byName("text")).setValue("молоко").pressEnter();

        //Проверяем, что результат поиска перекинул в раздел каталога "Молоко"
        $(".b-catalog-page__title").shouldHave(text("Молоко"));
   }

    @Test
    void avChangeRegionTest()
    {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(".b-region-modal__button").click();

        //в шапке нажать кнопку смены региона
        $(".b-header-dropdown_top").click();

        //выбрать Санкт-Петербург
        $(".b-header-dropdown__link_top").click();

        //Проверить, что выбран Санкт-Петербург
        $(".b-header-dropdown__title']").shouldHave(text("Санкт-Петербург"));

    }

    @Test
    void avTimeslotSelectionTest() throws InterruptedException {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(".b-region-modal__button").click();

        //кнопка выбора таймслота
        $(".b-header-nav__timeslot-choose").click();

        //Ввод адреса
        $(byName("address")).setValue("Россия, Москва, улица Юных Ленинцев, 3").pressEnter();

        //Ввод номера телефона
        $(byName("phone")).setValue("9999999999").pressEnter();

        //ввод подъезда
        $(byName("porch")).setValue("1");

        //Перейти на шаг выбора времени доставки
        $(".b-timeslot-form__action").click();

        Thread.sleep(2000);

        //Выбор слота доставки
        $(".b-button_reactive").click();

        Thread.sleep(1000);

        //Проверить, что отображается правильный таймслот
        $(".b-header-nav__timeslot-date").shouldHave(text("За 60 минут"));
    }

    @Test
    void avAddToCart() throws InterruptedException {
        // открыть сайт av.ru
        open("https://av.ru");

        //выбрать регион Москва
        $(".b-region-modal__button").click();

        //Развернуь каталог
        $(".b-header-dropdown__title_catalog").click();

        //Выбираем раздел каталога Бакалея
        $(byLinkText("Бакалея")).click();

        //проверяем, что открылся нужный раздел каталога
        $("h1").shouldHave(text("Бакалея"));

        // Макароны, крупы
        $(byLinkText("Макароны, крупы")).click();

        // Макароны
        $(byLinkText("Макароны")).click();

        //Кликаем на иконку корзины у первого товара в категории
        $(".b-grid__item").$(byTitle("В корзину")).click();

        //Ввод адреса
        $(byName("address")).setValue("Россия, Москва, улица Юных Ленинцев, 3").pressEnter();

        //Ввод номера телефона
        $(byName("phone")).setValue("9999999999").pressEnter();

        //ввод подъезда
        $(byName("porch")).setValue("1");

        //Перейти на шаг выбора времени доставки
        $(".b-timeslot-form__action").click();

        Thread.sleep(2000);

        //Выбор слота доставки
        $(".b-button_reactive").click();

        Thread.sleep(1000);

        //Кликаем на иконку корзины у первого товара в категории
        $(".b-grid__item").$(byTitle("В корзину")).click();

        //Провряем, что в корзине 1 товар.
        $(".b-link__basket-text_total").shouldHave(text("1"));
    }
}

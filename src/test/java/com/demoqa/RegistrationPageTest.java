package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.qameta.allure.Allure.step;

public class RegistrationPageTest {
    RegistrationPage registrationPage = new RegistrationPage();
    static Faker faker = new Faker();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String emailAddress = faker.internet().emailAddress();
    public String gender = "Female";
    public String mobilePhone = faker.phoneNumber().subscriberNumber(10);
    public String day = "1";
    public String month = "February";
    public String year = "1995";
    public String subject = "English";
    public String hobbies = "Reading";
    public String fileName = "test.png";
    public String address = faker.address().fullAddress();
    public String state = "NCR";
    public String city = "Gurgaon";

    @BeforeAll
    static void setup() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void registerUser() {
        step("Открыть страницу с формой регистрации", () -> {
            registrationPage.openRegistrationPage();
        });
        step("Заполнить форму регистрации", () -> {
            registrationPage.enterFirstNameAndLastName(firstName, lastName)
                    .enterEmail(emailAddress)
                    .selectGender(gender)
                    .enterMobilePhone(mobilePhone)
                    .selectDatOfBirth(day, month, year)
                    .selectSubject(subject)
                    .selectHobbies(hobbies)
                    .uploadPicture(fileName)
                    .enterAddress(address)
                    .selectStateAndCity(state, city);
        });
        step("Отправить заполненную форму регистрации", () -> {
            registrationPage.pressSubmit();
        });
        step("Проверить поля", () -> {
            registrationPage.checkResultsTitle();
            registrationPage.checkForm(firstName + " " + lastName)
                    .checkForm(emailAddress)
                    .checkForm(gender)
                    .checkForm(mobilePhone)
                    .checkForm(day + " " + month + "," + year)
                    .checkForm(subject)
                    .checkForm(hobbies)
                    .checkForm(fileName)
                    .checkForm(address)
                    .checkForm(state + " " + city);
        });
    }
}

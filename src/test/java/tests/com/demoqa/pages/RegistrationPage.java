package tests.com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.com.demoqa.components.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    private SelenideElement modal = $("[role=dialog]");
    private Calendar calendar = new Calendar();

    @Step("Открыть страницу регистрации")
    public void openRegistrationPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    }
    @Step("Ввести имя/фамилию")
    public RegistrationPage enterFirstNameAndLastName(String firstName, String lastName) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        return this;
    }
    @Step("Ввести email")
    public RegistrationPage enterEmail(String email) {
        $("#userEmail").setValue(email);
        return this;
    }
    @Step("Выбрать пол")
    public RegistrationPage selectGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }

    @Step("Ввести номер телефона")
    public RegistrationPage enterMobilePhone(String mobilePhone) {
        $("#userNumber").setValue(mobilePhone);
        return this;
    }

    @Step("Указать дату рождения")
    public RegistrationPage selectDatOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Ввести номер телефона")
    public RegistrationPage selectSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    @Step("Выбрать хобби")
    public RegistrationPage selectHobbies(String hobbies) {
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        return this;
    }

    @Step("Загрузить фото")
    public RegistrationPage uploadPicture(String nameFile) {
        $("#uploadPicture").uploadFromClasspath(nameFile);
        return this;
    }

    @Step("Ввести адрес")
    public RegistrationPage enterAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    @Step("Выбрать страну и город")
    public RegistrationPage selectStateAndCity(String state, String city) {
        $("#stateCity-wrapper").$(byText("Select State")).scrollTo().click();
        $("#state").$(byText(state)).click();
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#city").$(byText(city)).click();
        return this;
    }

    @Step("Нажать Отправить")
    public RegistrationPage pressSubmit() {
        $("#submit").click();
        return this;
    }

    @Step("Проверить финальное сообщение Thanks for submitting the form")
    public void checkResultsTitle() {
        modal.$(".modal-title").shouldHave(text("Thanks for submitting the form"));
    }

    @Step("Проверить форму")
    public RegistrationPage checkForm(String value) {
        modal.$(".table-responsive").shouldHave(text(value));
        return this;
    }
}

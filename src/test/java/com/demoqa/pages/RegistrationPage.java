package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.components.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    private SelenideElement modal = $("[role=dialog]");
    private Calendar calendar = new Calendar();


    public void openRegistrationPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    }

    public RegistrationPage enterFirstNameAndLastName(String firstName, String lastName) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        $("#userEmail").setValue(email);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }

    public RegistrationPage enterMobilePhone(String mobilePhone) {
        $("#userNumber").setValue(mobilePhone);
        return this;
    }

    public RegistrationPage selectDatOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage selectSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage selectHobbies(String hobbies) {
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String nameFile) {
        $("#uploadPicture").uploadFromClasspath(nameFile);
        return this;
    }

    public RegistrationPage enterAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    public RegistrationPage selectStateAndCity(String state, String city) {
        $("#stateCity-wrapper").$(byText("Select State")).scrollTo().click();
        $("#state").$(byText(state)).click();
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#city").$(byText(city)).click();
        return this;
    }

    public RegistrationPage pressSubmit() {
        $("#submit").click();
        return this;
    }

    public void checkResultsTitle() {
        modal.$(".modal-title").shouldHave(text("Thanks for submitting the form"));
    }

    public RegistrationPage checkForm(String value) {
        modal.$(".table-responsive").shouldHave(text(value));
        return this;
    }
}

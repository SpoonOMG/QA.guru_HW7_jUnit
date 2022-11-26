package com.saucedemo.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class AuthorizationPage {

    @BeforeEach
    void setUp(){
        Selenide.open("https://www.saucedemo.com/");
        $(".login_logo").shouldBe(Condition.visible);
    }

    @ParameterizedTest(name = "Success autentification. Credentials: login = {0} and password =  {1}. Following title page {2}")
    @CsvSource(value = {
            "standard_user | secret_sauce | Products",
            "problem_user | secret_sauce | Products",
            "performance_glitch_user | secret_sauce | Products"
    },
    delimiter = '|'
    )
    @Tags({@Tag("BLOCKER"), @Tag("Auth"), @Tag("Regress")})
    void validLoginInputTest(String login, String password, String titleProducts){
        $("#user-name").setValue(login);
        $("#password").setValue(password).pressEnter();
        $(byText("Products")).shouldHave(text(titleProducts));
    }

    @ParameterizedTest(name = "Autentification validation. Credential: login = {0}, password = {1}. Error message = {2}")
    @CsvFileSource(
            resources = {"/invalidcredentials.csv"},
            delimiter = '|'
    )
    @Tags({@Tag("BLOCKER"),@Tag("Auth"), @Tag("NewFeature")})
    void invalidLoginInputTest(String login, String password, String errorMessage){
        $("#user-name").setValue(login);
        $("#password").setValue(password).pressEnter();
        $(withText("Epic sadface:")).shouldHave(text(errorMessage));
    }
}

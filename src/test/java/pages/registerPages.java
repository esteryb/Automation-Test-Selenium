package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class registerPages {
    WebDriver driver;

    public registerPages(WebDriver driver){
        this.driver = driver;
    }

    By firstnameField = By.id("customer.firstName");
    By lastNameField = By.id("customer.lastName");
    By streetField = By.id("customer.address.street");
    By cityField = By.id("customer.address.city");
    By stateField = By.id("customer.address.state");
    By zipCodeField = By.id("customer.address.zipCode");
    By phoneNumberField = By.id("customer.phoneNumber");
    By ssnField = By.id("customer.ssn");

    public void inputData(String firstName, String lastName, String street, String city, String state, String zipCode, String phoneNumber, String ssn) {
        driver.findElement(firstnameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(streetField).sendKeys(street);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        driver.findElement(ssnField).sendKeys(ssn);
    }
}

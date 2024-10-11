package stepDef;

import config.env_target;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends env_target {
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        //Set driverlocation path
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        //Maximize driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Set url
        driver.get(baseUrl);
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit'][@data-test='login-button']"))
        );
    }

    @When("User fill username and password")
    public void userFillUsernameAndPassword() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("User click login button")
    public void userClickLoginButton() {
        driver.findElement(By.xpath("//input[@type='submit'][@data-test='login-button']")).click();
    }

    @Then("User verify login result")
    public void userVerifyLoginResult() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Products')]"))
        );

        driver.quit();
    }

    @When("User fill invalid username and password")
    public void userFillInvalidUsernameAndPassword() {
        driver.findElement(By.name("user-name")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("123");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
        );

        driver.quit();
    }

    @When("^User input (.*) and (.*)$")
    public void userFillInvalidUsernameAndPassword(String username, String password) {
        driver.findElement(By.name("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("^User get verify login (.*)$")
    public void userVerifyLoginResultTDD(String result) {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        if (result == "Passed") {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Products')]"))
            );
        } else if (result == "Failed") {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
            ));
        }

        driver.quit();
    }
}

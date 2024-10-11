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
import java.util.Random;

public class MyStepdefs extends env_target {
    @Given("User is on Para Bank page")
    public void userIsOnParaBankPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(parabankLink);
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"leftPanel\"]"))
        );
    }

    @When("User click register")
    public void userClickRegister() {
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a")).click();
    }

    @Then("User is on Register Page")
    public void userIsOnRegisterPage() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rightPanel\"]/h1"))
        );
    }

    @When("User fill data")
    public void userFillData() {
        driver.findElement(By.id("customer.firstName")).sendKeys("Ester");
        driver.findElement(By.id("customer.lastName")).sendKeys("Berutu");
        driver.findElement(By.id("customer.address.street")).sendKeys("Sei Mencirim");
        driver.findElement(By.id("customer.address.city")).sendKeys("Medan");
        driver.findElement(By.id("customer.address.state")).sendKeys("Sumatera");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("20121");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("08123456789");
        driver.findElement(By.id("customer.ssn")).sendKeys("12345678");
    }

    @And("User fill valid username and password")
    public void userFillValidUsernameAndPassword() {
        Random rand = new Random();
        int userRand = rand.nextInt(10000);
        driver.findElement(By.id("customer.username")).sendKeys("user"+userRand);
        driver.findElement(By.id("customer.password")).sendKeys("12345");
    }

    @And("User input password confirmation")
    public void userInputPasswordConfirmation() {
        driver.findElement(By.id("repeatedPassword")).sendKeys("12345");
    }

    @And("User click register button")
    public void userClickRegisterButton() {
        driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).click();
    }

    @Then("User register successfully")
    public void userRegisterSuccessfully() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rightPanel\"]/p"))
        );
        driver.quit();
    }

    @And("User input invalid password confirmation")
    public void userInputInvalidPasswordConfirmation() {
        driver.findElement(By.id("repeatedPassword")).sendKeys("123");
    }

    @Then("User get error password did not match")
    public void userGetErrorPasswordDidNotMatch() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("repeatedPassword.errors"))
        );
        driver.quit();
    }
}

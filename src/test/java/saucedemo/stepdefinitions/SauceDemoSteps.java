package saucedemo.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SauceDemoSteps {
    private WebDriver driver;

    @Given("I am on the Sauce Demo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        System.setProperty(
                "webdriver.chrome.driver",
                System.getProperty("user.dir") + "\\src\\test\\java\\resources\\driver\\chromedriver.exe"
        );
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("I enter {string} in the username field")
    public void iEnterInTheUsernameField(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @When("I enter {string} in the password field")
    public void iEnterInThePasswordField(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("I click the Login button")
    public void iClickTheLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String title = driver.findElement(By.xpath("//span[@class='title']")).getText();
        assertEquals("Products", title);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage);
    }

    @Given("I am logged in to Sauce Demo with user {string} and password {string}")
    public void IAmLoggedInToSauceDemo(String username, String password) {
        iAmOnTheSauceDemoLoginPage();
        iEnterInTheUsernameField(username);
        iEnterInThePasswordField(password);
        iClickTheLoginButton();
        iShouldBeLoggedIn();
    }

    @When("I click the Add to Cart button for the {string} product")
    public void iClickTheAddToCartButtonForTheProduct(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']")).click();
        driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
    }

    @Then("the product should be added to my shopping cart")
    public void theProductShouldBeAddedToMyShoppingCart() {
        int productCount = driver.findElements(By.className("shopping_cart_badge")).size();
        assertEquals(1, productCount);
    }

    @When("I click logout button")
    public void iClickLogoutButton() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    @Then("I should be logged out")
    public void iShouldBeLoggedOut() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String loginButtontext = driver.findElement(By.id("login-button")).getAttribute("value");
        assertEquals("Login", loginButtontext);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
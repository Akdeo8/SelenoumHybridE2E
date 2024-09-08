package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Utils;

public class LoginPage extends Utils {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	By email = By.cssSelector("#userEmail");
	By pwd = By.cssSelector("#userPassword");
	By login = By.cssSelector("#login");

	public Products loginApp(String name, String password) {
		driver.findElement(email).sendKeys(name);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(login).click();
		Products product = new Products(driver);
		return product;

	}

}

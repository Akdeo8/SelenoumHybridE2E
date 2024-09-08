package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utilities.Utils;

public class ThankYouPage extends Utils {
	WebDriver driver;

	ThankYouPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By orderConfirm = By.cssSelector(".hero-primary");

	public String getOrderConfirmationText() {
		return driver.findElement(orderConfirm).getText();
	}
}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import Utilities.Utils;

public class BillingPage extends Utils {

	WebDriver driver;

	public BillingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By country = By.cssSelector("[placeholder='Select Country']");
	By countrySelection = By.cssSelector(".ta-results");
	By submitOrder = By.cssSelector(".btnn.action__submit.ng-star-inserted");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(country)).sendKeys(countryName).build().perform();
		waitForElementLocated(countrySelection);
		driver.findElement(By.cssSelector("button:nth-child(2) span:nth-child(1)")).click();
	}


	public ThankYouPage submitOrder() {
		driver.findElement(submitOrder).click();
		return new ThankYouPage(driver);
	}

}

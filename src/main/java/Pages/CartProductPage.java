package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utilities.Utils;

public class CartProductPage extends Utils {

	WebDriver driver;

	CartProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By cartProduct = By.cssSelector(".cartSection h3");
	By checkout = By.cssSelector(".totalRow button");

	public List<WebElement> getCartList() {
		return driver.findElements(cartProduct);
	}

	public void AssertProductName(String ProductName) {
		Boolean pmatch = getCartList().stream().anyMatch(product -> product.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(pmatch);
	}

	public BillingPage clickCheckout() {
		driver.findElement(checkout).click();
		BillingPage billingpage = new BillingPage(driver);
		return billingpage;
	}

}

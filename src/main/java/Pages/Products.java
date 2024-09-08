package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.Utils;

public class Products extends Utils {

	WebDriver driver;

	public Products(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By toastmessage = By.cssSelector("#toast-container");
	By products = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");

	public void waitForToastMessage() {
		waitForElementLocated(toastmessage);
	}

	public List<WebElement> getProductList() {
		return driver.findElements(products);
	}

	public WebElement getProductName(String pname) {

		return getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(pname)).findFirst()
				.orElse(null);
	}

	public void AddItemToCart(String productName) {
		WebElement e = getProductName(productName);
		e.findElement(addToCart).click();
		waitForToastMessage();
	}

	public CartProductPage goToCartPage() {
		getCartHeader().click();
		CartProductPage catproductpage = new CartProductPage(driver);
		return catproductpage;
	}

}

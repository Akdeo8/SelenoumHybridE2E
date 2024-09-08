package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	WebDriver driver;

	public Utils(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;

	public WebElement waitForElementLocated(By webElemnt) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(webElemnt));
	}

	public String getText(WebElement e) {
		return e.getText();
	}

	public static void localWAit() throws InterruptedException {
		Thread.sleep(3000);
	}

	public WebElement getCartHeader() {
		return cartHeader;
	}

}

package TestCases;

import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Pages.BillingPage;
import Pages.CartProductPage;
import Pages.Products;
import Pages.ThankYouPage;
import TestComponents.Base;
import Utilities.RetryTest;
import Utilities.Utils;

public class LoginTests extends Base {

	WebDriver driver;

	@Test(groups = { "Full" })
	public void placeOrder() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		Products product = loginpage.loginApp(prop.getProperty("username"), prop.getProperty("password"));
		product.waitForToastMessage();
		Utils.localWAit();
		product.AddItemToCart(productName);
		CartProductPage cartproductpage = product.goToCartPage();
		cartproductpage.AssertProductName(productName);
		BillingPage billingpage = cartproductpage.clickCheckout();
		billingpage.selectCountry("India");
		ThankYouPage typ = billingpage.submitOrder();
		String orderFinalText = typ.getOrderConfirmationText();
		Assert.assertEquals(orderFinalText, "THANKYOU FOR THE ORDER.");
	}

	@Test(dataProvider = "getData", groups = { "Regression" })
	public void placeOrderDataProvider(HashMap<String, String> input) throws IOException, InterruptedException {
		String pname = input.get("product");
		Products product = loginpage.loginApp(prop.getProperty("username"), prop.getProperty("password"));
		product.waitForToastMessage();
		Utils.localWAit();
		product.AddItemToCart(pname);
		CartProductPage cartproductpage = product.goToCartPage();
		cartproductpage.AssertProductName(pname);
		BillingPage billingpage = cartproductpage.clickCheckout();
		billingpage.selectCountry("India");
		ThankYouPage typ = billingpage.submitOrder();
		String orderFinalText = typ.getOrderConfirmationText();
		Assert.assertEquals(orderFinalText, "THANKYOU FOR THE ORDER.");
	}

	@Test(groups = { "Full" }, retryAnalyzer = RetryTest.class)
	public void placeOrderFailure() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		Products product = loginpage.loginApp(prop.getProperty("username"), prop.getProperty("password"));
		product.waitForToastMessage();
		Utils.localWAit();
		product.AddItemToCart(productName);
		CartProductPage cartproductpage = product.goToCartPage();
		cartproductpage.AssertProductName(productName);
		BillingPage billingpage = cartproductpage.clickCheckout();
		billingpage.selectCountry("India");
		ThankYouPage typ = billingpage.submitOrder();
		String orderFinalText = typ.getOrderConfirmationText();
		Assert.assertEquals(orderFinalText, "THANKYOU");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		String pth = System.getProperty("user.dir") + "/src/test/java/TestData/Data.json";
		List<HashMap<String, String>> data = getJsonDataToMap(pth);
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}

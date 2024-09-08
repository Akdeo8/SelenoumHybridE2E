package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	WebDriver driver;
	public Properties prop;
	public LoginPage loginpage;

	public WebDriver initialize() throws Exception {
		Properties p = getEnvironmentConfigFile();
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: p.getProperty("browser");
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("url"));

		return driver;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param testCaseName
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	public static String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws Exception {
		driver = initialize();
		loginpage = new LoginPage(driver);
		return loginpage;

	}

	/**
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public Properties getEnvironmentConfigFile() throws Exception {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("-----> Running test cases on environment: ----->" + envName);

		if (envName == null) {
			System.out.println("No env is given..hence running it on SYS env.....");
			try {
				ip = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/java/ConfigData/config-sys.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName) {
				case "sys":
					ip = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/java/ConfigData/config-sys.properties");
					break;
				case "int":
					ip = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/java/ConfigData/config-int.properties");
					break;
				default:
					System.out.println("please pass the right env name...." + envName);
					throw new Exception();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}

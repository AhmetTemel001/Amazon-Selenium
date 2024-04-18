package Amazon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
//TÜM TESTLERİ ÇALIŞTIRMAK İÇİN TERMİNAL YAZ
// mvn clean test -DsuiteXmlFile=src/test/resources/testng-selenium-suite.xml
    WebDriver driver ;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com.tr/");
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
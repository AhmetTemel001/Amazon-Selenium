package Amazon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class BaseTest {
//TÜM TESTLERİ ÇALIŞTIRMAK İÇİN TERMİNAL YAZ
// mvn clean test -DsuiteXmlFile=src/test/resources/testng-selenium-suite.xml
    WebDriver driver ;

    @BeforeAll
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com.tr/");
        driver.manage().window().maximize();
    }
    @AfterAll
    public void tearDown(){
        driver.quit();
    }

}
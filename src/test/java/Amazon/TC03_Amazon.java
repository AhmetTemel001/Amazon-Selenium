package Amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.ProductsPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class TC03_Amazon extends BaseTest {
    //TESTLERİN HEPSİNİ AYNANDA KOŞ

    By low = By.xpath("//input[@id='low-price']");
    By high = By.xpath("//input[@id='high-price']");
    By Fiyatlar = By.xpath("//span[@class=\"a-price\"and @data-a-size=\"xl\"]");
    HomePage homePage;
    ProductsPage productsPage;


    @org.junit.jupiter.api.Test
    @Order(1)
    public void search_a_product() {
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        productsPage = new ProductsPage(driver);
        homePage.searchBox().search("Akıllı Saat");
        Assertions.assertTrue(productsPage.isOnProductPage(),
                "Ürünlerin Sayfasına Ulaşılanamadı");
    }

    @Test
    @Order(2)
    public void price() throws InterruptedException, AWTException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 450)");

        driver.findElement(low).sendKeys("1000");
        driver.findElement(high).sendKeys("2000");
        driver.findElement(high).click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
    }


    @Test
    @Order(3)
    public void pricefiltre() throws InterruptedException {
        By productNameLocator = By.xpath("//a[@class=\"a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal\"]");

        WebElement pageNumberElement = driver.findElement(By.xpath("//span[@class='s-pagination-item s-pagination-disabled']"));
        String pageNumberText = pageNumberElement.getText();
        int pageNumber = Integer.parseInt(pageNumberText.replaceAll("[^0-9]", ""));

        // Hatalı fiyatlı olan ürünleri listelemek için.
        List<WebElement> outOfRangeProducts = new ArrayList<>();

        // Sayfa sayısını öğreniliyor.
        for (int i = 1; i <= pageNumber; i++) {
            // Sayfadaki tüm ürünleri alır.
            List<WebElement> productElements = driver.findElements(Fiyatlar);
            for (WebElement productElement : productElements) {
                WebElement priceElement = productElement.findElement(Fiyatlar);
                String priceText = priceElement.getText();
                double price = parsePrice(priceText);

                // Fiyatın istenilen aralıkta olup olmadığını kontrol edin (1000-2000 TL)
                if (price < 1000 || price > 2000) {
                    outOfRangeProducts.add(productElement);
                }
            }
            // Sıradaki sayfaya gider
            if (i < pageNumber) {
                driver.findElement(By.xpath("//a[.='Sonraki']")).click();
                Thread.sleep(5000);
            }
        }
        //Tekrar listeye alınak.
        if (!outOfRangeProducts.isEmpty()) {
            System.out.println("Aşağıdaki ürünler hatalı fiyatlara sahiptir.");
            for (WebElement faultyProduct : outOfRangeProducts) {
                // Hatalı ürünün adını bulcak
                WebElement productNameElement = faultyProduct.findElement(productNameLocator);
                String productName = productNameElement.getText();
                System.out.println(productName);
            }
        } else {
            System.out.println("Tüm ürünleri fiyatı doğru");

        }
    }

    private double parsePrice(String priceText) {
        String cleanPriceText = priceText.replace("\n", "").replace("TL","").trim();
        return parseDouble(cleanPriceText);
    }

}

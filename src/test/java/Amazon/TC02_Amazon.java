package Amazon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pages.HomePage;

import pages.ProductsPage;

import java.util.List;

public class TC02_Amazon extends BaseTest {
    HomePage homePage ;
    ProductsPage productsPage ;
    By Urunler = By.xpath("//a[@class=\"a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal\"]");

    @org.junit.jupiter.api.Test
    @Order(1)
    public void search_a_product(){
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        productsPage = new ProductsPage(driver);
        homePage.searchBox().search("İphone 15");
        Assertions.assertTrue(productsPage.isOnProductPage() ,
                "Ürünlerin Sayfasına Ulaşılanamadı");
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    public void verification_a_product(){
        List<WebElement> searchResults = driver.findElements(Urunler);
        for (WebElement result : searchResults) {
            if (result.getText().toLowerCase().contains("apple") || result.getText().toLowerCase().contains("iphone") || result.getText().toLowerCase().contains("15")){
                System.out.println("Arama sonuçları doğru.");
            } else {
                System.out.println("Arama sonuçları hatalı: " + result.getText());
            }
        }
    }
}
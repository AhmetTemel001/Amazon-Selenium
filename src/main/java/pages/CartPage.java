package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class CartPage extends BasePage {


    By productNameLocator = new By.ByXPath("//span[@class=\"a-truncate-cut\"]");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkIfProductAdded() {//Ürünün Eklenip Eklenmedini Kontrol Ediyo
        return getProducts().size() > 0 ;//Eğer Sıfırdan Büyük İse True Küçükse False
    }

    private List<WebElement> getProducts(){//Xpat ile alınan Ürünlerin Listesini Oluşturuyo
        return findAll(productNameLocator);
    }


}
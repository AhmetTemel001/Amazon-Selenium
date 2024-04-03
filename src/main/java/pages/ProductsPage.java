package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ProductsPage extends BasePage {
    By shippingPotionLocator = By.xpath("//span[@class='a-size-medium-plus a-color-base a-text-bold']");
    By productNameLocator = By.xpath("//img[@alt='Asus Notebook Laptop, F1502ZA-EJ1527 FHD, i5-1235U İşlemci, 8 GB RAM, 512 SSD, Share, wo/OS']");
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductPage() {
        return isDisplayed(shippingPotionLocator);
    }

    public void selectProduct(int i) {
       click(productNameLocator);

    }

    public List<WebElement> getProductElements() {
        return null;
    }
}

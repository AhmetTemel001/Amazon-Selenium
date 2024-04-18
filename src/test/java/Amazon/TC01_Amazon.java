package Amazon;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class TC01_Amazon extends BaseTest {
    HomePage homePage ;
    ProductsPage productsPage ;
    ProductDetailPage productDetailPage ;
    CartPage cartPage ;

    @Test(priority = 1)
    public void search_a_product(){
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        productsPage = new ProductsPage(driver);
        homePage.searchBox().search("Laptop");
        Assertions.assertTrue(productsPage.isOnProductPage() ,
                "Ürünlerin Sayfasına Ulaşılanamadı");
    }
    @Test(priority = 2)
    public void select_a_product(){//Arama Sonucundaki Ürünü Seçiyo
        productDetailPage = new ProductDetailPage(driver);
        productsPage.selectProduct(1);//Liste oluşturup çekilecek.
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(),
                "Seçilen Ürünün Detay Sayfasına Ulaşılanamadı");
    }

    @Test(priority = 3)
    public void add_product_to_cart(){//Ürünü Sepete Ekliyor
        productDetailPage.addToCart();
        Assertions.assertTrue(homePage.isProductCountUp(),
                "Sepetteki Ürünün Sayısı Artmadı");
    }

    @Test(priority = 4)
    public  void go_to_cart(){//Sepeti Kontrol Ediyo
        cartPage = new CartPage(driver);
        homePage.goToCart();
        Assertions.assertTrue(cartPage.checkIfProductAdded() ,//Seçilen ürünün ismini kabul et.
                "Ürün Sepete Görüntülenemedi");
    }
}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class Test_Add_Product_To_Cart extends BaseTest {

    HomePage homePage ;
    ProductsPage productsPage ;
    ProductDetailPage productDetailPage ;
    CartPage cartPage ;

    @Test
    @Order(1)

    public void search_a_product(){
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        productsPage = new ProductsPage(driver);
        homePage.searchBox().search("Laptop");
        Assertions.assertTrue(productsPage.isOnProductPage() ,
                "Ürünlerin Sayfasına Ulaşılanamadı");
    }

    @Test
    @Order(2)
    public void select_a_product(){//Arama Sonucundaki Ürünü Seçiyo
        productDetailPage = new ProductDetailPage(driver);
        productsPage.selectProduct(1);
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(),
                "Seçilen Ürünün Detay Sayfasına Ulaşılanamadı");
    }

    @Test
    @Order(3)
    public void add_product_to_cart(){//Ürünü Sepete Ekliyor
        productDetailPage.addToCart();
        Assertions.assertTrue(homePage.isProductCountUp(),
                "Sepetteki Ürünün Sayısı Artmadı");
    }

    @Test
    @Order(4)
    public  void go_to_cart(){//Sepeti Kontrol Ediyo
        cartPage = new CartPage(driver);
        homePage.goToCart();
        Assertions.assertTrue(cartPage.checkIfProductAdded() ,
                "Ürün Sepete Görüntülenemedi");
    }




}
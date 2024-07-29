package Task.Youxel;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Amazon Shopping Cart and Checkout")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingCartAndCheckout 
{
	private static ChromeDriver driver;	
	private static HomePage homePage;
	private static ShoppingCart shoppingCart;

	@BeforeAll
    public static void OpenBrowserAndURL()
    {
    	homePage = new HomePage();
    	shoppingCart = new ShoppingCart();
    	
    	//Setup Webdriver
    	WebDriverManager.chromedriver().setup();
    	ChromeOptions chromeOptions = new ChromeOptions();
    	chromeOptions.addArguments("--start-maximized");
    	chromeOptions.addArguments("--remote-allow-origins=*");
    	
   	    driver = new ChromeDriver(chromeOptions);
   	    
   	    //Open Amazon URL
   	    homePage.OpenURL(driver);
   	    
   	    //Assert that Electronics is displayed
   	    assertThat(homePage.IsFound_Electronics(driver)); 
    }
    
    @Test
    @Order(1)
    public void AddToCart()
    {
    	//Navigate to grocery
    	homePage.NavigatetoGrocery(driver);
    	
    	//Add items to the cart
    	int itemsCount = homePage.AddItemsToCart(driver);
    	
    	//Assert that number of items on the cart are correct
    	assertThat(itemsCount == homePage.GetActualItemsInShoppingCart(driver));
    }
    
    @Test
    @Order(2)
    public void CheckShoppingCart()
    {
    	//Navigate to shopping cart
    	homePage.NavigatetoShoppingCart(driver);
    	
    	//Check that total price is calsulated successfully
    	assertThat(shoppingCart.CheckTotalPrice(driver));
    	
    }
    
    @Test
    @Order(3)
    public void Checkout()
    {
    	//Click on "Proceed to pay"
    	shoppingCart.ClickonCheckout(driver);
    	
    }
    
    @AfterAll
    public static void CloseBrowser() {
    	//Close the browser
    	driver.close();
    }
}

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


@DisplayName("Amazon Search")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTest 
{
	private static ChromeDriver driver;	
	private static HomePage homePage;
	private static ElectronicsPage electronicsPage;

	@BeforeAll
    public static void OpenBrowserAndURL()
    {
    	homePage = new HomePage();
    	electronicsPage = new ElectronicsPage();
    	
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
    public void SearchByCategory()
    {
    	//Click on electronics
    	homePage.ClickOnElectronics(driver);
    	
    	//Assert that TVs are displayed
    	assertThat(electronicsPage.IsFound_TVs(driver)); 
    	
    	//Click on TVs
    	electronicsPage.ClickOnTVs(driver);
    	
    	//Assert that all displayed items are TV
    	assertThat(electronicsPage.CheckAllItemsAreTV(driver));
    	
    }
    
    @Test
    @Order(2)
    public void SearchByKeyword()
    {

    	//Click on electronics
    	homePage.ClickOnElectronics(driver);
    	
    	//Assert that search box is displayed
    	assertThat(electronicsPage.IsFound_SearchTxt(driver));
    	
    	//Search by any text
    	electronicsPage.SearchbyTxt(driver);
    	
    	//Assert that all test results have the same keyword in description
    	assertThat(electronicsPage.CheckSearchResult(driver));
    }
    
    @Test
    @Order(3)
    public void FilterByFulfilledByAmazon()
    {
    	//Filter by "Fulfilled by Amazon"
    	electronicsPage.SelectFulfilledByAmazon(driver);
    	
    	//Assert that all test results are fulfilled by Amazon
    	assertThat(electronicsPage.CheckFulfilledByAmazonItemsDisplayed(driver));
    }
    
    @Test
    @Order(4)
    public void SortbyPriceLowToHigh() {
    	//Sorting items by price from low to high
    	electronicsPage.SortbyPriceLowToHigh(driver);
    	
    	//Assert that items are sorted correctly
    	assertThat(electronicsPage.SortingIsCorrect(driver));
    }
    
    @AfterAll
    public static void CloseBrowser() {
    	//Close the browser
    	driver.close();
    }
    
}

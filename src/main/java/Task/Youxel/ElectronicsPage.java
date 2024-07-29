package Task.Youxel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class ElectronicsPage 
{
	private By TVs = By.xpath("//*[text()='Televisions']");
	private By TVitems = By.className("a-size-base");
	private By SearchTxt = By.id("twotabsearchtextbox");
	private By SearchButton = By.id("nav-search-submit-button");
	private By Searchitems = By.className("a-size-base-plus");
	private By FulfilledByAmazon = By.className("a-icon-checkbox");
	private By FulfilledByAmazonItems = By.cssSelector("[aria-label='Fulfilled by Amazon - FREE Shipping']");
	private By Sorting = By.id("s-result-sort-select");
	private By Price = By.className("a-price-whole");
	
	private String Text = "Samsung";
	
	
	public boolean IsFound_TVs(ChromeDriver driver) {
		return driver.findElement(TVs).isDisplayed();
	}
	
	public void ClickOnTVs(ChromeDriver driver) {
		driver.findElement(TVs).click();
	}
	
	public boolean CheckAllItemsAreTV(ChromeDriver driver) {
		boolean result = true;
		
		List<WebElement> TVitemTxt = driver.findElements(TVitems);

		//Loop on all items in the page to check that they have TV in their description
		for (WebElement webElement : TVitemTxt) {
            String txt = webElement.getText();
            result = txt.contains("TV");
            if(!result)
            	return false;
        }
		return true;
	}
	
	public boolean IsFound_SearchTxt(ChromeDriver driver) {
		return driver.findElement(SearchTxt).isDisplayed();
	}
	
	public void SearchbyTxt(ChromeDriver driver) {
		//Search by brand name (Example: Samsung)
		driver.findElement(SearchTxt).sendKeys(Text);
		driver.findElement(SearchButton).click();
	}
	
	public boolean CheckSearchResult(ChromeDriver driver) {
		boolean result = true;
		
		List<WebElement> SearchitemTxt = driver.findElements(Searchitems);
		//Loop on all items in the page to check that they have the correct brand name
		for (WebElement webElement : SearchitemTxt) {
            String txt = webElement.getText();
            result = txt.contains(Text);
            if(!result)
            	return false;
        }
		return true;
	}
	
	public void SelectFulfilledByAmazon(ChromeDriver driver) {
		
		driver.findElement(FulfilledByAmazon).click();
	}
	
	public boolean CheckFulfilledByAmazonItemsDisplayed(ChromeDriver driver) {
		return driver.findElement(FulfilledByAmazonItems).isDisplayed();
	}
	
	public void SortbyPriceLowToHigh(ChromeDriver driver) {
		//Select Price low to high from the dropdown list
		Select dropdown = new Select(driver.findElement(Sorting));
		dropdown.selectByVisibleText("Price: Low to High");
	}
	
	public boolean SortingIsCorrect(ChromeDriver driver) {
		int current_value = 0 ;
		int Prev_value = 0;
		List<WebElement> SortedItems = driver.findElements(Price);
		//Loop on all items and check that the current one has higher price than the previous one
		for (WebElement webElement : SortedItems) {
			
			current_value = Integer.valueOf(webElement.getText());
			
			if(current_value-Prev_value < 0){
				return false;
			}
			Prev_value = current_value;
		}
		return true;
	}
	
}

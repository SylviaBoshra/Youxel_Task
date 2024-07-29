package Task.Youxel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class HomePage 
{
	private String URL = "https://www.amazon.eg/s?language=en_AE";
	private By Electronics = By.xpath("//a[text()='Electronics']");
	private By Grocery = By.xpath("//a[text()='Grocery']");
	private By Item = By.className("s-image");
	private By AddtoCartBtn = By.id("add-to-cart-button");
	private By ShoppingCart = By.id("nav-cart-count");
	
	public void OpenURL(ChromeDriver driver) {
		//Open URL
    	driver.get(URL); 
    }
	
	public boolean IsFound_Electronics(ChromeDriver driver) {
		return driver.findElement(Electronics).isDisplayed();
	}
	
	public void ClickOnElectronics(ChromeDriver driver) {
		driver.findElement(Electronics).click();
	}
	
	public void NavigatetoGrocery(ChromeDriver driver) {
		driver.findElement(Grocery).click();
	}
	
	public int AddItemsToCart(ChromeDriver driver)
	{
		int items_count = 0;
		List<WebElement> Items;
		//Loop on 4 items on the page to be added to the cart
		for (int i=1;i<5;i++) {
			Items = driver.findElements(Item);
			WebElement item = Items.get(i);
			item.click();
			//Check that "add to cart" button is displayed to add the item to the cart and increment the items count
			if(driver.findElements(AddtoCartBtn).size() > 0)
			{
				driver.findElement(AddtoCartBtn).click();
				items_count++;
			}
			driver.findElement(Grocery).click();
		}
		//return number of items added to the cart
		return items_count;
	}
	
	public int GetActualItemsInShoppingCart(ChromeDriver driver)
	{
		//Get the actual number of items added to the cart
		return Integer.valueOf(driver.findElement(ShoppingCart).getText());
	}
	
	
	public void NavigatetoShoppingCart(ChromeDriver driver) {
		driver.findElement(ShoppingCart).click();
	}
	
	
	
	
	
}

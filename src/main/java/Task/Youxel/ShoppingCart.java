package Task.Youxel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ShoppingCart 
{
	private By Price = By.className("sc-white-space-nowrap");
	private By Checkout = By.name("proceedToRetailCheckout");
	
	
	public boolean CheckTotalPrice(ChromeDriver driver)
	{
		List<WebElement> CartItems = driver.findElements(Price);
		//Calculate the number of items in the cart (2 items are removed from the calculation as they are the subtotal
		int CartItemsSize = CartItems.size() - 2;
		
		float CalcTotalPrice = 0;
		
		//Subtotal are displayed 2 times in the screen, get their value
		float Subtotal1 = Float.parseFloat(CartItems.get(0).getText().replace("EGP ", ""));
		float Subtotal2 = Float.parseFloat(CartItems.get(CartItemsSize+1).getText().replace("EGP ", ""));
		
		//Loop on all items and calculate their summ
		for (int i=1;i<=CartItemsSize;i++)
		{
			
			CalcTotalPrice += Float.parseFloat(CartItems.get(i).getText().replace("EGP ", ""));
		}
		
		//Compare the calculated total price by the displayed subtotal price
		if(CalcTotalPrice == Subtotal1 && CalcTotalPrice == Subtotal2)
			return true;
		else
			return false;
		
	}
	
	
	public void ClickonCheckout(ChromeDriver driver)
	{
		driver.findElement(Checkout).click();
	}
	
	
	
	
	
	
	
}

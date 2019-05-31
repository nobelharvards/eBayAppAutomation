package appium;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

public class SampleTestCase extends AppiumDriverBase
{
	@Test
	public void sampleTest() throws InterruptedException
	{
		// Element ID strings
		String signinButtonID = "com.ebay.mobile:id/button_sign_in";
		String signinGoogleButtonID = "com.ebay.mobile:id/button_google";
		String googleAccountNameButtonID = "com.google.android.gms:id/account_name";
		String homeSearchBoxID = "com.ebay.mobile:id/search_box";
		String actualSearchBoxID = "com.ebay.mobile:id/search_src_text";
		String annoyingBlueBoxID = "com.ebay.mobile:id/text_slot_1";
		String addToCartButtonID = "com.ebay.mobile:id/button_add_to_cart";
		String addToCartButtonID2 = "com.ebay.mobile:id/button_add_to_basket";
		String initialItemNameID = "com.ebay.mobile:id/textview_item_name";
		String initialItemPriceID = "com.ebay.mobile:id/textview_item_price";
		String goToCartButtonID = "com.ebay.mobile:id/call_to_action_button";
		String goToCheckoutButtonID = "com.ebay.mobile:id/shopping_cart_checkout";
		String checkoutItemNameID = "com.ebay.mobile:id/item_title";
		String checkoutItemPriceID = "com.ebay.mobile:id/item_price";
		String topLeftHomeButtonID = "com.ebay.mobile:id/home";
		String removeItemFromCartButtonID = "com.ebay.mobile:id/item_action_remove_from_cart";

		// Search term to enter into search box
		String searchTerm = "65 inch TV";
		String initialItemNameText;
		String initialItemPriceText;
		String checkoutItemNameText;
		String checkoutItemPriceText;

		// WebElements
		By signinButton = By.id(signinButtonID);
		By signinGoogleButton = By.id(signinGoogleButtonID);
		By googleAccountNameButton = By.id(googleAccountNameButtonID);
		By homeSearchBox = By.id(homeSearchBoxID);
		By actualSearchBox = By.id(actualSearchBoxID);
		By annoyingBlueBox = By.id(annoyingBlueBoxID);
		By addToCartButton = By.id(addToCartButtonID);
		By addToCartButton2 = By.id(addToCartButtonID2);
		By initialItemName = By.id(initialItemNameID);
		By initialItemPrice = By.id(initialItemPriceID);
		By goToCartButton = By.id(goToCartButtonID);
		By goToCheckoutButton = By.id(goToCheckoutButtonID);
		By checkoutItemName = By.id(checkoutItemNameID);
		By checkoutItemPrice = By.id(checkoutItemPriceID);
		By topLeftHomeButton = By.id(topLeftHomeButtonID);
		By removeItemFromCartButton = By.id(removeItemFromCartButtonID);

		KeyEvent enter = new KeyEvent(AndroidKey.ENTER);

		TouchAction action = new TouchAction(driver);

		SoftAssert sa = new SoftAssert();

		// Waits until the sign in button is located, then clicks on it.
		wait.until(ExpectedConditions.presenceOfElementLocated(signinButton));
		driver.findElement(signinButton).click();

		// Google sign in button
		wait.until(ExpectedConditions.presenceOfElementLocated(signinGoogleButton));
		driver.findElement(signinGoogleButton).click();

		// Account name button
		wait.until(ExpectedConditions.presenceOfElementLocated(googleAccountNameButton));
		driver.findElement(googleAccountNameButton).click();

		// Tap on home search bar
		wait.until(ExpectedConditions.presenceOfElementLocated(homeSearchBox));
		driver.findElement(homeSearchBox).click();

		// Send search term to actual search bar
		driver.findElement(actualSearchBox).sendKeys(searchTerm);
		driver.pressKey(enter);

		// Get rid of annoying blue box that prevents interaction with anything else.
		if (ExpectedConditions.presenceOfElementLocated(annoyingBlueBox) != null)
		{
			driver.findElement(annoyingBlueBox).click();
		}
		/*
		 * wait.until(ExpectedConditions.presenc??eOfElementLocated(annoyingBlueBox));
		 * driver.findElement(annoyingBlueBox).click();
		 */

		// Swipe down
		action.press(PointOption.point(500, 1800)).moveTo(PointOption.point(500, 1720)).release().perform();

		// Wait for scroll inertia to settle
		Thread.sleep(5000);

		// Tap by coordinates.
		action.tap(PointOption.point(600, 1600)).perform();

		wait.until(ExpectedConditions.presenceOfElementLocated(initialItemPrice));
		// Initial item name label
		initialItemNameText = driver.findElement(initialItemName).getText();
		System.out.println("Initial item name: " + initialItemNameText);
		// Initial item price label
		initialItemPriceText = driver.findElement(initialItemPrice).getText();
		System.out.println("Initial item price: " + initialItemPriceText);

		// Small swipe in case add to cart button is off screen
		try
		{
			driver.findElement(addToCartButton).click();
			System.out.println("Add to cart ID.");
		} catch (Exception e)
		{
			/*System.out.println("Cannot find add to cart button, scrolling down.");
			action.press(PointOption.point(500, 1200)).moveTo(PointOption.point(500, 1150)).release().perform();
			Thread.sleep(3000);*/
			System.out.println("Trying add to basket ID.");
			driver.findElement(addToCartButton2).click();
		}
		// Go To Cart button
		wait.until(ExpectedConditions.presenceOfElementLocated(goToCartButton));
		driver.findElement(goToCartButton).click();

		// Go To Checkout button
		wait.until(ExpectedConditions.presenceOfElementLocated(goToCheckoutButton));
		driver.findElement(goToCheckoutButton).click();

		// Checkout name and price labels
		checkoutItemNameText = driver.findElement(checkoutItemName).getText();
		System.out.println("Checkout item name: " + checkoutItemNameText);
		checkoutItemPriceText = driver.findElement(checkoutItemPrice).getText();
		System.out.println("Checkout item price: " + checkoutItemPriceText);

		// Check initial and checkout values are the same
		sa.assertEquals(checkoutItemNameText, initialItemNameText);

		// sa.assertEquals(checkoutItemPriceText, initialItemPriceText);
		// Assert.assertTrue(checkoutItemPriceText.equals(initialItemPriceText));

		Thread.sleep(2000);

		// Remove item from cart after test
		driver.findElement(topLeftHomeButton).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(removeItemFromCartButton));
		driver.findElement(removeItemFromCartButton).click();
		/*
		 * driver.navigate().back(); driver.navigate().back();
		 * driver.findElement(By.id("com.ebay.mobile:id/home")).click();
		 * driver.findElement(By.id("com.ebay.mobile:id/button_toggle")).click();
		 */
		sa.assertAll();
	}

}
package com.visionit.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef1 {
	
	    WebDriver driver;
	    String base_url = "https://amazon.in";
	    int implicit_wait_timeout_in_sec = 20;
	    Scenario scn;
	    
	    
	    @Before
	    public void setUp(Scenario s){
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
	        this.scn= s;
	    }

	    @After
	    public void cleanUp(){
	        driver.quit();
	        scn.log("Browser Closed");
	        
	    }

	
	/*//@Given("User opened browser")
	@Deprecated
	public void user_opened_browser() 
	{
		
	} */


    @Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() 
    {
    	driver.get(base_url);
    	scn.log("Browser navigated to URL: " + base_url);
    	
        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actual =driver.getTitle();
        Assert.assertEquals("Page Title validation",expected,actual);
        scn.log("Page title validation successfull. Actual title: " + actual );

	 }
    
	@When("User Search for product {string}")
	public void user_search_for_product(String productName) 
	{
		
		WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement elementSearchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

        elementSearchBox.sendKeys(productName);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
		
	 }
	 
	
	@Then("Search Result page is displayed")
	public void search_result_page_is_displayed()
	{
		//Wait for title
        WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
        webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in :Laptop"));

        //Assertion for Page Title
        Assert.assertEquals("Page Title validation","Amazon.in : Laptop", driver.getTitle());
		
	 }



     @When("User click on any product")
     public void user_click_on_any_product() 
     {
    	//listOfProducts will have all the links displayed in the search box
         List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));

         //But as this step asks click on any link, we can choose to click on Index 0 of the list
         listOfProducts.get(0).click();
     }
    
     
    @Then("Product Description is displayed in new tab")
    public void product_description_is_displayed_in_new_tab()
    {
        //As product description click will open new tab, we need to switch the driver to the new tab
    	
    	Set<String> handles = driver.getWindowHandles(); 
        Iterator<String> it = handles.iterator(); 
        scn.log("List of windows found: "+handles.size());
        scn.log("Windows handles: " + handles.toString());
        String original = it.next();
        String prodDescp = it.next();
        
        driver.switchTo().window(prodDescp); 
        scn.log("Switched to the new product Description window/tab");
        
        //Now driver can access new driver window, but can not access the orignal tab
        
        //Check product title is displayed
        
        WebElement productTitle = driver.findElement(By.id("productTitle"));
        Assert.assertEquals("Product Title",true,productTitle.isDisplayed());
        scn.log("Product Title header is matched and displayed as: " + productTitle.getText() );

        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Shopping Cart']"));
        Assert.assertEquals("Product Title",true,addToCartButton.isDisplayed());
        scn.log("Add to cart Button is displayed");

        //Switch back to the Original Window, however no other operation to be done
        driver.switchTo().window(original);
        scn.log("Switched back to Original tab");
    }
    	
    	
    	
}	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	

     
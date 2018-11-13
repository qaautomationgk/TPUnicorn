package lib.util;

import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import lib.cisco.util.PropertiesFileReader;
import lib.cisco.util.Report;

public class SearchUtility {

	Report report=new Report();
	WrapperActionTest mondeeInc=new WrapperActionTest();
	ApplicationUtility appUtility=null;
	String ScreenshotName="";
	public WebDriver driver=null;
	public BufferedWriter writer=null;
	public AppTestDTO appDto=null;
	private final static Logger LOGGER = Logger.getLogger(SearchUtility.class.getName());
	private static final long MAX_WAIT_TIME = 0;

	SearchUtility(WebDriver driver,AppTestDTO appDto,WrapperActionTest mondeeInc, Report report,BufferedWriter writer,ApplicationUtility appUtility)
	{
		this.driver = driver;
		this.appDto = appDto;
		this.mondeeInc = mondeeInc;
		this.appUtility = appUtility;
		this.report = report;
		this.writer = writer;
	}

	public void waitInSeconds(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);

		}
	}

	public static  WebDriver getDriver(String Browser){
		WebDriver driver = null;
		if(Browser.equalsIgnoreCase("Firefox")){
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "wwwin-sso-nprd.cisco.com");
			driver = new FirefoxDriver(profile);
		}
		return driver; 

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	/********************************************************************************************
	 *  
	 * @Function_Name :  logMessage
	 * @Description : This method is used write the steps into html report
	 * @version 1.0
	 * @param string 
	 ********************************************************************************************/

	public void logMessage(String strDescr,String strExpected,String Actual,String status)
	{
		try{
			ScreenshotName=appDto.getTestCaseName();
			report.logMessage(driver,writer,ScreenshotName,strDescr,strExpected,Actual, status);
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception found in Initialization of ApplicationUtility class for report log message :",e);
		}
	}


	/********************************************************************************************
	 * @Function_Name :  mondeeInc_DelryDetails_Select_ServiceLevel
	 * @Description : RMA Simplification: Select Service Level should entered Successfully in Delivery Details
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Enter_OriginDestination(){

		try{

			//Verify that ExploreTrip Landing page is loaded Successfully ***** 
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Verify_landPage"))){
				String verfylandingPage= mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Verify_landPage")).getText(); 
				if(verfylandingPage.contains("About ExploreTrip")){
					logMessage("Verify that the ExploreTrip Landing page is loaded", "ExploreTrip Landing page should be loaded Successfully ", "You have Successfully Landed to ExploreTrip", "Passed");
					mondeeInc.waitInSeconds(6);
				}else {
					logMessage("Verify that the ExploreTrip Landing page is loaded", "ExploreTrip Landing page should be loaded Successfully ", "ExploreTrip is NOT loaded Successfully", "Failed");
					Assert.assertTrue(false);
				}
			}

			//Verify and Enter Origin ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_Origin"))){
				logMessage("Verify that the Origin Text box is avaialabe", "Origin Text box should be avaialabe Successfully ", "Origin Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_Origin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that the Passanger Origin is entered", "Passanger Origin should be entered Successfully ", "Passanger Origin box is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passanger Origin is entered", "Passanger Origin should be entered Successfully ", "Passanger Origin box is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Enter Destination ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_Destination"))){
				logMessage("Verify that the Destination Text box is avaialabe", "Destination Text box should be avaialabe Successfully ", "Destination Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that the Passanger Destination is entered", "Passanger Destination should be entered Successfully ", "Passanger Destination is entered successfully as: ", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that the Passanger Destination is entered", "Passanger Destination should be entered Successfully ", "Passanger Destination is NOT entered successfully as: ", "Failed");
				Assert.assertTrue(false);
			}
		}

		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  mondeeInc_DelryDetails_Select_ServiceLevel
	 * @Description : RMA Simplification: Select Service Level should entered Successfully in Delivery Details
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Enter_DepartReturnDates(){

		try{

			//Verify and Enter Origin ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_ClkClose_Popup"))){
				logMessage("Verify that the ExploreTrip Pop Up is avaialabe", "ExploreTrip Pop Up should be avaialabe Successfully ", "ExploreTrip Pop Up is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_ClkClose_Popup")).click();
				logMessage("Verify that the ExploreTrip Pop Up is clicked", "ExploreTrip Pop Up should be clicked Successfully ", "ExploreTrip Pop Up is clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
			}

			//Verify and Enter Origin ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_DepartDate"))){
				logMessage("Verify that the Depart date Text box is avaialabe", "Depart date Text box should be avaialabe Successfully ", "Depart date Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_DepartDate")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[35]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[35]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[35]/div[1]/table/tbody/tr[2]/td[3]")).click();

				logMessage("Verify that the Passanger Depart date is entered", "Passanger Depart date should be entered Successfully ", "Passanger Depart date is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passanger Depart date is entered", "Passanger Depart date should be entered Successfully ", "Passanger Depart date is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Enter Destination ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_ReturnDate"))){
				logMessage("Verify that the Return date Text box is avaialabe", "Return date Text box should be avaialabe Successfully ", "Return date Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_ReturnDate")).click();
				driver.findElement(By.xpath("html/body/div[35]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[35]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[35]/div[1]/table/tbody/tr[2]/td[3]")).click();

				logMessage("Verify that the Passanger Return date is entered", "Passanger Return date should be entered Successfully ", "Passanger Return date is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passanger Return date is entered", "Passanger Return date should be entered Successfully ", "Passanger Return date is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Select Passenger Class ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_Class"))){
				logMessage("Verify that the Passenger Class is avaialabe", "Passenger Class should be avaialabe Successfully ", "Passenger Class is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_Class")).click();
				logMessage("Verify that the Passenger Class is clicked", "Passenger Class should be clicked Successfully ", "Passenger Class is clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Class")).sendKeys("First");
				logMessage("Verify that the Passenger Class is selected", "Passenger Class should be selected Successfully ", "Passenger Class is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Class")).sendKeys("Economy");
				logMessage("Verify that the Passenger Class is selected", "Passenger Class should be selected Successfully ", "Passenger Class is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passanger Return date is entered", "Passanger Return date should be entered Successfully ", "Passanger Return date is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}
		}

		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	/********************************************************************************************
	 * @Function_Name :  mondeeInc_DelryDetails_Select_ServiceLevel
	 * @Description : RMA Simplification: Select Service Level should entered Successfully in Delivery Details
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Clk_Serach(){

		try{

			//Verify and Click on Search button ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_Search"))){
				logMessage("Verify that the Search button is avaialabe", "Search button should be avaialabe Successfully ", "Search button is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_Search")).click();
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is clicked Successfully", "Passed");

				long start = System.currentTimeMillis();
				WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='resetAllFilters']")));

				//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath((".//*[@id='resetAllFilters']"))));
				long finish = System.currentTimeMillis();
				long totalTimeInMiilis = finish - start; 
				long minutes = (totalTimeInMiilis / 1000) / 60; 
				long seconds = (totalTimeInMiilis / 1000) %60;
				long miilis  = (totalTimeInMiilis)%1000;
				System.out.println("Total Time taken to load Search results page - " + minutes + " min " + seconds + " sec " +  miilis + " milliseconds");
				logMessage("Verify that the Search results page displayed", "Total Time taken to Search results page should get displayed", "Total Time to Search results Page# " + + minutes + " min " + seconds + " sec " + miilis + " milliseconds", "Passed");
				mondeeInc.waitInSeconds(8);
				System.out.println("\n");
			}else {
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}

			String totalItinerarys =  driver.findElement(By.xpath(".//*[@id='flightResultContentContainer']/div[4]")).getText();
			System.out.println("Total # of Itinerarys: " + totalItinerarys);


			/*List<WebElement> list1 = driver.findElements(By.xpath(".//*[contains(@id,'bookNow')]"));  
			System.out.println("Total # of Itinerarys: " +list1.size());
			//System.out.println("ID:" + list);
			System.out.println("\n");*/

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  mondeeInc_DelryDetails_Select_ServiceLevel
	 * @Description : RMA Simplification: Select Service Level should entered Successfully in Delivery Details
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Select_AirLines(){

		try{

			//Verify and Click on Search button ****
			/*if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_Search"))){
				logMessage("Verify that the Search button is avaialabe", "Search button should be avaialabe Successfully ", "Search button is avaialabe Successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='onlyAirlinesCheckbox_EK']")).click();
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(10);
			}else {
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}*/

			//This will scroll until the element is in view ***
			WebElement element = driver.findElement(By.id("airlineNoneSelect"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			mondeeInc.waitInSeconds(2);

			driver.findElement(By.xpath(".//*[@id='showAllAirlines']")).click();
			mondeeInc.waitInSeconds(2);

			//This will scroll until the element is in view ***
			WebElement element1 = driver.findElement(By.id("stopsCountNone"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			mondeeInc.waitInSeconds(2);
			driver.findElement(By.id("airlineNoneSelect")).click();
			mondeeInc.waitInSeconds(3);

			List<WebElement> list = driver.findElements(By.xpath(".//*[contains(@id,'airlinesCheckbox_')]"));
			System.out.println("Total # of Airlines :" +list.size());

			for(WebElement webElement : list)
			{
				if(webElement.getText().equals("AI"))
				{
					webElement.click();
					webElement.click();	
				}
			}

			driver.findElement(By.xpath(".//*[@id='airlinesCheckbox_EK']")).click();
			mondeeInc.waitInSeconds(2);
			logMessage("Verify that the Search button is clicked", "Airlines should be selected Successfully ", "AirLines is selected Successfully", "Passed");
			mondeeInc.waitInSeconds(5);

			List<WebElement> list1 = driver.findElements(By.xpath(".//*[contains(@id,'bookNow')]"));  
			System.out.println("Total # of Emirates Itinerarys: " +list1.size()/2);
			//System.out.println("ID:" + list);
			System.out.println("\n");

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  mondeeInc_DelryDetails_Select_ServiceLevel
	 * @Description : RMA Simplification: Select Service Level should entered Successfully in Delivery Details
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Select_Itinerary(){

		try{

			List<WebElement> list1 = driver.findElements(By.xpath(".//*[contains(@xpath,'itinerary_')]"));  
			//System.out.println("Total # of Emirates Itinerarys: " +list1.size());
			list1.get(2).click();
			//System.out.println("ID:" + list1);
			//System.out.println("\n");
			logMessage("Verify that the Itinerary is clicked", "Itinerary should be selected Successfully ", "Itinerary is selected Successfully", "Passed");



			//			WebElement boknow = list2.get(3);
			//			System.out.println("Ele: " + boknow);
			//			boknow.getSize();
			//			boknow.click();

			//Verify and Click on Search button ****
			/*if     (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_Search"))){
				logMessage("Verify that the Search button is avaialabe", "Search button should be avaialabe Successfully ", "Search button is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_Search")).click();
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(60);
			}else {
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}*/

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	/********************************************************************************************
	 * @Function_Name :  skyScanner_Enter_OriginDestination
	 * @Description : RMA Simplification: Select Service Level should entered Successfully in Delivery Details
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void skyScanner_SearchforExploreTrip(){

		try{

			//Verify that SkyScanner Landing page is loaded Successfully ***** 
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("skyScanner_Verify_landPage"))){
				String verfylandingPage= mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Verify_landPage")).getText(); 
				System.out.println("landingpage: " + verfylandingPage);
				if(verfylandingPage.contains("About ExploreTrip")){
					logMessage("Verify that the SkySanner Landing page is loaded", "SkySanner Landing page should be loaded Successfully ", "You have Successfully Landed to SkySanner", "Passed");
					mondeeInc.waitInSeconds(6);
				}else {
					logMessage("Verify that the SkySanner Landing page is loaded", "SkySanner Landing page should be loaded Successfully ", "SkySanner is NOT loaded Successfully", "Failed");
					Assert.assertTrue(false);
				}
			}

			//Verify and Enter Origin ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("skyScanner_Entr_Origin"))){
				logMessage("Verify that the Origin Text box is avaialabe", "Origin Text box should be avaialabe Successfully ", "Origin Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Entr_Origin")).clear();
				mondeeInc.waitInSeconds(2);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Entr_Origin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that the Passanger Origin is entered", "Passanger Origin should be entered Successfully ", "Passanger Origin box is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passanger Origin is entered", "Passanger Origin should be entered Successfully ", "Passanger Origin box is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Enter Destination ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("skyScanner_Entr_Destination"))){
				logMessage("Verify that the Destination Text box is avaialabe", "Destination Text box should be avaialabe Successfully ", "Destination Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Entr_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Entr_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that the Passanger Destination is entered", "Passanger Destination should be entered Successfully ", "Passanger Destination is entered successfully as: ", "Passed");
				mondeeInc.waitInSeconds(5);
			}else {
				logMessage("Verify that the Passanger Destination is entered", "Passanger Destination should be entered Successfully ", "Passanger Destination is NOT entered successfully as: ", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Enter Origin date ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("skyScanner_Entr_DepartDate"))){
				logMessage("Verify that the Depart date Text box is avaialabe", "Depart date Text box should be avaialabe Successfully ", "Depart date Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Entr_DepartDate")).click();
				driver.findElement(By.xpath(".//*[@id='category-flights']/div[14]/div/div/div[2]/div/section/div[1]/button[2]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='category-flights']/div[14]/div/div/div[2]/div/section/div[1]/button[2]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='category-flights']/div[14]/div/div/div[2]/div/section/div[2]/table/tbody/tr[1]/td[4]/a")).click();

				logMessage("Verify that the Passanger Depart date is entered", "Passanger Depart date should be entered Successfully ", "Passanger Depart date is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passanger Depart date is entered", "Passanger Depart date should be entered Successfully ", "Passanger Depart date is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Enter Destination date ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("skyScanner_Entr_ReturnDate"))){
				logMessage("Verify that the Return date Text box is avaialabe", "Return date Text box should be avaialabe Successfully ", "Return date Text box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Entr_ReturnDate")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath(".//*[@id='category-flights']/div[15]/div/div/div[2]/div/section/div[1]/button[2]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='category-flights']/div[15]/div/div/div[2]/div/section/div[1]/button[2]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='category-flights']/div[15]/div/div/div[2]/div/section/div[1]/button[2]")).click();

				logMessage("Verify that the Passanger Return date is entered", "Passanger Return date should be entered Successfully ", "Passanger Return date is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passanger Return date is entered", "Passanger Return date should be entered Successfully ", "Passanger Return date is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Select Passenger Class ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("skyScanner_Clk_Class"))){
				logMessage("Verify that the Passenger Class is avaialabe", "Passenger Class should be avaialabe Successfully ", "Passenger Class is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Clk_Class")).click();
				logMessage("Verify that the Passenger Class is clicked", "Passenger Class should be clicked Successfully ", "Passenger Class is clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Entr_Origin")).click();
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that the Passanger Return date is entered", "Passanger Return date should be entered Successfully ", "Passanger Return date is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify and Click on Search button ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("skyScanner_Clk_Search"))){
				logMessage("Verify that the Search button is avaialabe", "Search button should be avaialabe Successfully ", "Search button is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("skyScanner_Clk_Search")).click();
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is clicked Successfully", "Passed");

				long start = System.currentTimeMillis();
				WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='fareAlertSubscribeId']")));
				long finish = System.currentTimeMillis();
				long totalTimeInMiilis = finish - start; 
				long minutes = (totalTimeInMiilis / 1000) / 60; 
				long seconds = (totalTimeInMiilis / 1000) %60;
				long miilis  = (totalTimeInMiilis)%1000;
				System.out.println("Total Time taken to load Search results page - " + minutes + " min " + seconds + " sec " +  miilis + " milliseconds");
				logMessage("Verify that the Search results page displayed", "Total Time taken to Search results page should get displayed", "Total Time to Search results Page# " + + minutes + " min " + seconds + " sec " + miilis + " milliseconds", "Passed");
				mondeeInc.waitInSeconds(5);
				System.out.println("\n");
			}else {
				logMessage("Verify that the Search button is clicked", "Search button should be clicked Successfully ", "Search button is NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}

		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}
















}

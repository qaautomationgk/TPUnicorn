package lib.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import lib.cisco.util.PropertiesFileReader;
import lib.cisco.util.Report;

public class BookItineraryUtility {

	Report report=new Report();
	WrapperActionTest mondeeInc=new WrapperActionTest();
	ApplicationUtility appUtility=null;
	String ScreenshotName="";
	public WebDriver driver=null;
	public BufferedWriter writer=null;
	public AppTestDTO appDto=null;
	private final static Logger LOGGER = Logger.getLogger(BookItineraryUtility.class.getName());
	private static final long MAX_WAIT_TIME = 0;

	BookItineraryUtility(WebDriver driver,AppTestDTO appDto,WrapperActionTest mondeeInc, Report report,BufferedWriter writer,ApplicationUtility appUtility)
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
	 * @Function_Name :  mondeeInc_ReviewSubmit_Clk_ReviewSubmitContinue#
	 * @Description : RMA Simplification: Click on Delivery Details Continue button Successfully
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized String exploreTrip_ConfimBooking(){
		String rmaNumber = null;

		try{

			//Verify that Review & Submit Landing page is loaded Successfully ***** 
			//Verify & Click Confirm Booking ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_ConfimBooking"))){
				logMessage("Verify that the Passenger Confirm Booking button is avaialabe", "Passenger Confirm Booking button should be avaialabe Successfully ", "Passenger Confirm Booking button is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_ConfimBooking")).click();
				logMessage("Verify that the Passenger Confirm Booking button is entered", "Passenger Confirm Booking button should be entered Successfully ", "Passenger Confirm Booking button is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger Confirm Booking button is entered", "Passenger Confirm Booking button should be entered Successfully ", "Passenger Confirm button Booking is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}
			
			//Click on  Review & Submit Continue button Successfully ***** 
			/*if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("mondeeInc_ReviewSubmit_Clk_ReviewSubmitContinue"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("mondeeInc_ReviewSubmit_Clk_ReviewSubmitContinue")).click();
				logMessage("Verify that Click on Revivew & Submit Continue button", "Revivew & Submit Continue button should Clicked Successfully", "Revivew & Submit Continue button is Clicked Successfully", "Passed");
				long start = System.currentTimeMillis();
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@id='RULE_KEY']/div/div/div/div/div/div/div/div/div/div)[22]")));
				long finish = System.currentTimeMillis();
				long totalTimeInMiilis = finish - start; 
				long minutes = (totalTimeInMiilis / 1000) / 60; 
				long seconds = (totalTimeInMiilis / 1000) %60;
				long miilis  = (totalTimeInMiilis)%1000;
				String rmaNum= mondeeInc.findElement(driver, PropertiesFileReader.getProperty("mondeeInc_Get_RMANum")).getText(); 
				
				//String test = "RMA # 88903188 Submitted";
				String arr[] = rmaNum.split(" ");
				rmaNumber = arr[2];
				System.out.println("RMA Num: "+ rmaNumber);

				System.out.println("Total Time taken to Create RMA# - " + minutes + " min " + seconds + " sec " +  miilis + " milliseconds");
				logMessage("Verify that the to Create RMA#", "Total Time taken to Create RMA# should get displayed", "Total Time to to Create RMA# " + + minutes + " min " + seconds + " sec " + miilis + " milliseconds", "Passed");
				logMessage("Verify that WareHouse Details got mached with Excepted", "WareHouse Details should be get match with Excepted WeareHouse", "Passed Test Script # ", "Passed");
				//logMessage("Verify that WareHouse Details got matched with Excepted", "WareHouse Details should be get match with Excepted WeareHouse", "WareHouse Details are matched with Excepted WeareHouse, Passed RMA# " + orderNumber, "Passed");
				logMessage("Verify that the Order Number Created", "RMA Number should be loaded Successfully ", "RMA # got Created Successfully: " +rmaNumber, "Passed");
				mondeeInc.waitInSeconds(4);
				System.out.println("\n");
			}else {
				logMessage("Verify that WareHouse Details got mached with Excepted", "WareHouse Details should be get match with Excepted WeareHouse", "Failed Test Script # " , "Failed");
				logMessage("Verify that the Review & Submit displayed ", "Review & Submit page should displayed Successfully", "Review & Submit button is NOT displayed Successfully", "Failed");
				Assert.assertTrue(false);
			}*/
			appUtility.deleteAllCookies(); 
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
		return rmaNumber;
	}
	
	
	
}
package lib.util;

import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.Driver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.sound.sampled.Port;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import oracle.jdbc.proxy.annotation.GetProxy;

import org.omg.CORBA.SetOverrideType;
//import org.openqa.jetty.html.Page;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.AddApplicationCache;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.remote.server.handler.GetPageSource;
import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.server.browserlaunchers.DrivenSeleniumLauncher;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.seleniumhq.jetty9.server.ResourceCache.Content;
//import org.springframework.test.AssertThrows;
import org.testng.Assert;

//import resources.DefectDetailsDriver;

//import resources.UtilLib;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import com.cisco.testscripts.ProductionValidation.ProdValidation_1011094_DutyManager_Reports;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
/*import com.thoughtworks.selenium.webdriven.commands.GetText;
import com.thoughtworks.selenium.webdriven.commands.GetTitle;
import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;*/

import lib.cisco.util.LocalDriverFactory;
import lib.cisco.util.PropertiesFileReader;
import lib.cisco.util.Report;


public class ApplicationUtility {


	Report report=new Report();
	WrapperActionTest mondeeInc=new WrapperActionTest();
	String ScreenshotName="";
	public  WebDriver driver=null;
	public BufferedWriter writer=null;
	public AppTestDTO appDto=null;
	private final static Logger LOGGER = Logger.getLogger(ApplicationUtility.class.getName());
	private static final long MAX_WAIT_TIME = 0;

	/********************************************************************************************
	 *  
	 * @Function_Name :  ApplicationUtility
	 * @Description : Initialization the Report LocalDriverFactory and AppTestDTO
	 * @version 2.0
	 ********************************************************************************************/
	public ApplicationUtility(Report r,LocalDriverFactory localDriverFactory,AppTestDTO appDto)
	{
		try{
			this.appDto=appDto;
			this.writer=r.createlogfile(appDto.getTestCaseName(),appDto.getTestCaseName());
			//this.driver =localDriverFactory.getDriver(appDto.getBrowserType(),appDto.getGridUrl());
			//			this.driver =localDriverFactory.getDriver(appDto.getBrowserType(),"http://bhive-beta.cisco.com/getsession");
			//			this.driver =localDriverFactory.getDriver(appDto.getBrowserType(),"http://10.65.211.73:4444/wd/hub");
                			this.driver = getDriver(appDto.getBrowserType());


			//			this.driver = localDriverFactory.getDriver(appDto);
			//			this.driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			//			if(appDto.getAppType().equalsIgnoreCase("Web")){			
			//			this.driver.get(appDto.getUrl());
		}
		catch(Exception e)
		{	LOGGER.log(Level.SEVERE, "Exception found in Initialization of ApplicationUtility class :",e);
		logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+e.getMessage(), "failed");

		}
	}

	public static void shutDownDriver(WebDriver driver) {
		if (driver != null)
			driver.quit();
	}

	public void waitInSeconds(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);

		}
	}

	/*public static  WebDriver getDriver(String Browser){
		WebDriver driver = null;
		if(Browser.equalsIgnoreCase("Firefox")){
			/*FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "wwwin-sso-nprd.cisco.com");
			driver = new FirefoxDriver(profile);
			driver = new FirefoxDriver();
		}

		return driver; 

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}*/

	/*public static WebDriver getDriver(String Browser){
		if (Browser.toLowerCase().contains("firefox")) {
			LOGGER.log(Level.INFO, "Executing on FireFox");
			driver = new FirefoxDriver();
			//System.out.println("Driver::"+driver);
		}
		return driver; 

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}*/

	public WebDriver getDriver(String browserName)throws Exception
	{
		Runner.countersDto.setGridUsage("No");
		if(browserName.toLowerCase().contains("firefox"))
		{
			LOGGER.log(Level.INFO, "Executing on FireFox");
			System.setProperty("webdriver.gecko.driver", (new StringBuilder()).append(System.getProperty("user.dir")).append("\\firefox").toString());
			driver = new FirefoxDriver();
		}
		if(browserName.toLowerCase().contains("iexplorer"))
		{
			LOGGER.log(Level.INFO, " Executing on IE");
			System.setProperty("webdriver.ie.driver", (new StringBuilder()).append(System.getProperty("user.dir")).append("\\IEDriverServer.exe").toString());
			DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
			capab.setCapability("ignoreProtectedModeSettings", true);
			driver = new InternetExplorerDriver(capab);
		}
		if(browserName.toLowerCase().contains("chrome"))
		{
			LOGGER.log(Level.INFO, " Executing on Chrome");
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver", (new StringBuilder()).append(System.getProperty("user.dir")).append("\\chromedriver.exe").toString());
			driver = new ChromeDriver();
		}
		return driver;
	}

	/********************************************************************************************
	 *  
	 * @Function_Name : dutyMgr_Get_BKO_Status
	 * @Description : routing: Planner search for BKO & BKL Resolve Back Orders too
	 * * @param TestCase_Name - Name of the TestCase
	 * * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * @return 
	 ********************************************************************************************/
	public synchronized void deleteAllCookies(){

		try{
			driver.manage().deleteAllCookies();
			mondeeInc.waitInSeconds(4);
		}	
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	/********************************************************************************************
	 *  
	 * @Function_Name :  logMessage
	 * @Description : This method is used write the steps into html report
	 * @version 1.0
	 * @param string 
	 ********************************************************************************************/

	public void logMessage(String strDescr,String strExpected,String Actual,String status){
		try{
			ScreenshotName=appDto.getTestCaseName();
			report.logMessage(driver,writer,ScreenshotName,strDescr,strExpected,Actual, status);
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception found in Initialization of ApplicationUtility class for report log message :",e);
		}
	}

	public boolean isAlertPresent() {
		boolean presentFlag = false;
		try {

			// Check the presence of alert
			Alert alert = driver.switchTo().alert();
			// Alert present; set the flag
			presentFlag = true;
			// if present consume the alert
			alert.accept();
			//( Now, click on ok or cancel button )

		} catch (NoAlertPresentException ex) {
			// Alert not present
			//ex.printStackTrace();
		}
		return presentFlag;
	}


	/*
	 ********************************************************************************************************************************************************
								@@@@@@@@@@@@@@@@@@@@@@@@@@  TripPro Unicorn  @@@@@@@@@@@@@@@@@@@@@@@@@@ 
	 ********************************************************************************************************************************************************/


	/********************************************************************************************
	 * @Function_Name :  Launch portal
	 * @Description : Launch portal
	 * @author Kalyan Gumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void launchPortal(){

		try{
			// Getting URL from Database *****
			mondeeInc.startBrowser(driver, appDto.getUrl());
			logMessage("Verify that the Browser is avaialabe", "Browser should be avaialabe Successfully ", appDto.getUrl() + "  is lanched Successfully", "Passed");
			mondeeInc.waitInSeconds(4);

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  Close menu tabs
	 * @Description : Close menu tabs
	 * @author Kalyan Gumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void tripProUnicorn_closeTabs(){

		try{
			// Close menu tabs *****
			while (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_closeTabs"))){
				try {
					mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_closeTabs")).click();
					logMessage("Verify that select Close menu tabs  is avaialabe & clicked", "Close menu tabs  should be avaialabe & Clicked successfully","Close menu tabs  is be avaialabe & clicked successfully", "Passed");
					mondeeInc.waitInSeconds(2);
				}
				catch(Exception ex) {
					logMessage("Verify that select Close menu tabs is avaialabe & clicked", "Close menu tabs should be avaialabe & Clicked successfully","Tabs are NOT be avaialabe to close", "Passed");
				}
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
	 * @Function_Name :  pnandg_DelryDetails_Select_ServiceLevel
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


	public synchronized void tripProUnicorn_menus(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_menus();
	}

	public synchronized void tripProUnicorn_enrollment(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_enrollment();
	}

	public synchronized void tripProUnicorn_login(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_login();
	}

	public synchronized void tripProUnicorn_selectSabre(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectSabre();
	}

	public synchronized void tripProUnicorn_selectAmadeus(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectAmadeus();
	}

	public synchronized void tripProUnicorn_selectFlights(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectFlights();
	}
	
	public synchronized void tripProUnicorn_selectGroupTravel(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectGroupTravel();
	}

	public synchronized void tripProUnicorn_selectHotles(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectHotles();
	}

	public synchronized void tripProUnicorn_selectUserManagement(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectUserManagement();
	}
	
	public synchronized void tripProUnicorn_selectTrips(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectTrips();
	}
	

	public synchronized void tripProUnicorn_selectFinance(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectFinance();
	}
	
	public synchronized void tripProUnicorn_selectFinanceDispute(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectFinanceDispute();
	}
	
	public synchronized void tripProUnicorn_selectFinanceCustomerStatement(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectFinanceCustomerStatement();
	}
	
	public synchronized void tripProUnicorn_selectSites(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectSites();
	}
	
	public synchronized void tripProUnicorn_selectBuild(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectBuild();
	}
	
	public synchronized void tripProUnicorn_selectPages(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectPages();
	}
	
	public synchronized void tripProUnicorn_selectFlightBooking(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectFlightBooking();
	}
	
	public synchronized void tripProUnicorn_selectMarkupManagement(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectMarkupManagement();
	}
	
	public synchronized void tripProUnicorn_selectHotelBookingReport(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectHotelBookingReport();
	} 
	
	public synchronized void tripProUnicorn_selectProfile(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectProfile();
	}
	
	public synchronized void tripProUnicorn_Signout(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Signout();
	}

	public synchronized void mondeeInc_selectCustomerCare(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.mondeeInc_selectCustomerCare();
	}

	public synchronized void mondeeInc_selectDebitMemos(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.mondeeInc_selectDebitMemos();
	}

	public synchronized void tripProUnicorn_selectReports(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectReports();
	}

	public synchronized void tripProUnicorn_selectFlightBookingReports(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectFlightBookingReports();
	}

	public synchronized void tripProUnicorn_Menu_HotelBookingReports(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Menu_HotelBookingReports();
	}

	public synchronized void tripProUnicorn_Menu_BookingFailureReports(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Menu_BookingFailureReports();
	}

	public synchronized void tripProUnicorn_selectMarketing(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectMarketing();
	}

	public synchronized void tripProUnicorn_selectMarketingResources(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectMarketingResources();
	}

	public synchronized void tripProUnicorn_selectMarketingCampaign(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectMarketingCampaign();
	}

	public synchronized void tripProUnicorn_selectMarketingContacts(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectMarketingContacts();
	}

	public synchronized void tripProUnicorn_selectMarketingEmailTemplates(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectMarketingEmailTemplates();
	}

	public synchronized void tripProUnicorn_selectTutorials(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectTutorials();
	}

	public synchronized void tripProUnicorn_selectQuickStartGuide(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_selectQuickStartGuide();
	}

	public synchronized void tripProUnicorn_searchSabre(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_searchSabre();
	}

	public synchronized void tripProUnicorn_Flight_Search(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_Search();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_1(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_1();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_2(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_2();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_3(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_3();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_4(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_4();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_5(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_5();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_6(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_6();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_7(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_7();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_8(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_8();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_9(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_9();
	}
	
	public synchronized void tripProUnicorn_Flight_MultipleSearch_10(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_MultipleSearch_10();
	}
	
	public synchronized void tripProUnicorn_Flight_verifysearchResultsData(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_verifysearchResultsData();
	}
	
	public synchronized void tripProUnicorn_SendAgentMarkupThroughEmailID(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_SendAgentMarkupThroughEmailID();
	}

	public synchronized void tripProUnicorn_Flight_selectItinerary(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_selectItinerary();
	}

	public synchronized void tripProUnicorn_Flight_passengerInfo(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_passengerInfo();
	}

	public synchronized void tripProUnicorn_Flight_paymentInfo(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_paymentInfo();
	}

	public synchronized void tripProUnicorn_Flight_billingInfo(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_billingInfo();
	}

	public synchronized void tripProUnicorn_Flight_contactInfo(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_contactInfo();
	}

	public synchronized void tripProUnicorn_Flight_orderTicket(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_orderTicket();
	}

	public synchronized void tripProUnicorn_Flight_shift_1(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_1();
	}

	public synchronized void tripProUnicorn_Flight_shift_2(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_2();
	}

	public synchronized void tripProUnicorn_Flight_shift_3(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_3();
	}

	public synchronized void tripProUnicorn_Flight_shift_4(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_4();
	}

	public synchronized void tripProUnicorn_Flight_shift_5(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_5();
	}

	public synchronized void tripProUnicorn_Flight_shift_6(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_6();
	}

	public synchronized void tripProUnicorn_Flight_shift_7(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_7();
	}

	public synchronized void tripProUnicorn_Flight_shift_8(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_8();
	}

	public synchronized void tripProUnicorn_Flight_shift_9(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_9();
	}

	public synchronized void tripProUnicorn_Flight_shift_0(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_0();
	}

	public synchronized void tripProUnicorn_Flight_shift_IG(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_IG();
	}

	public synchronized void tripProUnicorn_Flight_shift_M(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_M();
	}

	public synchronized void tripProUnicorn_Flight_shift_R(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_R();
	}

	public synchronized void tripProUnicorn_Flight_shift_X(){
		TripProUnicornUtility tripProUnicornUtility = new TripProUnicornUtility(driver,appDto,mondeeInc,report,writer,this);
		tripProUnicornUtility.tripProUnicorn_Flight_shift_X();
	}
	







}

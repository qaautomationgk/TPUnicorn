package lib.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.Select;

import lib.cisco.util.Report;



public class WrapperActionTest {
	//public WebDriver driver = null;
	public WebElement webelement;
	private final static Logger LOGGER = Logger.getLogger(WrapperActionTest.class.getName());

	public void startBrowser(WebDriver driver, String url) throws Exception {

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
	}

	public boolean isElementPresent(WebDriver driver,String locator)throws Exception{
		List<WebElement> arrElements = findElements(driver,locator);
		if (arrElements.size() > 0){
			return true;
		}
		return false;		
	}
	public List<WebElement> findElements(WebDriver driver,String locator) {
		List<WebElement> webelements=null;
		if (locator != null) {
			String[] arrLocator = locator.split("==");
			String locatorTag = arrLocator[0].trim();
			String objectLocator = arrLocator[1].trim();

			if (locatorTag.equalsIgnoreCase("id")) {
				webelements = driver.findElements(By.id(objectLocator));
			} else if (locatorTag.equalsIgnoreCase("name")) {
				webelements = driver.findElements(By.name(objectLocator));
			} else if (locatorTag.equalsIgnoreCase("xpath")) {
				webelements = driver.findElements(By.xpath(objectLocator));
			} else if (locatorTag.equalsIgnoreCase("linkText")) {
				webelements = driver.findElements(By.linkText(objectLocator));
			} else if (locatorTag.equalsIgnoreCase("class")) {
				webelements = driver.findElements(By.className(objectLocator));
			} else if(locatorTag.equalsIgnoreCase("cssSelector")){
				webelement = driver.findElement(By.cssSelector(objectLocator));
			}else {
				System.out.println("Please Check the Locator Syntax Given :"+ locator);
				//WrapperActions.logMessage("Please Check the Locator Syntax(Path) Given : "+locator, "Element not found.","failed");
				String error="Please Check the Locator Syntax Given :"+ locator;
				error = error.replaceAll("'", "\"");
				error = Report.getErrorMsg() + ";" + error;
				Report.setErrorMsg(error);
				return null;
			}
		}
		return webelements;
	}

	public boolean isElementPresent(WebDriver driver,String message,String field){
		try {
			WebElement element = null;
			element= findElement(driver,field);
			if(element != null && field != null){
				//logMessage(message+" should be displayed.", message+" is displayed.", "Pass");
				return true;
			}else{
				//logMessage(message+" should be displayed.", message+" is not displayed.", "fail");
				return false;
			}
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			//logMessage(message+" should be displayed.", "Exception occurred.Exception : "+e.getMessage(), "fail");
			return false;
		}
	}

	public WebElement findElement(WebDriver driver,String locator) {
		WebElement webelement = null;
		if (locator != null) {
			String[] arrLocator = locator.split("==");
			String locatorTag = arrLocator[0].trim();
			String objectLocator = arrLocator[1].trim();
			try {
				if (locatorTag.equalsIgnoreCase("id")) {
					webelement = driver.findElement(By.id(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("name")) {
					webelement = driver.findElement(By.name(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("xpath")) {
					webelement = driver.findElement(By.xpath(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("linkText")) {
					webelement = driver.findElement(By.linkText(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("class")) {
					webelement = driver.findElement(By.className(objectLocator));
				} else if(locatorTag.equalsIgnoreCase("cssSelector")){
					webelement = driver.findElement(By.cssSelector(objectLocator));
				}else {
					String error = "Please Check the Given Locator Syntax :"+ locator;
					error = error.replaceAll("'", "\"");
					error = Report.getErrorMsg() + ";" + error;
					Report.setErrorMsg(error);

					return null;
				}
			} catch (Exception exception) {
				//exception.printStackTrace();
				String error = "Please Check the Given Locator Syntax :"+ locator;
				System.out.println("error=="+error);
				error = error.replaceAll("'", "\"");
				error = Report.getErrorMsg() + ";" + error;
				Report.setErrorMsg(error);
				return null;
			}
		}
		return webelement;
	}

	public String getText(WebDriver driver,String locator) {
		WebElement element;
		String text = "NO VALUE RETRIVED";
		try{
			element = findElement(driver,locator, true);
			if (element != null){
				text = element.getText();
			}
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
		}
		element = null;
		return text;
	}

	public WebElement findElement(WebDriver driver,String locator, boolean val) {
		WebElement webelement = null;
		if (locator != null) {
			String[] arrLocator = locator.split("==");
			String locatorTag = arrLocator[0].trim();
			String objectLocator = arrLocator[1].trim();
			try {
				if (locatorTag.equalsIgnoreCase("id")) {
					webelement = driver.findElement(By.id(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("name")) {
					webelement = driver.findElement(By.name(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("xpath")) {
					webelement = driver.findElement(By.xpath(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("linkText")) {
					webelement = driver.findElement(By.linkText(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("class")) {
					webelement = driver.findElement(By.className(objectLocator));
				}else if(locatorTag.equalsIgnoreCase("tagname")){
					webelement = driver.findElement(By.tagName(objectLocator));
				}else if(locatorTag.equalsIgnoreCase("cssSelector")){
					webelement = driver.findElement(By.cssSelector(objectLocator));
				}
			} catch (Exception exception) {
				return null;
			}
		}
		return webelement;
	}
	public static void shutDownDriver(WebDriver driver) {
		if (driver != null)
			driver.quit();
	}

	public void click(WebDriver driver,String field)throws Exception {
		WebElement element = findElement(driver,field);
		element.click();
	}
	public void clearText(WebDriver driver,String field) {
		try {
			WebElement element = findElement(driver,field);
			element.clear();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);
		}
	}

	public void enterText(WebDriver driver,String field, String value) {

		try {
			WebElement element = findElement(driver,field);
			element.sendKeys(value);

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);
		}
	}

	public void waitInSeconds(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);

		}
	}


	/********************************************************************************************
	 * @Function_Name :  FindSysTimeExecution
	 * @Description : Find systime in specified format

	 ***************************************************************************************/

	public static String FindSysTimeExecution(){

		SimpleDateFormat SysDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return SysDate.format(cal.getTime());

		//return SysDate;
	}

	public static String getCurrentTime(long tcStartTime){
		String todayTime="";
		SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("dd-MM-yyyy");
		String todayDate = DATE_FORMAT1.format(new Date());
		Date d1=new Date(tcStartTime);
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh-mm-ss");
		String time = DATE_FORMAT.format(d1);
		todayTime=todayDate+"-"+time;

		return todayTime;
	}

	/********************************************************************************************
	 * @Function_Name :  selectDropDrownValue
	 * @Description : Select Drop Drown Value from the list

	 ***************************************************************************************/

	public boolean selectDropDrownValue(WebDriver driver,String field, String value) {
		try {
			WebElement element = findElement(driver,field);

			if (element != null) {
				Select selectBox = new Select(element);
				List<WebElement> listValues = selectBox.getOptions();
				String eleValue = "";
				for (int i = 0; i < listValues.size(); i++) {
					eleValue = listValues.get(i).getText();
					eleValue = eleValue.replaceAll(" ", "");
					value = value.replaceAll(" ", "");
					// System.out.println(eleValue+" : "+value);
					if (eleValue.trim().equalsIgnoreCase(value)) {
						selectBox.selectByIndex(i);
						return true;
					}
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/********************************************************************************************
	 * @Function_Name :  selectDropDrownValueAcceptAlert
	 * @Description : Select Drop Drown Value Accept Alert

	 ***************************************************************************************/


	public boolean selectDropDrownValueAcceptAlert(WebDriver driver,String field, String value) {
		try {
			if(selectDropDrownValue(driver, field, value))
			{
				waitInSeconds(2);
				acceptAlert(driver);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public void acceptAlert(WebDriver driver)
	{
		Alert a =driver.switchTo().alert();
		a.accept();
	}

	/********************************************************************************************
	 * @Function_Name :  verifyElementAbsent
	 * @Description : Select Drop Drown Value Accept Alert

	 ***************************************************************************************/

	public boolean verifyElementAbsent(WebDriver driver,String field, String value) {
		try {
			WebElement element = findElement(driver,field);
			System.out.println("Element Present");
			return false;

		} catch (NoSuchElementException e) {
			System.out.println("Element absent");
			return true;
		}
	}

	/********************************************************************************************
	 * @Function_Name :  Doubleclick
	 * @Description : Doubleclick on any element

	 ***************************************************************************************/

	public void Doubleclick(WebDriver driver,String field)throws Exception {
		Actions actions = new Actions(driver);
		WebElement element = findElement(driver,field);
		actions.doubleClick(element).build().perform();
	}


}

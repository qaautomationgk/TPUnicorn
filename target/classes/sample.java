import java.util.List;

import lib.cisco.util.PropertiesFileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class sample {

	public static void main(String arga[])
	{
		/*String stime = "14-Jun-2016 14:20 (GMT -4) ";
		String result = stime.substring(0,11);
		System.out.println("Result::" + result);*/

		//***************************************************************************
		//To check the list size and click on required button ****
		/*List<WebElement> list = driver.findElements(By.xpath(".//*[contains(@id,'menu-1')]/li/a"));
		System.out.println("List Size:" +list.size());

		for(WebElement webElement : list)
		{
			if(webElement.getText().equals("search"))
			{
				webElement.click();	
			}
		}*/

		/*//Selecting S2S from Application list ****
		List<WebElement> list = driver.findElements(By.xpath(".//*[contains(@id,'System-Queue')]"));
		System.out.println("List Size:" +list.size());

		for(WebElement webElement : list)
		{
		if(webElement.getText().equals(appDto.getSerialNum()))
		{
		webElement.click(); 
		logMessage("Verify that the S2S applicaion is selected from the Application List", "S2S Application should be selected from List Successfully", "S2S Application is selected from List Successfully", "Passed");
		gsloMontring.waitInSeconds(5);
		}else{
		logMessage("Verify that the S2S applicaion is selected from the Application List", "S2S Application should be selected from List Successfully", "S2S Application is NOT selected from List Successfully", "Failed");
		}
		}*/

		//***************************************************************************

		/*try {

			long start = System.currentTimeMillis();
			Thread.sleep(65080);
			long finish = System.currentTimeMillis();
			long totalTimeInMiilis = finish - start; 
			//System.out.println("Total Time get RMA - "+totalTimeInMiilis + " Milliseconds" +"\n");
			long minutes = (totalTimeInMiilis / 1000) / 60; 
			long seconds = (totalTimeInMiilis / 1000) %60;
			long miilis  = (totalTimeInMiilis)%1000;
			System.out.println("Total Time taken to load Part Information page - " + minutes + " min " + seconds + " sec " +  miilis + " milliseconds");

		} catch (Exception e) {
			// TODO: handle exception
		}*/

		// Get the element count and itterate till the element present and click all elements
		/*List<WebElement> list = rmaSymftion.findElements(driver, PropertiesFileReader.getProperty("//*[contains(@id, 'pySelected')]"));
		for(WebElement item : list)
		{
			item.click();
		}*/

		/*//**************************************************************
		if(npi.isElementPresent(driver, PropertiesFileReader.getProperty("search_Clk_STE_Search"))){
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget1Ifr");
			npi.waitInSeconds(3);
		}else if(npi.isElementPresent(driver, PropertiesFileReader.getProperty("search_Clk_Buyer_Search"))){
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget2Ifr");
			npi.waitInSeconds(3);
		}else if(npi.isElementPresent(driver, PropertiesFileReader.getProperty("search_Clk_RP_Search"))){
			driver.switchTo().defaultContent();
			driver.switchTo().frame("PegaGadget3Ifr");
			npi.waitInSeconds(3);*/


		//Click on Continue button if exits in Shipping ****
		/*try{
			if (rmaSymftion.isElementPresent(driver, PropertiesFileReader.getProperty("rma_Clk_ShipContinueButton"))){
				logMessage("Select Click on Continue button in Shipping in Address option ", "Continue button should be clicked in Shipping", "Continue button is clicked Succussfuly in Shipping", "Passed");	
				rmaSymftion.findElement(driver, PropertiesFileReader.getProperty("rma_Clk_ShipContinueButton")).click();
			}else{
			}
		}catch(Throwable e){
			System.err.println("elivery Continue Button is NOT Displayed to click "+e.getMessage());
		}*/
		
		
		/*
		 
		 //Checking on Continue to CreateOrder *****
			if (rmaSymftion.isElementPresent(driver, PropertiesFileReader.getProperty("rmaSymftion_Clk_Next"))){
				logMessage("Click on Next to Continue to Create Order", "Continue button should be clicked Succussfuly", "Continue button is clicked Succussfuly", "Passed");
				rmaSymftion.findElement(driver, PropertiesFileReader.getProperty("rmaSymftion_Clk_Next")).click();
				rmaSymftion.waitInSeconds(9);
				rmaSymftion_Clk_SoftHardStop();

				if(rmaSymftion.findElement(driver, PropertiesFileReader.getProperty("rmaSymftion_Clk_Next")).isDisplayed()){
					rmaSymftion.findElement(driver, PropertiesFileReader.getProperty("rmaSymftion_Clk_Next")).click();
					rmaSymftion.waitInSeconds(2);
				}

				long start = System.currentTimeMillis();
				WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("FailureCode")));

				long finish = System.currentTimeMillis();
				long totalTimeInMiilis = finish - start; 
				//System.out.println("Total Time get RMA - "+totalTimeInMiilis + " Milliseconds" +"\n");
				long minutes = (totalTimeInMiilis / 1000) / 60; 
				long seconds = (totalTimeInMiilis / 1000) %60;
				long miilis  = (totalTimeInMiilis)%1000;
				System.out.println("Total Time taken to load Part Information page - " + minutes + " min " + seconds + " sec " +  miilis + " milliseconds");
				logMessage("Verify for Next to Continue to Create Order", "Total Time taken to load Part Information Page should get displayed", "Total Time to load Part Information Page# " + + minutes + " min " + seconds + " sec " + miilis + " milliseconds", "Passed");
				rmaSymftion.waitInSeconds(2);
				System.out.println("\n");
			}else{
				logMessage("Click on Next to Continue to Create Order", "Continue button should be clicked Succussfuly", "Continue button is NOT clicked Succussfuly", "Failed");
				Assert.assertTrue(false);
			}
		}
		
		// Verifying filter Condition name in Pending Back order report child window******
			if(driver.getPageSource().contains("Condition")){
				System.out.println("Filter Titles: 'Condition' is Present");
			}else{
				System.out.println("Filter Titles: 'Condition' is NOT Present");
			}
			
			//to perform Scroll on application using  Selenium
			mondeeInc.waitInSeconds(3);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,5000)", "");
			
			//Get number of Itinerary search results ***
			List<WebElement> list = driver.findElements(By.xpath(".//*[contains(@id, 'bookNow_')]"));
			System.out.println("Total Itinerary loaded:: " +list.size());
			
			//Select in Itinerary from search results ***
			List<WebElement> list = driver.findElements(By.xpath(".//*[contains(@id, 'bookNow_')]"));
			System.out.println("List Size:" +list.size());
			list.get(1).click();
			mondeeInc.waitInSeconds(4)
		 
		 */
		
		
		

	}
}

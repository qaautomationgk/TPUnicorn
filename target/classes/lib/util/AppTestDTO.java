package lib.util;

import java.io.Serializable;

/**
 * @author kagumpa
 *
 */
public class AppTestDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	/*private String dataId = null;
	private String testScriptId = null;
	private String testMethodName=null;
	private String invocationMethod=null;
	private String browserVersion=null;
	private String osType=null;
	private String gridUrl=null;

	public String getSchdDeliryDate() {
		return SchdDeliryDate;
	}
	public void setSchdDeliryDate(String schdDeliryDate) {
		SchdDeliryDate = schdDeliryDate;
	}
	public String getSchdDeliryTime() {
		return SchdDeliryTime;
	}
	public void setSchdDeliryTime(String schdDeliryTime) {
		SchdDeliryTime = schdDeliryTime;
	}
	public String getAuthorizedBy() {
		return AuthorizedBy;
	}
	public void setAuthorizedBy(String authorizedBy) {
		AuthorizedBy = authorizedBy;
	}
	//Project related ***
	private String rmaUserName=null;
	private String rmaPassword=null;
	private String ccoId=null;
	private String siteId=null; 
	private String contractNum=null;
	private String PartNum=null; 
	private String PrimPhoneNum=null; 
	private String ParFaliurCode=null; 
	private String ReactionHours=null; 
	private String WareHouse=null; 
	private String AduitLogTag1=null; 
	private String AduitLogTag2=null; 
	private String AduitLogTag3=null; 
	private String AnyData=null; 
	private String AnyDataII=null; 
	private String AnyDataIII=null; 
	private String AnyDataIV=null; 
	private String AnyDataV=null; 
	private String AnyDataVI=null; 
	private String AnyDataVII=null; 
	private String FPRLink=null; 
	private String SchdDeliryDate=null; 
	private String SchdDeliryTime=null; 
	private String AuthorizedBy=null; 
	private String PremFildEngAction=null; 
	private String LborLoc=null;
	private String Recevpart=null; 
	private String ManUnManSite=null; 
	private String Secutyclear=null;
	private String SiteType=null;
	private String OnSitServLevl=null;
	private String AddFildEng=null;

	private String SerialNum_I=null; 
	private String SerialNum_II=null; 
	private String SerialNum_III=null; 

	private String ServiceLevel_II=null; 
	private String ReactionHours_II=null; 
	private String ServLevlVarice_II=null; 






	public String getServiceLevel_II() {
		return ServiceLevel_II;
	}
	public void setServiceLevel_II(String serviceLevel_II) {
		ServiceLevel_II = serviceLevel_II;
	}
	public String getReactionHours_II() {
		return ReactionHours_II;
	}
	public void setReactionHours_II(String reactionHours_II) {
		ReactionHours_II = reactionHours_II;
	}
	public String getServLevlVarice_II() {
		return ServLevlVarice_II;
	}
	public void setServLevlVarice_II(String servLevlVarice_II) {
		ServLevlVarice_II = servLevlVarice_II;
	}
	public String getSerialNum_II() {
		return SerialNum_II;
	}
	public void setSerialNum_II(String serialNum_II) {
		SerialNum_II = serialNum_II;
	}
	public String getSerialNum_III() {
		return SerialNum_III;
	}
	public void setSerialNum_III(String serialNum_III) {
		SerialNum_III = serialNum_III;
	}
	public String getSerialNum_I() {
		return SerialNum_I;
	}
	public void setSerialNum_I(String serialNum_I) {
		SerialNum_I = serialNum_I;
	}
	public String getAddFildEng() {
		return AddFildEng;
	}
	public void setAddFildEng(String addFildEng) {
		AddFildEng = addFildEng;
	}
	public String getOnSitServLevl() {
		return OnSitServLevl;
	}
	public void setOnSitServLevl(String onSitServLevl) {
		OnSitServLevl = onSitServLevl;
	}
	public String getSiteType() {
		return SiteType;
	}
	public void setSiteType(String siteType) {
		SiteType = siteType;
	}
	public String getRecevpart() {
		return Recevpart;
	}
	public void setRecevpart(String recevpart) {
		Recevpart = recevpart;
	}
	public String getManUnManSite() {
		return ManUnManSite;
	}
	public void setManUnManSite(String manUnManSite) {
		ManUnManSite = manUnManSite;
	}
	public String getSecutyclear() {
		return Secutyclear;
	}
	public void setSecutyclear(String secutyclear) {
		Secutyclear = secutyclear;
	}

	public String getLborLoc() {
		return LborLoc;
	}
	public void setLborLoc(String lborLoc) {
		LborLoc = lborLoc;
	}

	public String getPremFildEngAction() {
		return PremFildEngAction;
	}
	public void setPremFildEngAction(String premFildEngAction) {
		PremFildEngAction = premFildEngAction;
	}
	public String getFPRLink() {
		return FPRLink;
	}
	public void setFPRLink(String fPRLink) {
		FPRLink = fPRLink;
	}
	public String getAnyDataVII() {
		return AnyDataVII;
	}
	public void setAnyDataVII(String anyDataVII) {
		AnyDataVII = anyDataVII;
	}
	public String getAnyDataII() {
		return AnyDataII;
	}
	public void setAnyDataII(String anyDataII) {
		AnyDataII = anyDataII;
	}
	public String getAnyDataIII() {
		return AnyDataIII;
	}
	public void setAnyDataIII(String anyDataIII) {
		AnyDataIII = anyDataIII;
	}
	public String getAnyDataIV() {
		return AnyDataIV;
	}
	public void setAnyDataIV(String anyDataIV) {
		AnyDataIV = anyDataIV;
	}
	public String getAnyDataV() {
		return AnyDataV;
	}
	public void setAnyDataV(String anyDataV) {
		AnyDataV = anyDataV;
	}
	public String getAnyDataVI() {
		return AnyDataVI;
	}
	public void setAnyDataVI(String anyDataVI) {
		AnyDataVI = anyDataVI;
	}
	public String getAnyData() {
		return AnyData;
	}
	public void setAnyData(String anyData) {
		AnyData = anyData;
	}
	public String getAduitLogTag1() {
		return AduitLogTag1;
	}
	public void setAduitLogTag1(String aduitLogTag1) {
		AduitLogTag1 = aduitLogTag1;
	}
	public String getAduitLogTag2() {
		return AduitLogTag2;
	}
	public void setAduitLogTag2(String aduitLogTag2) {
		AduitLogTag2 = aduitLogTag2;
	}
	public String getAduitLogTag3() {
		return AduitLogTag3;
	}
	public void setAduitLogTag3(String aduitLogTag3) {
		AduitLogTag3 = aduitLogTag3;
	}
	public String getWareHouse() {
		return WareHouse;
	}
	public void setWareHouse(String wareHouse) {
		WareHouse = wareHouse;
	}
	public String getPremProdFamly() 
	{
		return PremProdFamly;
	}
	public void setPremProdFamly(String premProdFamly) {
		PremProdFamly = premProdFamly;
	}
	private String ServLevlVarice=null;
	private String ServiceLevel=null;
	private String PremProdFamly=null;
	public String getServiceLevel() {
		return ServiceLevel;
	}
	public void setServiceLevel(String serviceLevel) {
		ServiceLevel = serviceLevel;
	}
	private String SerialNum=null; 
	private String PortalrUrl=null;
	private String WorkBasketType=null;
	private String Qty=null;
	private String PartNum_II=null;
	private String PartNum_III=null;
	private String CompanyName=null;
	private String Address_I=null;
	private String Address_II=null;
	private String Country=null;
	private String City=null;
	private String State=null;
	private String Zip=null;
	private String OriginalAddrs=null;
	private String Flag=null;
	private String PrtInfoServiceLevel=null;
	private String PrtInfContrt=null;
	private String PrtInfoServiceLevel_II=null;
	private String PrtInfContrt_II=null;




	public String getPrtInfoServiceLevel_II() {
		return PrtInfoServiceLevel_II;
	}
	public void setPrtInfoServiceLevel_II(String prtInfoServiceLevel_II) {
		PrtInfoServiceLevel_II = prtInfoServiceLevel_II;
	}
	public String getPrtInfContrt_II() {
		return PrtInfContrt_II;
	}
	public void setPrtInfContrt_II(String prtInfContrt_II) {
		PrtInfContrt_II = prtInfContrt_II;
	}
	public String getPrtInfContrt() {
		return PrtInfContrt;
	}
	public void setPrtInfContrt(String prtInfContrt) {
		PrtInfContrt = prtInfContrt;
	}
	public String getPrtInfoServiceLevel() {
		return PrtInfoServiceLevel;
	}
	public void setPrtInfoServiceLevel(String prtInfoServiceLevel) {
		PrtInfoServiceLevel = prtInfoServiceLevel;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getOriginalAddrs() {
		return OriginalAddrs;
	}
	public void setOriginalAddrs(String originalAddrs) {
		OriginalAddrs = originalAddrs;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getAddress_II() {
		return Address_II;
	}
	public void setAddress_II(String address_II) {
		Address_II = address_II;
	}
	public String getAddress_I() {
		return Address_I;
	}
	public void setAddress_I(String address_I) {
		Address_I = address_I;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getPortalrUrl() {
		return PortalrUrl;
	}
	public void setPortalrUrl(String portalrUrl) {
		PortalrUrl = portalrUrl;
	}
	public String getServLevlVarice() {
		return ServLevlVarice;
	}
	public void setServLevlVarice(String servLevlVarice) {
		ServLevlVarice = servLevlVarice;
	}
	public String getPartNum_III() {
		return PartNum_III;
	}
	public void setPartNum_III(String partNum_III) {
		PartNum_III = partNum_III;
	}
	public String getPartNum_II() {
		return PartNum_II;
	}
	public void setPartNum_II(String partNum_II) {
		PartNum_II = partNum_II;
	}
	public String getReactionHours() {
		return ReactionHours;
	}
	public void setReactionHours(String reactionHours) {
		ReactionHours = reactionHours;
	}
	public String getPrimPhoneNum() {
		return PrimPhoneNum;
	}
	public void setPrimPhoneNum(String primPhoneNum) {
		PrimPhoneNum = primPhoneNum;
	}
	public String getWorkBasketType() {
		return WorkBasketType;
	}
	public void setWorkBasketType(String workBasketType) {
		WorkBasketType = workBasketType;
	}
	public String getPortal_URL() {
		return PortalrUrl;
	}
	public void setPortal_URL(String portal_URL) {
		PortalrUrl = portal_URL;
	}
	public String getParFaliurCode() {
		return ParFaliurCode;
	}
	public void setParFaliurCode(String parFaliurCode) {
		ParFaliurCode = parFaliurCode;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getTestScriptId() {
		return testScriptId;
	}
	public void setTestScriptId(String testScriptId) {
		this.testScriptId = testScriptId;
	}

	public String SURL() {
		return SURL;
	}
	public void SURL(String SURL) {
		this.SURL = SURL;
	}

	public String getTestClassName() {
		return testClassName;
	}
	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}


	public String getTestMethodName() {
		return testMethodName;
	}
	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}
	public String getInvocationMethod() {
		return invocationMethod;
	}
	public void setInvocationMethod(String invocationMethod) {
		this.invocationMethod = invocationMethod;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getGridUrl() {
		return gridUrl;
	}
	public void setGridUrl(String gridUrl) {
		this.gridUrl = gridUrl;
	}
	public String getCcoId() {
		return ccoId;
	}
	public void setCcoId(String ccoId) {
		this.ccoId = ccoId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractnum) {
		this.contractNum = contractnum;
	}

	public String getRmauserName() {
		return rmaUserName;
	}
	public String getRmaUserName() {
		return rmaUserName;
	}
	public void setRmaUserName(String rmaUserName) {
		this.rmaUserName = rmaUserName;
	}
	public void setRmauserName(String rmauserName) {
		this.rmaUserName = rmauserName;
	}
	public String getRmaPassword() {
		return rmaPassword;
	}
	public void setRmaPassword(String rmapassword) {
		this.rmaPassword = rmapassword;
	}
	public String getPartNum() {
		return PartNum;
	}
	public String getQty() {
		return Qty;
	}
	public void setQty(String qty) {
		Qty = qty;
	}
	public void setPartNum(String partNum) {
		PartNum = partNum;
	}
	public String getSerialNum() {
		return SerialNum;
	}
	public void setSerialNum(String serialNum) {
		SerialNum = serialNum;
	}*/



	//////////////////////////////////////
	////// NEW PROPERTIES
	////////////////////////////////////


	private String testCaseName=null;
	private String url = null;
	private String userName = null;
	private String password = null;
	private String browserType = null;
	//private String SURL=null;
	//private String testCaseName=null;




	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTestCaseName() {
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}



}

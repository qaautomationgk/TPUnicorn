package lib.util;

import java.util.Map;

public class OrderInfo { 
	
	private String orderNumber;
	private Map<String,String> bkoBklMap;
	
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Map<String, String> getBkoBklMap() {
		return bkoBklMap;
	}
	public void setBkoBklMap(Map<String, String> bkoBklMap) {
		this.bkoBklMap = bkoBklMap;
	}
}
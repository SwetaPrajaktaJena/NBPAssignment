package pl.parser.npb.Model;

public class Request {
private String startDate;
private String endDate;
private String startYear;
private String endYear;
private String currencyCode;

public Request(String currencyCode,String startDate,String endDate){
	this.startDate=startDate;
	this.endDate=endDate;
	this.currencyCode=currencyCode;
	this.setStartYear(startDate);
	this.setEndYear(endDate);
}

public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public void setStartYear(String startDate) {
	String [] dateParts = startDate.split("-");
	String startYear = dateParts[0];
	this.startYear= startYear;
}
public String getStartYear() {
	return startYear;
}
public void setEndYear(String endDate) {
	String [] dateParts = endDate.split("-");
	String endYear = dateParts[0];
	this.endYear = endYear;
}
public String getEndYear() {
	
	return endYear;
}
public String getCurrencyCode() {
	return currencyCode;
}
public void setCurrencyCode(String currencyCode) {
	this.currencyCode = currencyCode;
}

}

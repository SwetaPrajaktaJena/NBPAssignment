package pl.parser.nbp.inputChecker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pl.parser.npb.Constants.Constants;
import pl.parser.npb.Model.Request;

public class InputChecker {
	public boolean validateRequestedInputs(Request reqObj)throws IllegalArgumentException, ParseException{
		if(!Constants.CurrencyCodes.contains(reqObj.getCurrencyCode())){
			System.out.println("!!!Invalid CurrencyCode");	
			return false;
		}
		else if(!isThisDateValid(reqObj.getStartDate(),"yyyy-MM-dd")){
			System.out.println("!!!Invalid StartDate");
			return false;
		}
		else if(!isThisDateValid(reqObj.getEndDate(),"yyyy-MM-dd")){
			System.out.println("!!!Invalid EndDate");
			return false;
		}
		else if(validateDates(reqObj)){
			System.out.println("Invalid StartDate and EndDate");
		return false;
		}
		else{
		return true;
		}

	}
	public boolean isThisDateValid(String dateToValidate, String dateFromat){
		
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		
		try {
			Date date = sdf.parse(dateToValidate);
		} 
		 catch (ParseException e) {
            e.printStackTrace();
        } 
		
		return true;
	}
	public boolean validateDates(Request reqObj) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(reqObj.getStartDate());
        Date date2 = sdf.parse(reqObj.getEndDate());
        if (date1.before(date2)) {
           
            return false;
        }
        else
        	  return true;
	}
}

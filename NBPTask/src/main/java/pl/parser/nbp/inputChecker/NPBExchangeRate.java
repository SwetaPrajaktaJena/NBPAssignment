package pl.parser.nbp.inputChecker;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import pl.parser.nbp.Parser.ParserImpl;
import pl.parser.npb.Model.Request;
import pl.parser.npb.Services.ServiceConnection;
import pl.parser.npb.Services.ServiceConnectionImpl;

public class NPBExchangeRate {
	List<String> fileNames=new ArrayList<String>();
	ParserImpl obj=new ParserImpl();
	InputChecker inputCheck=new InputChecker();
	ServiceConnection connectionObj=new ServiceConnectionImpl();
public void getAverageandStandardDeviation(String currencyCode,String startDate,String endDate) throws ParserConfigurationException, SAXException, IllegalArgumentException, ParseException{
	
	Request reqObj=new Request(currencyCode,startDate,endDate);
	if(inputCheck.validateRequestedInputs(reqObj)){
	
	try {
		getYears(reqObj.getStartYear(), reqObj.getEndYear());
		for(String year:getYears(reqObj.getStartYear(), reqObj.getEndYear())){
			fileNames.addAll(connectionObj.getYearlyFileNames(year));	
		}
		
		obj.getFilterdFileNames(fileNames,reqObj);
		System.out.println(new DecimalFormat("#.####").format(getAverageOfBuyingRates(obj.buyRateList)));
		System.out.println(new DecimalFormat("#.####").format(getStandardDeviationOfSellingRates(obj.sellRateList)));
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	else{
		System.out.println("InvalidArgumentInput");
	}
}

public Double getAverageOfBuyingRates(List<Double> buyrates){

	 return obj.buyRateList.stream()
             .mapToDouble(a -> a)
             .average().getAsDouble();

}
public List<String> getYears(String str,String str1){
	List<String> retStr=new ArrayList<String>();
	int i;
	int l=Integer.parseInt(str1);
	for(i=Integer.parseInt(str);i<=l;i++){
	retStr.add( Integer.toString(i));	
	}
return	retStr;
}

public static double getStandardDeviationOfSellingRates(List<Double> numArray)
{
    double sum = 0.0, standardDeviation = 0.0;

    for(double num : numArray) {
        sum += num;
    }

    double mean = sum/numArray.size();

    for(double num: numArray) {
        standardDeviation += Math.pow(num - mean, 2);
    }

    return Math.sqrt(standardDeviation/numArray.size());
}

}

package pl.parser.nbp;

import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import pl.parser.nbp.inputChecker.NPBExchangeRate;

public class MainClass {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IllegalArgumentException, ParseException {
		if (args.length == 3) {
          
                NPBExchangeRate ans=new NPBExchangeRate();
        		ans.getAverageandStandardDeviation(args[0], args[1], args[2]);
            
        } else {
            System.out.print("Incorrect number of arguments");
            //NPBExchangeRate ans=new NPBExchangeRate();
            //ans.getAverageandStandardDeviation("EUR","2013-12-30","2013-01-31");
        }
		
	}

}

package pl.parser.npb.Constants;

import java.util.*;

public class Constants {
	public static final String BaseURL = "http://www.nbp.pl/kursy/xml/";
	public static final String SubRequestBUY = "kurs_kupna";
	public static final String SubRequestSELL = "kurs_sprzedazy";
	public static final String[] COURSETYPE = {"A","B","C","H"};
	public static final List<String> CurrencyCodes  = Arrays.asList("USD","EUR","CHF","GBP","BYR","XOF","BZD","BGN","INR");
    
}

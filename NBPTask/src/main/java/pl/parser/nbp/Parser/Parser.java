package pl.parser.nbp.Parser;

import java.util.List;

import org.w3c.dom.Node;

import pl.parser.npb.Model.Request;

public interface Parser {
	public  boolean getFilterdFileNames(List<String> fileNames,Request reqObj);
	public Node parseXmlContent(String xmlContent,String currencyCode);
	public double getBuyingRate(String currencyCode, String file);
	public double getSellingRate(String currencyCode, String file);
}

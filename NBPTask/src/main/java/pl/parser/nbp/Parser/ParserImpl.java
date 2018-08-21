package pl.parser.nbp.Parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pl.parser.npb.Constants.Constants;
import pl.parser.npb.Model.Request;
import pl.parser.npb.Services.ServiceConnection;
import pl.parser.npb.Services.ServiceConnectionImpl;

public class ParserImpl implements Parser {
public  List<Double> buyRateList=new ArrayList<Double>();
public	List<Double> sellRateList=new ArrayList<Double>();
ServiceConnection connectionObj=new ServiceConnectionImpl();
public  boolean getFilterdFileNames(List<String> fileNames,Request reqObj) {
	boolean flag=false;	
	if(fileNames!=null){
	    try{
			for(String file:fileNames){
				if(file.startsWith("c")){
					if(flag || file.endsWith(startconverter(reqObj.getStartDate()))){
						flag=true;
					}
					if(flag){
						String xmlcontent = connectionObj.getxmlContent(file);
						if(xmlcontent.equals("FileNotFound")){
							return false;
						}else{
							sellRateList.add(getSellingRate(reqObj.getCurrencyCode(),xmlcontent));
							buyRateList.add(getBuyingRate(reqObj.getCurrencyCode(),xmlcontent));
						}
					}
					if(flag && file.endsWith(startconverter(reqObj.getEndDate()))){
						flag=false;
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	    return true;
	    
	}else{
		return false;
	}
	
}

public String startconverter(String inputDateStr){
	String retStr=inputDateStr.replace("-","");
	retStr=retStr.substring(2);
	return retStr;
}

public Node parseXmlContent(String xmlContent,String currencyCode){
	try{
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(new InputSource(new StringReader(xmlContent)));
	doc.getDocumentElement().normalize();			
	NodeList nList = doc.getElementsByTagName("pozycja");
	NodeList positionChildNodes;		
	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		positionChildNodes = nList.item(temp).getChildNodes();
        for (int j = 0; j < positionChildNodes.getLength(); j++) {
            if (positionChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE && positionChildNodes.item(j).getTextContent().equals(currencyCode)) {
            	return nList.item(temp);
            }
        }
	}
	}
	catch(SAXException | IOException|ParserConfigurationException e1){
		e1.printStackTrace();
	}
	 
	return null;
	 }
	

public double getBuyingRate(String currencyCode, String file){
	try{
	NodeList positionChildNodes = parseXmlContent(file,currencyCode).getChildNodes();
	for (int i = 0; i < positionChildNodes.getLength(); i++) {
        if (positionChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE &&
                positionChildNodes.item(i).getNodeName().equals(Constants.SubRequestBUY)) {
            return Double.parseDouble(positionChildNodes.item(i).getTextContent().replace(',', '.'));
        }
    }
	}
	catch(Exception e1){
		e1.printStackTrace();
	}
	return 0;
}
public double getSellingRate(String currencyCode, String file){
	try{
	NodeList positionChildNodes = parseXmlContent(file,currencyCode).getChildNodes();
	for (int i = 0; i < positionChildNodes.getLength(); i++) {
        if (positionChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE &&
                positionChildNodes.item(i).getNodeName().equals(Constants.SubRequestSELL)) {
            return Double.parseDouble(positionChildNodes.item(i).getTextContent().replace(',', '.'));
        }
    }
	}
	catch(Exception e1){
		e1.printStackTrace();
	}
	return 0;
}
}

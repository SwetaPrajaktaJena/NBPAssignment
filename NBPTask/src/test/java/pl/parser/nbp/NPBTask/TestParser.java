package pl.parser.nbp.NPBTask;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import pl.parser.nbp.Parser.ParserImpl;
import pl.parser.npb.Model.Request;

public class TestParser {
ParserImpl testObj=new ParserImpl();
List <String> testStr=Arrays.asList("c019z130128","h001z130102","c022z130131", "b001z130102","c002z130103");
List <String> WrongtestStr=Arrays.asList("c001z130128","h001z130102","a001z130102", "b001z130102","c002z130103");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void  testGetFilterdFileNames() throws IOException, ParserConfigurationException, SAXException {
		Request reqObj=new Request("EUR","2013-01-28","2013-01-31");
		assertTrue(testObj.getFilterdFileNames(testStr,reqObj));
	}
	@Test
	public void  testWrongGetFilterdFileNames() throws IOException, ParserConfigurationException, SAXException {
		Request reqObj=new Request("EUR","2013-01-28","2013-01-31");
		assertTrue(!testObj.getFilterdFileNames(WrongtestStr,reqObj));
	}
}

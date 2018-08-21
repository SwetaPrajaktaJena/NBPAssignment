package pl.parser.nbp.NPBTask;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.parser.nbp.inputChecker.InputChecker;
import pl.parser.npb.Model.Request;

public class TestInputChecker {
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	
	}
	@Test
	public void testValidateRequestedInputWithInvalidInputs() throws IllegalArgumentException, ParseException {	
		InputChecker test=new InputChecker();
		Request req=new Request("EURTEST","2013-01-28","2013-01-31");
		assertTrue(!test.validateRequestedInputs(req));
	}

	@Test
	public void testValidateRequestedInputWithValidInputs() throws IllegalArgumentException, ParseException {
		InputChecker test=new InputChecker();
		Request	req=new Request("EUR","2013-01-28","2013-01-31");
		assertTrue(test.validateRequestedInputs(req));
	}
	
}

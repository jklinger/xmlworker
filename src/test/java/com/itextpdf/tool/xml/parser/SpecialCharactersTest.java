/**
 *
 */
package com.itextpdf.tool.xml.parser;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.tool.xml.parser.state.InsideTagHTMLState;
import com.itextpdf.tool.xml.parser.state.SpecialCharState;

/**
 * @author itextpdf.com
 *
 */
public class SpecialCharactersTest {


	private String regHtml;
	private int reg;
	private SpecialCharState scState;
	private XMLParser parser;
	private String regStr;
	private InsideTagHTMLState itState;
	private int hex;

	@Before
	public void setUp() {
		parser = new XMLParser();
		scState = new SpecialCharState(parser);
		itState = new InsideTagHTMLState(parser);
		reg = 174;
		regHtml = "&reg";
		regStr = "�";
		hex = 0x00ae;
	}

	@Test
	public void testIntCode() throws UnsupportedEncodingException {
		itState.process(hex);
		String str = new String(parser.memory().current().toByteArray(), "ISO-8859-1");
		System.out.println(str);
		Assert.assertEquals(hex, str.charAt(0));
	}
	@Test
	public void testHtmlChar() throws UnsupportedEncodingException {
		scState.process('r');
		scState.process('e');
		scState.process('g');
		scState.process(';');
		String str = new String(parser.memory().current().toByteArray(), "ISO-8859-1");
		Assert.assertEquals(hex, str.charAt(0));
	}
}

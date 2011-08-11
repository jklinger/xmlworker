/**
 *
 */
package com.itextpdf.tool.xml.parser;



import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author itextpdf.com
 *
 */
public class HTMLWhiteSpacesTest {

	private String str;

	@Before
	public void setUp() {
		str = "<body><b>&euro;<b> 124</body>";
	}

	@Test
	public void checkIfSpaceIsStillThere() throws IOException {
		final StringBuilder b = new StringBuilder();
		XMLParser p = new XMLParser(true, new XMLParserListener() {

			public void unknownText(final String text) {
				// TODO Auto-generated method stub

			}

			public void text(final String text) {
				b.append(text);

			}

			public void startElement(final String tag, final Map<String, String> attributes, final String ns) {
				// TODO Auto-generated method stub

			}

			public void init() {
				// TODO Auto-generated method stub

			}

			public void endElement(final String tag, final String ns) {
				// TODO Auto-generated method stub

			}

			public void comment(final String comment) {
				// TODO Auto-generated method stub

			}

			public void close() {
				// TODO Auto-generated method stub

			}
		});
		p.parse(new StringReader(str));
		Assert.assertEquals("€ 124", b.toString());
	}
}

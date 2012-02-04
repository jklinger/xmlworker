/**
 *
 */
package com.itextpdf.tool.xml.html;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.tool.xml.Tag;
import com.itextpdf.tool.xml.css.apply.ChunkCssApplier;
import com.itextpdf.tool.xml.css.apply.MarginMemory;
import com.itextpdf.tool.xml.css.apply.PageSizeContainable;
import com.itextpdf.tool.xml.html.pdfelement.HtmlCell;
import com.itextpdf.tool.xml.html.pdfelement.NoNewLineParagraph;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.ImageProvider;

/**
 * @author redlab
 *
 */
public interface CssAppliers {

	/**
	 * Given the element e, this method will lookup the right applier for the given Element. The mapping is done by
	 * instance of.<br />
	 * order of check:
	 * <ol>
	 * <li>{@link Chunk}</li>
	 * <li>{@link Paragraph}</li>
	 * <li>{@link NoNewLineParagraph}</li>
	 * <li>{@link HtmlCell}</li>
	 * <li>{@link List}</li>
	 * <li>{@link LineSeparator}</li>
	 * <li>{@link Image}</li>
	 * </ol>
	 *
	 * @param e the Element
	 * @param t the tag
	 * @param mm the MarginMemory
	 * @param psc the {@link PageSize} container
	 * @param ip an ImageProvider
	 * @return the element with CSS applied onto, note: the element can be a new element.
	 */
	Element apply(Element e, final Tag t, final MarginMemory mm, final PageSizeContainable psc, final ImageProvider ip);

	/**
	 * Convenience method.
	 *
	 * @see #apply(Element, Tag, MarginMemory, PageSizeContainable, ImageProvider)
	 * @param e the Element
	 * @param t the tag
	 * @param ctx the Context object
	 * @return the element with CSS applied onto, note: the element can be a new element.
	 */
	Element apply(final Element e, final Tag t, final HtmlPipelineContext ctx);

	/**
	 * @return the chunk css applier
	 */
	ChunkCssApplier getChunkCssAplier();

}
